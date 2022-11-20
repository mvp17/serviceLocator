package testServices;

import servicelocator.Factory;
import servicelocator.LocatorError;
import servicelocator.ServiceLocator;
import testInterfaces.ImplementationA1;
import testInterfaces.InterfaceA;
import testInterfaces.InterfaceB;
import testInterfaces.InterfaceC;

public class FactoryA1 implements Factory {
    @Override
    public InterfaceA create(ServiceLocator sl) throws LocatorError {
        try{
            InterfaceB b = (InterfaceB) sl.getObject("B");
            InterfaceC c = (InterfaceC) sl.getObject("C");
            return new ImplementationA1(b, c);
        } catch (ClassCastException ex){
            throw new LocatorError(ex);
        }
    }
}
