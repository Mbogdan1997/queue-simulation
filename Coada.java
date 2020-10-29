package program;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada implements Runnable {

	private BlockingQueue<Client> clienti;
	private AtomicBoolean deschis;
	private AtomicInteger timpAsteptare;
	private static AtomicInteger idMaximCoada = new AtomicInteger(0);
	private AtomicInteger idCoada;
	private String numeCoada;
	private AtomicInteger timpTotalAsteptare;
	private AtomicInteger numarTotalClienti;

	public Coada() {
		this.clienti = new LinkedBlockingQueue<Client>();
		timpAsteptare = new AtomicInteger(0);
		timpTotalAsteptare = new AtomicInteger(0);
		numarTotalClienti = new AtomicInteger(0);
		timpTotalAsteptare = new AtomicInteger(0);
		deschis = new AtomicBoolean(false);
		idMaximCoada.incrementAndGet();
		idCoada = new AtomicInteger(idMaximCoada.get());
		this.setNumeCoada("Coada" + idCoada.get());
	}

	public void addClient(Client c) {
		int timpFinalizare = c.getTimpVenire() + this.timpAsteptare.get() + c.getTimpProcesare();
		c.setTimpFinalizare(timpFinalizare);
		c.setTimpAsteptare(this.timpAsteptare.get());
		this.timpTotalAsteptare.addAndGet(c.getTimpAsteptare());
		this.numarTotalClienti.incrementAndGet();
		String s = this.numeCoada + " add " + c + " la:" + ManagerSimulare.timpulCurent;
		ManagerView.makeLog(s);
		timpAsteptare.addAndGet(c.getTimpProcesare());
		// ManagerView.makeLog(s);
		clienti.add(c);

	}

	public synchronized void asteapta(int timp) {
		try {
			Thread.sleep(timp * 1000);
		} catch (InterruptedException e) {
			System.out.println("coada este Intrerupta");
		}
	}

	public synchronized void asteaptaSimulator() {
		Client clientulCurent = null;
		if (!this.clienti.isEmpty()) {
			try {
				clientulCurent = clienti.peek();
				while (clientulCurent.getTimpFinalizare() != ManagerSimulare.timpulCurent)
					wait(1000);
			} catch (InterruptedException e) {
				System.out.println("coada intrerupta");
			}
			if (clientulCurent.getTimpFinalizare() == ManagerSimulare.timpulCurent) {
				ManagerView.makeLog(this.getNume() + " remove " + clientulCurent + " la " + ManagerSimulare.timpulCurent);
				int timpNouAsteptare = this.timpAsteptare.get() - clientulCurent.getTimpProcesare();
				this.timpAsteptare.set(timpNouAsteptare);
				this.clienti.remove();
			}
			notifyAll();
		}

	}

	@Override
	public void run() {
		System.out.println("AM intrat in run");
		while (deschis.get() == true) {
			if (!(this.clienti.isEmpty())) {
				Client clientulCurent = clienti.peek();
				this.asteapta(clientulCurent.getTimpProcesare());
				this.asteaptaSimulator();

			}
		}
	}

	public Client[] getClienti() {
		Client[] clienti_vector = new Client[this.clienti.size()];
		this.clienti.toArray(clienti_vector);
		return clienti_vector;

	}

	public int getTimpAsteptare() {
		return this.timpAsteptare.get();
	}

	public BlockingQueue<Client> getCoada() {
		return this.clienti;
	}

	public void setNumeCoada(String s) {
		this.numeCoada = s;
	}

	public int getId() {
		return this.idCoada.get();
	}

	public void setDeschis(boolean deschis) {
		this.deschis.set(deschis);
	}

	public String getNume() {
		return this.numeCoada;
	}

	public int getTimpTotalAsteptare() {
		return this.timpTotalAsteptare.get();

	}

	public int getNumarTotalClienti() {
		return this.getNumarTotalClienti();
	}

	public static void setIdMaximCoada(int nr) {
		idMaximCoada.set(0);
	}
	
	public String toString() {
		String s=this.numeCoada+":";
		if(!this.clienti.isEmpty())
		for(Client c:this.clienti)
			s+=c.getNume()+" ";
	return s;
		
	}
	

}
