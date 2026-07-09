# SOLID Principles - Interview Notes

Quick memory line:
SOLID helps us write code that is easy to maintain, extend, test, and understand.

| Letter | Principle | Interview Definition | Example File |
| --- | --- | --- | --- |
| S | Single Responsibility | A class should have only one reason to change. | `SingleResponsibilityPrinciple.java` |
| O | Open/Closed | Software entities should be open for extension but closed for modification. | `OpenClosedPrinciple.java` |
| L | Liskov Substitution | Child types should be replaceable wherever the parent type is expected, without breaking the program. | `LiskovSubstitutionPrinciple.java` |
| I | Interface Segregation | A class should not be forced to implement methods it does not use. | `InterfaceSegregationPrinciple.java` |
| D | Dependency Inversion | High-level modules should depend on abstractions, not concrete classes. | `DependencyInversionPrinciple.java` |

## S - Single Responsibility Principle

Interview answer:
A class should have only one reason to change.

Simple meaning:
One class should do one main job.

Example:
`Invoice` calculates invoice data. `InvoicePrinter` prints it. `InvoiceRepository` saves it.

## O - Open/Closed Principle

Interview answer:
Software entities should be open for extension but closed for modification.

Simple meaning:
Add new behavior by creating new classes, not by changing old tested code.

Payment example:
`PaymentProcessor` depends on `PaymentMethod`. To add card, UPI, wallet, or net banking, create a new payment class. The processor does not need to change.

## L - Liskov Substitution Principle

Interview answer:
Child types should be replaceable wherever the parent type is expected, without breaking the program.

Simple meaning:
If a method accepts a parent type, all child types should work correctly there.

Bird example:
All birds can be represented as `Bird`, but not all birds can fly. So only flying birds implement `Flyable`. `Ostrich` is still a bird, but it is not forced to fly.

## I - Interface Segregation Principle

Interview answer:
A class should not be forced to implement methods it does not use.

Simple meaning:
Create small, specific interfaces instead of one big interface.

Worker and robot example:
Human worker implements `Workable` and `Eatable`. Robot worker implements `Workable` and `Rechargeable`. Robot is not forced to implement `eat()`.

## D - Dependency Inversion Principle

Interview answer:
High-level modules should depend on abstractions, not concrete classes.

Simple meaning:
Business logic should depend on interfaces, not directly on specific implementations.

Example:
`NotificationService` depends on `MessageSender`, not directly on email or SMS classes. That makes it easy to add future senders.

## Run Commands

From `SOLID_Principles` folder:

```powershell
java SingleResponsibilityPrinciple.java
java OpenClosedPrinciple.java
java LiskovSubstitutionPrinciple.java
java InterfaceSegregationPrinciple.java
java DependencyInversionPrinciple.java
```
