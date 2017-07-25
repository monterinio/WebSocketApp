package pl.pwr.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	public AdminLoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (USERNAME.equals(username) && PASSWORD.equals(password)) {
			request.getSession(true).setAttribute("username", username);
			response.sendRedirect("admin/index.html");
		}
		else {
			response.sendRedirect("/Test2/error/error.html");
		}
	}

}
