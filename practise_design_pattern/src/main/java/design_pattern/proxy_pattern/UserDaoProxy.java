package design_pattern.proxy_pattern;

public class UserDaoProxy implements IUserDao{
    //被代理对象
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target = target;
    }

    public void save(){
        System.out.println("开启事务");
        target.save();
        System.out.println("提交事务");
    }
}
