package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Sell")
public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Sell() {
        super();
      
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//String name = request.getParameter("user");
			//String password = request.getParameter("password");
			String sql = "select * from Category";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			response.setContentType("text/html");
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
						"    <div class=\"navbar\">\r\n" + 
						"	  <a href=\"index.html\">Home</a>\r\n" + 
						"	  <form method=\"post\" action=\"Sell\">\r\n"+ 
						"		<button>Sell Items</button>\r\n" + 
						"	  </form>\r\n"+
						"     <form method=\"post\" action=\"Buy\">\r\n"+
						"	    <button>Buy Items</button>\r\n" +
						"	  </form>"+
						"	    <div class=\"login\">\r\n" + 
						"	    	<a href=\"register.html\">Sign Up</a>\r\n" + 
						"	    	<a href=\"login.html\">Sign In</a>\r\n" + 
						"	    </div>\r\n" + 
						"	  \r\n" + 
						"	 </div>\r\n" + 
						"    <div class=\"container\">\r\n" + 
						"	  <form  method=\"post\" action=\"SellServlet\">\r\n" + 
						"	  \r\n" + 
						"		  <div class=\"row\">\r\n" + 
						"			    <div class=\"col-25\">\r\n" + 
						"			      <label for=\"category\">Choose Category</label>\r\n" + 
						"			    </div>\r\n" + 
						"			    <div class=\"col-75\">\r\n" + 
						"			      	<select id=\"category\" name=\"category\">\r\n");  
						while(rs.next()) {
							
							out.println("<option value=\"");
							out.println(rs.getString(2));
							out.println("\">");
							out.println(rs.getString(2));
							out.println("</option>");
						}
						out.println("			      	</select>\r\n" + 
						"			    </div>\r\n" + 
						"		  </div>\r\n" + 
						"		  <div class=\"row\">\r\n" + 
						"			    <div class=\"col-25\">\r\n" + 
						"			      	<label for=\"describe\">What are you selling?</label>\r\n" + 
						"			    </div>\r\n" + 
						"			    <div class=\"col-75\">\r\n" + 
						"			      	<input type=\"text\" id=\"title\" placeholder=\"Write a title for your product...\" name=\"title\" required style=\"width:200px; height: 20px; \"></input>\r\n" + 
						"			    </div>\r\n" + 
						"		  </div>\r\n" + 
						"		  <div class=\"row\">\r\n" + 
						"			    <div class=\"col-25\">\r\n" + 
						"			      	<label for=\"describe\">Price</label>\r\n" + 
						"			    </div>\r\n" + 
						"			    <div class=\"col-75\">\r\n" + 
						"			      	<input id=\"price\" name=\"price\" style=\"width:100px; height: 20px; \">\r\n" + 
						"			    </div>\r\n" + 
						"		  </div>"+
						"		  <div class=\"row\">\r\n" + 
						"			    <div class=\"col-25\">\r\n" + 
						"			      	<label for=\"describe\">Description</label>\r\n" + 
						"			    </div>\r\n" + 
						"			    <div class=\"col-75\">\r\n" + 
						"			      	<input type=\"text\" id=\"describe\" name=\"describe\" placeholder=\"Write some details for your product...\" required style=\"width:500px; height: 200px; required\"></input>\r\n" + 
						"			    </div>\r\n" + 
						"		  </div>\r\n" + 
						"		  <div class=\"row\">\r\n" + 
						"		    	<input type=\"submit\" value=\"Submit\">\r\n" + 
						"		  </div>\r\n" + 
						"	  </form>\r\n" + 
						"</div>\r\n" + 
						"\r\n" + 
						"</body>\r\n" + 
						"</html>");
				
		} catch (SQLException e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("You didnt upload!");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
