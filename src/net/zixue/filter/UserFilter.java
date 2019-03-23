package net.zixue.filter;

import net.zixue.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpExchange;
import java.io.IOException;

// 需要过滤category 的servlet
@WebFilter(filterName = "UserFilter", urlPatterns = "/category")
public class UserFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       // 解决乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 检查有没有用户信息
        // ServletRequest中没有getSession方法，需要强转下类型
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 判断user是否为空  如果为null，说明没有登录
        if (user == null) {
            // 跳转到登录界面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        // 继续执行
        // user不为空，放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
