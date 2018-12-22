package graphics;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import executar.Testar;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import javax.swing.JButton;
import java.util.Scanner;

public class Moda {
	public JFrame frame;
	
	public Moda() throws Exception {
		initialize();
	}
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(400, 100, 534, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAtributoEntredata = new JLabel(Testar.getAtributo());
		
		lblAtributoEntredata.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAtributoEntredata.setBounds(240, 36, 243, 25);
		frame.getContentPane().add(lblAtributoEntredata);
		
		JLabel lblAgrupamento = new JLabel("Agrupamento");
		lblAgrupamento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgrupamento.setBounds(127, 97, 130, 17);
		frame.getContentPane().add(lblAgrupamento);
		
		JLabel lblModa = new JLabel("Moda");
		lblModa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModa.setBounds(320, 97, 120, 14);
		frame.getContentPane().add(lblModa);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		BufferedReader br = Testar.executarAtributoNaoNumerico();
		Scanner sc = new Scanner(br);
		
		int modoAgrupamento = Testar.modoAgrupamento;
		while(sc.hasNext()) {
			if(modoAgrupamento != 4) {
				model.addElement("       "+String.format("%-25s", sc.next()) + sc.next());
			}
			else {
				int diaDaSemana = Integer.parseInt(sc.next());
				model.addElement("       "+String.format("%-25s", Testar.diaDaSemana.get(diaDaSemana)) + sc.next());
			}
		}
		sc.close();
		br.close();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 122, 383, 185);
		
		JList<String> list = new JList<String>();
		list.setFont(new Font("monospaced", Font.BOLD, 13));
		list.setModel(model);
		
		scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);
		
		JButton btnVoltar = new JButton("Voltar para o inicio");
		btnVoltar.setBounds(160, 330, 200, 30);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Periodo.exibir();
			}
		});
		frame.getContentPane().add(btnVoltar);
	}
	public static void exibir() {
		try {
			Moda window = new Moda();
			window.frame.setVisible(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}