package net.zixue.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.zixue.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    // 1 判断注册的用户名是否存在
    // 如果存在则返回false，不存在则true
    public boolean checkUser(String name) {
        try {
            // c3p0处理
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "select name from user where name=?";   // 传参
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class), name);
            if (user == null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 注册用户
    public boolean register(String name, String password, String email) {
        try {
            // c3p0处理
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "insert into user values(null,?,?,?)";
            int row = queryRunner.update(sql, name, password, email);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String name, String password) throws SQLException {
        // c3p0处理
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select * from user where name=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), name, password);
        return user;
    }
}
