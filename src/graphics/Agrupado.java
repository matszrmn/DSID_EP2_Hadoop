package graphics;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import executar.Testar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.ButtonGroup;

public class Agrupado {

	private JFrame frame;
	private ButtonGroup group;
	
	public Agrupado() {
		initialize();
	}
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 150, 396, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		group = new ButtonGroup();
		
		JButton btnProximo = new JButton("Proximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enumeration buttons = group.getElements();
				while(buttons.hasMoreElements()) {
					JRadioButton rdAtual = (JRadioButton) buttons.nextElement();
					if(rdAtual.isSelected()) {
						Testar.modoAgrupamento = Integer.parseInt(rdAtual.getName());
						break;
					}
				}
				frame.dispose();
				Atributos.exibir();
			}
		});
		btnProximo.setBounds(215, 253, 120, 23);
		frame.getContentPane().add(btnProximo);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Periodo.exibir();
			}
		});
		btnAnterior.setBounds(70, 253, 120, 23);
		frame.getContentPane().add(btnAnterior);
		
		JRadioButton rdbtnAno = new JRadioButton("Ano");
		rdbtnAno.setName("1");
		rdbtnAno.setBounds(150, 66, 200, 20);
		rdbtnAno.setSelected(true); //Default
		frame.getContentPane().add(rdbtnAno);
		
		JRadioButton rdbtnMes = new JRadioButton("Mes");
		rdbtnMes.setName("2");
		rdbtnMes.setBounds(150, 103, 200, 23);
		frame.getContentPane().add(rdbtnMes);
		
		JRadioButton rdbtnDiaDoMes = new JRadioButton("Dia do mes");
		rdbtnDiaDoMes.setName("3");
		rdbtnDiaDoMes.setBounds(150, 142, 200, 32);
		frame.getContentPane().add(rdbtnDiaDoMes);
		
		JRadioButton rdbtnDiaDaSemana = new JRadioButton("Dia da semana");
		rdbtnDiaDaSemana.setName("4");
		rdbtnDiaDaSemana.setBounds(150, 184, 200, 32);
		frame.getContentPane().add(rdbtnDiaDaSemana);
		
		group.add(rdbtnAno);
		group.add(rdbtnMes);
		group.add(rdbtnDiaDoMes);
		group.add(rdbtnDiaDaSemana);
		
		JLabel lblAgruparValoresPor = new JLabel("Agrupar valores por:");
		lblAgruparValoresPor.setFont(new Font("Arial", Font.BOLD, 17));
		lblAgruparValoresPor.setBounds(97, 11, 200, 32);
		frame.getContentPane().add(lblAgruparValoresPor);
	}
	public static void exibir() {
		Agrupado window = new Agrupado();
		window.frame.setVisible(true);
	}
}