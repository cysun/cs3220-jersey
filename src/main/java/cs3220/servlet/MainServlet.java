package cs3220.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.util.DbUtils;

@WebServlet(urlPatterns = "/Main", loadOnStartup = 1)
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public MainServlet()
    {
        super();
    }

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        DbUtils dbUtils = new DbUtils();
        request.setAttribute( "files", dbUtils.getFiles() );
        dbUtils.close();

        request.getRequestDispatcher( "/WEB-INF/Main.jsp" ).forward( request,
            response );
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        doGet( request, response );
    }

}
