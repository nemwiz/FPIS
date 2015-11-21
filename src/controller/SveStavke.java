package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SveStavke
 */
@WebServlet("/svestavke")
public class SveStavke extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SveStavke() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<String>> stavke = (ArrayList<ArrayList<String>>) session.getAttribute("stavke");
		
		if(stavke!= null){
		out.println("<tr class='success'>");
		out.println("<th class='text-center'>Izaberi stavku</th>");
		out.println("<th class='text-center'>RedniBroj</th>");
		out.println("<th class='text-center'>Kolicina</th>");
		out.println("<th class='text-center'>SifraProizvoda</th>");
		out.println("<th class='text-center'>Status</th>");
		out.println("</tr>");
		for(ArrayList<String> stavka:stavke){
			out.println("<tr>");
			out.println("<td><input type='submit' name='action' class='btn btn-warning' data-toggle='tooltip' data-placement='top' title='Pritisnite da biste ucitali stavku u polja' value='Ucitaj'></td>");
			out.println("<td>"+stavka.get(0)+"</td>");
			out.println("<td>"+stavka.get(1)+"</td>");
			out.println("<td>"+stavka.get(2)+"</td>");
			out.println("<td>"+stavka.get(3)+"</td>");
			out.println("</tr>");
		}
		
		
	}

}
	
}
