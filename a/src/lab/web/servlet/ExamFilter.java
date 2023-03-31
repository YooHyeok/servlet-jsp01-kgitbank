package lab.web.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/Filter.do")
public class ExamFilter implements Filter {


    public ExamFilter() {
    }

	
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("필터 실행됨");
		System.out.println(request.getAttribute("message"));
		chain.doFilter(request, response);
		System.out.println("응답시 필터 실행");
		System.out.println(request.getAttribute("message"));
	}




}
