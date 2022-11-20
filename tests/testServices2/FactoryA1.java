package testServices2;

import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import servicelocator2.ServiceLocator;
import testInterfaces.ImplementationA1;
import testInterfaces.InterfaceA;
import testInterfaces.InterfaceB;
import testInterfaces.InterfaceC;

public class FactoryA1 implements FactoryT {
    @Override
    public InterfaceA create(ServiceLocator sl) throws LocatorError {
            InterfaceB b = sl.getObject(InterfaceB.class);
            InterfaceC c = sl.getObject(InterfaceC.class);
            return new ImplementationA1(b, c);
    }
}
