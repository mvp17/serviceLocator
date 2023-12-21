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
