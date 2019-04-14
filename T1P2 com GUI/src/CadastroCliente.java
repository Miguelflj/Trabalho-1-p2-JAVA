import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;




@SuppressWarnings("serial")
public class CadastroCliente extends JFrame {
	
	private JLabel nomeFrame;
	
	//entrada de dados do novo cliente
	private JTextField cpf;
	private JTextField nome;
	private JTextField endereco;
	private JTextField telefone;
	private JTextField divida;
	
	private JButton voltar;
	private JButton inserir;
	
	//referencia para lista de clientes
	private Lista lc;
	
	private JLabel imagem;
	
	//referencia pro frame principal
	private MenuOps menu;
	
	public CadastroCliente(MenuOps menu,Lista lc) {
		
		this.menu = menu;
		this.lc = lc;
		
		JLabel c_cpf = new JLabel("CPF: ");
		cpf = new JTextField(20);
		
		JLabel c_nome = new JLabel("Nome: ");
		nome =  new JTextField(20);
		
		JLabel c_endereco = new JLabel("Endereço: ");
		endereco = new JTextField(20);
		
		JLabel c_telefone = new JLabel("Telefone: ");
		telefone = new JTextField(20);
		
		JLabel c_divida = new JLabel("Divida: ");
		divida = new JTextField("0.0",20);
		
		
		c_cpf.setHorizontalAlignment(JLabel.RIGHT);
		c_nome.setHorizontalAlignment(JLabel.RIGHT);
		c_endereco.setHorizontalAlignment(JLabel.RIGHT);
		c_telefone.setHorizontalAlignment(JLabel.RIGHT);
		c_divida.setHorizontalAlignment(JLabel.RIGHT);
		
		
		
		
		//botoes e panel e adiciona a captura de clique as botoes
		JPanel botoes = new JPanel();
		voltar = new  JButton("Voltar");
		inserir = new JButton("Inserir");
		TrataBotao tb = new TrataBotao();
		voltar.addActionListener(tb);
		inserir.addActionListener(tb);
		botoes.add(voltar);
		botoes.add(inserir);
		
		
		
		JPanel painel = new JPanel(new GridLayout(5,2));
		painel.add(c_cpf);
		painel.add(cpf);
		painel.add(c_nome);
		painel.add(nome);
		painel.add(c_endereco);
		painel.add(endereco);
		painel.add(c_telefone);
		painel.add(telefone);
		painel.add(c_divida);
		painel.add(divida);
		
		
		nomeFrame = new JLabel("CADASTRAR CLIENTE");
		nomeFrame.setHorizontalAlignment(JLabel.CENTER);
		
		//adiciona icone da barra de execução
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
		//imagem background
		Icon carro = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/carros_destaque1.png");
		imagem = new JLabel(carro);
		
		//seta cor de background
		this.getContentPane().setBackground(Color.LIGHT_GRAY); 
		painel.setBackground(Color.LIGHT_GRAY);
		botoes.setBackground(Color.LIGHT_GRAY);

		//adiciona panels e labels ao frame
		this.add(nomeFrame,BorderLayout.NORTH);
		this.add(painel,BorderLayout.CENTER);
		this.add(botoes,BorderLayout.SOUTH);
		this.add(imagem,BorderLayout.WEST);
		
		
		//configs do frame
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
	public JTextField getCpf() {
		return cpf;
	}
	public void cleanCpf() {
		this.cpf.setText("");
	}
	public JTextField getNome() {
		return nome;
	}
	public void cleanNome() {
		this.nome.setText("");
	}
	public JTextField getEndereco() {
		return endereco;
	}
	public void cleanEndereco() {
		this.endereco.setText("");
	}
	public JTextField getTelefone() {
		return telefone;
	}
	public void cleanTelefone() {
		this.telefone.setText("");
	}
	public JTextField getDivida() {
		return divida;
	}
	public void cleanDivida() {
		this.divida.setText("0.0");
	}
	
	
	
	
	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux == voltar) {
				menu.setVisible(true);
				setVisible(false);
			} else if( aux == inserir) {
				
				CadastraCliente(lc);
				cleanCpf();
				cleanDivida();
				cleanEndereco();
				cleanNome();
				cleanTelefone();
			}
			
		}
		
	}
	class TrataSaida extends WindowAdapter{
		
		public void windowClosing(WindowEvent e) {
			int resp = JOptionPane.showConfirmDialog(null, "Deseja sair?","LOCAHELLS",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			if(resp == JOptionPane.YES_OPTION) {
				arquivos maniArq = new arquivos();
				maniArq.upaArquivo(lc,"cliente");
				//maniArq.upaArquivo(lca,"carro");
				//maniArq.upaArquivo(lal, "aluguel");
				
				System.exit(0);
				
			} else {
				repaint();
			
				
			}
		}
	}
	
public void CadastraCliente(Lista l) {
		
		String nome_t = nome.getText();
		int cpf_t = Integer.parseInt(cpf.getText());
		String endereco_t = endereco.getText();
		float divida_t = Float.parseFloat(divida.getText());
		int telefone_t = Integer.parseInt(telefone.getText());
		//flag para saber se o cpf do cliente ja existe
		boolean flag = true;
		
		No aux = l.primeiro;
		while( aux != null) {
			cliente c = (cliente)aux.el;
			if(c.getCpf() == cpf_t) {
				flag = false;
			}
			aux = aux.prox;
		}
		if( flag == false) {
			JOptionPane.showMessageDialog(null, "Cliente Já Cadastrado!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			return;
		}
	
		
		cliente novo = new cliente(cpf_t,nome_t,endereco_t,telefone_t,divida_t);
		l.inserirNoFim(new No(novo));
		JOptionPane.showMessageDialog(null, "Cliente Cadastrado Com Sucesso!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
		
		
	}
}
