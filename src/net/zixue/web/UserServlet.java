package net.zixue.web;

import net.zixue.bean.User;
import net.zixue.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String method = request.getParameter("method");
//        if("login".equals(method)){
//            login(request,response);
//        }else if("register".equals(method)){
//            register(request,response);
//        }
//    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("name = " + name + ", password = " + password);
        // 由Service层处理逻辑
        UserService userService = new UserService();
        User user = null;
        try {
            user = userService.login(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            // 获取记住密码控件的状态
            String remember = request.getParameter("remember");
            if (remember!=null&&remember.equals("yes")) {
                // 通过cookie记录密码
                Cookie nameCookie = new Cookie("name", name);
                Cookie passwordCookie = new Cookie("password", password);
                // 持久化
                nameCookie.setMaxAge(60 * 10);// 10分钟
                passwordCookie.setMaxAge(60 * 10);
                // 将cookie发送给客户端保存  然后在login的jsp中取出
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
            }
            // 把用户信息保护到session中，用以权限控制
            // 防止用户在没有登录的情况下修改生鲜信息
            request.getSession().setAttribute("user",user);
            // 登录成功  跳转到生鲜的分类页面
            response.sendRedirect(request.getContextPath() + "/category?method=getCategoryList");
        } else {
            // 失败
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户登录失败");
        }
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // 业务逻辑由service层来处理
        UserService userService = new UserService();

        User user = new User();
        // 拿到参数map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            // 设置对象属性值
            // BeanUtils.setProperty(user,"name",name);
            // 将属性的map集合封装到对象中
            BeanUtils.populate(user,parameterMap);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //boolean register = userService.register(name, password, email);
        boolean register = userService.register(user);
        if(register){
            // 注册成功   跳转到登录界面
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }else{
            // 失败
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }

    }
}
