package graphics;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.io.BufferedReader;
import executar.Testar;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Numerico extends JFrame {
	
	private LinkedList<Double[]> valoresXY = new LinkedList<Double[]>();
	
	public double a;
	public double b;
	public double funcao(double x) throws Exception {
		return a + (b*x);
	}
	
	public XYSeries[] preencherGraficoEMinQuadrado(DefaultListModel<String> model, String titulo, int estatistica)
															throws Exception {
		XYSeries serieReal = new XYSeries(titulo);
		XYSeries serieMMQ = new XYSeries("MMQ");
		
		BufferedReader br = Testar.executarAtributoNumerico(estatistica);
		Scanner sc = new Scanner(br);
		
		String x, y;
		double xMedia = 0;
		double yMedia = 0;
		double n = 0;
		
		while(sc.hasNext()) {
			x = sc.next();
			y = sc.next();
			
			Double[] novo = {Double.parseDouble(x), Double.parseDouble(y)};
			xMedia += novo[0];
			yMedia += novo[1];
			n += 1;
			
			serieReal.add(novo[0], novo[1]);
			if(Testar.modoAgrupamento != 4) {
				model.addElement("   "+String.format("%-26s", x) + y);
			}
			else {
				model.addElement("   "+String.format("%-26s", Testar.diaDaSemana.get(Integer.parseInt(x))) + y);
			}
			valoresXY.add(novo);
		}
		sc.close();
		br.close();
		xMedia = xMedia/n;
		yMedia = yMedia/n;
		
		double somaSuperior = 0;
		double somaInferior = 0;
		
		for(Double[] xy : valoresXY) {
			somaSuperior += xy[0] * (xy[1] - yMedia);
			somaInferior += xy[0] * (xy[0] - xMedia);
		}
		if(somaInferior != 0) {
			b = somaSuperior/somaInferior;
			a = yMedia - (b * xMedia);
		}
		else {
			b = 0;
			a = 0;
		}
		double xAux;
		for(Double[] xy : valoresXY) {
			xAux = xy[0];
			serieMMQ.add(xAux, funcao(xAux));
		}
		XYSeries[] series = {serieReal, serieMMQ};
		return series;
	}
	public Numerico(final String title, int estatistica) throws Exception {
		super(title);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String nomeEstatistica;
		if(estatistica==1)		nomeEstatistica = "Minimo";
		else if(estatistica==2)	nomeEstatistica = "Maximo";
		else if(estatistica==3)	nomeEstatistica = "Media";
		else if(estatistica==4)	nomeEstatistica = "Variancia";
		else if(estatistica==5)	nomeEstatistica = "Desvio Padrao";
		else					nomeEstatistica = "Desvio Medio";
		
		String agrupamento;
		int agrupamentoInt = Testar.modoAgrupamento;
		if(agrupamentoInt==1) agrupamento = "Ano";
		else if(agrupamentoInt==2) agrupamento = "Mes";
		else if(agrupamentoInt==3) agrupamento = "Dia Do Mes";
		else agrupamento = "Dia da Semana";
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		XYSeries[] series = preencherGraficoEMinQuadrado(model, nomeEstatistica, estatistica);
		
		final XYSeriesCollection data = new XYSeriesCollection();
		data.addSeries(series[0]);
		
		final JFreeChart chart = ChartFactory.createXYLineChart(
				Testar.getAtributo(), agrupamento, nomeEstatistica, data, PlotOrientation.VERTICAL,
				true, true, false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		
		getContentPane().setLayout(null);
		this.setBounds(450, 90, 600, 530);
		
		JScrollPane scroll1 = new JScrollPane();
		JScrollPane scroll2 = new JScrollPane();
		JPanel panel = new JPanel();
		
		scroll1.setBounds(30, 30, 530, 270);
		scroll2.setBounds(30, 359, 331, 104);
		
		panel.add(chartPanel, BorderLayout.CENTER);
		scroll1.setViewportView(panel);
		
		JList<String> list = new JList<String>();
		list.setModel(model);
		list.setFont(new Font("monospaced", Font.BOLD, 13));
		scroll2.setViewportView(list);
		
		this.getContentPane().add(scroll1);
		this.getContentPane().add(scroll2);
		
		JLabel lblAgrupamento = new JLabel("Agrupamento");
		lblAgrupamento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgrupamento.setBounds(53, 323, 130, 25);
		getContentPane().add(lblAgrupamento);
		
		JLabel lblValor = new JLabel(nomeEstatistica);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValor.setBounds(260, 323, 77, 25);
		getContentPane().add(lblValor);
		
		JFrame thisFrame = this;
		JButton btnVoltar = new JButton("Voltar para o inicio");
		btnVoltar.setBounds(160, 477, 200, 38);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			  	thisFrame.dispose();
			  	Periodo.exibir();
			}
		});
		getContentPane().add(btnVoltar);
		
		if(Testar.modoAgrupamento == 1) { //Por ano
			data.addSeries(series[1]);
			
			JTextField textField = new JTextField();
			textField.setBounds(440, 443, 86, 20);
			getContentPane().add(textField);
			textField.setColumns(10);
		
			JButton btnPrever = new JButton("Prever!");
			btnPrever.setBounds(437, 485, 100, 25);
			btnPrever.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
				  	try {
				  		double valorX = Double.parseDouble(textField.getText());
				  		JOptionPane.showMessageDialog(null, String.valueOf(funcao(valorX)));
				  	}
				  	catch(Exception ex) {
				  		JOptionPane.showMessageDialog(null, "Digite um numero no campo texto");
				  	}
				}
			});
			getContentPane().add(btnPrever);
			
			double x0 = 0;
			double x1 = 0;
			
			if(valoresXY.size() > 0) {
				x0 = valoresXY.getFirst()[0];
				x1 = valoresXY.getLast()[0];
			}
			
			JLabel lblNewLabel = new JLabel("Y0 = "+String.valueOf(funcao(x0)));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(426, 347, 200, 15);
			getContentPane().add(lblNewLabel);
		
			JLabel lblY = new JLabel("Y1 = "+String.valueOf(funcao(x1)));
			lblY.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblY.setBounds(426, 386, 200, 15);
			getContentPane().add(lblY);
		}
	}
	public static void exibir(String escolhido) {
		try {
			int estatistica = Integer.parseInt(escolhido);
			Numerico novo = new Numerico("Resultado", estatistica);
			novo.setVisible(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
