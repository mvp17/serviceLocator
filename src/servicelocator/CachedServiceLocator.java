package servicelocator;

import java.util.HashMap;
import java.util.Map;

public class CachedServiceLocator implements ServiceLocator {
    private Map<String, Object> cache;
    private Map<String, Factory> services;

    public CachedServiceLocator(){
        services = new HashMap<>();
        cache = new HashMap<>();
    }

    public void setService(String name, Factory factory) throws LocatorError {
        if (!services.containsKey(name))
            services.put(name, factory);
        else
            throw new LocatorError(new ClassCastException());
    }

    public void setConstant(String name, Object value) throws LocatorError {

        if (!cache.containsKey(name))
            cache.put(name, value);
        else
            throw new LocatorError(new ClassCastException());
    }

    public Object getObject(String name) throws LocatorError {
        if (cache.containsKey(name))
            return cache.get(name);
        else if(services.containsKey(name)) {
            cache.put(name, services.get(name).create(this));
            return cache.get(name);
        } else throw new LocatorError(new ClassCastException());
    }
}
