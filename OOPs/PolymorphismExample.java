public class PolymorphismExample {
    public static void main(String[] args) {
        PolymorphismShape circle = new PolymorphismCircle(5);
        PolymorphismShape rectangle = new PolymorphismRectangle(4, 6);

        printArea(circle);
        printArea(rectangle);
    }

    public static void printArea(PolymorphismShape shape) {
        // Same method call, but different object gives different behavior.
        System.out.println(shape.getName() + " area: " + shape.calculateArea());
    }
}

interface PolymorphismShape {
    String getName();

    double calculateArea();
}

class PolymorphismCircle implements PolymorphismShape {
    private double radius;

    public PolymorphismCircle(double radius) {
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "Circle";
    }

    @Override
    public double calculateArea() {
        return 3.14 * radius * radius;
    }
}

class PolymorphismRectangle implements PolymorphismShape {
    private double length;
    private double width;

    public PolymorphismRectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}
