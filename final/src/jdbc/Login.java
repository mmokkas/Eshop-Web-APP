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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
      
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("user");
			String password = request.getParameter("password");
			String sql = "select * from registration where user=? and password=?";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			response.setContentType("text/html");
			
			if(rs.next()) {
				Cookie ck = new Cookie("user",name);
				ck.setMaxAge(20*60);
				response.addCookie(ck);
				PrintWriter out = response.getWriter();
				out.println("<h3 id="+"MessagePara"+">You have succesfully Loged In! Automatic logout after 20 mins.Enjoy!</h3>");
				request.getRequestDispatcher("index.html").include(request,response);
				
			}else {
				
				PrintWriter out = response.getWriter();
				out.println("You are not registered!");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("You didnt register 2!");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
