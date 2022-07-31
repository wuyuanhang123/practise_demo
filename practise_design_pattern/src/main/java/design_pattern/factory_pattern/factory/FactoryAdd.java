package design_pattern.factory_pattern.factory;

public class FactoryAdd implements IFactory {
    @Override
    public Operation createOption() {
        return new OperationAdd();
    }
}
