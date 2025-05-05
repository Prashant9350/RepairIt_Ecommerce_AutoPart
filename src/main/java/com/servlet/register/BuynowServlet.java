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


@WebServlet("/buynowdb")
public class BuynowServlet extends HttpServlet {
private static final String INSERT_QUERY = "INSERT INTO BUYNOWDB(FULL_NAME,EMAIL,ADDRESS,CITY,STATE,PHONE,ZIPCODE) VALUES(?,?,?,?,?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		//read the form values
		String full_name = req.getParameter("full_name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String phone = req.getParameter("phone");
		String zipcode = req.getParameter("zipcode");
		
		//load the jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//create the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///project_repairit","root","Prashant@1887");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
					
			//set the value
			ps.setString(1, full_name);
			ps.setString(2, email);
			ps.setString(3, address);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, phone);
			ps.setString(7, zipcode);
			
			//executive the query
			int count = ps.executeUpdate();
			if(count==0) {
				pw.println("<br><br> <center><h2> Shipment is not done.<br> Please try again... </h2> <br><br> <h2><a href='BuyNow.html'>Click Here</h2> </center>");
			}else {
				pw.println("<br><br> <center><h2> Your Shipment is Done <br>Once the Shipment ready, we will share with you shipment AWB (AirWayBill).<br>( AWB received on your provided phone number & Email )<br> -RepairIt</h2> <br> <h2><a href='LAutoPart.html'>Click Here</h2> </center>");
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}


}
