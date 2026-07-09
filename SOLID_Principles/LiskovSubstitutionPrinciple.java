import java.util.ArrayList;
import java.util.List;

public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        printHeader();

        List<Bird> birds = new ArrayList<>();
        birds.add(new Crow());
        birds.add(new Eagle());
        birds.add(new Ostrich());

        BirdFeeder feeder = new BirdFeeder();
        feeder.feedAll(birds);

        System.out.println();

        List<Flyable> flyingBirds = new ArrayList<>();
        flyingBirds.add(new Crow());
        flyingBirds.add(new Eagle());

        FlightShow flightShow = new FlightShow();
        flightShow.start(flyingBirds);
    }

    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("L - Liskov Substitution Principle");
        System.out.println("==================================================");
        System.out.println("Interview line: Child types should safely replace parent types.");
        System.out.println();
    }
}

/*
 * ==================================================
 * L - Liskov Substitution Principle
 * ==================================================
 *
 * Interview definition:
 * Child classes should be replaceable wherever the parent type is expected,
 * without breaking the program.
 *
 * Simple meaning:
 * If code works with a parent type, it should also work correctly with every
 * child type.
 *
 * Bird example:
 * Not every bird can fly. Ostrich is a bird, but it cannot fly.
 *
 * Bad design:
 * If Bird has fly(), then Ostrich must also implement fly(), which is wrong.
 *
 * Better design:
 * Bird has common bird behavior like eat().
 * Only birds that can fly implement Flyable.
 */
abstract class Bird {
    public abstract String getName();

    public void eat() {
        System.out.println(getName() + " is eating.");
    }
}

interface Flyable {
    String getName();

    void fly();
}

class Crow extends Bird implements Flyable {
    @Override
    public String getName() {
        return "Crow";
    }

    @Override
    public void fly() {
        System.out.println("Crow is flying.");
    }
}

class Eagle extends Bird implements Flyable {
    @Override
    public String getName() {
        return "Eagle";
    }

    @Override
    public void fly() {
        System.out.println("Eagle is flying high.");
    }
}

class Ostrich extends Bird {
    @Override
    public String getName() {
        return "Ostrich";
    }
}

class BirdFeeder {
    public void feedAll(List<Bird> birds) {
        System.out.println("Feeding all birds:");

        for (Bird bird : birds) {
            bird.eat();
        }
    }
}

class FlightShow {
    public void start(List<Flyable> flyingBirds) {
        System.out.println("Starting flight show:");

        for (Flyable bird : flyingBirds) {
            bird.fly();
        }
    }
}
