public class ArvoreAVL{
	private Elemento ele;
	private ArvoreAVL 	dir;
	private ArvoreAVL 	esq;
	private int         balanceamento;
	
	public ArvoreAVL(){
		this.ele = null;
		this.dir = null;
		this.esq = null;
		this.balanceamento = 0;
	}
	
	public ArvoreAVL (Elemento elem){
		this.ele = elem;
		this.dir = null;
		this.esq = null;
		this.balanceamento = 0;
	}


	public int calculoAltura(){
		if (this.esq ==null && this.dir == null) 
			return 1;
		else if (this.esq != null && this.dir == null){
				return 1 + this.esq.calculoAltura();
			}
		else if (this.esq == null && this.dir != null){
				return 1+ this.dir.calculoAltura();
			}
		else{
				return 1 + Math.max(this.esq.calculoAltura(), this.dir.calculoAltura());
			}
	}

	public void calcularBalanceamento(){
		if (this.dir ==null && this.esq==null){
			this.balanceamento=0;
			}
			else if (this.esq == null && this.dir != null){
			this. balanceamento = this.dir.calculoAltura() - 0; 
			}
			else if (this.esq != null && this.dir == null){
				this.balanceamento = 0 - this.esq.calculoAltura();
			}
			else {
				this.balanceamento = this.dir.calculoAltura() - this.esq.calculoAltura();
			}
			if (this.dir != null) this.dir.calcularBalanceamento();
			if (this.esq != null) this.esq.calcularBalanceamento();
			
		}

	//verificar e rotacionar
	public ArvoreAVL verificaBalanceamento(){
		if(this.balanceamento >=2 || this.balanceamento <=-2){
			if (this.balanceamento>=2){
				if (this.balanceamento * this.dir.getBalanceamento () > 0){
					System.out.println("Rotacao Simples a Direita");
					return rotacaoSimplesDireita();
				}
				else{
					System.out.println("Rotacao Dupla a Direita");
					return rotacaoDuplaDireita();
				}
			}
			else{ //bal <=-2
				if (this.balanceamento * this.esq.getBalanceamento() > 0){
					System.out.println("Rotacao Simples a Esquerda");
					return rotacaoSimplesEsquerda();
				}
				else{
					System.out.println("Rotacao Dupla a Esquerda");
					return rotacaoDuplaEsquerda();
				}
			}
		}
		this.calcularBalanceamento();
		if (this.esq!= null) this.esq = this.esq.verificaBalanceamento();
		if (this.dir!=null) this.dir = this.dir.verificaBalanceamento();
		return this;
	}

	public ArvoreAVL rotacaoSimplesDireita(){
		ArvoreAVL filhoDir;
		ArvoreAVL filhoDoFilho = null;

		filhoDir = this.getDireita();
		if (this.dir != null){
			if (this.dir.getEsquerda()  != null){
				filhoDoFilho = filhoDir.getEsquerda();
			}

		}
		filhoDir.setEsquerda(this);
		this.setDireita(filhoDoFilho);

		return filhoDir;
	}
	public ArvoreAVL rotacaoSimplesEsquerda(){
		ArvoreAVL filhoEsq;
		ArvoreAVL filhoDoFilho = null;

		filhoEsq = this.getEsquerda();
		if (this.dir != null){
			if (this.esq.getDireita()  != null){
				filhoDoFilho = filhoEsq.getDireita();
			}

		}
		filhoEsq.setDireita(this);
		this.setEsquerda(filhoDoFilho);

		return filhoEsq;
	}
	public ArvoreAVL rotacaoDuplaEsquerda(){
		//alinhamento
		ArvoreAVL arvore = this;
		ArvoreAVL filhoEsq = this.getEsquerda();
		ArvoreAVL filhoDoFilho = filhoEsq.getDireita();
		ArvoreAVL noInserido = filhoDoFilho.getEsquerda();

		filhoEsq.setDireita(noInserido);
		filhoDoFilho.setEsquerda(filhoEsq);
		this.setEsquerda(filhoDoFilho);

		// tornar filho como nova raiz
		ArvoreAVL novoFilhoEsquerda = this.getEsquerda();
		arvore.setEsquerda(null);
		novoFilhoEsquerda.setDireita(arvore);
		return novoFilhoEsquerda;
	}
	public ArvoreAVL rotacaoDuplaDireita(){
		//alinhamento
		ArvoreAVL arvore = this;
		ArvoreAVL filhoDir = this.getDireita();
		ArvoreAVL filhoDoFilho = filhoDir.getEsquerda();
		ArvoreAVL noInserido = filhoDoFilho.getDireita();

		filhoDir.setEsquerda(noInserido);
		filhoDoFilho.setDireita(filhoDir);
		this.setDireita(filhoDoFilho);

		// tornar filho como nova raiz
		ArvoreAVL novoFilhoDireita = this.getDireita();
		arvore.setDireita(null);
		novoFilhoDireita.setEsquerda(arvore);
		return novoFilhoDireita;
	}
	//controle

