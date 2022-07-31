package design_pattern.factory_pattern.factory;

public class FactoryTest {
    public static void main(String[] args){
        IFactory factory = new FactoryAdd();
        Operation operation = factory.createOption();
        operation.getResult(1,2);
    }
}
