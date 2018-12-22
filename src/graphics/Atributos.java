package graphics;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import executar.Testar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class Atributos {

	private JFrame frame;
	private ButtonGroup group;
	
	public Atributos() {
		initialize();
	}
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 10));
		frame.setBounds(480, 100, 523, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		group = new ButtonGroup();
		
		final JLabel lblTitulo = new JLabel("Escolha um atributo");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 17));
		lblTitulo.setBounds(176, 11, 200, 32);
		
		final JLabel lblAtrNum = new JLabel("_______________________Atributos Numericos_______________________");
		lblAtrNum.setFont(new Font("Arial", Font.PLAIN, 13));
		lblAtrNum.setBounds(33, 47, 450, 32);
		
		final JLabel lblAtrNaoNum = new JLabel("_____________________Atributos Nao-Numericos_____________________");
		lblAtrNaoNum.setFont(new Font("Arial", Font.PLAIN, 13));
		lblAtrNaoNum.setBounds(33, 264, 450, 32);
		
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(120, 397, 120, 25);
		frame.getContentPane().add(btnAnterior);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
				Agrupado.exibir();
			}
		});
		
		JButton btnProximo = new JButton("Proximo");
		btnProximo.setBounds(266, 397, 120, 25);
		frame.getContentPane().add(btnProximo);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enumeration buttons = group.getElements();
				while(buttons.hasMoreElements()) {
					JRadioButton rdAtual = (JRadioButton) buttons.nextElement();
					if(rdAtual.isSelected()) {
						Testar.setAtributo(rdAtual.getName());
						break;
					}
				}
				frame.dispose();
				if(Testar.atributoNumerico) Estatisticas.exibir();
				else Moda.exibir();
			}
		});
		JRadioButton rdTemp = new JRadioButton("temp");
		rdTemp.setSelected(true); //Default
		
		JRadioButton rdCountTemp = new JRadioButton("countTemp");
		JRadioButton rdDewp = new JRadioButton("dewp");
		JRadioButton rdCountDewp = new JRadioButton("countDewp");
		JRadioButton rdSlp = new JRadioButton("slp");
		JRadioButton rdCountSlp = new JRadioButton("countSlp");
		JRadioButton rdStp = new JRadioButton("stp");
		JRadioButton rdCountStp = new JRadioButton("countStp");
		JRadioButton rdVisib = new JRadioButton("visib");
		JRadioButton rdCountVisib = new JRadioButton("countVisib");
		JRadioButton rdWdsp = new JRadioButton("wdsp");
		JRadioButton rdCountWdsp = new JRadioButton("countWdsp");
		JRadioButton rdMaxSpd = new JRadioButton("maxSpd");
		JRadioButton rdMaxGust = new JRadioButton("maxGust");
		JRadioButton rdMinTemp = new JRadioButton("minTemp");
		JRadioButton rdMaxTemp = new JRadioButton("maxTemp");
		JRadioButton rdPrcp = new JRadioButton("prcp");
		JRadioButton rdSndp = new JRadioButton("sndp");
		JRadioButton rdFlagPrcp = new JRadioButton("flagPrcp");
		JRadioButton rdFog = new JRadioButton("fog");
		JRadioButton rdRain = new JRadioButton("rain");
		JRadioButton rdSnow = new JRadioButton("snow");
		JRadioButton rdHail = new JRadioButton("hail");
		JRadioButton rdThunder = new JRadioButton("thunder");
		JRadioButton rdTornado = new JRadioButton("tornado");
		
		rdTemp.setName("temp");
		rdCountTemp.setName("countTemp");
		rdDewp.setName("dewp");
		rdCountDewp.setName("countDewp");
		rdSlp.setName("slp");
		rdCountSlp.setName("countSlp");
		rdStp.setName("stp");
		rdCountStp.setName("countStp");
		rdVisib.setName("visib");
		rdCountVisib.setName("countVisib");
		rdWdsp.setName("wdsp");
		rdCountWdsp.setName("countWdsp");
		rdMaxSpd.setName("maxSpd");
		rdMaxGust.setName("maxGust");
		rdMinTemp.setName("minTemp");
		rdMaxTemp.setName("maxTemp");
		rdPrcp.setName("prcp");
		rdSndp.setName("sndp");
		rdFlagPrcp.setName("flagPrcp");
		rdFog.setName("fog");
		rdRain.setName("rain");
		rdSnow.setName("snow");
		rdHail.setName("hail");
		rdThunder.setName("thunder");
		rdTornado.setName("tornado");
		
		rdTemp.setBounds(30, 89, 100, 30);
		rdCountTemp.setBounds(130, 89, 120, 30);
		rdDewp.setBounds(265, 89, 100, 30);
		rdCountDewp.setBounds(365, 89, 120, 30);
		
		rdSlp.setBounds(30, 122, 100, 30);
		rdCountSlp.setBounds(130, 122, 120, 30);	
		rdStp.setBounds(265, 122, 100, 30);
		rdCountStp.setBounds(365, 122, 120, 30);
		
		rdVisib.setBounds(30, 155, 100, 30);
		rdCountVisib.setBounds(130, 155, 120, 30);
		rdWdsp.setBounds(265, 155, 100, 30);
		rdCountWdsp.setBounds(365, 155, 120, 30);
		
		rdMaxSpd.setBounds(30, 191, 100, 30);
		rdMaxGust.setBounds(130, 191, 120, 30);
		rdMinTemp.setBounds(265, 191, 100, 30);
		rdMaxTemp.setBounds(365, 191, 120, 30);
		
		rdPrcp.setBounds(30, 227, 100, 30);
		rdSndp.setBounds(130, 227, 120, 30);
		
		
		rdFlagPrcp.setBounds(40, 302, 110, 30);
		rdFog.setBounds(173, 302, 110, 30);
		rdRain.setBounds(280, 302, 110, 30);
		rdSnow.setBounds(390, 302, 110, 30);
		rdHail.setBounds(40, 338, 110, 30);
		rdThunder.setBounds(173, 338, 110, 30);
		rdTornado.setBounds(280, 338, 110, 30);
		
		group.add(rdTemp);
		group.add(rdCountTemp);
		group.add(rdDewp);
		group.add(rdCountDewp);
		group.add(rdSlp);
		group.add(rdCountSlp);
		group.add(rdStp);
		group.add(rdCountStp);
		group.add(rdVisib);
		group.add(rdCountVisib);
		group.add(rdWdsp);
		group.add(rdCountWdsp);
		group.add(rdMaxSpd);
		group.add(rdMaxGust);
		group.add(rdMinTemp);
		group.add(rdMaxTemp);
		group.add(rdPrcp);
		group.add(rdSndp);
		group.add(rdFlagPrcp);
		group.add(rdFog);
		group.add(rdRain);
		group.add(rdSnow);
		group.add(rdHail);
		group.add(rdThunder);
		group.add(rdTornado);
		
		frame.getContentPane().add(lblTitulo);
		frame.getContentPane().add(lblAtrNum);
		frame.getContentPane().add(lblAtrNaoNum);
		
		frame.getContentPane().add(rdTemp);
		frame.getContentPane().add(rdCountTemp);
		frame.getContentPane().add(rdDewp);
		frame.getContentPane().add(rdCountDewp);
		frame.getContentPane().add(rdSlp);
		frame.getContentPane().add(rdCountSlp);
		frame.getContentPane().add(rdStp);
		frame.getContentPane().add(rdCountStp);
		frame.getContentPane().add(rdVisib);
		frame.getContentPane().add(rdCountVisib);
		frame.getContentPane().add(rdWdsp);
		frame.getContentPane().add(rdCountWdsp);
		frame.getContentPane().add(rdMaxSpd);
		frame.getContentPane().add(rdMaxGust);
		frame.getContentPane().add(rdMinTemp);
		frame.getContentPane().add(rdMaxTemp);
		frame.getContentPane().add(rdPrcp);
		frame.getContentPane().add(rdSndp);
		frame.getContentPane().add(rdFlagPrcp);
		frame.getContentPane().add(rdFog);
		frame.getContentPane().add(rdRain);
		frame.getContentPane().add(rdSnow);
		frame.getContentPane().add(rdHail);
		frame.getContentPane().add(rdThunder);
		frame.getContentPane().add(rdTornado);
	}
	public static void exibir() {
		Atributos window = new Atributos();
		window.frame.setVisible(true);
	}
}