package program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ManagerSimulare implements Runnable {
	public static int timpSimulare;
	private int timpProcesareMin;
	private int timpProcesareMax;
	private int timpAjungereMin;
	private int timpAjungereMax;
	private int numarCozi;
	private int numarClienti;
	public static int timpulCurent;
	private ModPlanificator mod;
	private Planificator planificator;
	private List<Client> listaClienti;
	private List<Client> clientiDeSters;

	public ManagerSimulare(int timpSimulare, int timpProcesareMin, int timpProcesareMax, int numarCozi,
			ModPlanificator mod, int timpAjungereMin, int timpAjungereMax) {
		ManagerSimulare.timpSimulare = timpSimulare;
		this.timpProcesareMin = timpProcesareMin;
		this.timpProcesareMax = timpProcesareMax;
		this.timpAjungereMin = timpAjungereMin;
		this.timpAjungereMax = timpAjungereMax;
		this.numarCozi = numarCozi;
		Random rand = new Random();
		this.numarClienti = rand.nextInt(timpSimulare) + timpSimulare;
		this.mod = mod;
		this.listaClienti = new ArrayList<Client>();

		generareClienti();
		timpulCurent = 0;
		this.planificator = new Planificator(numarCozi, mod);
	}

	public void generareClienti() {
		int diferenta = this.timpAjungereMax - this.timpAjungereMin;
		int diferenta1 = this.timpProcesareMax - this.timpProcesareMin;
		int ta;
		int tp;

		for (int i = 0; i < this.numarClienti; i++) {
			Random rand = new Random();
			ta = rand.nextInt(diferenta) + this.timpAjungereMin;
			tp = rand.nextInt(diferenta1) + this.timpProcesareMin;
			System.out.println("ta:" + ta + "tp:" + tp);
			Client c = new Client(ta, tp);
			this.listaClienti.add(c);
		}
		Collections.sort(this.listaClienti);

	}

	public synchronized void asteaptaCozi() {
		for (Coada c : this.planificator.getMagazin())
			if (!(c.getCoada().isEmpty()))
				for (Client cl : c.getCoada())
					if (cl.getTimpFinalizare() == timpulCurent) {
						try {
							// display();
							wait(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		notifyAll();
	}

	public float totalAsteptare() {
		int sumaAsteptare = 0;

		for (Coada c : this.planificator.getMagazin())
			sumaAsteptare += c.getTimpTotalAsteptare();

		return (float) sumaAsteptare;
	}

	public int peek_time(int timp) {
		int peek = 0;
		for (Coada c : this.planificator.getMagazin())
			if (c.getCoada().size() > 0)
				for (Client cl : c.getCoada())
					if (cl.getTimpFinalizare() > timp)
						peek += (cl.getTimpFinalizare() - timp);

		return peek;

	}

	void display() {
		for (Coada c : this.planificator.getMagazin()) {
			System.out.println(c);
			ManagerView.makeAnimatie(c.toString());
		}
	}

	@Override
	public void run() {

		int timpulUrmator = 0;
		int peekTime = 0;
		int clientiPeekTime = 0;
		int nrClientiTotal = 0;
		float medieAsteptare = 0;

		clientiDeSters = new ArrayList<>();
		ManagerView.setAnimatie("");
		this.display();
		// ManagerView.makeAnimatie();
		while (timpulCurent <= timpSimulare) {
			for (Client c : listaClienti) {
				if (c.getTimpVenire() == timpulCurent) {
					Coada coada = this.planificator.distribuieClient(c);
					if (coada != null) {
						clientiDeSters.add(c);
						nrClientiTotal++;

					}

				}
			}

			this.listaClienti.removeAll(clientiDeSters);
			clientiDeSters.removeAll(clientiDeSters);

			this.asteaptaCozi();
			timpulUrmator = timpulCurent + 1;
			int aux = this.peek_time(timpulUrmator);
			if (aux > clientiPeekTime) {
				peekTime = timpulCurent;
				clientiPeekTime = aux;
			}
			ManagerView.setAnimatie("");
			this.display();
			timpulCurent++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Threadul e pe pauza");
			}
		}

		medieAsteptare = this.totalAsteptare() / (float) nrClientiTotal;
		ManagerView.setMedie(String.valueOf(medieAsteptare));
		String s = String.valueOf(peekTime);
		ManagerView.setVarf(s);

		planificator.stop();
		// TODO Auto-generated method stub

	}

}
