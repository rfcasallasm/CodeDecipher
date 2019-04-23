package co.bitcarpentry.codedecipher.view.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bitcarpentry.codedecipher.logic.CodeDecipherFacade;

/**
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProject")
public class CreateProjectServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProjectServlet() {
        super();
    }
    
    private String localPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.localPath = System.getProperty("java.io.tmpdir");
		super.init(config);
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String urlGit = request.getParameter("url");
		if(urlGit == null || urlGit.isEmpty() ) {
			response.sendRedirect(request.getContextPath()+ "/index.jsp");
			return;
		}
		else {
			URI uriGit;
			try {
				uriGit = new URI(urlGit);
			} catch (URISyntaxException e) {
				response.sendRedirect(request.getContextPath()+ "/index.jsp");
				return;
			}
			CodeDecipherFacade codeDecipherFacade = new CodeDecipherFacade(urlGit.substring(urlGit.lastIndexOf('/'), urlGit.lastIndexOf('.')));
			codeDecipherFacade.init();
			codeDecipherFacade.loadSource(uriGit);
			codeDecipherFacade.extractSource();
		}
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
