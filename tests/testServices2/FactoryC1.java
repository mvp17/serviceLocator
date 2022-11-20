package testServices2;

import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import servicelocator2.ServiceLocator;
import testInterfaces.ImplementationC1;
import testInterfaces.InterfaceC;

public class FactoryC1 implements FactoryT {
    @Override
    public InterfaceC create(ServiceLocator sl) throws LocatorError {
            String s = sl.getObject(String.class);
            return new ImplementationC1(s);
    }
}
