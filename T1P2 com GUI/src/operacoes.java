import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
public class operacoes {
	Scanner in = new Scanner(System.in);
	
	
	public Lista CadastraCliente(Lista l) {
		
		System.out.println("Digite as infomações do cliente: \n");
		boolean flag = true;
		System.out.printf("CPF: ");
		int cpf = in.nextInt();
		No aux = l.primeiro;
		while( aux != null) {
			cliente c = (cliente)aux.el;
			if(c.getCpf() == cpf) {
				flag = false;
			}
			aux = aux.prox;
		}
		if( flag == false) {
			//cliente ja cadastrado
			return l;
		}
		
	
		System.out.printf("\nNome: ");
		in.nextLine();
		String nome = in.nextLine();
		
		System.out.printf("\nEndereco: ");
		String endereco = in.nextLine();
		System.out.printf("\nTelefone: ");
		int telefone = in.nextInt();
		float div = 0.0f;
		in.nextLine();
		
		cliente novo = new cliente(cpf,nome,endereco,telefone,div);
		l.inserirNoFim(new No(novo));
		
		
		return l;
	}
	
	public Lista CadastraCarro(Lista l) {
		
		boolean flag = true,situacao;
		String placa,desc,obs,modelo;
		float km,tx_km,tx_dia;
		No aux;
		
		System.out.println("Digite as informações do carro\n");
		System.out.printf("Placa: ");
		
		placa = in.nextLine();
		aux = l.primeiro;
		while( aux != null) {
			carro ca = (carro)aux.el;
			if( ca.getPlaca() == placa) {
				flag = false;
			}
			aux = aux.prox;
		}
		if(flag == false) {
			//carro ja cadastrado
			return l;
		}
		System.out.printf("Observcações: ");
		obs = in.nextLine();
		System.out.printf("Modelo: ");
		modelo = in.nextLine();
		System.out.printf("Descrições: ");
		desc = in.nextLine();
		System.out.printf("Taxa P/ Dia: ");
		tx_dia = in.nextFloat();
		System.out.printf("Taxa P/ KM: ");
		tx_km = in.nextFloat();
		System.out.printf("Kilometragem: ");
		km = in.nextFloat();
		situacao = false;
		
		carro novo = new carro(placa,modelo,desc,km,situacao,tx_dia,tx_km,obs);
		l.inserirNoFim(new No(novo));
		
		return l;
		
	}
	
	public boolean ContemCliente(int cpf,Lista l) {
		boolean flag = false;
		if( l.checkIfListaVazia() ){
			return true;
		} else {
			No aux = l.primeiro;
			while(aux != null ) {
				cliente c = (cliente)aux.el;
				if( c.getCpf() == cpf) {
					flag = true;
				}
				aux = aux.prox;
			}
			return flag;
		}
	}
	
	public Lista LocaCarro(Lista lca,Lista lal,Lista lcli, int cpf) {
		LocalDate dt = LocalDate.now();
		int i = 1,n;
		No aux;
		aux = lcli.primeiro;
		while(aux != null) {
			cliente cl = (cliente)aux.el;
			if(cpf == cl.getCpf() ) {
				if( cl.getDivida() > 0.0f) {
					System.out.println("(Não é possível realizar a locação) - Cliente inadimplente: valor R$" + cl.getDivida());
					return lal;
				}
			}
			aux = aux.prox;
		}
		
		
		aux = lca.primeiro;
		
		System.out.println("Carros disponiveis:");
		while( aux != null) {
			carro ca = (carro)aux.el;
			if(ca.isSituacao() == false) {
				System.out.println("[" + i + "] Modelo: " + ca.getModelo() +"/ KM's:" + ca.getKm() + "/ Taxa P/Dia " + ca.getTaxa_dia() + "/ Taxa P/KM:" + ca.getTaxa_km()  );
				i += 1;
			}	
			aux = aux.prox;
		}
		System.out.println("Numero do carro que deseja locar: ");
		n = in.nextInt();
		in.nextLine();
		
		
		aux = lca.primeiro;
		i = 1;
		carro ca = (carro)aux.el;
		while( i <= n) {
			ca = (carro)aux.el;
			i += 1;
			aux = aux.prox;
		}
		ca.setSituacao(true);
		String cod = cpf +" " +ca.getPlaca();
		aluguel novo = new aluguel(cod,dt.getYear(),dt.getMonthValue(),dt.getDayOfMonth());
		lal.inserirNoFim(new No(novo) );
		return lal;
		
		
	}
	
