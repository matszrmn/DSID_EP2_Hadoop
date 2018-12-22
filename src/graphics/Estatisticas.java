package graphics;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class Estatisticas {

	private JFrame frame;
	private ButtonGroup group;
	
	public Estatisticas() {
		initialize();
	}
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 150, 396, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		group = new ButtonGroup();
		
		final JRadioButton rdMin = new JRadioButton("Minimo");
		final JRadioButton rdMax = new JRadioButton("Maximo");
		final JRadioButton rdMedia = new JRadioButton("Media");
		final JRadioButton rdVariancia = new JRadioButton("Variancia");
		final JRadioButton rdDesvioPadrao = new JRadioButton("Desvio Padrao");
		final JRadioButton rdDesvioMedio = new JRadioButton("Desvio Medio");
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(70, 275, 120, 25);
		frame.getContentPane().add(btnAnterior);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				  	frame.dispose();
				  	Atributos.exibir();
			}
		});
		
		JButton btnProximo = new JButton("Proximo");
		btnProximo.setBounds(215, 275, 120, 25);
		frame.getContentPane().add(btnProximo);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escolhido = "1";
				
				Enumeration buttons = group.getElements();
				while(buttons.hasMoreElements()) {
					JRadioButton rdAtual = (JRadioButton) buttons.nextElement();
					if(rdAtual.isSelected()) {
						escolhido = rdAtual.getName();
						break;
					}
				}
				frame.dispose();
				Numerico.exibir(escolhido);
			}
		});
		rdMin.setSelected(true); //Default
		
		rdMin.setName("1");
		rdMax.setName("2");
		rdMedia.setName("3");
		rdVariancia.setName("4");
		rdDesvioPadrao.setName("5");
		rdDesvioMedio.setName("6");
		
		group.add(rdMin);
		group.add(rdMax);
		group.add(rdMedia);
		group.add(rdVariancia);
		group.add(rdDesvioPadrao);
		group.add(rdDesvioMedio);
		
		rdMin.setBounds(135, 50, 140, 30);
		rdMax.setBounds(135, 83, 140, 30);
		rdMedia.setBounds(135, 116, 140, 30);
		rdVariancia.setBounds(135, 149, 140, 30);
		rdDesvioPadrao.setBounds(135, 182, 140, 30);
		rdDesvioMedio.setBounds(135, 215, 140, 30);
		
		frame.getContentPane().add(rdMin);
		frame.getContentPane().add(rdMax);
		frame.getContentPane().add(rdMedia);
		frame.getContentPane().add(rdVariancia);
		frame.getContentPane().add(rdDesvioPadrao);
		frame.getContentPane().add(rdDesvioMedio);
		
		JLabel lblEstatistica = new JLabel("Escolha uma estatistica:");
		lblEstatistica.setFont(new Font("Arial", Font.BOLD, 17));
		lblEstatistica.setBounds(90, 11, 250, 32);
		frame.getContentPane().add(lblEstatistica);
	}
	public static void exibir() {
		Estatisticas window = new Estatisticas();
		window.frame.setVisible(true);
	}
}