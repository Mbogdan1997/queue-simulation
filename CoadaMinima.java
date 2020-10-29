package program;
import java.util.ArrayList;
import java.util.List;

public class CoadaMinima implements Strategie_Planificator {

	@Override
	public Coada adaugaClient(List<Coada> magazin, Client c) {
		// TODO Auto-generated method stub
		int minim = Integer.MAX_VALUE;
		int indice = -1;
		boolean ok = false;

		for (int i = 0; i < magazin.size(); i++) {
			int aux = magazin.get(i).getCoada().size();
			if (aux < minim ) {
				minim = aux;
				ok = true;
				indice = i;
			}
		}
		if (ok) {
			if(magazin.get(indice).getTimpAsteptare() + c.getTimpProcesare() + ManagerSimulare.timpulCurent<= ManagerSimulare.timpSimulare)
			{magazin.get(indice).addClient(c);
			return magazin.get(indice);
			}
			return null;
		}
		return null;

	}

}
