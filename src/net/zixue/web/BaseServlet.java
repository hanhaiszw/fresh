package net.zixue.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 框架会帮助生成BaseServlet，实际开发时可以直接使用框架的
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题
        req.setCharacterEncoding("utf-8");

        try {
            // 1、获取方法名
            String method = req.getParameter("method");
            if (method == null) {
                method = "getCategoryList";
            }
            // 2、拿到类的字节码文件
            Class clazz = this.getClass();
            // 3、拿到字节码对象中的方法
            // 使用反射找到要调用的方法
            Method classMethod = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            // 4、执行方法
            classMethod.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
