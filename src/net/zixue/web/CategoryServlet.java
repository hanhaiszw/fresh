package net.zixue.web;

import net.zixue.bean.Category;
import net.zixue.bean.Page;
import net.zixue.service.CategoryService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {

    public void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String[]> parameterMap = req.getParameterMap();
            Category category = new Category();
            BeanUtils.populate(category, parameterMap);
            category.setCreatetime(new Date());
            //
            CategoryService categoryService = new CategoryService();
            boolean addCategory = categoryService.addCategory(category);
            if (addCategory) {
                // 添加成功
                resp.setStatus(201);
                // 请求转发到指定界面
                req.getRequestDispatcher("/category-add.jsp").forward(req, resp);
            } else {
                // 添加失败
                resp.setStatus(600);
                req.getRequestDispatcher("/category-add.jsp").forward(req, resp);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getCategoryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用service中的查询方法
        try {
            // 当前页和当前页显示的数目 由前端传过来的
            int currentPage = 0;
            int currentCount = 0;
            try {
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
                currentCount = Integer.parseInt(req.getParameter("currentCount"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            if (currentCount == 0) {
                currentCount = 10;
            }
            if (currentPage == 0) {
                currentPage = 1;
            }
            CategoryService categoryService = new CategoryService();
            //List<Category> categoryList = categoryService.findCategory();

            Page page = categoryService.findPageCategory(currentPage, currentCount);


            if (page != null) {
                // 添加到list中
                req.setAttribute("page", page);
                req.getRequestDispatcher("/category-list.jsp").forward(req, resp);
            } else {
                // 没有拿到列表
                req.getRequestDispatcher("/category-list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String[]> parameterMap = req.getParameterMap();
            Category category = new Category();
            BeanUtils.populate(category,parameterMap);
            CategoryService categoryService=new CategoryService();
            boolean updateCategory = categoryService.updateCategory(category);
            if(updateCategory){
                // 修改成功后重定向到生鲜列表的界面
                resp.sendRedirect(req.getContextPath() + "/category?method=getCategoryList");
            }else{
                // 失败了直接提示
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("修改失败");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String[]> parameterMap = req.getParameterMap();
            Category category = new Category();
            BeanUtils.populate(category,parameterMap);
            CategoryService categoryService=new CategoryService();
            boolean updateCategory = categoryService.deleteCategory(category);
            if(updateCategory){
                // 删除成功后重定向到生鲜列表的界面
                // 本来就在此界面，会刷新一次
                resp.sendRedirect(req.getContextPath() + "/category?method=getCategoryList");
            }else{
                // 失败了直接提示
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("删除失败");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