	public boolean isEmpty(){
		return (this.ele ==null);
	}
	
	public void imprimirPreOrdem(){
		if(!isEmpty()){
			System.out.print(this.ele.getValor()+ " ");
			if(this.esq !=null){
				this.esq.imprimirPreOrdem();
			}
			if (this.dir !=null){
				this.dir.imprimirPreOrdem();
			}
		}
	}
	
	public void imprimirInOrdem(){
		if(!isEmpty()){
			if(this.esq != null){
				this.esq.imprimirInOrdem();
			}
			System.out.print(this.ele.getValor()+ " ");
			if (this.dir != null){
				this.dir.imprimirInOrdem();
			}
		}
	}
	
	public void imprimirPosOrdem(){
		if(!isEmpty()){
			if (this.dir != null){
				this.dir.imprimirPosOrdem();
			}
			if (this.esq !=null){
				this.esq.imprimirPosOrdem();
			}
			System.out.print(this.ele.getValor() + " ");
		}
	}
	
	public ArvoreAVL inserir(Elemento novo){
		if(isEmpty()){
			this.ele = novo;
		}
		else {
			ArvoreAVL novaArvore = new ArvoreAVL(novo);
			if (novo.getValor() < this.ele.getValor()){
				if(this.esq==null){
					this.esq=novaArvore;
				}
			else{
				this.esq = this.esq.inserir(novo);
			}
			}
			else if (novo.getValor() > this.ele.getValor()){
				if(this.dir==null){
					this.dir=novaArvore;
				}
				else{
					this.dir = this.dir.inserir(novo);
				}
			}
		}
		return this;
	}
	
	public ArvoreAVL remover (int elemento){
		if(this.ele.getValor() == elemento){
			if (this.esq == null && this.dir == null){
				return null;
			}
			
			else {
				if (this.esq !=null &&this.dir ==null){
					return this.esq;
				}
			else if (this.dir !=null && this.esq==null){
				return this.dir;
			}
			else{
				ArvoreAVL aux = this.dir;
				while (aux.esq != null){
					aux = aux.esq;
				}
				this.setElemento(aux.getElemento());
				aux.getElemento().setValor(elemento);
				this.dir = dir.remover(elemento);
			}
			}
		}
			else{
				if (elemento < this.ele.getValor() ){
					if(this.esq != null){
					this.esq = this.esq.remover(elemento);
					}
				}
				
				else if (elemento>this.ele.getValor()){
					if (this.dir !=null){
						this.dir = this.dir.remover(elemento);
					}
				}
				
			}
			return this;
			
	}
	
	public void setElemento(Elemento ele){
		this.ele = ele;
	}
	public void setDireita(ArvoreAVL dir){
		this.dir = dir;
	}
	public void setEsquerda(ArvoreAVL esq){
		this.esq = esq;
	}
	public int getBalanceamento(){
		return this.balanceamento;
	}
	public void setBalanceamento(int balanceamento){
		this.balanceamento = balanceamento;
	}
	public ArvoreAVL getDireita(){
		return this.dir;
	}
	public ArvoreAVL getEsquerda(){
		return this.esq;
	}
	public Elemento getElemento(){
		return this.ele;
	}
	
//mostra a arvore
public String printArvore(int level){
	String str=this.toString()+"\n";
	for (int i=0;i<level;i++){
			str += "\t";
	}
	if (this.esq!=null){
		
		str += "+-ESQUERDA:" + this.esq.printArvore(level+1);
	}
	else{
		str += "+-ESQUERDA: NULL";
	}
	str+="\n";
for (int i=0;i<level;i++){
			str += "\t";
		}
	if (this.dir !=null){
		
		str += "+-DIREITA:" + this.dir.printArvore(level+1);
	}
	else{
		str += "+-DIREITA: NULL";
	}
	str +="\n";
	return str;
	}

	public String toString(){
	return "["+this.ele.getValor()+"] ("+this.balanceamento+")";
	}

}