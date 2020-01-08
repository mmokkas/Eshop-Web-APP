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

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("add");
		String sql1 = "select * from Category";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
			if (name!=null) {
				String sql2 = "insert into Category(name) values (?)";
				PreparedStatement ps2= conn.prepareStatement(sql2);
				ps2.setString(1,name);
				ps2.executeUpdate();
			}
			PreparedStatement ps = conn.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
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
					"	  <form method=\"post\" action=\"AdminUploads\">\r\n"+
					"	    <button>See Uploads</button>\r\n" +  
					"     </form>"+
					"	  <form method=\"post\" action=\"AdminTransactions\">\r\n"+
					"	    <button>See Transactions</button>\r\n" +  
					"     </form>"+
//					"	<form method=\"post\" action=\"Logout\">\r\n" + 
//					"		<button value=\"Logout\">Logout</button\r\n" + 
//					"	</form>"+
					"	 </div>\r\n"+
					"	<h1 id=\"MainPara\">\r\n" + 
					"		ADMIN Page\r\n" + 
					"	</h1>\r\n" + 
					"	<div class=\"container\">\r\n" + 
					"	  	<form method=\"post\" action=\"Admin\">\r\n" + 
					"		  	<div class=\"row\">\r\n" + 
					"			    <div class=\"col-25\">\r\n" + 
					"			      <label for=\"category\">Existing Categories:</label>\r\n" + 
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
			out.print("</select>\r\n" + 
					"			    </div>\r\n" + 
					"		  	</div>\r\n" + 
					"		  	<div class=\"row\">\r\n" + 
					"			    <div class=\"col-25\">\r\n" + 
					"			      	<label for=\"add \">Add Category:</label>\r\n" + 
					"			    </div>\r\n" + 
					"			    <div class=\"col-75\">\r\n" + 
					"			      	<textarea id=\"add\" name=\"add\" style=\"width:200px; height: 20px; \"></textarea>\r\n" + 
					"			    </div>\r\n" + 
					"		  	</div>\r\n" + 
					"		  	<div class=\"row\">\r\n" + 
					"		    	<input type=\"submit\" value=\"Submit\">\r\n" + 
					"		  	</div>\r\n" + 
					"		</form>\r\n" + 
					"	</div>\r\n" + 
					"\r\n" + 
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


