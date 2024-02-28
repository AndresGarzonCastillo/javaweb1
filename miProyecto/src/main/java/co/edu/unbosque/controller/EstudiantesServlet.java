package co.edu.unbosque.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import co.edu.unbosque.exceptions.ExcepcionesEstudiante;
import co.edu.unbosque.model.EstudianteDAO;
import co.edu.unbosque.model.EstudianteDAOMemoria;
import co.edu.unbosque.model.EstudianteDTO;
/**
 * Servlet implementation class EstudiantesServlet
 */
@WebServlet("/estudiantes")
public class EstudiantesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EstudianteDAO dao = new EstudianteDAOMemoria();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EstudiantesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null || action.equals("obtener")) {
			listStudents(request, response);
		} else if (action.equals("crear")) {
			showCreateForm(request, response);
		} else if (action.equals("editar")) {
			showEditForm(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        switch (action) {
            case "almacenar":
                createStudent(request, response);
                break;
            case "actualizar":
                updateStudent(request, response);
                break;
            case "eliminar":
                deleteStudent(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
	
	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<EstudianteDTO> estudiantes = dao.todosEstudiantes();
        request.setAttribute("estudiantes", estudiantes);
        request.getRequestDispatcher("/Lista.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Almacenar.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("identificacion");
        try {
            EstudianteDTO estudiante = dao.obtener(id);
            request.setAttribute("estudiante", estudiante);
            request.getRequestDispatcher("/Actualizar.jsp").forward(request, response);
        } catch (ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("identificacion");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apeliidos");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String correo = request.getParameter("correo");

        try {
            EstudianteDTO estudiante = new EstudianteDTO(id, nombres, apellidos, correo, edad);
            dao.almacenar(estudiante);
            response.sendRedirect("/estudiantes");
        } catch (ExcepcionesEstudiante.ExcepcionEstudianteExistente e) {
            response.sendError(HttpServletResponse.SC_CONFLICT, e.getMessage());
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("identificacion");
        String correo = request.getParameter("correo");

        try {
            EstudianteDTO estudianteExistente = dao.obtener(id);
            estudianteExistente.setCorreo(correo);
            dao.actualizar(estudianteExistente);
            response.sendRedirect("/estudiantes");
        } catch (ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("identificacion");

        try {
            dao.eliminar(id);;
            response.sendRedirect("/estudiantes");
        } catch (ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
}
