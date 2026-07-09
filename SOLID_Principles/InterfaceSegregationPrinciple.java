public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {
        printHeader();

        HumanWorker humanWorker = new HumanWorker("Ravi");
        humanWorker.work();
        humanWorker.eat();

        System.out.println();

        RobotWorker robotWorker = new RobotWorker("Robot-101");
        robotWorker.work();
        robotWorker.recharge();
    }

    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("I - Interface Segregation Principle");
        System.out.println("==================================================");
        System.out.println("Interview line: Do not force a class to implement unused methods.");
        System.out.println();
    }
}

/*
 * ==================================================
 * I - Interface Segregation Principle
 * ==================================================
 *
 * Interview definition:
 * A class should not be forced to implement methods it does not use.
 *
 * Simple meaning:
 * Prefer small and focused interfaces instead of one large interface.
 *
 * Worker and robot example:
 * Human worker can work and eat.
 * Robot worker can work and recharge.
 *
 * Bad design:
 * One big Worker interface with work(), eat(), sleep(), recharge().
 * Robot would be forced to implement eat() and sleep(), which makes no sense.
 *
 * Better design:
 * Split behavior into small interfaces:
 * Workable, Eatable, Rechargeable.
 */
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Rechargeable {
    void recharge();
}

class HumanWorker implements Workable, Eatable {
    private String name;

    public HumanWorker(String name) {
        this.name = name;
    }

    @Override
    public void work() {
        System.out.println(name + " is working on a task.");
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating lunch.");
    }
}

class RobotWorker implements Workable, Rechargeable {
    private String robotId;

    public RobotWorker(String robotId) {
        this.robotId = robotId;
    }

    @Override
    public void work() {
        System.out.println(robotId + " is assembling parts.");
    }

    @Override
    public void recharge() {
        System.out.println(robotId + " is recharging.");
    }
}
