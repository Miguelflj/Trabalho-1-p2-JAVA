import java.time.LocalDate;

public class aluguel {
	private String codigoAluguel;
	private LocalDate inicioPeriodo;
	private LocalDate fimPeriodo;
	
	//construtores
	public aluguel(String aCod,int ano,int mes,int dia) {
		this.codigoAluguel = aCod;
		this.inicioPeriodo = LocalDate.of(ano, mes, dia);
		this.fimPeriodo = null;
		
	}
	public aluguel(String aCod,int ano,int mes,int dia,int anof,int mesf,int diaf) {
		this.codigoAluguel = aCod;
		this.inicioPeriodo = LocalDate.of(ano, mes, dia);
		this.fimPeriodo = LocalDate.of(anof, mesf, diaf);
		
	}

	
	//gets e sets
	public String getCodigoAluguel() {
		return codigoAluguel;
	}

	public void setCodigoAluguel(String codigoAluguel) {
		this.codigoAluguel = codigoAluguel;
	}

	public LocalDate getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(int ano,int mes,int dia) {
		this.inicioPeriodo = LocalDate.of(ano,mes,dia);
	}

	public LocalDate getFimPeriodo() {
		return fimPeriodo;
	}

	public void setFimPeriodo(int ano,int mes,int dia) {
		this.fimPeriodo = LocalDate.of(ano,mes,dia);
	}
	
}	
