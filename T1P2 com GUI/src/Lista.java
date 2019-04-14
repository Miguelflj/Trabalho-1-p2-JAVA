
public class Lista {
	No primeiro,ultimo;
    int totalNos;

    public Lista(){
        primeiro = ultimo = null;
        totalNos = 0;
    }

    public int getTotalNos(){
        return totalNos;
    }

    public boolean checkIfListaVazia(){
        if (getTotalNos() == 0){
            return true;
       }
       return false;
    }

    public void inserirNoInicio(No n) {
        if ( checkIfListaVazia() ){
            primeiro = ultimo = n;
        }
        else{
            n.prox = primeiro;
            primeiro = n;
        }
        totalNos++;
    }

    public void inserirNoFim(No n){
        // caso não existam nós inseridos,
        // insere o primeiro nó (n) na lista
        if ( checkIfListaVazia() ){
            primeiro = ultimo = n;
        }
        else{
            ultimo.prox = n;
            ultimo = n;
       }
       totalNos++;
    }

    public void excluirNo(No n){
        No noAtual;
        No noAnterior;
        noAtual = noAnterior = primeiro;
        int contador = 1;

        if (checkIfListaVazia() == false){
            while (contador <= getTotalNos() &&
                     noAtual.el != n.el){
                noAnterior = noAtual;
                noAtual = noAtual.prox;
                contador++;
            } 

		    if(noAtual.el == n.el){
		        if ( getTotalNos() == 1){
		            primeiro = ultimo = null;
		        }
		       else{
		           if (noAtual == primeiro){
		               primeiro = noAtual.prox;
		           }
		           else{
		               noAnterior.prox = noAtual.prox;
		           }
		       }
		       totalNos--;
		    }
        }
    }
    
    public void show() {
    	if(this.totalNos == 0) {
    		System.out.println("Lista vazia =( \n");
    	} else {
    		No aux = this.primeiro;
    		System.out.printf("[");
    		while( aux != null) {
    			if( aux.el instanceof cliente) {
    				cliente c = (cliente)aux.el;
    				if( aux == this.primeiro ) {
    					System.out.printf(c.getNome());
    				} else {
    					System.out.printf(", " + c.getNome());
    				}
    				
    				
    			} else if ( aux.el instanceof carro) {
    				carro ca = (carro)aux.el;
    				if(aux == this.primeiro) {
    					System.out.printf(ca.getPlaca());
    				} else {
    					System.out.printf(", " + ca.getPlaca());
    				}
    			}
    			aux = aux.prox;
    		}
    		System.out.println("]");
    	}
    }
    
}
