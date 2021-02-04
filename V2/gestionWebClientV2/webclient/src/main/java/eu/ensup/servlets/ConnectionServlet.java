package eu.ensup.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ensup.domaine.User;
import eu.ensup.service.UserService;
import eu.ensup.service.IUserService;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Connexion")
public class ConnectionServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private RequestDispatcher dispatcher = null;

	/**
	 * Default constructor.
	 */
	public ConnectionServlet()
	{
		userService = new UserService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dispatcher = request.getRequestDispatcher("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		connect(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void connect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		try
		{
			User user = userService.getUser(request.getParameter("login"), request.getParameter("password"));
			
			// page de retour
			if (user != null && user.getLogin().equalsIgnoreCase(request.getParameter("login"))
					&& user.getPassword().equalsIgnoreCase(request.getParameter("password")))
			{
				dispatcher = request.getRequestDispatcher("home.jsp");
				session.setAttribute("user", user);
			}
			else
			{
				dispatcher = request.getRequestDispatcher("index.jsp");
				session.setAttribute("error", "Login et/ou mot de passe incorrect(s).");
			}
		}
		catch(Exception e)
		{
			// TODO beboutro : Ajouter gestion exceptions
			dispatcher = request.getRequestDispatcher("index.jsp");
			session.setAttribute("error", "Impossible de joindre le service distant et/ou la base de données.");
		}

		dispatcher.forward(request, response);
	}
}
