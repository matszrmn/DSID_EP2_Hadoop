package graphics;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import executar.Testar;

@SuppressWarnings({"rawtypes","unchecked"})
public class Periodo extends JPanel {

	private static final long serialVersionUID = 1L;

	private String[] months = { "Janeiro", "Fevereiro", "Marco", "Abril",
			"Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
			"Novembro", "Dezembro" };

	private final static int dom[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private int yy, mm, dd;
	private JButton labs[][];

	private int activeDay = -1;
	private int leadGap = 0;

	private Calendar calendar = new GregorianCalendar();
	private final int thisYear = calendar.get(Calendar.YEAR);
	private final int thisMonth = calendar.get(Calendar.MONTH);

	private JButton b0;
	private JComboBox monthChoice;
	private JComboBox yearChoice;
	
	public Periodo(boolean inicio) {
		super();
		if(inicio)	setDDMMYY(1, 0, 1901);
		else 		setDDMMYY(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
		buildGUI();
		recompute();
	}
	private void setDDMMYY(int day, int month, int year) {
		this.dd = day;
		this.mm = month;
		this.yy = year;
	}
	private void buildGUI() {
		getAccessibleContext().setAccessibleDescription("Calendario nao acessivel");
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new BorderLayout());

		JPanel painel = new JPanel();
		painel.add(monthChoice = new JComboBox());
		for (int i = 0; i < months.length; i++)
			monthChoice.addItem(months[i]);

		monthChoice.setSelectedItem(months[mm]);
		monthChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int i = monthChoice.getSelectedIndex();
				if (i >= 0) {
					mm = i;
					recompute();
				}
			}
		});
		monthChoice.getAccessibleContext().setAccessibleName("Months");
		monthChoice.getAccessibleContext().setAccessibleDescription("Choose a month of the year");

		painel.add(yearChoice = new JComboBox());
		yearChoice.setEditable(true);
		for (int i = 1901; i <= 2017; i++) yearChoice.addItem(Integer.toString(i));
		
		yearChoice.setSelectedItem(Integer.toString(yy));
		yearChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int i = yearChoice.getSelectedIndex();
				if (i >= 0) {
					yy = Integer.parseInt(yearChoice.getSelectedItem().toString());
					recompute();
				}
			}
		});
		add(BorderLayout.CENTER, painel);

		JPanel bp = new JPanel();
		bp.setLayout(new GridLayout(7, 7));
		labs = new JButton[6][7];
		
		bp.add(b0 = new JButton("D"));
		bp.add(new JButton("S"));
		bp.add(new JButton("T"));
		bp.add(new JButton("Q"));
		bp.add(new JButton("Q"));
		bp.add(new JButton("S"));
		bp.add(new JButton("S"));

		ActionListener dateSetter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				if (!num.equals(""))
					setDayActive(Integer.parseInt(num));
			}
		};
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				bp.add(labs[i][j] = new JButton(""));
				labs[i][j].addActionListener(dateSetter);
			}
		}
		add(BorderLayout.SOUTH, bp);
	}
	private void recompute() {
		if (mm < 0 || mm > 11)
			throw new IllegalArgumentException("Month " + mm
					+ " bad, must be 0-11");
		clearDayActive();
		calendar = new GregorianCalendar(yy, mm, dd);
		leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;
		int daysInMonth = dom[mm];

		if (isLeap(calendar.get(Calendar.YEAR)) && mm == 1) ++daysInMonth;

		for (int i = 0; i < leadGap; i++) labs[0][i].setText("");
		for (int i = 1; i <= daysInMonth; i++) {
			JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];
			b.setText(Integer.toString(i));
		}
		for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {
			labs[(i) / 7][(i) % 7].setText("");
		}
		if (thisYear == yy && mm == thisMonth) setDayActive(dd);
		
		repaint();
	}
	private boolean isLeap(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) return true;
		return false;
	}
	private void clearDayActive() {
		JButton b;

		if (activeDay > 0) {
			b = labs[(leadGap + activeDay - 1) / 7][(leadGap + activeDay - 1) % 7];
			b.setBackground(b0.getBackground());
			b.repaint();
			activeDay = -1;
		}
	}
	private void setDayActive(int newDay) {

		clearDayActive();

		if (newDay <= 0)
			dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
		else
			dd = newDay;

		Component square = labs[(leadGap + newDay - 1) / 7][(leadGap + newDay - 1) % 7];
		square.setBackground(Color.red);
		square.repaint();
		activeDay = newDay;
	}

	public static void exibir() {
		final JFrame frame = new JFrame("Inicio");
		final Periodo painel1 = new Periodo(true);
		final Periodo painel2 = new Periodo(false);
		
		JButton botaoProximo = new JButton("Proximo");
		JLabel lblTitulo = new JLabel("Defina o periodo dos dados a serem analisados");
		JLabel lblIni = new JLabel("Data inicial");
		JLabel lblFim = new JLabel("Data final");
		
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 17));
		lblIni.setFont(new Font("Arial", Font.BOLD, 15));
		lblFim.setFont(new Font("Arial", Font.BOLD, 15));
		
		painel1.setBounds(10, 120, 370, 250);
		painel2.setBounds(400, 120, 370, 250);
		botaoProximo.setBounds(330, 390, 100, 50);
		lblTitulo.setBounds(200, -100, 500, 250);
		lblIni.setBounds(150, -30, 500, 250);
		lblFim.setBounds(540, -30, 500, 250);
		
		botaoProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Testar.diaInicio = painel1.dd;
					Testar.mesInicio = painel1.mm;
					Testar.anoInicio = painel1.yy;
					Testar.diaFim = painel2.dd;
					Testar.mesFim = painel2.mm;
					Testar.anoFim = painel2.yy;
					frame.dispose();
					Agrupado.exibir();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(painel1);
		frame.getContentPane().add(painel2);
		frame.getContentPane().add(botaoProximo);
		frame.getContentPane().add(lblTitulo);
		frame.getContentPane().add(lblIni);
		frame.getContentPane().add(lblFim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(320, 90, 790, 470);
		
		frame.setVisible(true);
	}
}