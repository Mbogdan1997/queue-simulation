package program;

import java.util.List;

public class TimpMinim implements Strategie_Planificator {

	@Override
	public Coada adaugaClient(List<Coada> magazin, Client c) {
		// TODO Auto-generated method stub
		int minim = Integer.MAX_VALUE;
		int indice = -1;
		boolean ok = false;

		for (int i = 0; i < magazin.size(); i++) {
			int aux = magazin.get(i).getTimpAsteptare();
			if (aux < minim) {
				minim = aux;
				ok = true;
				indice = i;
			}
		}
		if (ok) {
			magazin.get(indice).addClient(c);
			return magazin.get(indice);
		}
		return null;

	}

}
