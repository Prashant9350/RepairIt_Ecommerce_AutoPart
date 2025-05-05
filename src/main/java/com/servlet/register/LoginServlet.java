package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/logindb")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			PrintWriter out = resp.getWriter();
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql:///project_repairit","root","Prashant@1887");
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			PreparedStatement ps = con.prepareStatement("select username from register where username=? and password=?");
			
			//set the value 
			ps.setString(1, username); 
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				RequestDispatcher rd = req.getRequestDispatcher("LIndex.html");
				rd.forward(req, resp);
			}
			else {
				out.println("<font color = red size=5 >Login Failed!!<br>");
				out.println("<font color = blue size=5 >Try Again!! <br> <a href=LoginPage.html>Click Here</a>");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

       
   }
