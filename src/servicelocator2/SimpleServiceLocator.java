package servicelocator2;

import java.util.HashMap;
import java.util.Map;

public class SimpleServiceLocator implements ServiceLocator {
    private Map<Class<?>, FactoryT<?>> factories;
    private Map<Class<?>, Object> constants;

    public SimpleServiceLocator(){
        factories = new HashMap<>();
        constants = new HashMap<>();
    }

    public <T> void setService(Class<T> klass, FactoryT<T> factoryT) throws LocatorError {
        if (!factories.containsKey(klass))
            factories.put(klass, factoryT);
        else
            throw new LocatorError();
    }

    public <T> void setConstant(Class<T> klass, T value) throws LocatorError {
        if (!constants.containsKey(klass))
            constants.put(klass, value);
        else
            throw new LocatorError();
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> klass) throws LocatorError {
        if (factories.containsKey(klass) && constants.containsKey(klass))
            throw new LocatorError();
        if (constants.containsKey(klass))
            return (T) constants.get(klass);
        else if (factories.containsKey(klass))
            return (T) factories.get(klass).create(this);
        else
            throw new LocatorError();
    }
}
