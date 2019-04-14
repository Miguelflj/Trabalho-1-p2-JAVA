import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
//import java.time.Period;

import javax.swing.*;



@SuppressWarnings("serial")
public class DevolveCarro extends JFrame {
	
	
	//entrada de dados
	private JTextField cpf;
	private JTextField placa;
	
	
	private JTextField fimAluguel;
	private JLabel nomeFrame;
	

	
	private JButton p_km;
	private JButton p_dia;
	private JLabel imagem;
	
	private JButton voltar;
	private JButton verificar;
	private MenuOps menu;
	private Lista lca,lcl,lal;
	private DevolveCarro atual;
	
	
	public DevolveCarro(MenuOps menu,Lista lca,Lista lcl, Lista lal) {
		LocalDate dt = LocalDate.now();
		this.menu = menu;
		this.lca = lca;
		this.lcl = lcl;
		this.lal = lal;
		this.atual = this;
		
		//labels e entradas
		JPanel painel = new JPanel(new GridLayout(5,2));
		JLabel c_cpf = new JLabel("CPF: ");
		JLabel c_placa = new JLabel("Placa: ");
		JLabel c_dataInicio = new JLabel("Data devolução: ");
		
		c_cpf.setHorizontalAlignment(JLabel.RIGHT);
		c_placa.setHorizontalAlignment(JLabel.RIGHT);
		c_dataInicio.setHorizontalAlignment(JLabel.RIGHT);
		cpf = new JTextField(20);
		placa = new JTextField(20);
		
		verificar = new JButton("Verificar");
		p_km = new JButton("Por Kilometragem");
		p_dia = new JButton("Por dia");
		
		
		fimAluguel = new JTextField(String.format("%d",dt.getDayOfMonth())+ "/" + String.format("%d",dt.getMonthValue()) + "/" + String.format("%d",dt.getYear()),20);           
		painel.add(c_cpf);
		painel.add(cpf);
		painel.add(c_placa);
		painel.add(placa);
		painel.add(c_dataInicio);
		painel.add(fimAluguel);
		
		nomeFrame = new JLabel("DEVOLVER CARRO");
		nomeFrame.setHorizontalAlignment(JLabel.CENTER);
		// PAINEL BOTOES
		
		
		//cria botoes e a seu panel e a captura de acoes
		JPanel botoes = new JPanel();
		voltar = new JButton("Voltar");		
		TrataBotao tb = new TrataBotao();
		voltar.addActionListener(tb);
		verificar.addActionListener(tb);
		p_km.addActionListener(tb);
		p_dia.addActionListener(tb);
		botoes.add(voltar);
		botoes.add(verificar);
		botoes.add(p_km);
		botoes.add(p_dia);
		
		
		
		//imagem background
		Icon carro = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/carros_destaque1.png");
		imagem = new JLabel(carro);
		
		
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		painel.setBackground(Color.LIGHT_GRAY);
		botoes.setBackground(Color.LIGHT_GRAY);
		
		this.add(nomeFrame,BorderLayout.NORTH);
		this.add(painel,BorderLayout.CENTER);
		this.add(botoes,BorderLayout.SOUTH);
		this.add(imagem,BorderLayout.WEST);
		
		
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
		
		
		//configs frame
		this.setTitle("LOCAHELLS");
		this.setSize(500,400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		//TrataSaida
		TrataSaida tef = new TrataSaida();	
		this.addWindowListener(tef);//registra com a fonte
		
	}
	
	public JTextField getCpf() {
		return cpf;
	}
	public void cleanCpf() {
		this.cpf.setText("");
	}
	public JTextField getPlaca() {
		return placa;
	}
	public void cleanPlaca() {
		this.placa.setText("");
	}
	

	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux == voltar) {
				menu.setVisible(true);
				setVisible(false);
			} else if(aux == verificar) {
				Verifica(lal);
			} else if(aux == p_km) {
				String cod = cpf.getText() + " " + placa.getText();
				int cpf_t = Integer.parseInt(cpf.getText());
 				porKM km = new porKM(lcl,lca,lal,cod,placa.getText(),cpf_t,atual);
 				km.setVisible(true);
 				setVisible(false);
			} else if(aux == p_dia) {
				String cod = cpf.getText() + " " + placa.getText();
				int cpf_t = Integer.parseInt(cpf.getText());
 				porDia dia = new porDia(lcl,lca,lal,cod,placa.getText(),cpf_t,atual);
 				dia.setVisible(true);
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
	
	
	
	
	public void Verifica(Lista lal) {
		No auxt = lal.primeiro;
		String codAluguel = cpf.getText() + " " + placa.getText();
		boolean flag = false;
		while(auxt != null) {
			aluguel al = (aluguel)auxt.el;
			if(al.getCodigoAluguel().equals(codAluguel)) {
				flag = true;
			}
			auxt = auxt.prox;
		}
		if(flag == false) {
			JOptionPane.showMessageDialog(null, "Aluguel Inexistente!! \n (Verifique CPF ou Placa)", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			cleanCpf();
			cleanPlaca();
			menu.setVisible(true);
			setVisible(false);
			return;
		}
		JOptionPane.showMessageDialog(null, "Escolha a forma de pagamento!!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
		return;
		
	}
	

}
