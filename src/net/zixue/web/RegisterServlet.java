package net.zixue.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 获取参数
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//
//        // 业务逻辑由service层来处理
//        UserService userService = new UserService();
//
//        User user = new User();
//        // 拿到参数map集合
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        try {
//            // 设置对象属性值
//            // BeanUtils.setProperty(user,"name",name);
//            // 将属性的map集合封装到对象中
//            BeanUtils.populate(user,parameterMap);
//
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        //boolean register = userService.register(name, password, email);
//        boolean register = userService.register(user);
//        if(register){
//            // 注册成功   跳转到登录界面
//            response.sendRedirect(request.getContextPath()+"/login.jsp");
//        }else{
//            // 失败
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write("注册失败");
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
