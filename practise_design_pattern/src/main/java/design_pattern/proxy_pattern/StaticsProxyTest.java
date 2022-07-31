package design_pattern.proxy_pattern;

public class StaticsProxyTest {
    public static void main(String[] args){
        IUserDao target = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();
    }

}
