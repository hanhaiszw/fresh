package net.zixue.service;

import net.zixue.bean.Category;
import net.zixue.bean.Page;
import net.zixue.dao.CategoryDao;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    public boolean addCategory(Category category) throws SQLException {
        CategoryDao dao = new CategoryDao();
        boolean addCategory = dao.addCategory(category);
        return addCategory;
    }

    public List<Category> findCategory() throws SQLException {
        CategoryDao dao = new CategoryDao();
        List<Category> categoryList = dao.queryCategoryList();
        return categoryList;
    }


    public Page findPageCategory(int currentPage, int currentCount) throws SQLException {
        // 1 查询出生鲜数据总数
        CategoryDao dao = new CategoryDao();
        int totalCount = dao.queryCount();
        // 计算总页数    ceil 进一法
        int totalPage = (int) Math.ceil((double) totalCount / currentCount);
        Page page = new Page();
        page.setCurrentCount(currentCount);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);


        // 找出起始位置   当前页数减一 乘以  每页的显示数量
        int startPosition = (currentPage - 1) * currentCount;
        List<Category> categoryList = dao.queryPageCategoryList(startPosition,currentCount);
        page.setList(categoryList);

        return page;
    }

    public boolean updateCategory(Category category) throws SQLException {
        CategoryDao dao = new CategoryDao();
        boolean updateCategory = dao.updateCategory(category);
        return updateCategory;
    }

    public boolean deleteCategory(Category category) throws SQLException {
        CategoryDao dao = new CategoryDao();
        boolean updateCategory = dao.deleteCategory(category);
        return updateCategory;
    }
}
