package model;

public class Dobavljac {
	
	private int sifraDobavljaca;
	private String nazivDobavljaca;
	private String status;
	/**
	 default constructor
	 */
	
	public Dobavljac() {
		this.sifraDobavljaca = 0;
		this.nazivDobavljaca = "none";
		this.status = "none";
	}
	
	public Dobavljac(int sifraDobavljaca, String nazivDobavljaca, String status) {
		this.sifraDobavljaca = sifraDobavljaca;
		this.nazivDobavljaca = nazivDobavljaca;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