	public void DevolveCarro(Lista lca,Lista lal,Lista lcl,String codAluguel) {
		arquivos comprovante = new arquivos();
		LocalDate dt = LocalDate.now();
		int f_pg;
		float div = 0.0f;
		Period periodo;
		
		periodo = Period.between(dt, dt);
		
		String[] text = codAluguel.split(" ");
		int cpf = Integer.parseInt(text[0]);
		
		System.out.println("Forma de cobrança que deseja: ");
		System.out.println("[1] - Taxa por KM // [2] - Taxa por DIA ");
		f_pg = in.nextInt();
		in.nextLine();
		
		
		
		No aux = lal.primeiro;
		while(aux != null) {
			
			aluguel al = (aluguel)aux.el;
			//System.out.println("Setado: " + al.getCodigoAluguel());
			//System.out.println("vari: " + codAluguel);
			if(codAluguel.equals(al.getCodigoAluguel()) ) {
				
				al.setFimPeriodo(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth());
				periodo  = Period.between(al.getInicioPeriodo(),al.getFimPeriodo() );
			}
			aux = aux.prox;
		}
		aux = lca.primeiro;
		
		while(aux != null) {
			carro ca = (carro)aux.el;
			if( text[1].equals(ca.getPlaca()) ) {
				System.out.println("Digite a atual Kilometragem do carro");
				float km_atual = in.nextFloat();
				in.nextLine();
				if(f_pg == 1) {
					div = ca.getTaxa_km() * (km_atual - ca.getKm() );
				} else {
					div = ( periodo.toTotalMonths() * 30 ) *  ca.getTaxa_dia() + periodo.getDays();
				}
				ca.setKm(km_atual);
				ca.setSituacao(false);
			}
			aux = aux.prox;
		}
		
		System.out.println("Quitar divida: S or N: " + div);
		String op = in.nextLine();
		if( op.equals("S") ) {
			comprovante.geraComprovante(cpf, div,0.0f);
		} else {
			aux = lcl.primeiro;
			
			while(aux != null) {
				cliente cl = (cliente)aux.el;
				if(cpf == cl.getCpf() ) {
					cl.setDivida(div);
				}
				aux = aux.prox;
			}
		}
		
	}

	
	public void QuitaDivida(Lista lcl,int cpf,float valor_pago) {
		No aux;
		arquivos maniArq = new arquivos();
		aux = lcl.primeiro;
		while(aux != null) {
			cliente c = (cliente)aux.el;
			if(c.getCpf() == cpf) {
				if(valor_pago <= c.getDivida() ) {
					maniArq.geraComprovante(cpf, valor_pago,0.0f);
					c.setDivida(c.getDivida() - valor_pago );
				} else {
					maniArq.geraComprovante(cpf, valor_pago,valor_pago - c.getDivida());
					c.setDivida(0.0f );
				}
				
			}
			aux = aux.prox;
		}
	}
	
	public void RelatorioCarro(Lista l) {
		No aux = l.primeiro;
		while (aux != null) {
			carro c = (carro)aux.el;
			System.out.println("Placa: " + c.getPlaca() + "\nModelo: "+ c.getModelo() + "\nKM's: "+ c.getKm()+ "\nTaxa_por_dia: " + c.getTaxa_dia() );
			System.out.println( "Taxa_por_Km: " +c.getTaxa_km() + "\nDescrição: " + c.getDescricao() +"\nObservações: "+ c.getObservacoes());
			if(c.isSituacao() == false) {
				System.out.println("Disponível.\n");
			} else {
				System.out.println("Alugado.\n");
			}
			System.out.println("--\n\n");
			aux = aux.prox;
		}
	}

	public void RelatorioCliente(Lista l) {
		No aux = l.primeiro;
		while (aux != null) {
			cliente c = (cliente)aux.el;
			System.out.println("Nome: " + c.getNome() + "\nCPF: "+ c.getCpf() +  "\nEndereço: "+ c.getEndereco());
			System.out.println("Telefone: " + c.getTelefone() + "\nDivida: " + c.getDivida());
			System.out.println("--\n\n");
			aux = aux.prox;
		}
	}
	
	public void RelatorioAluguel(Lista l) {
		int ano,mes,dia;
		System.out.println("Ano do relatório: ");
		ano = in.nextInt();
		in.nextLine();
		System.out.println("Mes do relatório: ");
		mes = in.nextInt();
		in.nextLine();
		System.out.println("Dia do relatório: ");
		dia = in.nextInt();
		in.nextLine();
		
		LocalDate rl = LocalDate.of(ano, mes, dia);
		No aux = l.primeiro;
		while( aux != null) {
			aluguel al = (aluguel)aux.el;
			if( rl.isBefore(al.getInicioPeriodo() )  || rl.equals(al.getFimPeriodo() ) ){
				System.out.println("Codigo Aluguel: " + al.getCodigoAluguel() + "\nInicioAluguel: " + al.getInicioPeriodo() );
				if(al.getFimPeriodo() != null) {
					System.out.println("Fim do período: " +al.getFimPeriodo());
				} else {
					System.out.println("Aluguel em trâmite.\n");
				}
			}
			System.out.println("--\n\n");
			aux = aux.prox;
		}
	}

}

