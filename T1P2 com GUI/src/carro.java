
public class carro {
	private String placa;
	private String modelo;
	private String descricao;
	private float km;
	private boolean situacao;
	private float taxa_dia;
	private float taxa_km;
	private String observacoes;
	
	
	public carro() {
		this.placa = "";
		this.modelo = "";
		this.descricao = "";
		this.km = 0.0f;
		this.situacao = false;
		this.taxa_dia = 0.0f;
		this.taxa_km = 0.0f;
		this.observacoes = ""; 
	}
	
	public carro(String aPlaca,String aModelo,String aDesc,float aKm,boolean aSit,float aTd,float aTk,String aObs) {
		this.placa = aPlaca;
		this.modelo = aModelo;
		this.descricao = aDesc;
		this.km = aKm;
		this.situacao = aSit;
		this.taxa_dia = aTd;
		this.taxa_km = aTk;
		this.observacoes = aObs;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public float getKm() {
		return km;
	}


	public void setKm(float km) {
		this.km = km;
	}


	public boolean isSituacao() {
		return situacao;
	}


	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}


	public float getTaxa_dia() {
		return taxa_dia;
	}


	public void setTaxa_dia(float taxa_dia) {
		this.taxa_dia = taxa_dia;
	}


	public float getTaxa_km() {
		return taxa_km;
	}


	public void setTaxa_km(float taxa_km) {
		this.taxa_km = taxa_km;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
