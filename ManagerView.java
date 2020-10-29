package program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ManagerView extends JFrame {

	private JPanel contentPane;
	private ManagerController mg;
	private JTextField taMin;
	private JTextField taMax;
	private JTextField tpMin;
	private JTextField tpMax;
	private JTextField nrCozi;
	private JTextField timpSimulare;
	private static JTextField medie;
	private static JTextField varf;
	private JLabel etMomentVarf;
	private JLabel etTimpAjungere;
	private JButton reset;
	private JButton simulare;
	private JLabel et1DeLa;
	private JLabel et1PanaLa;
	private JLabel etTimpProcesare;
	private JLabel et2DeLa;
	private JLabel et2PanaLa;
	private JLabel etNumarCozi;
	private JLabel etTimpSimulare;
	private JComboBox modPlanificator;
	private JLabel etMedie;
	private JScrollPane scrolanime;
	private static JTextArea log;
	private static JTextArea animatie;
	private JScrollPane scrollog;
	// private JScrollPane scrollPane;

	public ManagerView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 894, 700);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		etTimpAjungere = new JLabel("Timp Ajungere:");
		etTimpAjungere.setBounds(10, 10, 120, 15);
		contentPane.add(etTimpAjungere);

		et1DeLa = new JLabel("De la:");
		et1DeLa.setBounds(10, 40, 50, 15);
		contentPane.add(et1DeLa);

		taMin = new JTextField();
		taMin.setBounds(45, 40, 100, 20);
		contentPane.add(taMin);
		taMin.setColumns(10);

		et1PanaLa = new JLabel("Pana la:");
		et1PanaLa.setBounds(180, 40, 50, 15);
		contentPane.add(et1PanaLa);

		taMax = new JTextField();
		taMax.setColumns(10);
		taMax.setBounds(230, 40, 100, 20);
		contentPane.add(taMax);

		etTimpProcesare = new JLabel("Timp Procesare:");
		etTimpProcesare.setBounds(10, 80, 120, 15);
		contentPane.add(etTimpProcesare);

		et2DeLa = new JLabel("De la:");
		et2DeLa.setBounds(10, 110, 50, 15);
		contentPane.add(et2DeLa);

		tpMin = new JTextField();
		tpMin.setColumns(10);
		tpMin.setBounds(45, 110, 100, 20);
		contentPane.add(tpMin);

		et2PanaLa = new JLabel("Pana la:");
		et2PanaLa.setBounds(180, 110, 50, 15);
		contentPane.add(et2PanaLa);

		tpMax = new JTextField();
		tpMax.setColumns(10);
		tpMax.setBounds(230, 110, 100, 20);
		contentPane.add(tpMax);

		etNumarCozi = new JLabel("Numar Cozi:");
		etNumarCozi.setBounds(10, 150, 120, 15);
		contentPane.add(etNumarCozi);

		etTimpSimulare = new JLabel("Timp Simulare:");
		etTimpSimulare.setBounds(180, 150, 120, 15);
		contentPane.add(etTimpSimulare);

		nrCozi = new JTextField();
		nrCozi.setColumns(10);
		nrCozi.setBounds(45, 180, 100, 20);
		contentPane.add(nrCozi);

		timpSimulare = new JTextField();
		timpSimulare.setColumns(10);
		timpSimulare.setBounds(230, 180, 100, 20);
		contentPane.add(timpSimulare);

		simulare = new JButton("SIMULARE");
		simulare.setBounds(45, 230, 100, 20);
		contentPane.add(simulare);

		modPlanificator = new JComboBox();
		modPlanificator.setModel(new DefaultComboBoxModel(new String[] { "Coada Minima", "Timp Minim" }));
		modPlanificator.setBounds(230, 230, 100, 20);
		contentPane.add(modPlanificator);

		// log.setVisible(true);
		// log.setEnabled(true);

		// log.setText("aaaaaaaaaaaaaaaa");

		etMedie = new JLabel("Medie Asteptare:");
		etMedie.setBounds(10, 270, 120, 15);
		contentPane.add(etMedie);

		etMomentVarf = new JLabel("Moment Varf:");
		etMomentVarf.setBounds(180, 270, 120, 15);
		contentPane.add(etMomentVarf);

		medie = new JTextField();
		medie.setColumns(10);
		medie.setBounds(45, 290, 100, 20);
		contentPane.add(medie);

		varf = new JTextField();
		varf.setColumns(10);
		varf.setBounds(230, 290, 100, 20);
		contentPane.add(varf);
		reset = new JButton("RESET");
		reset.setBounds(230, 6, 100, 20);
		contentPane.add(reset);

		scrolanime = new JScrollPane();
		scrolanime.setBounds(10, 330, 370, 410);
		contentPane.add(scrolanime);
		medie.setEditable(false);
		varf.setEditable(false);
		
		animatie=new JTextArea();
		scrolanime.setViewportView(animatie);
		scrollog = new JScrollPane();
		scrollog.setBounds(410, 10, 450, 640);
		contentPane.add(scrollog);
		
		log = new JTextArea();
		scrollog.setViewportView(log);
		mg = new ManagerController(this);
		this.reset.addActionListener(mg);
		this.simulare.addActionListener(mg);
		//log.setEditable(false);
		//animatie.setEditable(false);
		// scrollog= new JScrollPane(log);
		// scrollog.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// scrollog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	public static synchronized void makeLog(String s) {
		log.append(s);
		log.append("\n");
	}
	
	public static synchronized void makeAnimatie(String s) {
		animatie.append(s);
		animatie.append("\n");
	}
	public static synchronized void setAnimatie(String s) {
		animatie.setText(s);
	}

	public String getTaMin() {
		return taMin.getText();

	}

	public void setTaMin(String s) {
		this.taMin.setText(s);
		;
	}

	public String getTaMax() {
		return taMax.getText();
	}

	public void setTaMax(String s) {
		this.taMax.setText(s);
	}

	public String getTpMin() {
		return tpMin.getText();
	}

	public void setTpMin(String s) {
		this.tpMin.setText(s);
	}

	public String getTpMax() {
		return tpMax.getText();
	}

	public void setTpMax(String s) {
		this.tpMax.setText(s);
	}

	public String getNrCozi() {
		return nrCozi.getText();
	}

	public void setNrCozi(String s) {
		this.nrCozi.setText(s);
	}

	public String getTimpSimulare() {
		return timpSimulare.getText();
	}

	public void setTimpSimulare(String s) {
		this.timpSimulare.setText(s);
	}

	public String getMedie() {
		return medie.getText();
	}

	public static void setMedie(String s) {
		medie.setText(s);
	}

	public String getVarf() {
		return varf.getText();
	}

	public static void setVarf(String s) {
		varf.setText(s);
	}

	public JButton getReset() {
		return reset;
	}

	public JButton getSimulare() {
		return simulare;
	}

	// public void setLog(String s) {
	// ManagerView.log.setText(s);
	// }

	public String getMod() {
		String s = (String) (this.modPlanificator.getItemAt(this.modPlanificator.getSelectedIndex()));
		return s;

	}
	public void setLog(String s) {
		log.setText(s);
	}
}

