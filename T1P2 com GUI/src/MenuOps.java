
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

//import java.net.URL;
import javax.swing.*;



@SuppressWarnings("serial")
public class MenuOps extends JFrame{
	
	@SuppressWarnings("unused")
	//botoes do frame
	private JButton exit;
	private JButton CadsCarro;
	private JButton CadsCliente;
	private JButton LocCarro;
	private JButton DevolCarro;
	private JButton relCarro;
	private JButton relCliente;
	private JButton relLocs;
	private JButton quitaDiv;
	
	//imagem de 'background'
	private JLabel imagem;
	
	
	//referencias para os outros frames
	private CadastroCliente cliente;
    private CadastroCarro TelaCarro;
    private LocarCarro Loc;
    private quitaDivida quitDiv;
    private DevolveCarro devolCarro;
    
    //listas para controle do model
    private Lista lcl,lca,lal;
    //lcl-cliente//lca-carro//lal-alugueis
  
	
    // nome do fram
	private JLabel nomeMenu;
	//usado para passar referencia deste frame dos relatórios
	private MenuOps atual;
	
	public MenuOps() {
		
		Scanner input = new Scanner(System.in);
		
		//control dos dados do meu trabalho
		arquivos maniArq = new arquivos();
		
		//carrega as listas com os dados ja existentes nos arquivos txt
		lcl = maniArq.carregaArquivo("cliente.txt");
		
		lal = maniArq.carregaArquivo("aluguel.txt");
		
		lca = maniArq.carregaArquivo("carro.txt");
		
		
		//grias os botoes
		exit = new JButton("Sair");
		CadsCarro = new JButton("Cadastrar Carro");
		CadsCliente = new JButton("Cadastrar Cliente");
		LocCarro = new JButton("Locar Carro");
		DevolCarro = new JButton("Devolver Carro");
		relCliente = new JButton("Relatório Cliente");
		relCarro = new JButton("Relatório Carros");
		relLocs = new JButton("Relatório Locações");
		quitaDiv = new JButton("Quitar Divida");
		
		
		//cria grid com 4 linhas(para colocar um botao a baixo do outro)
		GridLayout layout = new GridLayout(4,0);
		//espaçamento vertical entre os botoes
		layout.setVgap(15);
		
		//painel de botoes para colocar no west e east
		JPanel paineldir = new JPanel(layout);
		JPanel painelesq = new JPanel(layout);
		
		//cria e adiciona o captura de clique de cada botao
		TrataBotao tb = new TrataBotao();
		CadsCarro.addActionListener(tb);
		CadsCliente.addActionListener(tb);
		LocCarro.addActionListener(tb);
		DevolCarro.addActionListener(tb);
		relCliente.addActionListener(tb);
		relCarro.addActionListener(tb);
		relLocs.addActionListener(tb);
		quitaDiv.addActionListener(tb);
		
		
		//adiciona os botoes nos seus devidos paineis
		paineldir.add(CadsCarro);
		paineldir.add(CadsCliente);
		paineldir.add(LocCarro);
		paineldir.add(DevolCarro);
		
		painelesq.add(relCliente);
		painelesq.add(relCarro);
		painelesq.add(relLocs);
		painelesq.add(quitaDiv);
		
	
		
		//imagem na barra de em execucação(icone)
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
		
		nomeMenu = new JLabel("LOCADORA DE VEICULOS");
		nomeMenu.setHorizontalAlignment(JLabel.CENTER);
		
		//muda cor de backgroud
		this.getContentPane().setBackground(Color.LIGHT_GRAY); 
		paineldir.setBackground(Color.LIGHT_GRAY);  
		painelesq.setBackground(Color.LIGHT_GRAY);
		
		
		//imagem no background
		Icon carro = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/carros_destaque.png");
		imagem = new JLabel(carro);
		
		//adiciona os paineis no frame
		this.add(nomeMenu,BorderLayout.NORTH);
		this.add(paineldir,BorderLayout.WEST);
		this.add(painelesq,BorderLayout.EAST);
		this.add(imagem,BorderLayout.CENTER);
		
			
		
		//configs de janela
		this.setTitle("LOCAHELLS");
		this.setSize(500,400);
		//colocar janela no centro da tela
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//nao poder redimensionar o frame
		this.setResizable(false);
		
		//TrataSaida
		TrataSaida tef = new TrataSaida();	
		this.addWindowListener(tef);//registra com a fonte
		
		
		//cria todos os frames
		cliente = new CadastroCliente(this,lcl);
	    TelaCarro = new CadastroCarro(this,lca);
	   
	    quitDiv = new quitaDivida(this,lcl);
	    devolCarro = new DevolveCarro(this,lca,lcl,lal); 
		Loc = new LocarCarro(lca,lcl,lal,this);
		atual = this;
		
		input.close();
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
	
	
	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux == CadsCarro) {
				TelaCarro.setVisible(true);
				setVisible(false);
				
			} else if(aux == CadsCliente) {
				cliente.setVisible(true);
				setVisible(false);
				
			} else if(aux == LocCarro) {
				Loc.setVisible(true);
				setVisible(false);
				
			}else if(aux == DevolCarro) {
				devolCarro.setVisible(true);
				setVisible(false);
			}else if(aux == relCliente) {
				JOptionPane.showMessageDialog(null, "Relatório de Clientes Gerado!!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
				//showClientes sCl = new showClientes(lcl,atual);
				relatorioClientes sCl = new relatorioClientes(lcl,atual);
				sCl.setVisible(true);
				setVisible(false);
			} else if(aux == relCarro) {
				JOptionPane.showMessageDialog(null, "Relatório de Carros Gerado!!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
				//showCarros sCa = new showCarros(lca,atual);
				relatorioCarros sCa = new relatorioCarros(lca,atual);
				sCa.setVisible(true);
				setVisible(false);
			} else if(aux == relLocs) {
				JOptionPane.showMessageDialog(null, "Relatório de Locações Gerado!!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
				//showAlugueis sAl = new showAlugueis(lal,atual);
				relatorioAluguel sAl = new relatorioAluguel(lal,atual);
				sAl.setVisible(true);
				setVisible(false);
			}else if(aux == quitaDiv) {
				quitDiv.setVisible(true);
				setVisible(false);
			} else {
				System.out.println("Outro botao");
			}
		}
		
	}
	
}
