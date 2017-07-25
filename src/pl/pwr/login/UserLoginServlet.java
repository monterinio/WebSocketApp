package pl.pwr.login;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, String> redirects = RedirectData.createConnectionRedirects();
	private static final String PASSWORD = "client";

	public UserLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	 String username = request.getParameter("username");
    	 String password = request.getParameter("password");
         
    	 String redirect = redirects.get(username);
    	 
    	 if(redirect == null) {
    		 response.sendRedirect("/Test2/error/error.html");
    	 }
    	 else {
	    	 if(PASSWORD.equals(password)) {
	    		 request.getSession(true).setAttribute("username", username);
	    		 response.sendRedirect(redirect);
	    	 }
	    	 else {
	    		 response.sendRedirect("/Test2/error/error.html");
	    	 }
    	 }
    }

}
