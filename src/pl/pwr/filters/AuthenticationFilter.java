package pl.pwr.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
 		
		if(session != null && session.getAttribute("username") != null) {
			chain.doFilter(httpRequest, response);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("/Test2/validationError/validationError.html");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
