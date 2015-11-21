package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasebroker.DatabaseBroker;

/**
 * Servlet implementation class SviDobavljaci
 */
@WebServlet("/svidobavljaci")
public class SviDobavljaci extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SviDobavljaci() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		DatabaseBroker dbbr = new DatabaseBroker();
		
		dbbr.otvoriBazu();
		ArrayList<String> dobavljaci = dbbr.vratiDobavljace();
		dbbr.zatvoriBazu();
		
		out.println("<select name='sviDobavljaci' id='sviDobavljaci' class='form-control text-center'>");
		out.println("<option value='0'></option>");

		for(String dobavljac:dobavljaci){
			int dobID = Integer.valueOf(dobavljac.substring(0, dobavljac.indexOf(" ")));
			if(dobID==1)
			              out.println("<option value='" + dobID+ "'selected>" + dobavljac.toString() + "</option>");
			else
			              out.println("<option value='" + dobID+ "'>" + dobavljac.toString() + "</option>");
		}
		out.println("</select>");
		
	}

}
