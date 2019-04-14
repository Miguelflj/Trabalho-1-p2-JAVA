import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.*;



@SuppressWarnings("serial")
public class LocarCarro extends JFrame{
	private JTextField cpf;
	private JTextArea listaCarro;
	private Lista lca,lcl,lal;
	private JButton voltar;
	private JButton verificar;
	private JLabel nomeFrame;
	private MenuOps menu;
	private JLabel imagem;
	private JTextField ops;
	private JButton escol;
	public LocarCarro(Lista lca,Lista lcl,Lista lal,MenuOps menu ) {
		//referencia para listas de cliente,carro e alugueis e para menu
		this.menu = menu;
		this.lca = lca;
		this.lcl = lcl;
		this.lal = lal;
		
		JPanel painel = new JPanel(new GridLayout(3,2));
		
		nomeFrame = new JLabel("VERIFICA CPF");
		nomeFrame.setHorizontalAlignment(JLabel.CENTER);
		JLabel cpf_cli = new JLabel("Digite o CPF: ");
		JLabel opcao_t = new JLabel("Opção: ");
		opcao_t.setHorizontalAlignment(JLabel.RIGHT);
		ops = new JTextField(20);
		cpf_cli.setHorizontalAlignment(JLabel.RIGHT);
		JLabel cardisp = new JLabel("Carros Disponiveis: ");
		cardisp.setHorizontalAlignment(JLabel.RIGHT);
		
		listaCarro = new JTextArea(20,20);
		JScrollPane br = new JScrollPane(listaCarro);
		//this.add(br,BorderLayout.CENTER);
		cpf = new JTextField(20);
		painel.add(cpf_cli);
		painel.add(cpf);
		painel.add(cardisp);
		painel.add(br);
		painel.add(opcao_t);
		painel.add(ops);
		
		
		escol = new JButton("Escolha");
		TrataBotao tb = new TrataBotao();
		JPanel botoes = new JPanel();
		
		Icon carro = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/carros_destaque2.png");
		imagem = new JLabel(carro);
		
		
		voltar = new JButton("Voltar");
		verificar = new JButton("Verificar");
		escol.addActionListener(tb);
		voltar.addActionListener(tb);
		verificar.addActionListener(tb);
		
		botoes.add(voltar);
		botoes.add(verificar);
		botoes.add(escol);
		
		ImageIcon imagemTituloJanela = new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1.png");
		this.setIconImage(imagemTituloJanela.getImage());
		
		
		this.add(nomeFrame,BorderLayout.NORTH);
		this.add(painel,BorderLayout.CENTER);
		this.add(botoes,BorderLayout.SOUTH);
		this.add(imagem,BorderLayout.WEST);
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
	
	public void cleanCPF() {
		this.cpf.setText("");
	}
	
	class TrataBotao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux == voltar) {
				menu.setVisible(true);
				setVisible(false);
			} else if (aux == verificar) {
				listaCarro.setText("");
				int cpf_t = Integer.parseInt(cpf.getText());
				No auxt;
				auxt = lcl.primeiro;
				boolean flag = false;
				while(auxt != null) {
					cliente cl = (cliente)auxt.el;
					if(cpf_t == cl.getCpf() ) {
						flag = true;
						if( cl.getDivida() > 0.0f) {
							
							JOptionPane.showMessageDialog(null, "(Não é possível realizar a locação) \n       Cliente inadimplente. \n         VALOR: R$" + cl.getDivida(), "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
							listaCarro.setText("");
							cleanCPF();
							menu.setVisible(true);
							setVisible(false);
							//System.out.println();
							return;
						}
					}
					auxt = auxt.prox;
				}
				if(flag != true) {
					JOptionPane.showMessageDialog(null, "  Cliente inexistente! \n (CADASTRE PRIMEIRO)", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
					cleanCPF();
					listaCarro.setText("");
					menu.setVisible(true);
					setVisible(false);
					return;
				}
				//cleanCPF();
				int i = 1;
				auxt = lca.primeiro;
				while( auxt != null) {
					carro ca = (carro)auxt.el;
					if(ca.isSituacao() == false) {
						listaCarro.append("[" + i + "] Modelo: " + ca.getModelo() +"/ KM's:" + ca.getKm() + "/ Taxa P/Dia " + ca.getTaxa_dia() + "/ Taxa P/KM:" + ca.getTaxa_km() + "\n"  );
						i += 1;
					}	
					auxt = auxt.prox;
				}
				
				
				
			} else if(aux == escol) {
				LocalDate dt = LocalDate.now();
				int cpf_t = Integer.parseInt(cpf.getText());
				int n = Integer.parseInt(ops.getText());
				No auxt = lca.primeiro;
				int i = 1;
				carro ca = (carro)auxt.el;
				while( i <= n) {
					ca = (carro)auxt.el;
					if(ca.isSituacao() == false) {
						i += 1;
					}
					auxt = auxt.prox;
				}
				ca.setSituacao(true);
				String cod = cpf_t +" " +ca.getPlaca();
				aluguel novo = new aluguel(cod,dt.getYear(),dt.getMonthValue(),dt.getDayOfMonth());
				lal.inserirNoFim(new No(novo) );
				JOptionPane.showMessageDialog(null, "Carro Locado Com Sucesso!!", "LOCAHELLS", JOptionPane.OK_OPTION, new ImageIcon("/home/miguelfreitas/eclipse-workspace/MenuTb1/src/Icone_carro1(copiar).png"));
				
				cleanCPF();
				listaCarro.setText("");
				menu.setVisible(true);
				setVisible(false);
				return;
				//return lal;
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
