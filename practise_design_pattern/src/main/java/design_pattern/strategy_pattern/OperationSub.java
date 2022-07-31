package design_pattern.strategy_pattern;

public class OperationSub implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
