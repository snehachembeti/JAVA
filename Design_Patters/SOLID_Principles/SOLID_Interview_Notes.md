# SOLID Principles - Interview Notes

## S - Single Responsibility Principle

Interview answer:
A class should have only one reason to change.

Simple meaning:
One class should do one main job.

Example:
An `Invoice` class should calculate invoice data. Printing and saving should be handled by separate classes.

## O - Open/Closed Principle

Interview answer:
Software entities should be open for extension but closed for modification.

Simple meaning:
Add new behavior by creating new classes, not by changing old tested code.

Example:
For discounts, create `RegularCustomerDiscount`, `PremiumCustomerDiscount`, etc. Do not keep changing the billing service every time.

## L - Liskov Substitution Principle

Interview answer:
Child classes should be replaceable wherever the parent type is expected, without breaking the program.

Simple meaning:
If a method accepts a parent type, all child types should work correctly there.

Example:
If `AreaCalculator` accepts `Shape`, then `Rectangle` and `Circle` should both work as valid shapes.

## I - Interface Segregation Principle

Interview answer:
A class should not be forced to implement methods it does not use.

Simple meaning:
Create small, specific interfaces instead of one big interface.

Example:
A simple printer should implement only `Printable`, not unnecessary methods like `scan()` or `fax()`.

## D - Dependency Inversion Principle

Interview answer:
High-level modules should depend on abstractions, not concrete classes.

Simple meaning:
Business logic should depend on interfaces, not directly on specific implementations.

Example:
`NotificationService` should depend on `MessageService`, not directly on `EmailService`. Then it can support email, SMS, WhatsApp, etc.

## Quick Memory Line

SOLID helps us write code that is easy to maintain, extend, test, and understand.
