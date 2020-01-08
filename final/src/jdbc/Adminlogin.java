package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Adminlogin")
public class Adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Adminlogin() {
        super();
      
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("user");
			String password = request.getParameter("password");
			String sql = "select * from registration where user=? and password=? and admin=1";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Cookie ck = new Cookie("user",name);
				ck.setMaxAge(60*3);
				response.addCookie(ck);
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
						"       <form method=\"post\" action=\"Admin\">"
						+ "			<button type=\"submit\">Add Categories</button>"
						+ "		</form>      "+
						"       <form method=\"post\" action=\"AdminUploads\">"
						+ "			<button type=\"submit\">Uploads</button>"
						+ "		</form>      "+
						"       <form method=\"post\" action=\"AdminTransactions\">"
						+ "			<button type=\"submit\">Transactions</button>"
						+ "		</form>      "+
					    "</body>\r\n" + 
						"</html>");
				
			}else {
				
				PrintWriter out = response.getWriter();
				out.println("You are not registered!");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("Admin login fail!");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}