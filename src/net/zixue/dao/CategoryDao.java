package net.zixue.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.zixue.bean.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    public boolean addCategory(Category category) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "insert into category values(null,?,?,?,?)";
        int row = queryRunner.update(sql, category.getC_name(), category.getPlace(), category.getCreatetime(), category.getType());
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Category> queryCategoryList() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select * from category";
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
        return categoryList;
    }

    public List<Category> queryPageCategoryList(int startPosition, int currentCount) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select * from category limit ?,?";  // 起始位置和当前页的显示数据量
        List<Category> categoryList = queryRunner.query(sql,
                new BeanListHandler<Category>(Category.class), startPosition, currentCount);
        return categoryList;
    }


    public int queryCount() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select count(*) from category";  // 查询总的数目
        Long query = queryRunner.query(sql, new ScalarHandler<>());// scalar用来接收聚合函数的返回值
        return query.intValue();
    }

    public boolean updateCategory(Category category) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "update category set c_name=?,place=?,type=? where c_id=?";
        int row = queryRunner.update(sql, category.getC_name(), category.getPlace(),category.getType(),category.getC_id());
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }


    // 删除生鲜条目
    public boolean deleteCategory(Category category) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "delete from category where c_id=?";
        int row = queryRunner.update(sql, category.getC_id());
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
}
