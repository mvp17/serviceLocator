package servicelocator2;

import java.util.HashMap;
import java.util.Map;

public class CachedServiceLocator implements ServiceLocator {
    private Map<Class<?>, Object> cache;
    private Map<Class<?>, FactoryT<?>> services;

    public CachedServiceLocator(){
        cache = new HashMap<>();
        services = new HashMap<>();
    }

    public <T> void setService(Class<T> klass, FactoryT<T> factoryT) throws LocatorError {
        if (!services.containsKey(klass))
            services.put(klass, factoryT);
        else
            throw new LocatorError();
    }

    public <T> void setConstant(Class<T> klass, T value) throws LocatorError {
        if (!cache.containsKey(klass))
            cache.put(klass, value);
        else
            throw new LocatorError();
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> klass) throws LocatorError {
        if (cache.containsKey(klass))
            return (T) cache.get(klass);
        else if(services.containsKey(klass)) {
            cache.put(klass, services.get(klass).create(this));
            return (T) cache.get(klass);
        }else throw new LocatorError();
    }
}
