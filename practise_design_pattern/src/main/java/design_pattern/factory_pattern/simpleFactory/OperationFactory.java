package design_pattern.factory_pattern.simpleFactory;

public class OperationFactory {
   public static Operation createOperation(String operation) throws Exception {
       Operation oper = null;
       switch (operation){
           case "add":
               oper = new OperationAdd();
               break;
           case "sub":
               oper = new OperationSub();
               break;
           default:
               throw new Exception();
       }
       return oper;
   }
}
