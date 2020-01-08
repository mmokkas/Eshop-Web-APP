package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/SellServlet")
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SellServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter("title");
			String describe = request.getParameter("describe");
			String category = request.getParameter("category");
			String price = request.getParameter("price");
			String image = request.getParameter("image");
			
			Cookie[] ck = request.getCookies();
			if( ck== null) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<link rel=\"stylesheet\"  href=\"final/WebContent/style.css\" />"
						+ "<h2 id="+"MessagePara"+">You must login first!</h2>");
				request.getRequestDispatcher("index.html").include(request,response);
				
			}else {
				String name=ck[0].getValue();
				String sql = "insert into testprod(title,price,description,category,flag) values (?,?,?,?,0)";
				String sql2 = "select * from testprod where title=? and price=?";
				String sql3 = "insert into upload(user_sid,product_pid,date) values (?,?,?)";
				
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
				PreparedStatement ps = conn.prepareStatement(sql);
				
				
				
				ps.setString(1,title);
				ps.setString(2, price);
				ps.setString(3, describe);
				ps.setString(4, category);
				ps.executeUpdate();
				
				
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1,title);
				ps2.setString(2, price);
				ResultSet rs = ps2.executeQuery();
				int pid =5;
				if(rs.next()) {
					pid = rs.getInt("prodid");
				}
				java.util.Date dt = new java.util.Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setString(1,name);
				ps3.setInt(2,pid);
				ps3.setString(3, currentTime);
				ps3.executeUpdate();
				request.getRequestDispatcher("index.html").include(request,response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("You didnt upload!");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
