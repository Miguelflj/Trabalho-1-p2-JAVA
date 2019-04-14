import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.Writer;
import java.time.LocalDate;

public class arquivos {
	
	public Lista carregaArquivo(String nome) {

		int qtdCli;
	    cliente c;
	    carro ca;
	    aluguel alu;
	    Lista l = new Lista();
	    
	    System.out.println(nome);
	    
	    System.out.printf("\nConteúdo do arquivo texto:\n");
	    try {
	      FileReader arq = new FileReader(nome);
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine();
	      System.out.printf("%s\n", linha);
	     
	      linha = lerArq.readLine(); // lê da segunda até a última linha
	      String[] textoSep = linha.split(" ");
	      qtdCli = Integer.parseInt(textoSep[1]);
	      System.out.println(qtdCli);
	      
	      while ( ( linha = lerArq.readLine() ) != null ) {
	    	  if("cliente.txt" == nome) {
	    		  
	    		  String[] textoSep1 = linha.split(";");  
	    		  String Nome = textoSep1[0];
	    		  int cpf = Integer.parseInt(textoSep1[1]);
	    		  String End = textoSep1[2];
	    		  int telefone = Integer.parseInt(textoSep1[3]);
	    		  float divida = Float.parseFloat(textoSep1[4]);
	    		  c = new cliente(cpf,Nome,End,telefone,divida);
	    		  l.inserirNoFim(new No(c));
	    		  //System.out.println(Nome + " Ok");
	    	  } else if ("carro.txt" == nome) {
	    		  
	    		  String[] textoSep2 = linha.split(";");
	    		  String placa = textoSep2[0];
	    		  String modelo = textoSep2[1];
	    		  float km = Float.parseFloat(textoSep2[2]);
	    		  boolean sit = Boolean.parseBoolean(textoSep2[3]);
	    		  String desc = textoSep2[4];
	    		  String obs = textoSep2[5];
	    		  float txdia = Float.parseFloat(textoSep2[6]);
	    		  float txkm = Float.parseFloat(textoSep2[7]);
	    		  
	    		  ca = new carro(placa,modelo,desc,km,sit,txdia,txkm,obs);
	    		  //System.out.println(ca.getModelo()+ " OK");
	    		  l.inserirNoFim(new No(ca));
	    	  } else if( "aluguel.txt" == nome) {
	    		  
	    		  String[] textoSep3 = linha.split(";");
	    		  if(textoSep3.length <= 5) {
		    		  String codAluguel = textoSep3[0];
		    		  int anoInicio = Integer.parseInt(textoSep3[1]);
		    		  int mesInicio = Integer.parseInt(textoSep3[2]);
		    		  int diaInicio = Integer.parseInt(textoSep3[3]);
		    		  alu = new aluguel(codAluguel,anoInicio,mesInicio,diaInicio);
	    		  } else {
	    			  String codAluguel = textoSep3[0];
		    		  int anoInicio = Integer.parseInt(textoSep3[1]);
		    		  int mesInicio = Integer.parseInt(textoSep3[2]);
		    		  int diaInicio = Integer.parseInt(textoSep3[3]);
		    		  int anoFim = Integer.parseInt(textoSep3[4]);
		    		  int mesFim = Integer.parseInt(textoSep3[5]);
		    		  int diaFim = Integer.parseInt(textoSep3[6]);
		    		  alu = new aluguel(codAluguel,anoInicio,mesInicio,diaInicio,anoFim,mesFim,diaFim);
	    		  }
	    		  l.inserirNoFim(new No(alu));
	    		 
	    	  }
	      }
	      //ler.close();
	 
	      arq.close();
	    } catch (IOException arq) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          arq.getMessage());
	    }
	 
	    System.out.println();
	    return l;
	}
	
	
	public void geraComprovante(int cpf,float div,float troco) {
		LocalDate hj = LocalDate.now();
		try {
			  String nome = "comprovante.txt";
			  FileWriter arq = new FileWriter(nome);
		      PrintWriter gravarArq = new PrintWriter(arq);
		      
		      gravarArq.printf("             LOCAHELLS LOCADORA DE CARROS\n");
		      gravarArq.printf("**********************Comprovante de Pagameto***************\n");
		      gravarArq.printf("CPF do cliente: " + cpf +"\n");
		      gravarArq.printf("Troco: " + troco + "\n");
		      gravarArq.printf("Pag em: "+ hj + "\n");
		      gravarArq.printf("Valor pago: R$" + div + "\n\n");
		      gravarArq.printf("LocalHells Agradece a preferência =D\n");
		      
		      arq.close();
		      gravarArq.close();
		      
		} catch (IOException arq) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          arq.getMessage());
	    }
	
	}
	
	
	
	public void upaArquivo(Lista l,String nome_arq)  {
		
		try {
			String nome = nome_arq + ".txt";
			FileWriter arq = new FileWriter(nome);
		    PrintWriter gravarArq = new PrintWriter(arq);
		 
		    gravarArq.printf("Dump_De_"+ nome +"_Da_LocaHells\n");
		    gravarArq.printf("Quantidade %d\n",l.totalNos);
		    No aux = (No)l.primeiro;
		    while(aux != null) {
		    	if(aux.el instanceof cliente) {
			    	cliente c = (cliente)aux.el;
			    	gravarArq.println(c.getNome() + ";" + c.getCpf() + ";" + c.getEndereco() + ";" + c.getTelefone() + ";" + c.getDivida());
		    	} else if(aux.el instanceof carro) {
		    		carro ca = (carro)aux.el;
		    		gravarArq.println(ca.getPlaca() + ";" + ca.getModelo() + ";" + ca.getKm() + ";" + ca.isSituacao() + ";"+ ca.getDescricao() + ";" + ca.getObservacoes() + ";" + ca.getTaxa_dia() + ";" + ca.getTaxa_km() );
		    	} else if( aux.el instanceof aluguel) {
		    		aluguel al = (aluguel)aux.el;
		    		if(al.getFimPeriodo() == null) {
		    			gravarArq.println(al.getCodigoAluguel() + ";" + al.getInicioPeriodo().getYear() + ";" + al.getInicioPeriodo().getMonthValue() + ";" + al.getInicioPeriodo().getDayOfMonth() + ";" + "S/FIM");
		    		} else {
		    			gravarArq.println(al.getCodigoAluguel() + ";" + al.getInicioPeriodo().getYear() + ";" + al.getInicioPeriodo().getMonthValue() + ";" + al.getInicioPeriodo().getDayOfMonth() + ";" + al.getFimPeriodo().getYear() + ";" + al.getFimPeriodo().getMonthValue() + ";" + al.getFimPeriodo().getDayOfMonth() );
		    		}
		    		
		    	}
			    aux = aux.prox;
		    }
		    gravarArq.close();
		    arq.close();
		} catch (IOException arq) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
			arq.getMessage());
		}
	}
}
