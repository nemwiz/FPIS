package databasebroker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Dobavljac;
import model.Porudzbina;
import model.StavkaPorudzbine;

public class DatabaseBroker {
	
	public Connection connection;
	public ResultSet results;
	
	public void otvoriBazu(){
		String url = "jdbc:oracle:thin://@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			this.connection = DriverManager.getConnection(url,"admin","oracle123");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Neuspela konekcija sa bazom " + e);
		}
	}
	
	public void zatvoriBazu(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom zatvaranja baze " + e);
		}
	}
	
	public void commit(){
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom commit operacije " + e);
		}
		
	}
	
	public void rollback(){
		try{
			connection.rollback();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom rollback operacije " + e);
		}
	}
	
	////Dobavljac
	
	public ArrayList<String> vratiDobavljace(){
		
		ArrayList<String> dobavljaci = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		try {
			String query = "SELECT * FROM DOBAVLJAC ORDER BY SIFRADOBAVLJACA";
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom citanja dobavljaca " + e);
		}
		
		try {
			
			while(results.next()){
				sb.append(results.getString(1));
				sb.append(" ");
				sb.append(results.getString(2));
				dobavljaci.add(sb.toString());
				sb.delete(0, sb.length());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dobavljaci;
	}
	
	
	public Dobavljac nadjiDobavljaca(int sifraDobavljacaPretraga) {
		
		Dobavljac dobavljac = new Dobavljac();
		String query = "";
		try {
			if (sifraDobavljacaPretraga==0) {
				query = "SELECT SIFRADOBAVLJACA, NAZIVDOBAVLJACA FROM DOBAVLJAC WHERE SIFRADOBAVLJACA=(SELECT MAX(SIFRADOBAVLJACA) FROM DOBAVLJAC)";
			} else {
				query = "SELECT SIFRADOBAVLJACA, NAZIVDOBAVLJACA FROM DOBAVLJAC WHERE SIFRADOBAVLJACA="+ sifraDobavljacaPretraga;
			}
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom citanja dobavljaca " + e);
		}
		try {
			while (results.next()) {
				dobavljac.setSifraDobavljaca(results.getInt(1));
				dobavljac.setNazivDobavljaca(results.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dobavljac;
	}
	
	
	 public int snimiDobavljaca(Dobavljac dobavljac) throws Exception {

		PreparedStatement ps;

		String query = "";
		if (dobavljac.getStatus().equals("insert")) {
			query = "INSERT INTO DOBAVLJAC (SIFRADOBAVLJACA,NAZIVDOBAVLJACA) VALUES (dobavljac_seq.nextval,'"+ dobavljac.getNazivDobavljaca() + "')";
		} else if (dobavljac.getStatus().equals("update")) {
			query = "UPDATE DOBAVLJAC SET NAZIVDOBAVLJACA ='" + dobavljac.getNazivDobavljaca() + "' WHERE SIFRADOBAVLJACA =" + dobavljac.getSifraDobavljaca();
		} else if (dobavljac.getStatus().equals("delete")) {
			query = "DELETE FROM DOBAVLJAC WHERE SIFRADOBAVLJACA =" + dobavljac.getSifraDobavljaca();
		}
		ps = connection.prepareStatement(query);
		ps.executeUpdate();
		
		return 1;
	}
	
	/// Kraj operacija sa dobavljacem
	
	/// Porudzbina 
	
	public Porudzbina nadjiPorudzbinu(int brojPorudzbinePretraga){
		
		Porudzbina porudzbina = new Porudzbina();
		//ArrayList<StavkaPorudzbine> stavke = new ArrayList<StavkaPorudzbine>();
		//porudzbina.setStavke(stavke);
		
		String query = "";
		if(brojPorudzbinePretraga==0){
			query = "SELECT * FROM PORUDZBINA LEFT JOIN STAVKAPORUDZBINE ON PORUDZBINA.BROJPORUDZBINE = STAVKAPORUDZBINE.BROJPORUDZBINE WHERE PORUDZBINA.DATUM=(SELECT MAX(DATUM) FROM PORUDZBINA) ORDER BY REDNIBROJ";
		} else
			query = "SELECT * FROM PORUDZBINA LEFT JOIN STAVKAPORUDZBINE ON PORUDZBINA.BROJPORUDZBINE = STAVKAPORUDZBINE.BROJPORUDZBINE WHERE PORUDZBINA.BROJPORUDZBINE=" + brojPorudzbinePretraga + " ORDER BY REDNIBROJ";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom citanja porudzbine " + e);
		}
		
		try {
			while(results.next()){
				
				porudzbina.setBrojPorudzbine(results.getInt(1));
				porudzbina.setDatum(results.getDate(2));
				porudzbina.setNazivDobavljaca(results.getString(4));
				porudzbina.setSifraDobavljaca(results.getInt(3));
				porudzbina.setUkupnaVrednost(results.getDouble(5));
				porudzbina.popuniStavke(results.getInt(7), results.getInt(8), results.getInt(9));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return porudzbina;
	}
	
	public void snimiStavkuPorudzbine(StavkaPorudzbine sp, String brojPorudzbine) {

		PreparedStatement ps;
		try {
			String query = "";
			if (sp.getStatus().equals("insert")) {
				query = "INSERT INTO STAVKAPORUDZBINE VALUES (" + brojPorudzbine + "," + sp.getRedniBroj() + "," + sp.getKolicina() + "," + sp.getSifraProizvoda() + ")";
			} else if (sp.getStatus().equals("update")) {
				query = "UPDATE STAVKAPORUDZBINE SET KOLICINA = " + sp.getKolicina() + ", SIFRAPROIZVODA = " + sp.getSifraProizvoda() + " WHERE BROJPORUDZBINE = " + brojPorudzbine + " AND REDNIBROJ = " + sp.getRedniBroj();
			} else if (sp.getStatus().equals("delete")) {
				query = "DELETE FROM STAVKAPORUDZBINE WHERE BROJPORUDZBINE = " + brojPorudzbine+ " AND REDNIBROJ = " + sp.getRedniBroj();
			} 
			
			if(query.equals("")){
				
			} else {
				ps = connection.prepareStatement(query);
				ps.executeUpdate();
			}
			


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom transakcije " + e);
		}
	}
	
	public int snimiPorudzbinu(Porudzbina porudzbina) throws Exception{
		PreparedStatement ps;
		String query="";

		if(porudzbina.getStatus().equals("insert")){
			query = "INSERT INTO PORUDZBINA (BROJPORUDZBINE, SIFRADOBAVLJACA) VALUES (porudzbina_seq.nextval," + porudzbina.getSifraDobavljaca() + ")";
			ps = connection.prepareStatement(query);
			ps.executeUpdate();

			ArrayList<StavkaPorudzbine> stavke = porudzbina.getStavke();

			if(stavke!=null){
				
			    for(int i =0; i<stavke.size();i++){
			    	
				StavkaPorudzbine sp = stavke.get(i);
					snimiStavkuPorudzbine(sp, "porudzbina_seq.currval");
			    }
			    
			}
		} else if(porudzbina.getStatus().equals("updateNazivDobavljaca")){
			query = "UPDATE PORUDZBINA SET NAZIVDOBAVLJACA='" + porudzbina.getNazivDobavljaca() + "' WHERE BROJPORUDZBINE=" + porudzbina.getBrojPorudzbine();
			ps = connection.prepareStatement(query);
			ps.executeUpdate();

		} else if(porudzbina.getStatus().equals("updateUkupnaVrednost")){
			query = "UPDATE PORUDZBINA SET UKUPNAVREDNOST=" + porudzbina.getUkupnaVrednost() + " WHERE BROJPORUDZBINE=" + porudzbina.getBrojPorudzbine();
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
			
		} else if(porudzbina.getStatus().equals("update")){
		
			query = "UPDATE PORUDZBINA SET SIFRADOBAVLJACA=" + porudzbina.getSifraDobavljaca() + " WHERE BROJPORUDZBINE=" + porudzbina.getBrojPorudzbine();
			ps = connection.prepareStatement(query);
			ps.executeUpdate();

			ArrayList<StavkaPorudzbine> stavke = porudzbina.getStavke();

			if(stavke!=null){
				
			    for(int i =0; i<stavke.size();i++){
			    	
				StavkaPorudzbine sp = stavke.get(i);
					snimiStavkuPorudzbine(sp, String.valueOf(porudzbina.getBrojPorudzbine()));
			    }
			    
			}
		} else if(porudzbina.getStatus().equals("delete")){
			query = "DELETE FROM PORUDZBINA WHERE BROJPORUDZBINE=" + porudzbina.getBrojPorudzbine();
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
		}
		
		return 1;
	}
	
	public String vratiSvePorudzbine() throws Exception{
		
		String query = "SELECT * FROM PORUDZBINA ORDER BY DATUM DESC";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Greska prilikom citanja porudzbina " + e);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='table table-bordered text-center'>");
		sb.append("<TR class='success'>");
		
		
		ResultSetMetaData rsmd;
		rsmd = results.getMetaData();

		int columnCount;
		columnCount = rsmd.getColumnCount();

		// table header
		for (int i = 0; i < columnCount; i++) {
			sb.append("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
		}
		sb.append("</TR>");
		
		// the data
		while (results.next()) {
			
			sb.append("<TR>");
			
			sb.append("<TD>" + results.getString(1) + "</TD>");
			sb.append("<TD>" + results.getDate(2) + "</TD>");
			sb.append("<TD>" + results.getString(3) + "</TD>");
			sb.append("<TD>" + results.getString(4) + "</TD>");
			sb.append("<TD>" + results.getString(5) + "</TD>");
			sb.append("</TR>");
			
		}
		sb.append("</table>");
		
		return sb.toString();
	}
	
	//// Kraj operacija sa porudzbinom
	
	/// Operacije za porudzbinu i dobavljaca
	
	
}
