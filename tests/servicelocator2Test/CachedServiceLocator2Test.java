package servicelocator2Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import servicelocator2.CachedServiceLocator;
import servicelocator2.LocatorError;
import testInterfaces.*;
import testServices2.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;


@SuppressWarnings("unchecked")
public class CachedServiceLocator2Test {

    private CachedServiceLocator cachedServiceLocator;


    @BeforeEach
    void setUp(){
        cachedServiceLocator = new CachedServiceLocator();
    }


    @Test
    @DisplayName("Check Throws LocatorError when use setService")
    void setServiceTestException(){

        assertThrows(LocatorError.class, ()->{
            cachedServiceLocator.setService(FactoryA1.class, new FactoryA1());
            cachedServiceLocator.setService(FactoryA1.class, new FactoryA1());
        });
    }

    @Test
    @DisplayName("Check Throws LocatorError when use setConstant")
    void setConstantTestException() {

        InterfaceC interface1 = new ImplementationC1("Hola");
        InterfaceC interface2 = new ImplementationC1("Hola");
        assertThrows(LocatorError.class, ()->{
            cachedServiceLocator.setConstant(InterfaceC.class, interface1);
            cachedServiceLocator.setConstant(InterfaceC.class, interface2);
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


        cachedServiceLocator.setConstant(interfaceBClass, interfaceB);
        cachedServiceLocator.setConstant(interfaceCClass, interfaceC);
        cachedServiceLocator.setService(FactoryA1.class, new FactoryA1());

        T object = (T) cachedServiceLocator.getObject(FactoryA1.class);
        T object1 = (T) cachedServiceLocator.getObject(FactoryA1.class);
        assertSame(object, object1);


        cachedServiceLocator.setConstant(interfaceDClass, interfaceD);
        cachedServiceLocator.setService(FactoryB1.class, new FactoryB1());
        assertSame(cachedServiceLocator.getObject(FactoryB1.class), cachedServiceLocator.getObject(FactoryB1.class));
    }

    @Test
    @DisplayName("Check setService using FactoryB1")
    <T> void setServiceTestB1() throws LocatorError {

        InterfaceD interfaceD = new ImplementationD1(0);
        Class interfaceDClass = InterfaceD.class;

        cachedServiceLocator.setConstant(interfaceDClass, interfaceD);
        cachedServiceLocator.setService(FactoryB1.class, new FactoryB1());

        T object = (T) cachedServiceLocator.getObject(FactoryB1.class);
        T object1 = (T) cachedServiceLocator.getObject(FactoryB1.class);
        assertSame(object, object1);

        T constant = (T) "Hola";
        Class constantClass = String.class;

        cachedServiceLocator.setConstant(constantClass, constant);
        cachedServiceLocator.setService(FactoryC1.class, new FactoryC1());
        assertSame(cachedServiceLocator.getObject(FactoryC1.class), cachedServiceLocator.getObject(FactoryC1.class));

    }

    @Test
    @DisplayName("Check setService using FactoryC1")
    <T> void setServiceTestC1() throws LocatorError {

        T constant = (T) "Hola";
        Class constantClass = String.class;

        cachedServiceLocator.setConstant(constantClass, constant);
        cachedServiceLocator.setService(FactoryC1.class, new FactoryC1());

        T object = (T) cachedServiceLocator.getObject(FactoryC1.class);
        T object1 = (T) cachedServiceLocator.getObject(FactoryC1.class);
        assertSame(object, object1);


        Integer integer = 0;

        cachedServiceLocator.setConstant(Integer.class, integer);
        cachedServiceLocator.setService(FactoryD1.class, new FactoryD1());
        assertSame(cachedServiceLocator.getObject(FactoryD1.class), cachedServiceLocator.getObject(FactoryD1.class));

    }

    @Test
    @DisplayName("Check setService using FactoryD1")
    <T> void setServiceTestD1() throws LocatorError {

        Integer integer = 0;

        cachedServiceLocator.setConstant(Integer.class, integer);
        cachedServiceLocator.setService(FactoryD1.class, new FactoryD1());

        T object = (T) cachedServiceLocator.getObject(FactoryD1.class);
        T object1 = (T) cachedServiceLocator.getObject(FactoryD1.class);
        assertSame(object, object1);


        InterfaceD interfaceD = new ImplementationD1(0);
        T interfaceB = (T) new ImplementationB1(interfaceD);
        T interfaceC = (T) new ImplementationC1("Hola");
        Class interfaceBClass = InterfaceB.class;
        Class interfaceCClass = InterfaceC.class;

        cachedServiceLocator.setConstant(interfaceBClass, interfaceB);
        cachedServiceLocator.setConstant(interfaceCClass, interfaceC);
        cachedServiceLocator.setService(FactoryA1.class, new FactoryA1());
        assertSame(cachedServiceLocator.getObject(FactoryA1.class), cachedServiceLocator.getObject(FactoryA1.class));

    }

}