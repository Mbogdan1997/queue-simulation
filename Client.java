package program;

public class Client implements Comparable<Client> {
	private int timpVenire;
	private int timpProcesare;
	private int timpFinalizare;
	private int timpAsteptare;
	private static int idMax = 0;
	private int idClient;
	private String nume;

	public Client(int timpVenire, int timpProcesare) {
		this.timpVenire = timpVenire;
		this.timpProcesare = timpProcesare;
		this.timpFinalizare = 0;
		idMax++;
		this.idClient = idMax;
		this.nume = "Client" + this.idClient;
	}

	public int getTimpVenire() {
		return this.timpVenire;
	}

	public int getTimpProcesare() {
		return this.timpProcesare;
	}

	public int getIdClient() {
		return this.idClient;
	}

	@Override
	public int compareTo(Client arg0) {

		return this.timpVenire - arg0.timpVenire;
	}

	public String getNume() {
		return this.nume;
	}

	public String toString() {
		return this.nume + " ts=" + this.timpVenire + " tp=" + this.timpProcesare + " tf=" + this.timpFinalizare
				+ " ta=" + this.timpAsteptare;
	}

	public void setTimpFinalizare(int nr) {
		this.timpFinalizare = nr;
	}

	public int getTimpFinalizare() {
		return this.timpFinalizare;
	}

	public int getTimpAsteptare() {
		return this.timpAsteptare;
	}

	public void setTimpAsteptare(int timpAsteptare) {
		this.timpAsteptare = timpAsteptare;
	}
	public static void setIdMax(int id) {
		idMax=id;
	}

}
