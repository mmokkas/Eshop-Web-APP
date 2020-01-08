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

@WebServlet("/AdminTransactions")
public class AdminTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public AdminTransactions() {
        super();
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String sql1 = "select * from transactions";
				String sql2 = "select * from testprod where prodid=?";
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
					PreparedStatement ps = conn.prepareStatement(sql1);
					ResultSet rs = ps.executeQuery();
					PrintWriter out = response.getWriter();
					out.print("<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"	<title>e-Thriftshop</title>\r\n" + 
							"	<link rel=\"stylesheet\"  href=\"style.css\" />\r\n" + 
							"</head>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"<body id=\"backmain\">\r\n" + 
							"    <div class=\"navbar\">\r\n" +  
							"	  <form method=\"post\" action=\"Admin\">\r\n"+
							"	    <button>Add Categories</button>\r\n" +  
							"     </form>"+
							"	  <form method=\"post\" action=\"AdminUploads\">\r\n"+
							"	    <button>See Uploads</button>\r\n" +  
							"     </form>"+
							"	  <form method=\"post\" action=\"Logout\">\r\n" + 
							
							"		<button value=\"Logout\">Logout</button>\r\n" + 
							
							"     </form>"+
							
							"	 </div>\r\n"+
							"	<h1 id=\"MainPara\">\r\n" + 
							"		ADMIN Page\r\n" + 
							"	</h1>\r\n");
					int id=0;
					PreparedStatement ps2=null;
					ResultSet rs2=null ;
					while(rs.next()) {
						
					    id=rs.getInt("product_id");
						
						ps2 = conn.prepareStatement(sql2);
						ps2.setInt(1, id);
						rs2 = ps2.executeQuery();
						
						
						out.println("<div class=\"box\">\r\n" + 
								"	 	<p class=\"title\">User:  ");
						out.print(rs.getString(3));
						out.print("<ul>"
								+ "<li>Date of transaction:   ");
						out.print(rs.getString(2));
						out.print("</li>\r\n" + 
								"	 	<li>Title:  ");
						if(rs2.next()) {
						out.print(rs2.getString(2));
						out.print("</li>\r\n" + 
								"	 	<li>Price:  ");
						out.print(rs2.getString(3));
						out.print("</li>\r\n" + 
								"	 	<li>Description:  ");
						out.print(rs2.getString(4));
						out.print("</li>\r\n" );
						}
						out.print("	 	<li>Transaction ID:");
						out.print(rs.getString(1));

						out.print("</li>\r\n"
								+ "</ul>"+
								"	 </div>");
					}
				   
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	}

}
