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


@WebFilter("/Board.do")
public class UserFilter implements Filter {

    public UserFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest)request;
		String action = hreq.getParameter("action");
		if(action.equals("update")||action.equals("delete")) {
			String loginId = (String)hreq.getSession().getAttribute("userid");
			if(!loginId.equals(hreq.getParameter("userid"))) {
				request.setAttribute("message", "본인 글이 아니면 수정 또는 삭제할 수 없습니다.");
				hreq.getRequestDispatcher("/error/error.jsp").forward(request, response);
				return;
				
			}
		}
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
