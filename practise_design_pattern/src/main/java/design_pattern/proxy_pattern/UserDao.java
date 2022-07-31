package design_pattern.proxy_pattern;

public class UserDao implements IUserDao{
    public void save() {
        System.out.println("保存数据");
    }
}
