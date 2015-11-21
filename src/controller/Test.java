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
import javax.xml.rpc.ServiceException;

import model.Porudzbina;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
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
		
		Porudzbina porudzbina;
		ArrayList<ArrayList<String>> stavke;
		String action = request.getParameter("action");
		
		String message = "";
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
				session.setAttribute("stavke", stavke);
			}
			session.setAttribute("stavke", stavke);
		
		}
		
		if(action.equals("Dodaj")){
			
			stavke = (ArrayList<ArrayList<String>>) session.getAttribute("stavke");
			ArrayList<String> stavka = new ArrayList<String>();
			
			int redniBroj = Integer.parseInt(request.getParameter("redniBroj"));
			int kolicina = Integer.parseInt(request.getParameter("kolicina"));
			int sifraProizvoda = Integer.parseInt(request.getParameter("sifraProizvoda"));
			porudzbina.dodajStavku(redniBroj, kolicina, sifraProizvoda);
			
			stavka.add(String.valueOf(redniBroj));
			stavka.add(String.valueOf(kolicina));
			stavka.add(String.valueOf(sifraProizvoda));
			stavka.add("insert");
			stavke.add(stavka);
			
			
			for(int i=0;i<stavke.size();i++){
				message = message + stavke.get(i).get(i).toString();
			}
			
			session.setAttribute("porudzbina", porudzbina);
			session.setAttribute("stavke", stavke);
			
		} else if(action.equals("Obrisi")){
			session.removeAttribute("porudzbina");
			session.removeAttribute("stavke");
			session.removeAttribute("sveStavke");
		}
		
		request.setAttribute("message", message);
		
		
		//String url = "/index.jsp";
		String url = "/porudzbina.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
