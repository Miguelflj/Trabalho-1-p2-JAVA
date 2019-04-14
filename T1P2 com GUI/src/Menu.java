//import java.io.IOException;
import java.util.Scanner;

public class Menu {
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int op;
		int cpf;
		boolean flag;
		Lista lcl,lca,lal;
		// lcl lista de cliente
		//lca lista de carros
		//lal lista de alugueis

		arquivos maniArq = new arquivos();
		operacoes ops = new operacoes();
		
		
		
		
		//bem aqui chama o metodo que inicializa a lista de cliente e carros e tudo funciona
		lcl = maniArq.carregaArquivo("cliente.txt");
		lal = maniArq.carregaArquivo("aluguel.txt");
		lca = maniArq.carregaArquivo("carro.txt");
		
		System.out.println("\n\n\n **********************MENU LOCAHEELS*********************** \n\n");
		do {
			System.out.println("1-Cadastrar Cliente \t\t 2-Cadastrar Carro\n3-Locar Carro       \t\t 4-Devolução Carro\n5-Relatório Locação \t\t 6-Relatório Carros \n7-Relatório Clientes \t\t 8-Quita Divida        \n        \t\t 0-SAIR           \n\n");
			
			op = input.nextInt();
			input.nextLine();
			
			switch(op) {
				case 1:
					lcl = ops.CadastraCliente(lcl);
					break;
				case 2:
					lca = ops.CadastraCarro(lca);
					break;
				case 3:
					System.out.println("Digite o cpf do cliente: ");
					cpf = input.nextInt();
					input.nextLine();
					flag = ops.ContemCliente(cpf, lcl);
					if( flag == false) {
						System.out.println("Primeiro eh preciso cadastrar o cliente!");
						lcl = ops.CadastraCliente(lcl);
					}
					
					ops.LocaCarro(lca , lal,lcl, cpf);
					
					break;
				case 4:
					System.out.println("Digite o codigo do aluguel: ");
					String codAluguel = input.nextLine();
					ops.DevolveCarro(lca, lal, lcl, codAluguel);
					break;
				case 5:
					ops.RelatorioAluguel(lal);
					break;
				case 8:
					System.out.println("Digite o cpf do cliente: ");
					cpf = input.nextInt();
					input.nextLine();
					System.out.println("Digite o valor pago: ");
					float valor = input.nextFloat();
					
					flag = ops.ContemCliente(cpf, lcl);
					if(flag == true) {
						ops.QuitaDivida(lcl, cpf, valor);
					}
					
					
					break;
				case 6:
					ops.RelatorioCarro(lca);
					break;
				case 7:
					ops.RelatorioCliente(lcl);
					break;
				case 0:
					//sai
					break;
				default:
					//op invalida
					break;
			}
		}while(op != 0);
		
		lcl.show();
		lca.show();
		
		maniArq.upaArquivo(lcl,"cliente");
		maniArq.upaArquivo(lca,"carro");
		maniArq.upaArquivo(lal, "aluguel");
		input.close();
	}
	
	
}
