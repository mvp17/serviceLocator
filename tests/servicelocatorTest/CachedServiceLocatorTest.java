package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import servicelocator.LocatorError;
import servicelocator.CachedServiceLocator;
import testInterfaces.*;
import testServices.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CachedServiceLocatorTest {

    private CachedServiceLocator cachedServiceLocator;


    @BeforeEach
    void setUp(){
        cachedServiceLocator = new CachedServiceLocator();
    }


    @Test
    @DisplayName("Check Throws LocatorError when use setService")
    void setServiceTestException(){
        assertThrows(LocatorError.class, ()-> {
            cachedServiceLocator.setService("s", new FactoryA1());
            cachedServiceLocator.setService("s",new FactoryA1());
        });
    }

    @Test
    @DisplayName("Check Throws LocatorError when use setConstant")
    void setConstantTestException(){

        assertThrows(LocatorError.class, ()-> {
            cachedServiceLocator.setConstant("s", new Object());
            cachedServiceLocator.setConstant("s",new Object());
        });

    }

    @Test
    @DisplayName("Check setService using FactoryA1")
    void setServiceTestA1() throws LocatorError{

        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        //Test with one factory same object reference
        cachedServiceLocator.setConstant("B",interfaceB);
        cachedServiceLocator.setConstant("C", interfaceC);
        cachedServiceLocator.setService("A", new FactoryA1());
        Object object = cachedServiceLocator.getObject("A");

        assertSame(object, cachedServiceLocator.getObject("A"));

        //Test setting the factory correctly
        ImplementationA1 implementationA1 = (ImplementationA1) object;
        assertEquals(interfaceB, implementationA1.getB());
        assertEquals(interfaceC, implementationA1.getC());

        //Test with two factory

        InterfaceD interfaceD1 = new ImplementationD1(0);

        cachedServiceLocator.setConstant("D", interfaceD1);
        cachedServiceLocator.setService("B1", new FactoryB1());
        assertSame(cachedServiceLocator.getObject("B1"), cachedServiceLocator.getObject("B1"));

    }

    @Test
    @DisplayName("Check setService using FactoryB1")
    void setServiceTestB1() throws LocatorError{

        InterfaceD interfaceD = new ImplementationD1(0);

        cachedServiceLocator.setConstant("D", interfaceD);
        cachedServiceLocator.setService("B", new FactoryB1());
        Object object = cachedServiceLocator.getObject("B");

        assertSame(object, cachedServiceLocator.getObject("B"));

        ImplementationB1 implementationB1 = (ImplementationB1) object;
        assertEquals(interfaceD,implementationB1.getD());

        cachedServiceLocator.setConstant("S", "Hola");
        cachedServiceLocator.setService("C", new FactoryC1());

        assertSame(cachedServiceLocator.getObject("C"), cachedServiceLocator.getObject("C"));

    }

    @Test
    @DisplayName("Check setService using FactoryC1")
    void setServiceTestC1() throws LocatorError{
        String constant = "Hola";

        cachedServiceLocator.setConstant("S", constant);
        cachedServiceLocator.setService("C",  new FactoryC1());
        Object object = cachedServiceLocator.getObject("C");

        assertSame(object, cachedServiceLocator.getObject("C"));

        ImplementationC1 implementationC1 = (ImplementationC1) object;
        assertEquals(constant,implementationC1.getS());

        int constant1 = 0;
        cachedServiceLocator.setConstant("I", constant1);
        cachedServiceLocator.setService("D", new FactoryD1());

        assertSame(cachedServiceLocator.getObject("D"),cachedServiceLocator.getObject("D"));

    }

    @Test
    @DisplayName("Check setService using FactoryD1")
    void setServiceTestD1() throws LocatorError{
        int constant = 0;

        cachedServiceLocator.setConstant("I", constant);
        cachedServiceLocator.setService("D", new FactoryD1());
        Object object = cachedServiceLocator.getObject("D");
        assertSame(object, cachedServiceLocator.getObject("D"));

        ImplementationD1 implementationD1 = (ImplementationD1) object;
        assertEquals(constant, implementationD1.getI());


        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        cachedServiceLocator.setConstant("B",interfaceB);
        cachedServiceLocator.setConstant("C", interfaceC);
        cachedServiceLocator.setService("A", new FactoryA1());

        assertSame(cachedServiceLocator.getObject("A"), cachedServiceLocator.getObject("A"));

    }

}
