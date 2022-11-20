package servicelocator2;

public interface FactoryT<T> {
    T create(ServiceLocator sl) throws LocatorError;
}
