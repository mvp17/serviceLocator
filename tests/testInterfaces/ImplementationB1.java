package testInterfaces;

public class ImplementationB1 implements InterfaceB{
    private InterfaceD d;
    public ImplementationB1(InterfaceD d){
        this.d = d;
    }
    public InterfaceD getD(){
        return d;
    }
}
