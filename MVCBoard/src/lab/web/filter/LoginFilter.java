package lab.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter({"/Board.do","/board/*"})
public class LoginFilter implements Filter {

	public LoginFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hres = (HttpServletResponse)response;
		HttpSession session = hreq.getSession();
		if(session.getAttribute("userid")==null){
			if(hreq.getParameter("action").equals("contact_do")) {

			}else {
				hres.sendRedirect("/MVC/login.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}



	public void init(FilterConfig fConfig) throws ServletException {

	}

}
