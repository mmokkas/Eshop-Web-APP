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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Checkout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prod_id =request.getParameter("buy");
		int del =Integer.parseInt(prod_id);
		
		Cookie[] ck = request.getCookies();
		if( ck== null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<link rel=\"stylesheet\"  href=\"final/WebContent/style.css\" />"
					+ "<h2 id="+"MessagePara"+">You must login first!</h2>");
			request.getRequestDispatcher("index.html").include(request,response);
			
		}else {
			String name=ck[0].getValue();
			String sql = "insert into transactions(date,user_sid,product_id) values(?,?,?)";
			String sql2 ="UPDATE testprod SET flag = 1 where prodid=?";
			
			
			
			Connection conn;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");

				PreparedStatement ps = conn.prepareStatement(sql);
				java.util.Date dt = new java.util.Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				ps.setString(1,currentTime);
				ps.setString(2, name);
				ps.setInt(3, del);
				
				ps.executeUpdate();
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, del);
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
						"		<h2 id=\"MainPara\">The owner will be informed and he will contact you!<h2>"+
						"       <form method=\"post\" action=\"Buy\">"
						+ "			<button type=\"submit\">Continue</button>"
						+ "		</form>     "+
					    "</body>\r\n" + 
						"</html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
