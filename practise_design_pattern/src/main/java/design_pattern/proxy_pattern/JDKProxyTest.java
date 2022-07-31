package design_pattern.proxy_pattern;

public class JDKProxyTest {
    public static void main(String[] args){
        IUserDao target = new UserDao();
        IUserDao proxy = (IUserDao) new UserProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}
