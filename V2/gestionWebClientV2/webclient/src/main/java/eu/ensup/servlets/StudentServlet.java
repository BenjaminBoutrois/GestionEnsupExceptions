package eu.ensup.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ensup.service.StudentService;
import eu.ensup.service.IStudentService;

/**
 * Servlet implementation class EtudiantServlet
 */
public class StudentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private IStudentService studentService;
	private RequestDispatcher dispatcher = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet()
	{
		studentService = new StudentService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		session.setAttribute("student", null);

		session.setAttribute("students", studentService.getAllStudents());

		dispatcher = request.getRequestDispatcher("etudiant.jsp");
		dispatcher.forward(request, response);
	}
}
