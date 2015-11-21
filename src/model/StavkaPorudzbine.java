package model;

public class StavkaPorudzbine {
	private int redniBroj;
	private int kolicina;
	private int sifraProizvoda;
	private String status;
	/**
	 default constructor
	 */
	
	public StavkaPorudzbine() {
		this.redniBroj = 0;
		this.kolicina = 0;
		this.sifraProizvoda = 0;
		this.status = "none";
	}
	
	public StavkaPorudzbine(int redniBroj, int kolicina, int sifraProizvoda,
			String status) {
		this.redniBroj = redniBroj;
		this.kolicina = kolicina;
		this.sifraProizvoda = sifraProizvoda;
		this.status = status;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public int getSifraProizvoda() {
		return sifraProizvoda;
	}

	public void setSifraProizvoda(int sifraProizvoda) {
		this.sifraProizvoda = sifraProizvoda;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
