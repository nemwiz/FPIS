package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dobavljac;
import databasebroker.DatabaseBroker;

/**
 * Servlet implementation class DobavljacKontrolor
 */
@WebServlet("/dobavljackontrolor")
public class DobavljacKontrolor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobavljacKontrolor() {
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
		
		Dobavljac dobavljac = new Dobavljac();
		DatabaseBroker dbbr = new DatabaseBroker();
		String action = request.getParameter("action");
		String message = "";
		int rezultat = 0;
		int rezultatTransakcije = 0;
		
		if(action.equalsIgnoreCase("Trazi")){
			
			int sifraDobavljacaPretraga = Integer.parseInt(request.getParameter("sifraDobavljacaPretraga"));
			
			dbbr.otvoriBazu();
			dobavljac = dbbr.nadjiDobavljaca(sifraDobavljacaPretraga);
			dbbr.zatvoriBazu();
			
			request.setAttribute("sifraDobavljaca", dobavljac.getSifraDobavljaca());
			request.setAttribute("nazivDobavljaca", dobavljac.getNazivDobavljaca());
			
			message = "Dobavljac sa sifrom " + dobavljac.getSifraDobavljaca() + " je uspesno ucitan.";
			rezultat = 1;
			
		} else if(action.equals("Dodaj novog dobavljaca")){
			
			String nazivDobavljacaUnos = request.getParameter("nazivDobavljacaUnos");
			
			dobavljac.setNazivDobavljaca(nazivDobavljacaUnos);
			dobavljac.setStatus("insert");
			
			dbbr.otvoriBazu();
			try {
				rezultatTransakcije = dbbr.snimiDobavljaca(dobavljac);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = "Doslo je do greske prilikom unosa dobavljaca. Greska " + e.getMessage();
				rezultatTransakcije = 2;
				rezultat = 2;
			}
			
			dobavljac = dbbr.nadjiDobavljaca(0);
			request.setAttribute("sifraDobavljaca", dobavljac.getSifraDobavljaca());
			request.setAttribute("nazivDobavljaca", dobavljac.getNazivDobavljaca());
			message = "Nov dobavljac sa sifrom " + dobavljac.getSifraDobavljaca() + " je uspesno snimljen.";
			rezultat = 1;
			
		} else if(action.equals("Izmeni dobavljaca")){
			
			dobavljac.setSifraDobavljaca(Integer.parseInt(request.getParameter("sifraDobavljaca")));
			dobavljac.setNazivDobavljaca(request.getParameter("nazivDobavljaca"));
			dobavljac.setStatus("update");
			
			dbbr.otvoriBazu();
			try {
				rezultatTransakcije = dbbr.snimiDobavljaca(dobavljac);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = "Doslo je do greske prilikom izmene dobavljaca. Greska " + e.getMessage();
				rezultatTransakcije = 2;
				rezultat = 2;
			}
			
			message = "Dobavljac sa sifrom " + dobavljac.getSifraDobavljaca() + " je uspesno izmenjen";
			rezultat = 1;
			
		} else if(action.equals("Obrisi dobavljaca")){
			
			dobavljac.setSifraDobavljaca(Integer.parseInt(request.getParameter("sifraDobavljaca")));
			dobavljac.setStatus("delete");
			
			dbbr.otvoriBazu();
			try {
				rezultatTransakcije = dbbr.snimiDobavljaca(dobavljac);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = "Doslo je do greske prilikom brisanja dobavljaca. Greska " + e.getMessage();
				rezultatTransakcije = 2;
				rezultat = 2;
			}
			
			message = "Dobavljac sa sifrom " + dobavljac.getSifraDobavljaca() + " je uspesno izbrisan";
			rezultat = 1;
			
		}
		
		switch(rezultatTransakcije){
		case 0:
			break;
		case 1:
			dbbr.commit();
			dbbr.zatvoriBazu();
			break;
		case 2:
			dbbr.rollback();
			dbbr.zatvoriBazu();
			break;
		}
		
		request.setAttribute("message", message);
		request.setAttribute("rezultat", rezultat);
		
		String url = "dobavljac.jsp";
		
		RequestDispatcher rq = request.getRequestDispatcher(url);
		rq.forward(request, response);
	}

}
