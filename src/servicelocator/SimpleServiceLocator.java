package servicelocator;

import java.util.HashMap;
import java.util.Map;

public class SimpleServiceLocator implements ServiceLocator {
    private Map<String, Factory> factories;
    private Map<String, Object> constants;

    public SimpleServiceLocator(){
        factories = new HashMap<>();
        constants = new HashMap<>();
    }

    public void setService(String name, Factory factory) throws LocatorError {
        if (!factories.containsKey(name))
            factories.put(name, factory);
        else
            throw new LocatorError(new ClassCastException());
    }

    public void setConstant(String name, Object value) throws LocatorError {
            if (!constants.containsKey(name))
                constants.put(name, value);
            else
                throw new LocatorError(new ClassCastException());
    }

    public Object getObject(String name) throws LocatorError {

        if (factories.containsKey(name) && constants.containsKey(name))
            throw new LocatorError(new ClassCastException());
        if (constants.containsKey(name))
            return constants.get(name);
        else if (factories.containsKey(name))
            return factories.get(name).create(this);
        else
            throw new LocatorError(new ClassCastException());
    }
}
