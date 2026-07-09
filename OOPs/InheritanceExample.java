public class InheritanceExample {
    public static void main(String[] args) {
        InheritanceDog dog = new InheritanceDog("Tommy");

        dog.eat();
        dog.sleep();
        dog.bark();
    }
}

class InheritanceAnimal {
    protected String name;

    public InheritanceAnimal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

class InheritanceDog extends InheritanceAnimal {
    public InheritanceDog(String name) {
        // super calls the parent class constructor.
        super(name);
    }

    public void bark() {
        System.out.println(name + " is barking.");
    }
}
