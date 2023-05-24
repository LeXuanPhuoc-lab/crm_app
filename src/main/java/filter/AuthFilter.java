package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        req.setCharacterEncoding("UTF-8");

        boolean isAdmin = (session.getAttribute("admin")!=null) ? true : false;
        boolean isUser = (session.getAttribute("user")!=null) ? true: false;

        boolean url1 = req.getServletPath().startsWith("/user/add") ? true : false;
        boolean url2 = req.getServletPath().startsWith("/project/add") ? true : false;
        boolean url3 = req.getServletPath().startsWith("/role/add") ? true : false;
        boolean url4 = req.getServletPath().startsWith("/work/add") ? true : false;

        if(!req.getServletPath().startsWith("/login")){
            if(isAdmin || isUser){
                if((url1 || url2 || url3 || url4) && isAdmin){
                    filterChain.doFilter(req,resp);
                }else if((url1 || url2 || url3 || url4) && isUser){
                    req.getRequestDispatcher("/404.jsp").forward(req,resp);
                }else{
                    filterChain.doFilter(req,resp);
                }
            }else{
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }else{
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
