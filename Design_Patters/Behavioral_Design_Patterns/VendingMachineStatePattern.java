public class VendingMachineStatePattern {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(2);

        machine.selectProduct();

        machine.insertCoin();
        machine.selectProduct();
        machine.dispense();

        machine.insertCoin();
        machine.selectProduct();
        machine.dispense();

        machine.insertCoin();
    }
}

interface VendingMachineState {
    void insertCoin();

    void selectProduct();

    void dispense();
}

class VendingMachine {
    private VendingMachineState idleState;
    private VendingMachineState hasCoinState;
    private VendingMachineState productSelectedState;
    private VendingMachineState soldOutState;
    private VendingMachineState currentState;
    private int productCount;

    public VendingMachine(int productCount) {
        this.productCount = productCount;
        this.idleState = new VendingIdleState(this);
        this.hasCoinState = new VendingHasCoinState(this);
        this.productSelectedState = new VendingProductSelectedState(this);
        this.soldOutState = new VendingSoldOutState();
        this.currentState = productCount > 0 ? idleState : soldOutState;
    }

    public void setState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public VendingMachineState getHasCoinState() {
        return hasCoinState;
    }

    public VendingMachineState getProductSelectedState() {
        return productSelectedState;
    }

    public VendingMachineState getSoldOutState() {
        return soldOutState;
    }

    public int getProductCount() {
        return productCount;
    }

    public void releaseProduct() {
        productCount--;
        System.out.println("Product dispensed. Remaining products: " + productCount);
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void selectProduct() {
        currentState.selectProduct();
    }

    public void dispense() {
        currentState.dispense();
    }
}

class VendingIdleState implements VendingMachineState {
    private VendingMachine machine;

    public VendingIdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public void selectProduct() {
        System.out.println("Insert coin before selecting product.");
    }

    @Override
    public void dispense() {
        System.out.println("Insert coin first.");
    }
}

class VendingHasCoinState implements VendingMachineState {
    private VendingMachine machine;

    public VendingHasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product selected.");
        machine.setState(machine.getProductSelectedState());
    }

    @Override
    public void dispense() {
        System.out.println("Select product first.");
    }
}

class VendingProductSelectedState implements VendingMachineState {
    private VendingMachine machine;

    public VendingProductSelectedState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product already selected.");
    }

    @Override
    public void dispense() {
        machine.releaseProduct();

        if (machine.getProductCount() == 0) {
            machine.setState(machine.getSoldOutState());
        } else {
            machine.setState(machine.getIdleState());
        }
    }
}

class VendingSoldOutState implements VendingMachineState {
    @Override
    public void insertCoin() {
        System.out.println("Machine is sold out.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Machine is sold out.");
    }

    @Override
    public void dispense() {
        System.out.println("Machine is sold out.");
    }
}
