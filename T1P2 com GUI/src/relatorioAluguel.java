import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



@SuppressWarnings("serial")
public class relatorioAluguel extends JFrame {
	
	private JTable test;
	private JLabel nomeFrame;
	private JButton voltar;
	private MenuOps menu;
	private Lista lal;
	public relatorioAluguel(Lista lc,MenuOps menu) {
		
		this.lal = lc;
		this.menu = menu;
		
		String[] colunas = {"Codigo Aluguel","(In)Dia","(In)Mês","(In)Ano","(F)Dia","(F)Mês","(F)Ano",};
		
		String [][] linhas = new String[lc.getTotalNos()+10][7];
		No aux = lc.primeiro;
		int i = 0;
		while( aux != null) {
			aluguel c = (aluguel)aux.el;
			
			linhas[i][0] = c.getCodigoAluguel();
			linhas[i][1] = String.format("%d",c.getInicioPeriodo().getDayOfMonth());
			linhas[i][2] = String.format("%d",c.getInicioPeriodo().getMonthValue());
			linhas[i][3] = String.format("%d",c.getInicioPeriodo().getYear());
			if(c.getFimPeriodo() == null) {
				linhas[i][4] = "S/FIM";
				linhas[i][5] = "S/FIM";
				linhas[i][6] = "S/FIM";
			
			} else {
				linhas[i][4] = String.format("%d",c.getFimPeriodo().getDayOfMonth());
				linhas[i][5] = String.format("%d",c.getFimPeriodo().getMonthValue());
				linhas[i][6] = String.format("%d",c.getFimPeriodo().getYear());
			}
			i++;
			aux = aux.prox;
		}
		
		test = new JTable(linhas,colunas);
		JScrollPane br = new JScrollPane(test);
		this.add(br,BorderLayout.CENTER);
		
		
		
		nomeFrame = new JLabel("RELATÓRIO DE ALUGUÉIS");
		nomeFrame.setHorizontalAlignment(JLabel.CENTER);
		
		voltar = new JButton("Voltar");
		TrataBotao tb = new TrataBotao();
		voltar.addActionListener(tb);
		JPanel botoes = new JPanel();
		
		
		botoes.add(voltar);
		this.add(botoes,BorderLayout.SOUTH);
		this.add(nomeFrame,BorderLayout.NORTH);
		
		this.setTitle("LOCAHELLS");
		this.setSize(500,400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		//imagem na barra de em execucação
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
		
		//TrataSaida
		TrataSaida tef = new TrataSaida();	
		this.addWindowListener(tef);//registra com a fonte
		
		
		//RelatorioCarro(lc);
	}
	
	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux == voltar) {
				menu.setVisible(true);
				setVisible(false);
			}
		}
		
	}
	
	class TrataSaida extends WindowAdapter{
		
		public void windowClosing(WindowEvent e) {
			int resp = JOptionPane.showConfirmDialog(null, "Deseja sair?","LOCAHELLS",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			if(resp == JOptionPane.YES_OPTION) {
				arquivos maniArq = new arquivos();
				
				maniArq.upaArquivo(lal, "aluguel");
				System.exit(0);
			} else {
				repaint();
			
				
			}
		}
	}
}
