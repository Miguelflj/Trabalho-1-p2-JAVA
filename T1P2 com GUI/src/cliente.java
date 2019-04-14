
public class cliente {
	private int cpf;
	private String nome;
	private String endereco;
	private int telefone;
	private float divida;
	private cliente prox;
	
	
	// construtores
	public cliente () {
		this.cpf = 0;
		this.nome = "";
		this.endereco = "";
		this.telefone = 0;
		this.divida = 0.0f;
		this.prox = null;
	}
	public cliente (int aCpf,String aNome,String aEnd,int aTel,float aDiv) {
		this.cpf = aCpf;
		this.nome = aNome;
		this.endereco = aEnd;
		this.telefone = aTel;
		this.divida = aDiv;
		this.prox = null;
	}
	
	//gets
	public cliente getProx() {
		return this.prox;
	}
	public int getCpf() {
		return this.cpf;
	}
	public String getNome() {
		return this.nome;
	}
	public String getEndereco() {
		return this.endereco;
	}
	public int getTelefone() {
		return this.telefone;
	}
	public float getDivida() {
		return this.divida;
	}
	
	//gets
	public void setProx(cliente aProx) {
		this.prox = aProx;
	}
	public void setNome(String aNome) {
		this.nome = aNome;
	}
	public void setCpf(int aCpf) {
		this.cpf = aCpf;
	}
	public void setEndereco(String aEnde) {
		this.endereco = aEnde;
	}
	public void setTelefone(int aTel) {
		this.telefone = aTel;
	}
	public void setDivida(float aDiv) {
		this.divida = aDiv;
	}
	
	
	
}
