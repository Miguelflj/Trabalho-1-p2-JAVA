import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



@SuppressWarnings("serial")
public class quitaDivida extends JFrame {
	private JTextField valorPago;
	private JButton voltar;
	private JButton Pagar;
	private JTextField cpf;
	private MenuOps menu;
	private JLabel imagem;
	private JLabel nomeFrame;
	
	private Lista lcl;
	
	public quitaDivida(MenuOps menu,Lista lcl) {
		this.menu = menu;
		this.lcl = lcl;
		nomeFrame = new JLabel("QUITAR DIVIDA");
		nomeFrame.setHorizontalAlignment(JLabel.CENTER);
		JPanel painel = new JPanel(new GridLayout(2,2));
		valorPago = new JTextField("0.0",5);
		JLabel c_valorPago = new JLabel("Valor: R$");
		cpf = new JTextField(20);
		JLabel c_cpf = new JLabel("CPF: ");
		
		c_valorPago.setHorizontalAlignment(JLabel.RIGHT);
		c_cpf.setHorizontalAlignment(JLabel.RIGHT);
		painel.add(c_valorPago);
		painel.add(valorPago);
		painel.add(c_cpf);
		painel.add(cpf);
		
		voltar = new JButton("Voltar");
		Pagar = new JButton("Pagar");
		JPanel botoes = new JPanel();
		TrataBotao tb = new TrataBotao();
		
		voltar.addActionListener(tb);
		Pagar.addActionListener(tb);
		botoes.add(voltar);
		botoes.add(Pagar);
		
		//imagem na barra de em execucação
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
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
		
		
		
		//configs de janela
		this.setTitle("LOCAHELLS");
		this.setSize(500,400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		//TrataSaida
		TrataSaida tef = new TrataSaida();	
		this.addWindowListener(tef);//registra com a fonte
		
		
	}
	class TrataSaida extends WindowAdapter{
		
		public void windowClosing(WindowEvent e) {
			int resp = JOptionPane.showConfirmDialog(null, "Deseja sair?","LOCAHELLS",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			if(resp == JOptionPane.YES_OPTION) {
				
				arquivos maniArq = new arquivos();
				maniArq.upaArquivo(lcl,"cliente");
				System.exit(0);
				
			} else {
				repaint();
			
				
			}
		}
	}
	public void cleanValorPago() {
		this.valorPago.setText("0.0");
	}
	public void cleanCpf() {
		this.cpf.setText("");
	}
	
	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux == voltar) {
				menu.setVisible(true);
				setVisible(false);
			} else if (aux == Pagar){
				acertaValor();
			}
			
		}
		
	}
	
	public void acertaValor() {
		
		int cpf_t = Integer.parseInt(cpf.getText());
		
		No auxt = lcl.primeiro;
		boolean flag = false;
		while(auxt != null) {
			cliente cl = (cliente)auxt.el;
			if(cl.getCpf() == cpf_t) {
				flag = true;
				if(cl.getDivida() > 0) {
					float valor_p = Float.parseFloat(valorPago.getText());
					float dif = cl.getDivida() - valor_p;
					arquivos comprovante = new arquivos();
					
					if( valor_p > cl.getDivida() ) {
						dif = valor_p - cl.getDivida();
						cl.setDivida(0.0f);
						comprovante.geraComprovante(cpf_t, cl.getDivida(),dif);
						
					} else {
						dif = cl.getDivida() - valor_p;
						cl.setDivida(dif);
						comprovante.geraComprovante(cpf_t, valor_p,0.0f);
						
					}
					JOptionPane.showMessageDialog(null, "Pagamento efetuado com sucesso!!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
					cleanCpf();
					cleanValorPago();
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não possui divida!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
					cleanCpf();
					cleanValorPago();
					return;
				}
			}
			auxt = auxt.prox;
		}
		if(flag == false) {
			JOptionPane.showMessageDialog(null, "Cliente não existente!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			cleanCpf();
			cleanValorPago();
			return;
		}
		
	}
	
}
