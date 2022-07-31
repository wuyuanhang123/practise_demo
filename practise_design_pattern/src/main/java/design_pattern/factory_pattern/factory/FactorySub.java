package design_pattern.factory_pattern.factory;

public class FactorySub implements IFactory {
    @Override
    public Operation createOption() {
        return new OperationSub();
    }
}
