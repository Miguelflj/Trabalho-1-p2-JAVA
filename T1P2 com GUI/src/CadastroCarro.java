
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
public class CadastroCarro extends JFrame {
	
	private JLabel nomeFrame;
	
	//entrada dos dados do novo carro
	private JTextField placa;
	private JTextField modelo;
	private JTextField descricao;
	private JTextField km;
	private JTextField situacao;
	private JTextField taxa_dia;
	private JTextField taxa_km;
	private JTextField observacoes;
	
	
	private JButton inserir;
	private JButton voltar;
	
	//referencia pro frame que chamou
	private MenuOps menu;
	
	//referencia para lista de carros
	private Lista lca;
	//imagem para background
	private JLabel imagem;
	
	public CadastroCarro(MenuOps menu,Lista lca) {
		this.menu = menu;
		this.lca = lca;
		
		
		//labels de cada entrada de dados
		JLabel c_placa = new JLabel("Placa: ");
		placa = new JTextField(20);
		
		JLabel c_modelo = new JLabel("Modelo: ");
		modelo = new JTextField(20);
		
		JLabel c_descricao = new JLabel("Descrição: ");
		descricao = new JTextField(20);
		
		JLabel c_km = new JLabel("Kilometragem: ");
		km = new JTextField(20);

		JLabel c_situacao = new JLabel("Situação: ");
		situacao = new JTextField("null",20);
		
		JLabel c_taxa_dia = new JLabel("Taxa diária: ");
		taxa_dia = new JTextField(20);
		
		JLabel c_taxa_km = new JLabel("Taxa por KM: ");
		taxa_km = new JTextField(20);
		
		JLabel c_obs = new JLabel("Observações: ");
		observacoes = new JTextField(20);
		
		//cria botoes e seu devido panel (flowlayout) e adciona a captura de clique de cada botao
		JPanel botoes = new JPanel();
		voltar = new JButton("Voltar");
		inserir = new JButton("Inserir");
		TrataBotao tb = new TrataBotao();
		voltar.addActionListener(tb);
		inserir.addActionListener(tb);
		botoes.add(voltar);
		botoes.add(inserir);
		
		
		c_placa.setHorizontalAlignment(JLabel.RIGHT);
		c_modelo.setHorizontalAlignment(JLabel.RIGHT);
		c_situacao.setHorizontalAlignment(JLabel.RIGHT);
		c_taxa_dia.setHorizontalAlignment(JLabel.RIGHT);
		c_taxa_km.setHorizontalAlignment(JLabel.RIGHT);
		c_obs.setHorizontalAlignment(JLabel.RIGHT);
		c_descricao.setHorizontalAlignment(JLabel.RIGHT);
		c_km.setHorizontalAlignment(JLabel.RIGHT);
		
		nomeFrame = new JLabel("CADASTRAR CARRO");
		nomeFrame.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel painel = new JPanel(new GridLayout(8,2));
		painel.add(c_placa);
		painel.add(placa);
		painel.add(c_modelo);
		painel.add(modelo);
		painel.add(c_descricao);
		painel.add(descricao);
		painel.add(c_km);
		painel.add(km);
		painel.add(c_situacao);
		painel.add(situacao);
		painel.add(c_taxa_dia);
		painel.add(taxa_dia);
		painel.add(c_taxa_km);
		painel.add(taxa_km);
		painel.add(c_obs);
		painel.add(observacoes);
		
		//imagem background
		Icon carro = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/carros_destaque1.png");
		imagem = new JLabel(carro);
		
		//cor de background
		this.getContentPane().setBackground(Color.LIGHT_GRAY); 
		painel.setBackground(Color.LIGHT_GRAY); 
		botoes.setBackground(Color.LIGHT_GRAY); 
		
		//adiciona os panels ao frame
		this.add(imagem,BorderLayout.WEST);
		this.add(nomeFrame,BorderLayout.NORTH);
		this.add(painel,BorderLayout.CENTER);
		this.add(botoes,BorderLayout.SOUTH);
		
		//icone da barra de execuçao
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
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
	
	
	public JTextField getPlaca() {
		return placa;
	}

	public void cleanPlaca() {
		this.placa.setText("");
	}

	public JTextField getModelo() {
		return modelo;
	}

	public void cleanModelo() {
		this.modelo.setText("");
	}

	public JTextField getDescricao() {
		return descricao;
	}

	public void cleanDescricao() {
		this.descricao.setText("");
	}

	public JTextField getKm() {
		return km;
	}

	public void cleanKm() {
		this.km.setText("");
	}

	public JTextField getSituacao() {
		return situacao;
	}

	public void cleanSituacao() {
		this.situacao.setText("null");
	}

	public JTextField getTaxa_dia() {
		return taxa_dia;
	}

	public void cleanTaxa_dia() {
		this.taxa_dia.setText("");
	}

	public JTextField getTaxa_km() {
		return taxa_km;
	}

	public void cleanTaxa_km() {
		this.taxa_km.setText("");
	}

	public JTextField getObservacoes() {
		return observacoes;
	}

	public void cleanObservacoes() {
		this.observacoes.setText("");
	}


	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton)e.getSource();
			if(clicado == voltar) {
				menu.setVisible(true);
				setVisible(false);	
			} else if(clicado == inserir) {
				CadastraCarro(lca);
				cleanPlaca();
				cleanModelo();
				cleanDescricao();
				cleanKm();
				cleanSituacao();
				cleanTaxa_dia();
				cleanTaxa_km();
				cleanObservacoes();
			
			}
			
			
		}
		
	}
	
	class TrataSaida extends WindowAdapter{
		
		public void windowClosing(WindowEvent e) {
			int resp = JOptionPane.showConfirmDialog(null, "Deseja sair?","LOCAHELLS",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			if(resp == JOptionPane.YES_OPTION) {
				arquivos maniArq = new arquivos();
				//maniArq.upaArquivo(lcl,"cliente");
				maniArq.upaArquivo(lca,"carro");
				//maniArq.upaArquivo(lal, "aluguel");
				
				System.exit(0);
				
			} else {
				repaint();
			
				
			}
		}
	}
public void CadastraCarro(Lista l) {
		
		boolean flag = true;
		No aux;
		String placa_t = placa.getText();
		String modelo_t = modelo.getText();
		String desc_t = descricao.getText();
		float km_t = Float.parseFloat(km.getText());
		boolean situacao_t;
		float taxa_dia_t = Float.parseFloat(taxa_dia.getText());
		float taxa_km_t = Float.parseFloat(taxa_km.getText());
		String observacoes_t = observacoes.getText();
		
		
		aux = l.primeiro;
		while( aux != null) {
			carro ca = (carro)aux.el;
			if( ca.getPlaca() == placa_t) {
				flag = false;
			}
			aux = aux.prox;
		}
		if(flag == false) {
			JOptionPane.showMessageDialog(null, "Carro Já Cadastrado!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
			return;
		}
		//todo novo carro ainda nao foi alugado
		situacao_t = false;
		
		carro novo = new carro(placa_t,modelo_t,desc_t,km_t,situacao_t,taxa_dia_t,taxa_km_t,observacoes_t);
		l.inserirNoFim(new No(novo));
		JOptionPane.showMessageDialog(null, "Carro Cadastrado com Sucesso!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
	
		
	}
	
	
}
