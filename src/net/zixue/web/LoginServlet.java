package net.zixue.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        System.out.println("name = " + name + ", password = " + password);
//        // 由Service层处理逻辑
//        UserService userService = new UserService();
//        User user = null;
//        try {
//            user = userService.login(name, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (user != null) {
//            // 获取记住密码控件的状态
//            String remember = request.getParameter("remember");
//            if (remember!=null&&remember.equals("yes")) {
//                // 通过cookie记录密码
//                Cookie nameCookie = new Cookie("name", name);
//                Cookie passwordCookie = new Cookie("password", password);
//                // 持久化
//                nameCookie.setMaxAge(60 * 10);// 10分钟
//                passwordCookie.setMaxAge(60 * 10);
//                // 将cookie发送给客户端保存  然后在login的jsp中取出
//                response.addCookie(nameCookie);
//                response.addCookie(passwordCookie);
//            }
//            // 登录成功  跳转到生鲜的分类页面
//            response.sendRedirect(request.getContextPath() + "/category-list.jsp");
//        } else {
//            // 失败
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write("用户登录失败");
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
