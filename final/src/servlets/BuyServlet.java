package servlets;

import java.io.File;
import java.io.FileOutputStream;
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

import com.mysql.jdbc.Blob;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BuyServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String category = request.getParameter("category");

			String sql = "select * from testprod where category=? and flag=0";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,category);
			ResultSet rs= ps.executeQuery();
			
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
					"	  <a href=\"index.html\">Home</a>\r\n" + 
					"	  <form method=\"post\" action=\"Sell\">\r\n"+ 
					"		<button>Sell Items</button>\r\n" + 
					"	  </form>\r\n"+
					"	  <form method=\"post\" action=\"Buy\">\r\n"+
					"	    <button>Buy Items</button>\r\n" +  
					"     </form>"+
					"	    <div class=\"login\">\r\n" + 
					"	    	<a href=\"register.html\">Sign Up</a>\r\n" + 
					"	    	<a href=\"login.html\">Sign In</a>\r\n" + 
					"	    </div>\r\n" + 
					"	 </div>\r\n");
			while(rs.next()) {
				
//						String fileName = "C:\\Users\\Michael\\eclipse-workspace\\final\\WebContent\\images\\mike.jpg";
						//File file = File.createTempFile("temp", null);
//						try(FileOutputStream fos = new FileOutputStream(fileName)){
//							Blob blob = (Blob) rs.getBlob("image");
//							int len = (int) blob.length();
//							byte[] buf=blob.getBytes(1, len);
//							fos.write(buf,0,len);
//						
//						}catch(IOException ex) {
//							
//						}
						out.println("<div class=\"box\">\r\n");
//								+ "<img src=\"" + request.getContextPath() + "\\images\\mike.jpg");
						//out.print(request.getContextPath());
						//out.print("/images/mike.jpg");
						//out.print(file.getAbsolutePath());
//						out.print("\" class=\"img\" height=\"150\" width=\"300\">" + 
						out.print("	 	<p class=\"title\">");
				out.print(rs.getString(2));
				out.print("</p>\r\n" + 
						"	 	<p>");
				out.print(rs.getString(4));
				out.print("</p>\r\n" + 
						"	 	<span class=\"price\">");
				out.print(rs.getString(3));
				out.print("$</span>\r\n"
						+ "<form method=\"post\"  action=\"Checkout\">"+
						"	 	<button name=\"buy\" value=\""+rs.getInt(1)
						+ "\">Buy</button>\r\n"
						+ "</form>" + 
						"	 </div>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("Search didnt work");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
