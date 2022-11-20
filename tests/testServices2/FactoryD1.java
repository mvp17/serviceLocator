package testServices2;

import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import servicelocator2.ServiceLocator;
import testInterfaces.ImplementationD1;
import testInterfaces.InterfaceD;

public class FactoryD1 implements FactoryT {
    @Override
    public InterfaceD create(ServiceLocator sl) throws LocatorError {
            int i = sl.getObject(Integer.class);
            return new ImplementationD1(i);
    }
}
