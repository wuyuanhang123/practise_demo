package design_pattern.factory_pattern.simpleFactory;

public class SimpleFactoryTest {
    public static void main(String[] args) throws Exception {
        Operation operation = OperationFactory.createOperation("add");
        System.out.println(operation.getResult(1,2));
    }
}
