package net.zixue.service;

import net.zixue.bean.User;
import net.zixue.dao.UserDao;

import java.sql.SQLException;


public class UserService {
    // 1 判断注册的用户名是否存在
    // 如果存在则返回false，不存在则注册
    public boolean register(String name, String password, String email) {
        // 交给dao层查询数据库
        UserDao userDao = new UserDao();
        boolean checkUser = userDao.checkUser(name);
        boolean register = false;
        if (checkUser) {
            register = userDao.register(name, password, email);
        }
        return register;
    }
    public boolean register(User user){
        return register(user.getName(),user.getPassword(),user.getEmail());
    }

    // 登录
    public User login(String name, String password) throws SQLException {
        // 调用数据库
        UserDao userDao = new UserDao();
        User user = userDao.login(name, password);
        return user;
    }
}
