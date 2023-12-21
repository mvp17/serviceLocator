# Design patterns assignment
When an object oriented system is designed, when the functionality of multiple objects and different responsibilites are allocated, a problem that needs to be solved is interconnection.
What is the meaning of interconnection? Suppose that there is a group of interfaces which can be implemented of many ways by several specific classes (in our case, all of this code will be part of the tests which we will run and check our implementation is working).

![image](https://github.com/mvp17/serviceLocator/assets/45287481/a66ff59d-b6c9-4864-b879-2b7c20aa0621)

Important aspects to consider:
- The specific classes depend on the interfaces and not the other specific classes.
- The specific classes are independent between them; they only depend on the interfaces.
- Each specific class requires that its constructor receives the objects which implement the interfaces of which depend on.

The interconnection problem consists of, for an specific system's configuration, to choose which implementations will be used from the interfaces and make these implementations be passed as parameters in order to build the needed objects.
In a real system, for instance, it could be chosen which database engine will be used, which template sytem and so on.

# Service locator pattern
There are various patterns in order to attain this goal, but the simplest of all, would be the Service Locator.

## First version
One option is creating a register (the service locator) where an string will be associated to each implementation for each interface.
There are many feasible designs for this register depending on the required properties of the extracted from the register:
- When it is requested by a key, and the same object is always wanted, it can be directly stored the instance.
- When a different object (but from the registered implementation) is required, exist several alternatives:
  - Store instances and clone them.
  - Store implementations' factories.

For that assignment, the last alternative will be used: register factories for the different implementations. This also will allow not tying the service locator with the implementations since it only will be used for the factories.
Therefore, the following interfaces and classes will be defined in the servicelocator package:

![image](https://github.com/mvp17/serviceLocator/assets/45287481/b487c2cc-e6ba-4a71-84e1-4c2b12e81c0a)

The ServiceLocator class methods behave as follows:
- setService set up a factory giving a name (and throws a LocatorError exception if there is something registered with this name).
- setConstant set up an Object value giving a name (and throws a LocatorError exception if there is something registered with this name).
- getObject, if the name is related to a constant, returns the associated object and, if it is related to a factory, returns the object created by this factory. Throws a LocatorError exception if there is nothing under this name.

It is noteworthy that, in case to be related to a factory, each time it is called, returns a distinct object.

Regarding the Factory interface, point out that the create method is using the received ServiceLocator so as to obtain the needed interfaces' implementations. It also can throw the LocatorError exception if there are problems when searching for dependencies in the ServieLocator or if these do not implement the required interface (see the factory example on the next section).

### Factories implementations
A factory will be defined for each specific classes which will be worked on.
For instance, the factory that will create the ImplementationA1 class instances is:

![image](https://github.com/mvp17/serviceLocator/assets/45287481/f92faed0-e860-4f30-9bfa-e2a17424ceb6)

And, in some part of this code, the ServiceLocator will be set up, associating the names with the constants or factories, which will be used later from the factories in order to get the dependencies.

### ServiceLocator implementation
Two implementations will be defined for the ServiceLocator:
- SimpleServiceLocator
- CachedServiceLocator
The difference between them is that on the second, whether a constant is registered or a factory, when an object associated with the name is required, **the same instance is always returned**. In the first implementation, this only happen when a name of a constant was registered, if a factory is registered, different objects will be attained.

## Second version
It is worth noting that, in the previous version, there is no control at all upon the ServiceLocator configuration (all are Objects in the end) and, therefore, the compiler is unable to detect whether the configuration is wrong or not.
This second section is aimed to achieve the **type security** in compilation time and to explore some things like:
- The generic class Class<T> in order to use "type literals" so as to mark the type in runtime (see Class Literals as Runtime-Type Tokens).
- Avoid using "raw types" in order to refer to generic classes without instanciating the type parameter (see Raw Types).
- -Xlint:unchecked directive from the compiler so as to point out the generics use. It usually is not enabled to allow that the old code, with no generics, does not arise problems (see Unchecked Error Messages).
- @SupressWarnings("unchecked") annotation so as to mark instructions that the compiler detect as warnings but, as for the program context, it is known that it does not create problems (see Predefined Annotation Types).

The classes and interfaces for this version will be in the servicelocator2 package and will be:

![image](https://github.com/mvp17/serviceLocator/assets/45287481/296679c5-6169-4271-9318-51e42d63f4c8)

Note that now the factories will be able to do:

![image](https://github.com/mvp17/serviceLocator/assets/45287481/a9e29a4f-9262-4a3c-9c65-177bf649b12e)

since now there is no chance for a ClassClastException when objects are required to the ServiceLocator.

Besides, there will be two versions of this new ServiceLocator:
- SimpleServiceLocator
- CachedServiceLocator
With the same features described previously.

# Requirements
IntelliJ project with the ServiceLocator's four different implementations code
- servicelocator
  - LocatorError
  - Factory
  - ServiceLocator
  - SimpleServiceLocator
  - CachedServiceLocator
- servicelocator2
  - LocatorError
  - Factory<T>
  - ServiceLocator
  - SimpleServiceLocator
  - CachedServiceLocator
- Test classes where the well behaviours of the implementations are checked.
  - In this code, it will be defined the interfaces and classes implementations for the tests, their factories, etc.
  - Try to avoid duplicated code in the tests (remember that test classes are like the other classes).
- Small report showing the final design, main difficulties found, how they have been solved, design considerations, etc.

# Links
Links used previously:
- Class Literals as Runtime-Type Tokens
  - https://docs.oracle.com/javase/tutorial/extra/generics/literals.html
- Raw Types and Unchecked Error Messages
  - https://docs.oracle.com/javase/tutorial/java/generics/rawTypes.html
- Predefined Annotation Types
  - https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html
