package design_pattern.factory_pattern.factory;

public class OperationAdd implements Operation {

    @Override
    public double getResult(double value1, double value2) {
        return value1+value2;
    }
}
