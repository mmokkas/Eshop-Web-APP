package jdbc;
import javax.servlet.*;
import java.sql.*;
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
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
		try {
			String name = request.getParameter("user");
			String password = request.getParameter("password");
			String firstname = request.getParameter("firstname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			
			String sql1 = "insert into registration(user,password) values(?,?)";
			String sql2 = "insert into info(firstname,email,address,city,state,zip,user_sid) values(?,?,?,?,?,?,?)";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","2013mokkaki1998");
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1,name);
			ps.setString(2,password);
			ps.executeUpdate();
			

			PreparedStatement ps3 = conn.prepareStatement(sql2);
			ps3.setString(1,firstname);
			ps3.setString(2,email);
			ps3.setString(3,address);
			ps3.setString(4,city);
			ps3.setString(5,state);
			ps3.setString(6,zip);
			ps3.setString(7,name);
			PrintWriter out = response.getWriter();
			//out.println(rs.getString("sid"));
			ps3.executeUpdate();
			
			
			response.setContentType("text/html");
			
			 out = response.getWriter();
			out.println("<b>You have succesfully registered! Now login.</b>");
			request.getRequestDispatcher("login.html").include(request,response);
			
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException s) {
				PrintWriter out = response.getWriter();
				out.println("User already exists!!!");
				request.getRequestDispatcher("register.html").include(request,response);
	           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("You didnt register!");
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
