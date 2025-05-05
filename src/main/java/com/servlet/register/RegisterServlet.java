package com.servlet.register;

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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	//create the query
	private static final String INSERT_QUERY = "INSERT INTO REGISTER(NAME,USERNAME," + "EMAIL,PHONE,DOB,PASSWORD) VALUES(?,?,?,?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		//read the form values
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String dob = req.getParameter("dob");
		String password = req.getParameter("password");
		
		//load the jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//create the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///project_repairit", "root","Prashant@1887");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
					
			//set the value
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setString(5, dob);
			ps.setString(6, password);
			
			//executive the query
			int count = ps.executeUpdate();
			if(count==0) {
				pw.println("<br><br> <center><h2> Record is not updated. </h2> <br><br> <h2><a href='SignupPage.html'>Click Here</h2> </center>");
			}else {
				pw.println("<br><br> <center><h2> Thank You for Registering in RepairIt</h2> <br><br> <h2><a href='LoginPage.html'>Click Here</h2> </center>");
				//Record is update sucessfully.
			}
		}catch(SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		}catch(Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		
		//closed the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 	ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

