package testServices;

import servicelocator.Factory;
import servicelocator.LocatorError;
import servicelocator.ServiceLocator;
import testInterfaces.ImplementationD1;
import testInterfaces.InterfaceD;

public class FactoryD1 implements Factory {
    @Override
    public InterfaceD create(ServiceLocator sl) throws LocatorError {
        try{
            int i = (int) sl.getObject("I");
            return new ImplementationD1(i);
        } catch (ClassCastException ex){
            throw new LocatorError(ex);
        }
    }
}
