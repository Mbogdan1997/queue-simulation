package program;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerController implements ActionListener {
	ManagerView view;
	ManagerSimulare simulator;

	public ManagerController(ManagerView view) {
		this.view = view;
		view.setVisible(true);
	}

	public void reset() {
		this.view.setTimpSimulare("");
		this.view.setTaMax("");
		this.view.setTaMin("");
		this.view.setTpMax("");
		this.view.setTpMin("");
		ManagerView.setVarf("");
		ManagerView.setAnimatie("");
	//	
		this.view.setLog("");
		this.view.setNrCozi("");
		this.view.setLog("");
		ManagerView.setMedie("");
	}
	

	public void simulare() throws NumberFormatException, Exception {
		ManagerView.setVarf("");
		this.view.setLog("");
		Client.setIdMax(0);
		Coada.setIdMaximCoada(0);
		
		
		//this.view.setLog("");
        //System.out.println("AM intrat in simulare");
		int timpSimulare = Integer.parseInt(this.view.getTimpSimulare());
		int timpSosireMin = Integer.parseInt(this.view.getTaMin());
		int timpSosireMax = Integer.parseInt(this.view.getTaMax());
		int timpProcesareMin = Integer.parseInt(this.view.getTpMin());
		int timpProcesareMax = Integer.parseInt(this.view.getTpMax());
		int nrCozi = Integer.parseInt(this.view.getNrCozi());
		ModPlanificator mod;

		if (timpSimulare <= 0 || timpSosireMin < 0 || timpSosireMax <= 0 || nrCozi <= 0 || timpProcesareMin <= 0
				|| timpProcesareMax <= 0)
			throw new Exception();
		if (timpSosireMin > timpSosireMax || timpProcesareMin > timpProcesareMax)
			throw new Exception();

		if (this.view.getMod().equals("Coada Minima"))
			mod = ModPlanificator.CEA_MAI_MICA_COADA;
		else
			mod = ModPlanificator.CEL_MAI_MIC_TIMP;

		this.simulator = new ManagerSimulare(timpSimulare, timpProcesareMin, timpProcesareMax, nrCozi, mod,
				timpSosireMin, timpSosireMax);
		Thread t = new Thread(this.simulator);
		t.start();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if ((arg0.getSource()) == this.view.getReset()) {
			this.reset();
		}
		if ((arg0.getSource()) == this.view.getSimulare()) {
			try {
				this.simulare();

			} catch (NumberFormatException e) {
				;
			} catch (Exception e) {
				;
			}
		}

	}

}
