import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



@SuppressWarnings("serial")
public class relatorioCarros extends JFrame {
	private JTextArea carro;
	private JTable test;
	private JLabel nomeFrame;
	private JButton voltar;
	private MenuOps menu;
	private Lista lca;
	public relatorioCarros(Lista lc,MenuOps menu) {
		
		this.lca = lc;
		this.menu = menu;
		
		String[] colunas = {"Placa","Modelo","KM","Descrição","Situação","Taxa P/Dia","Taxa P/KM","Observações"};
		
		String [][] linhas = new String[lc.getTotalNos()+10][8];
		
		No aux = lc.primeiro;
		int i = 0;
		while( aux != null) {
			carro c = (carro)aux.el;
			
			linhas[i][0] = c.getPlaca();
			linhas[i][1] = c.getModelo();
			linhas[i][2] = String.format("%.2f",c.getKm());
			
			linhas[i][3] = c.getDescricao();
			if(c.isSituacao() == false) {
				linhas[i][4] = "Disponivel";
			} else {
				linhas[i][4] = "Alugado";
			}
			
			linhas[i][5] = String.format("%.2f",c.getTaxa_dia());
			linhas[i][6] = String.format("%.2f",c.getKm());
			linhas[i][7] = c.getObservacoes();
			i++;
			aux = aux.prox;
		}
		
		test = new JTable(linhas,colunas);
		JScrollPane br = new JScrollPane(test);
		this.add(br,BorderLayout.CENTER);
		
		//carro = new JTextArea(20,20);
		//JScrollPane br = new JScrollPane(carro);
		//this.add(br,BorderLayout.CENTER);
		
		nomeFrame = new JLabel("RELATÓRIO DE CARROS");
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
				
				maniArq.upaArquivo(lca,"carro");
				System.exit(0);
				
			} else {
				repaint();
			
				
			}
		}
	}
	
	public void RelatorioCarro(Lista l) {
		No aux = l.primeiro;
		while (aux != null) {
			carro c = (carro)aux.el;
			//System.out.println("Placa: " + c.getPlaca() + "\nModelo: "+ c.getModelo() + "\nKM's: "+ c.getKm()+ "\nTaxa_por_dia: " + c.getTaxa_dia() );
			//System.out.println( "Taxa_por_Km: " +c.getTaxa_km() + "\nDescrição: " + c.getDescricao() +"\nObservações: "+ c.getObservacoes());
			carro.append("Placa: " + c.getPlaca() + "\nModelo: "+ c.getModelo() + "\nKM's: "+ c.getKm()+ "\nTaxa_por_dia: " + c.getTaxa_dia() + "\n" );
			carro.append("Taxa_por_Km: " +c.getTaxa_km() + "\nDescrição: " + c.getDescricao() +"\nObservações: "+ c.getObservacoes() + "\n");
			if(c.isSituacao() == false) {
				carro.append("Disponível.\n");
			} else {
				carro.append("Alugado.\n");
			}
			carro.append("\n\n");
			aux = aux.prox;
		}
	}
	
}
