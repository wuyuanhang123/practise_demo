package design_pattern.proxy_pattern;

public class CLIBProxyTest {
    public static void main(String[] args){
        UserDao target = new UserDao();
        IUserDao proxy = (IUserDao) new CLIBProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}
