package program;

import java.util.ArrayList;
import java.util.List;

public class Planificator {
	private List<Coada> magazin;
	private int maxNrCozi;
	private Strategie_Planificator strategie;

	public Planificator(int maxNrCozi, ModPlanificator mod) {
		this.maxNrCozi = maxNrCozi;
		magazin = new ArrayList<Coada>();
		schimbaStrategie(mod);
		this.creere_cozi();
	}

	public void schimbaStrategie(ModPlanificator mod) {
		if (mod == ModPlanificator.CEA_MAI_MICA_COADA)
			strategie = new CoadaMinima();
		else if (mod == ModPlanificator.CEL_MAI_MIC_TIMP)
			strategie = new TimpMinim();
	}

	public Coada distribuieClient(Client c) {
		return strategie.adaugaClient(magazin, c);
	}

	public List<Coada> getMagazin() {
		return this.magazin;
	}

	public void creere_cozi() {
		for (int i = 0; i < maxNrCozi; i++) {
			Coada coada = new Coada();
			magazin.add(coada);

			Thread thread = new Thread(coada);
			coada.setDeschis(true);
			thread.start();
		}
	}

	public void stop() {
		for (Coada c : magazin)
			c.setDeschis(false);

	}

}
