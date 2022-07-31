package design_pattern.strategy_pattern;

public class StrategyPatternTest {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSub());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMult());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
