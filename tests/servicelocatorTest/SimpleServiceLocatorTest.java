package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import servicelocator.LocatorError;
import servicelocator.SimpleServiceLocator;
import testInterfaces.*;
import testServices.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleServiceLocatorTest {

    private SimpleServiceLocator simpleServiceLocator;


    @BeforeEach
    void setUp(){
        simpleServiceLocator = new SimpleServiceLocator();
    }


    @Test
    @DisplayName("Check Throws LocatorError when use setService")
    void setServiceTestException(){

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setService("s", new FactoryA1());
            simpleServiceLocator.setService("s", new FactoryA1());
        });

    }


    @Test
    @DisplayName("Check Throws LocatorError when use setConstant")
    void setConstantTestException(){

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setConstant("s", new Object());
            simpleServiceLocator.setConstant("s", new Object());
        });

    }

    @Test
    @DisplayName("Check setService using FactoryA1")
    void setServiceTestA1() throws LocatorError{

        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        //Test with one factory get different objects
        simpleServiceLocator.setConstant("B",interfaceB);
        simpleServiceLocator.setConstant("C", interfaceC);
        simpleServiceLocator.setService("A", new FactoryA1());

        Object object = simpleServiceLocator.getObject("A");
        assertNotSame(object, simpleServiceLocator.getObject("A"));

        //Test setting the factory correctly
        ImplementationA1 implementationA1 = (ImplementationA1) object;
        assertEquals(interfaceB, implementationA1.getB());
        assertEquals(interfaceC, implementationA1.getC());

        //Test with two factories
        simpleServiceLocator.setConstant("D", new ImplementationD1(0));
        simpleServiceLocator.setService("B1", new FactoryB1());
        assertNotSame(simpleServiceLocator.getObject("B1"), simpleServiceLocator.getObject("B1"));

    }

    @Test
    @DisplayName("Check setService using FactoryB1")
    void setServiceTestB1() throws LocatorError{

        InterfaceD interfaceD = new ImplementationD1(0);

        simpleServiceLocator.setConstant("D", interfaceD);
        simpleServiceLocator.setService("B",  new FactoryB1());
        Object object = simpleServiceLocator.getObject("B");

        assertNotSame(object, simpleServiceLocator.getObject("B"));

        ImplementationB1 implementationB1 = (ImplementationB1) object;
        assertEquals(interfaceD,implementationB1.getD());

        simpleServiceLocator.setConstant("S", "Hola");
        simpleServiceLocator.setService("C", new FactoryC1());

        assertNotSame(simpleServiceLocator.getObject("C"), simpleServiceLocator.getObject("C"));

    }

    @Test
    @DisplayName("Check setService using FactoryC1")
    void setServiceTestC1() throws LocatorError{

        String string = "Hola";

        simpleServiceLocator.setConstant("S", string);
        simpleServiceLocator.setService("C", new FactoryC1());

        Object object = simpleServiceLocator.getObject("C");
        assertNotSame(object, simpleServiceLocator.getObject("C"));

        ImplementationC1 implementationC1 = (ImplementationC1) object;
        assertEquals(string,implementationC1.getS());


        int integer = 0;

        simpleServiceLocator.setConstant("I", integer);
        simpleServiceLocator.setService("D", new FactoryD1());
        Object object2 = simpleServiceLocator.getObject("D");
        assertNotSame(object2, simpleServiceLocator.getObject("D"));

    }

    @Test
    @DisplayName("Check setService using FactoryD1")
    void setServiceTestD1() throws LocatorError{

        int constant = 0;

        simpleServiceLocator.setConstant("I", constant);
        simpleServiceLocator.setService("D", new FactoryD1());
        Object object = simpleServiceLocator.getObject("D");

        assertNotSame(object, simpleServiceLocator.getObject("D"));

        ImplementationD1 implementationD1 = (ImplementationD1) object;
        assertEquals(constant, implementationD1.getI());


        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        simpleServiceLocator.setConstant("B",interfaceB);
        simpleServiceLocator.setConstant("C", interfaceC);
        simpleServiceLocator.setService("A", new FactoryA1());

        assertNotSame(simpleServiceLocator.getObject("A"), simpleServiceLocator.getObject("A"));

    }

}
