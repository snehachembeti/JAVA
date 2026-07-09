public class ATMStatePattern {
    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine(1000);

        atm.withdraw(200);

        atm.insertCard();
        atm.enterPin("1234");
        atm.withdraw(300);

        atm.insertCard();
        atm.enterPin("9999");
        atm.withdraw(100);
    }
}

interface ATMState {
    void insertCard();

    void enterPin(String pin);

    void withdraw(int amount);

    void ejectCard();
}

class ATMMachine {
    private ATMState noCardState;
    private ATMState hasCardState;
    private ATMState authenticatedState;
    private ATMState noCashState;
    private ATMState currentState;
    private int cash;

    public ATMMachine(int cash) {
        this.cash = cash;
        this.noCardState = new ATMNoCardState(this);
        this.hasCardState = new ATMHasCardState(this);
        this.authenticatedState = new ATMAuthenticatedState(this);
        this.noCashState = new ATMNoCashState();
        this.currentState = cash > 0 ? noCardState : noCashState;
    }

    public void setState(ATMState currentState) {
        this.currentState = currentState;
    }

    public ATMState getNoCardState() {
        return noCardState;
    }

    public ATMState getHasCardState() {
        return hasCardState;
    }

    public ATMState getAuthenticatedState() {
        return authenticatedState;
    }

    public ATMState getNoCashState() {
        return noCashState;
    }

    public int getCash() {
        return cash;
    }

    public void reduceCash(int amount) {
        cash -= amount;
    }

    public void insertCard() {
        currentState.insertCard();
    }

    public void enterPin(String pin) {
        currentState.enterPin(pin);
    }

    public void withdraw(int amount) {
        currentState.withdraw(amount);
    }

    public void ejectCard() {
        currentState.ejectCard();
    }
}

class ATMNoCardState implements ATMState {
    private ATMMachine atm;

    public ATMNoCardState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Card inserted.");
        atm.setState(atm.getHasCardState());
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Insert card first.");
    }

    @Override
    public void withdraw(int amount) {
        System.out.println("Insert card before withdrawing money.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No card to eject.");
    }
}

class ATMHasCardState implements ATMState {
    private ATMMachine atm;

    public ATMHasCardState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Card is already inserted.");
    }

    @Override
    public void enterPin(String pin) {
        if ("1234".equals(pin)) {
            System.out.println("PIN accepted.");
            atm.setState(atm.getAuthenticatedState());
        } else {
            System.out.println("Wrong PIN. Card ejected.");
            atm.setState(atm.getNoCardState());
        }
    }

    @Override
    public void withdraw(int amount) {
        System.out.println("Enter PIN before withdrawing money.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atm.setState(atm.getNoCardState());
    }
}

class ATMAuthenticatedState implements ATMState {
    private ATMMachine atm;

    public ATMAuthenticatedState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Card is already inserted.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("PIN already accepted.");
    }

    @Override
    public void withdraw(int amount) {
        if (amount > atm.getCash()) {
            System.out.println("Not enough cash in ATM.");
            ejectCard();
            return;
        }

        atm.reduceCash(amount);
        System.out.println("Please collect Rs." + amount);

        if (atm.getCash() == 0) {
            atm.setState(atm.getNoCashState());
        } else {
            atm.setState(atm.getNoCardState());
        }
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atm.setState(atm.getNoCardState());
    }
}

class ATMNoCashState implements ATMState {
    @Override
    public void insertCard() {
        System.out.println("ATM has no cash.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("ATM has no cash.");
    }

    @Override
    public void withdraw(int amount) {
        System.out.println("ATM has no cash.");
    }

    @Override
    public void ejectCard() {
        System.out.println("ATM has no cash.");
    }
}
