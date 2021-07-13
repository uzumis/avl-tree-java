import java.util.Scanner;
public class AppArvore{
	public static void main (String args[]){
Scanner in = new Scanner (System.in);
		int option;
		option=1;
		boolean arvorecriada;
		arvorecriada = false;
		boolean continuar;
		continuar = true;
		ArvoreAVL arvore = new ArvoreAVL();
		System.out.println ("Arvore AVL - Bora ver se funciona");
		while (option != 5) {
			System.out.println ("[1]- Criar Arvore - Inserir Itens");
			System.out.println ("[2]- Inserir Itens");
			System.out.println ("[3]- Remover Itens");
			System.out.println ("[4]- Imprimir");
			System.out.println ("[5]- Encerrar");		
			System.out.println ("-----------------------------------------------------");
			System.out.format("Escolha uma opção: ");
			option = in.nextInt();

			switch (option) {
					case 1:
					arvorecriada = true;
						System.out.println("Arvore Criada! Insira os itens \n");
						Scanner teclado = new Scanner(System.in);
							arvore =arvore.inserir(new Elemento (teclado.nextInt()));
							arvore.calcularBalanceamento();
							arvore = arvore.verificaBalanceamento();
							System.out.println(arvore.printArvore(0));//raiz 

								while(continuar==true){//inserir outros elementos

							arvore = arvore.inserir(new Elemento(teclado.nextInt()));
							arvore.calcularBalanceamento();
							arvore = arvore.verificaBalanceamento();
							System.out.println(arvore.printArvore(0));

							System.out.println("Continuar Inserindo? [1] - Sim ; [2] - Nao");
								int continuacao = new Scanner(System.in).nextInt();

									if (continuacao == 1){
										continuar=true;
										System.out.println("Insira um elemento novo:");
										}else if(continuacao == 2) {
										continuar=false;
								
									}

														}
					break;

					case 2:
					if (arvorecriada == false){
					System.out.println("Impossivel adicionar elemento. A arvore nao foi criada!");
					break;
				}
				else{
					continuar=true;
					System.out.println("Insira um novo Elemento: ");
					while(continuar==true){//inserir outros elementos
							Scanner teclado2 = new Scanner(System.in);
							arvore = arvore.inserir(new Elemento(teclado2.nextInt()));
							arvore.calcularBalanceamento();
							arvore = arvore.verificaBalanceamento();
							System.out.println(arvore.printArvore(0));

							System.out.println("Continuar Inserindo? [1] - Sim ; [2] - Nao");
								int continuacao = new Scanner(System.in).nextInt();

									if (continuacao == 1){
										continuar=true;
										System.out.println("Insira um elemento novo:");
										}else if(continuacao == 2) {
										continuar=false;
									
									}

				}
			}
					break;
							
					case 3:

					if (arvorecriada == false){
					System.out.println("Impossivel remover elemento. A arvore nao foi criada!");
					break;
				}else{
					System.out.println("Remova um Elemento: ");
					System.out.println("Atualmente, a arvore esta com estes elementos ");
					System.out.println(arvore.printArvore(0));
					Scanner teclado3 = new Scanner(System.in);
					arvore = arvore.remover(teclado3.nextInt());
							arvore.calcularBalanceamento();
							arvore = arvore.verificaBalanceamento();
							System.out.println("Removido com sucesso!\n");
							System.out.println("Sua arvore atual ficou: ");
							System.out.println(arvore.printArvore(0));
					}

					break;

					case 4: 


					if (arvorecriada == false){
					System.out.println("Impossivel imprimir elemento. A arvore nao foi criada!");
					break;
				}else{
					System.out.println("Atualmente, a arvore esta com estes elementos ");
					System.out.println(arvore.printArvore(0));
					break;
				}
					case 5:

					break;

		

			}
		}
System.out.println("Programa Encerrado!");
	}

}