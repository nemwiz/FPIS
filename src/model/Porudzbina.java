package model;

import java.util.ArrayList;
import java.util.Date;

public class Porudzbina {
	
	private int brojPorudzbine;
	private int sifraDobavljaca;
	private String nazivDobavljaca;
	private Date datum;
	private double ukupnaVrednost;
	private ArrayList<StavkaPorudzbine> stavke;
	private String status;
	
	// default constructor
	
	public Porudzbina() {
		this.brojPorudzbine = 0;
		this.sifraDobavljaca = 0;
		this.nazivDobavljaca = "none";
		this.datum = new Date();
		this.ukupnaVrednost = 0;
		this.stavke = new ArrayList<StavkaPorudzbine>();
		this.status = "none";
	}

	public Porudzbina(int brojPorudzbine, int sifraDobavljaca,
			String nazivDobavljaca, Date datum, double ukupnaVrednost,
			ArrayList<StavkaPorudzbine> stavke, String status) {
		this.brojPorudzbine = brojPorudzbine;
		this.sifraDobavljaca = sifraDobavljaca;
		this.nazivDobavljaca = nazivDobavljaca;
		this.datum = datum;
		this.ukupnaVrednost = ukupnaVrednost;
		this.stavke = stavke;
		this.status = status;
	}
	
	public int getBrojPorudzbine() {
		return brojPorudzbine;
	}
	public void setBrojPorudzbine(int brojPorudzbine) {
		this.brojPorudzbine = brojPorudzbine;
	}
	public int getSifraDobavljaca() {
		return sifraDobavljaca;
	}
	public void setSifraDobavljaca(int sifraDobavljaca) {
		this.sifraDobavljaca = sifraDobavljaca;
	}
	public String getNazivDobavljaca() {
		return nazivDobavljaca;
	}
	public void setNazivDobavljaca(String nazivDobavljaca) {
		this.nazivDobavljaca = nazivDobavljaca;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}
	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}

	public ArrayList<StavkaPorudzbine> getStavke() {
		return stavke;
	}

	public void setStavke(ArrayList<StavkaPorudzbine> stavke) {
		this.stavke = stavke;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList<ArrayList<String>> vratiStavkeKaoString(ArrayList<StavkaPorudzbine> stavke){
		
		ArrayList<ArrayList<String>> stavkeString = new ArrayList<ArrayList<String>>();
		
		for(int i=0;i<stavke.size();i++){
			
			ArrayList<String> stavka = new ArrayList<String>();
			
			stavka.add(String.valueOf(stavke.get(i).getRedniBroj()));
			stavka.add(String.valueOf(stavke.get(i).getKolicina()));
			stavka.add(String.valueOf(stavke.get(i).getSifraProizvoda()));
			stavka.add(String.valueOf(stavke.get(i).getStatus()));
			
			stavkeString.add(stavka);
			
		}
		
		
		return stavkeString;
	}
	
	public void popuniStavke(int redniBroj, int kolicina, int sifraProizvoda){
		StavkaPorudzbine sp = new StavkaPorudzbine();
		sp.setRedniBroj(redniBroj);
		sp.setKolicina(kolicina);
		sp.setSifraProizvoda(sifraProizvoda);
		sp.setStatus("none");
		getStavke().add(sp);
		
	}
	
	public void dodajStavku(int redniBroj, int kolicina, int sifraProizvoda){
		
		StavkaPorudzbine sp = new StavkaPorudzbine();
		sp.setRedniBroj(redniBroj);
		sp.setKolicina(kolicina);
		sp.setSifraProizvoda(sifraProizvoda);
		sp.setStatus("insert");
		
		getStavke().add(sp);
	}
	
	public void izmeniStavku(int redniBroj, int kolicina, int sifraProizvoda){

		for(int i=0; i<getStavke().size();i++){
			StavkaPorudzbine sp = getStavke().get(i);

			if(sp.getRedniBroj()==redniBroj){
			      sp.setKolicina(kolicina);
			      sp.setSifraProizvoda(sifraProizvoda);
			      sp.setStatus("update");
			}
		}
	}
	
	public void obrisiStavku(int redniBroj){

		for(int i=0; i<getStavke().size();i++){

			if(getStavke().get(i).getRedniBroj()==redniBroj) {
				getStavke().get(i).setStatus("delete");
			}
		}
	}
}
