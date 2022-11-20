package testServices2;

import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import servicelocator2.ServiceLocator;
import testInterfaces.ImplementationB1;
import testInterfaces.InterfaceB;
import testInterfaces.InterfaceD;

public class FactoryB1 implements FactoryT {
    @Override
    public InterfaceB create(ServiceLocator sl) throws LocatorError {
            InterfaceD d = sl.getObject(InterfaceD.class);
            return new ImplementationB1(d);
    }
}
