package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUpload
 */
@WebServlet("/DeleteUpload")
public class DeleteUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delete = request.getParameter("delete");
		int del=Integer.parseInt(delete);
		String sql1 = "delete from testprod where prodid=?";
		String sql2 = "delete from upload where product_pid=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
			PreparedStatement ps = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps.setInt(1, del);
			ps2.setInt(1, del);
			ps.executeUpdate();
			ps2.executeUpdate();
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"	<title>e-Thriftshop</title>\r\n" + 
					"	<link rel=\"stylesheet\"  href=\"style.css\" />\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<body id=\"backmain\">\r\n" + 
					"       <form method=\"post\" action=\"AdminUploads\">"
					+ "			<button type=\"submit\">Continue</button>"
					+ "		</form>      "+
				    "</body>\r\n" + 
					"</html>");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
