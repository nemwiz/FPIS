package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Porudzbina;
import databasebroker.DatabaseBroker;

/**
 * Servlet implementation class PorudzbinaKontrolor
 */
@WebServlet("/porudzbinakontrolor")
public class PorudzbinaKontrolor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PorudzbinaKontrolor() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Porudzbina porudzbina;
		DatabaseBroker dbbr = new DatabaseBroker();
		ArrayList<ArrayList<String>> stavke;
		String action = request.getParameter("action");
		String message = "";
		int rezultat = 0;
		int rezultatTransakcije = 0;
		
		int redniBroj;
		int kolicina;
		int sifraProizvoda;
		
		HttpSession session = request.getSession();
		
		if (session.isNew()) {
			
			porudzbina = new Porudzbina();
			session.setAttribute("porudzbina", porudzbina);
			stavke = new ArrayList<ArrayList<String>>();
			session.setAttribute("stavke", stavke);
		    
		} else {
			// Already created.
			
			porudzbina = (Porudzbina) session.getAttribute("porudzbina");
			
			if(porudzbina==null){
				porudzbina = new Porudzbina();
			}
			session.setAttribute("porudzbina", porudzbina);
			
			stavke = (ArrayList<ArrayList<String>>) session.getAttribute("stavke");
			
			if(stavke==null){
				stavke = new ArrayList<ArrayList<String>>();
			}
			session.setAttribute("stavke", stavke);
		
		}
		
		if(action.equals("Trazi")){
			
			int brojPorudzbinePretraga = Integer.parseInt(request.getParameter("brojPorudzbinePretraga"));
			
			dbbr.otvoriBazu();
			porudzbina = dbbr.nadjiPorudzbinu(brojPorudzbinePretraga);
			dbbr.zatvoriBazu();
			
			session.setAttribute("porudzbina", porudzbina);
			session.setAttribute("stavke", porudzbina.vratiStavkeKaoString(porudzbina.getStavke()));
			
			session.setAttribute("brojPorudzbine", porudzbina.getBrojPorudzbine());
			session.setAttribute("datum", porudzbina.getDatum());
			session.setAttribute("sifraDobavljaca", porudzbina.getSifraDobavljaca());
			session.setAttribute("nazivDobavljaca", porudzbina.getNazivDobavljaca());
			session.setAttribute("ukupnaVrednost", porudzbina.getUkupnaVrednost());
			
			message = "Porudzbina sa brojem " + porudzbina.getBrojPorudzbine() + " je uspesno ucitana";
			rezultat = 1;
			
		} else if(action.equals("Dodaj novu porudzbinu")){
			
			session.removeAttribute("porudzbina");
			session.removeAttribute("stavke");
			session.removeAttribute("brojPorudzbine");
			session.removeAttribute("datum");
			session.removeAttribute("sifraDobavljaca");
			session.removeAttribute("nazivDobavljaca");
			session.removeAttribute("ukupnaVrednost");
			
			porudzbina = new Porudzbina();
			stavke = new ArrayList<ArrayList<String>>();
			
			int sifraDobavljacaUnos = Integer.parseInt(request.getParameter("sviDobavljaci"));
			
			session.setAttribute("sifraDobavljaca", sifraDobavljacaUnos);
			session.setAttribute("porudzbina", porudzbina);
			session.setAttribute("stavke", stavke);
			
			message = "Nova porudzbina je ucitana u memoriju. Kliknite potvrdi da biste sacuvali izmene.";
			rezultat = 1;
			
		} else if(action.equals("Izmeni porudzbinu")){
			
			porudzbina.setBrojPorudzbine(Integer.parseInt(request.getParameter("brojPorudzbine")));
			porudzbina.setSifraDobavljaca(Integer.parseInt(request.getParameter("sifraDobavljaca")));
			porudzbina.setStatus("update");
			
			dbbr.otvoriBazu();
			try {
				rezultatTransakcije = dbbr.snimiPorudzbinu(porudzbina);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = "Doslo je do greske prilikom izmene porudzbine. Greska: " + e.getMessage();
				rezultatTransakcije = 2;
				rezultat = 2;
			}
			
			rezultat = 1;
			message = "Porudzbina sa brojem " + porudzbina.getBrojPorudzbine() + " je uspesno izmenjena. Ucitajte ponovo porudzbinu da biste videli izmene.";
			session.setAttribute("sifraDobavljaca", porudzbina.getSifraDobavljaca());
			
		} else if(action.equals("Obrisi porudzbinu")){
			
			porudzbina.setBrojPorudzbine(Integer.parseInt(request.getParameter("brojPorudzbine")));
			porudzbina.setStatus("delete");

			dbbr.otvoriBazu();
			try {
				rezultatTransakcije = dbbr.snimiPorudzbinu(porudzbina);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = "Doslo je do greske prilikom brisanja porudzbine. Greska: " + e.getMessage();
				rezultatTransakcije = 2;
				rezultat = 2;
			}
			
			rezultat = 1;
			message = "Porudzbina sa brojem " + porudzbina.getBrojPorudzbine() + " je uspesno obrisana.";
			
			session.removeAttribute("porudzbina");
			session.removeAttribute("stavke");
			session.removeAttribute("brojPorudzbine");
			session.removeAttribute("datum");
			session.removeAttribute("sifraDobavljaca");
			session.removeAttribute("nazivDobavljaca");
			session.removeAttribute("ukupnaVrednost");
			
			
		} else if(action.equals("Potvrdi novu porudzbinu")){
			
			porudzbina.setSifraDobavljaca(Integer.parseInt(request.getParameter("sifraDobavljaca")));
			porudzbina.setStatus("insert");
			
			dbbr.otvoriBazu();
			try {
				rezultatTransakcije = dbbr.snimiPorudzbinu(porudzbina);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = "Doslo je do greske prilikom unosa porudzbine. Greska: " + e.getMessage();
				rezultatTransakcije = 2;
				rezultat = 2;
			}
			
			rezultat = 1;
			message = "Porudzbina je uspesno snimljena";
			
		} else if(action.equals("Dodaj stavku")){
			
			ArrayList<String> stavka = new ArrayList<String>();
			
			if(porudzbina.getStavke().size()==0){
				redniBroj = 1;
			} else {
				redniBroj = porudzbina.getStavke().get(porudzbina.getStavke().size()-1).getRedniBroj() + 1;
			}
			
			kolicina = Integer.parseInt(request.getParameter("kolicina"));
			sifraProizvoda = Integer.parseInt(request.getParameter("sifraProizvoda"));
			porudzbina.dodajStavku(redniBroj, kolicina, sifraProizvoda);
			
			stavka.add(String.valueOf(redniBroj));
			stavka.add(String.valueOf(kolicina));
			stavka.add(String.valueOf(sifraProizvoda));
			stavka.add("insert");
			
			stavke.add(stavka);
			
			session.setAttribute("porudzbina", porudzbina);
			session.setAttribute("stavke", stavke);
			
			message = "Stavka je uspesno dodata.";
			rezultat = 1;
			
		} else if(action.equals("Ucitaj")){
			
			int redniBrojStavke = Integer.parseInt(request.getParameter("tableindex"))-1;
			
			request.setAttribute("redniBroj", stavke.get(redniBrojStavke).get(0));
			request.setAttribute("kolicina", stavke.get(redniBrojStavke).get(1));
			request.setAttribute("sifraProizvoda", stavke.get(redniBrojStavke).get(2));
			
			message = "Stavka sa rednim brojem " + stavke.get(redniBrojStavke).get(0) + " je uspesno ucitana";
			rezultat = 1;
			
			request.setAttribute("tableindex", redniBrojStavke);
			
		} else if(action.equals("Izmeni stavku")){
			
			redniBroj = Integer.parseInt(request.getParameter("redniBroj"));
			kolicina = Integer.parseInt(request.getParameter("kolicina"));
			sifraProizvoda = Integer.parseInt(request.getParameter("sifraProizvoda"));
			
			porudzbina.izmeniStavku(redniBroj, kolicina, sifraProizvoda);

			int tableindex = Integer.parseInt(request.getParameter("tableindex"));
			
			stavke.get(tableindex).set(1, String.valueOf(kolicina));
			stavke.get(tableindex).set(2, String.valueOf(sifraProizvoda));
			stavke.get(tableindex).set(3, "update");
			
			
			message = "Stavka je uspesno izmenjena. Kliknite izmeni porudzbinu da biste sacuvali izmene.";
			rezultat = 1;
			
		} else if(action.equals("Obrisi stavku")){
			
			int tableindex = Integer.parseInt(request.getParameter("tableindex"));
			
			redniBroj = Integer.parseInt(request.getParameter("redniBroj"));
			porudzbina.obrisiStavku(redniBroj);
			stavke.get(tableindex).set(3, "delete");
			
			message = "Stavka sa rednim brojem " + redniBroj + " je uspesno obrisana. Kliknite izmeni porudzbinu da biste sacuvali izmene.";
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
		
		String url = "/porudzbina.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
