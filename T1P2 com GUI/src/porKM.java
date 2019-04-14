import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.*;



public class porKM extends JFrame {
	
	private JLabel nomeFrame;
	
	private JTextArea valor;
	private JTextArea codigoAluguel;
	private JTextField novoKM;
	
	private JButton calcula;
	private JButton avista;
	private JButton credito;
	
	private JLabel imagem;
	
	private String codAluguel;
	private String placa;
	private int cpf;
	
	private Lista lcl,lca,lal;
	
	private float divida;
	private DevolveCarro dc;
	
	public porKM(Lista lcl,Lista lca,Lista lal,String codAluguel,String placa,int cpf,DevolveCarro dc) {
		this.codAluguel = codAluguel;
		this.cpf = cpf;
		this.placa = placa;
		this.dc = dc;
		this.lcl = lcl;
		this.lca = lca;
		this.lal = lal;
		
		nomeFrame = new JLabel("Cobrança por KM");
		
		nomeFrame.setHorizontalAlignment(JLabel.CENTER);
		
		
		JPanel painel = new JPanel(new GridLayout(3,2));
		//views e entrada de dado
		JLabel valor_t = new JLabel("Valor Divida: ");
		valor = new JTextArea(10,10);
		
		JLabel cod_t = new JLabel("Código Aluguel: ");
		codigoAluguel = new JTextArea(codAluguel,10,10);
		
		JLabel km_t = new JLabel("KM atual: ");
		novoKM = new JTextField(20);
		
		painel.add(valor_t);
		painel.add(valor);
		painel.add(cod_t);
		painel.add(codigoAluguel);
		painel.add(km_t);
		painel.add(novoKM);
		
		
		//painel botoes
		JPanel botoes = new JPanel();
		JLabel forma = new JLabel("Formas de pagamento:");
		avista = new JButton("A vista!");
		credito = new JButton("No Credito");
		calcula = new JButton("Calcula");
		
		TrataBotao tb = new TrataBotao();
		
		calcula.addActionListener(tb);
		avista.addActionListener(tb);
		credito.addActionListener(tb);
		
		botoes.add(calcula);
		botoes.add(forma);
		botoes.add(avista);
		botoes.add(credito);
		
		this.add(nomeFrame,BorderLayout.NORTH);
		this.add(painel,BorderLayout.CENTER);
		this.add(botoes,BorderLayout.SOUTH);
		
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
		//configs frame
		this.setTitle("LOCAHELLS");
		this.setSize(500,400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		//TrataSaida
		TrataSaida tef = new TrataSaida();	
		this.addWindowListener(tef);//registra com a fonte
	}
	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux == calcula) {
				LocalDate dt = LocalDate.now();
				Period periodo;
				
				periodo = Period.between(dt, dt);
				No auxt = lal.primeiro;
				//seta data de devolucao no aluguel
				while(auxt != null) {
					
					aluguel al = (aluguel)auxt.el;
					
					if(codAluguel.equals(al.getCodigoAluguel()) ) {
						
						al.setFimPeriodo(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth());
						periodo  = Period.between(al.getInicioPeriodo(),al.getFimPeriodo() );
					}
					auxt = auxt.prox;
				}
				
				
				//seta situacao do carro pra disponivel e o novo km
				auxt = lca.primeiro;
				while(auxt != null) {
					carro ca = (carro)auxt.el;
					if( placa.equals(ca.getPlaca()) ) {
						float km_atual = Float.parseFloat(novoKM.getText());
						
						divida = ca.getTaxa_km() * (km_atual - ca.getKm() );
						
						String diviz = String.format("%.2f",divida);
						valor.setText(diviz);
						ca.setKm(km_atual);
						ca.setSituacao(false);
					}
					auxt = auxt.prox;
				}
				
				
			} else if(aux == avista) {
				arquivos comprovante = new arquivos();
				comprovante.geraComprovante(cpf, divida,0.0f);
				dc.setVisible(true);
				setVisible(false);
				
			} else if(aux == credito) {
				No auxt = lcl.primeiro;
				
				while(auxt != null) {
					cliente cl = (cliente)auxt.el;
					if(cpf == cl.getCpf() ) {
						cl.setDivida(divida);
					}
					auxt = auxt.prox;
				}
				dc.setVisible(true);
				setVisible(false);
			}
		}
		
	}
	
	
	class TrataSaida extends WindowAdapter{
		
		public void windowClosing(WindowEvent e) {
			int resp = JOptionPane.showConfirmDialog(null, "Deseja sair?","LOCAHELLS",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			if(resp == JOptionPane.YES_OPTION) {
				arquivos maniArq = new arquivos();
				maniArq.upaArquivo(lcl,"cliente");
				maniArq.upaArquivo(lca,"carro");
				maniArq.upaArquivo(lal, "aluguel");
				
				System.exit(0);
				
			} else {
				repaint();
			
				
			}
		}
	}
}
