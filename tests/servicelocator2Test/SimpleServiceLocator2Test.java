package servicelocator2Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import servicelocator2.LocatorError;
import servicelocator2.SimpleServiceLocator;
import testInterfaces.*;
import testServices2.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@SuppressWarnings("unchecked")
public class SimpleServiceLocator2Test {

    private SimpleServiceLocator simpleServiceLocator;


    @BeforeEach
    void setUp(){
        simpleServiceLocator = new SimpleServiceLocator();
    }


    @Test
    @DisplayName("Check Throws LocatorError when use setService")
    void setServiceTestException() {

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setService(String.class, new FactoryA1());
            simpleServiceLocator.setService(String.class,new FactoryA1());
        });
    }

    @Test
    @DisplayName("Check Throws LocatorError when use setConstant")
    <T> void  setConstantTestException() {
        T object = (T) "Object";
        T object1 = (T) "Object1";

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setConstant(Object.class, object);
            simpleServiceLocator.setConstant(Object.class, object1);
        });
    }

    @Test
    @DisplayName("Check setService using FactoryA1")
    <T> void setServiceTestA1() throws LocatorError {

        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");
        Class interfaceBClass = InterfaceB.class;
        Class interfaceCClass = InterfaceC.class;
        Class interfaceDClass = InterfaceD.class;


        simpleServiceLocator.setConstant(interfaceBClass, interfaceB);
        simpleServiceLocator.setConstant(interfaceCClass, interfaceC);
        simpleServiceLocator.setService(FactoryA1.class, new FactoryA1());

        T object = (T) simpleServiceLocator.getObject(FactoryA1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryA1.class);
        assertNotSame(object, object1);


        simpleServiceLocator.setConstant(interfaceDClass, interfaceD);
        simpleServiceLocator.setService(FactoryB1.class, new FactoryB1());
        assertNotSame(simpleServiceLocator.getObject(FactoryB1.class), simpleServiceLocator.getObject(FactoryB1.class));
    }

    @Test
    @DisplayName("Check setService using FactoryB1")
    <T> void setServiceTestB1() throws LocatorError {
        InterfaceD interfaceD = new ImplementationD1(0);
        Class interfaceDClass = InterfaceD.class;

        simpleServiceLocator.setConstant(interfaceDClass, interfaceD);
        simpleServiceLocator.setService(FactoryB1.class, new FactoryB1());

        T object = (T) simpleServiceLocator.getObject(FactoryB1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryB1.class);
        assertNotSame(object, object1);

        T constant = (T) "Hola";
        Class constantClass = String.class;

        simpleServiceLocator.setConstant(constantClass, constant);
        simpleServiceLocator.setService(FactoryC1.class, new FactoryC1());
        assertNotSame(simpleServiceLocator.getObject(FactoryC1.class), simpleServiceLocator.getObject(FactoryC1.class));

    }

    @Test
    @DisplayName("Check setService using FactoryC1")
    <T> void setServiceTestC1() throws LocatorError {

        T constant = (T) "Hola";
        Class constantClass = String.class;

        simpleServiceLocator.setConstant(constantClass, constant);
        simpleServiceLocator.setService(FactoryC1.class, new FactoryC1());

        T object = (T) simpleServiceLocator.getObject(FactoryC1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryC1.class);
        assertNotSame(object, object1);


        Integer integer = 0;

        simpleServiceLocator.setConstant(Integer.class, integer);
        simpleServiceLocator.setService(FactoryD1.class, new FactoryD1());
        assertNotSame(simpleServiceLocator.getObject(FactoryD1.class), simpleServiceLocator.getObject(FactoryD1.class));

    }

    @Test
    @DisplayName("Check setService using FactoryD1")
    <T> void setServiceTestD1() throws LocatorError {

        Integer integer = 0;

        simpleServiceLocator.setConstant(Integer.class, integer);
        simpleServiceLocator.setService(FactoryD1.class, new FactoryD1());

        T object = (T) simpleServiceLocator.getObject(FactoryD1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryD1.class);
        assertNotSame(object, object1);


        InterfaceD interfaceD = new ImplementationD1(0);
        T interfaceB = (T) new ImplementationB1(interfaceD);
        T interfaceC = (T) new ImplementationC1("Hola");
        Class interfaceBClass = InterfaceB.class;
        Class interfaceCClass = InterfaceC.class;

        simpleServiceLocator.setConstant(interfaceBClass, interfaceB);
        simpleServiceLocator.setConstant(interfaceCClass, interfaceC);
        simpleServiceLocator.setService(FactoryA1.class, new FactoryA1());
        assertNotSame(simpleServiceLocator.getObject(FactoryA1.class), simpleServiceLocator.getObject(FactoryA1.class));

    }

}