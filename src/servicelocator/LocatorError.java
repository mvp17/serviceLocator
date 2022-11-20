package servicelocator;

public class LocatorError extends Exception {
    public LocatorError(ClassCastException ex){
        super(ex);
    }
}
