package Mercado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Produtos> produtos;
	private static Map<Produtos, Integer> carrinho;
	private static int id = 1;
	
	public static void main(String[]args) {
		produtos = new ArrayList<>();
		carrinho = new HashMap<>();
		menu();
	}
	
	private static void menu() {
	
	
			System.out.println("\n---------------------------------------------------------------");
			System.out.println("--------------------Bem-Vindo ao Supermercado------------------");
			System.out.println("----------------*Selecione o que deseja realizar*--------------");
			System.out.println("---------------------------------------------------------------");
			System.out.println("|   1 - Cadastar item    |");
			System.out.println("|   2 - Listar           |");
			System.out.println("|   3 - Comprar          |");
			System.out.println("|   4 - Carrinho         |");
			System.out.println("|   5 - Finalizar Compra |");
			System.out.println("|   6 - Sair             |");
			System.out.println("---------------------------------------------------------------");
			System.out.println("----------------*Digite a seguir a opção escolhida:------------\n");
		if (input.hasNextInt()) {
			int opcao = input.nextInt();
			
			switch (opcao) {
			case 1:
				cadastrarProdutos(); 
                break;
			case 2:
				listarProdutos();				
				break;
			case 3:
				comprarProdutos();				 
				break;
			case 4:
				verCarrinho(); 
				break;
			case 5:
				finalizarCompra();
				break;
			case 6:
				System.out.println("\nVai se FODER. Volta aqui mais não, seu MERDAA");
				System.exit(0);
				break;
			default:
				System.out.println("\nEscolhe direito filha da puta!");
				menu();
			
		}
	} else {
        System.out.println("\nDigita um número, filha da puta. Burro pra caralho");
        input.next(); 
        menu();
    }
}

	private static void cadastrarProdutos() {
		System.out.println("\nNome do produto: ");
		String nome = input.next();
		
		System.out.println("Preço do produto: ");
		if (input.hasNextDouble() ) {
			Double preco = input.nextDouble();
			Produtos produto = new Produtos (id, nome, preco);
			produtos.add(produto);
			
			System.out.println(produto.getNome() + " foi cadastrado com sucesso!");
			menu();
		}else{
			System.out.println("\nFaz o bagulho direito porra! Tiração do caraii \n");
			input.next(); 
			cadastrarProdutos();
		}	
	
	}
	
	private static void listarProdutos() {
		if (produtos.size() > 0) {
			System.out.println("\nLista de produtos \n");
			
			for(Produtos p: produtos) { //Para cada produto (p) dentro da lista de produtos
				System.out.println(p);
			}
		}else {
			System.out.println("\nTem porra nenhuma aqui não, comédia");
		}
		menu();
	}
	
	private static void comprarProdutos() {
		if(produtos.size() > 0) {
			// System.out.println("Código do Produto: \n");
			System.out.println("\n-----------------------Produtos Disponíveis--------------------");
			
			for(Produtos p: produtos) {
				System.out.println(p +"\n");
			}
			System.out.println("\nDigite a seguir o Id do produto para adcionar ao carrinho: ");
			if (input.hasNextInt() ){
				int id = input.nextInt();
				boolean isPresent = false;
	
	
			for(Produtos p: produtos) {
				if (p.getId() == id) { //Se o Id do produto for igual ao id digitado faça:
					int qtd;
					try {
						qtd = carrinho.get(p);
						// Checa se o produto já está no carrinho, incrementa quantidade.
						carrinho.put(p, qtd +1);
					}catch (NullPointerException e) {
						//Se o produto for o primeiro no carrinho
						carrinho.put(p, 1);
					}
					System.out.println(p.getNome()+" adicionado ao carrinho.");
					isPresent = true;
					break; // adicionado para interromper o loop caso o produto seja encontrado
				}
			}
			
			if(isPresent) {
				System.out.println("\nDeseja adicionar outro produto ao carrinho \n");
				System.out.println("\nDigite 1 para sim \nDigite 2 para ir ao menu \nDigite 0 para finalizar a compra. \n");
				if (input.hasNextInt() ){
					int opcao = Integer.parseInt(input.next());
		
					if(opcao == 1) {
						comprarProdutos();
					}else if(opcao == 2){
						menu();
					}else{
						finalizarCompra();
					}
				}else{
					System.out.println("\nVai se foder porra! Tá achando que eu sou otário é? Faz direito, carai \n");
					input.next(); 
					comprarProdutos();
				}
			} else {
				System.out.println("\nProduto existe não, Seu filha da puta! :)\n");
				menu();
			}
		}else{
			System.out.println("\nEi porra, tá de onda é? tu és Fresco, é? Digita essa porra direito!\n");
			input.next(); 
			comprarProdutos();
		}
		}else {
				System.out.println("\nTu cadastrou porra nenhuma não. Toma vergonha na tua cara!\n");
				menu();
		}
	
	}
	
			
		private static void verCarrinho() {
			double totalAtual = 0.0;
			System.out.println("\n***Produtos no seu carrinho!***\n");
			if(carrinho.isEmpty()) {
				System.out.println("\nTem nada aqui não, Pobre do caralho");
				menu();
			}else {
				
				for( Produtos p : carrinho.keySet()) {
					int qtd = carrinho.get(p);
				    totalAtual += p.getPreco() * qtd;
					System.out.println("Produto: "+ p +"\n Quantidade: "+ carrinho.get(p));
					
				}
			
				System.out.println("\nO total dessa merda é: "+ Dinheiro.doubleToString(totalAtual));
				if(totalAtual < 200){
					System.out.println("Pobre pra caralho, menos que 200 conto KKKK");
				}else if(totalAtual > 200 && totalAtual < 500){
					System.out.println("Compra mais coisa porra, tem quase nada aí");
				}else  if(totalAtual > 500){
					System.out.println("Eita carai, vai comprar a porra toda");
				}
				System.out.println("\nDigite 1 se deseja Finalizar a Compra? \nDigite qualquer dígito para ir ao Menu\n");
				if (input.hasNextInt() ){
					int opcao = Integer.parseInt(input.next());
					if(opcao == 1){
						finalizarCompra();
					}else{
						menu();
					}
				}else{
					System.out.println("\nPuta que pariuuuu, É pra digitar a porra de um número inteirooooo, CARALHOO\n");
					input.next(); 
					verCarrinho();
				}
			}
			
		}
		
		private static void finalizarCompra() {
			double valorCompra = 0.0;
			System.out.println("Seus produtos!");
			
			for(Produtos p : carrinho.keySet()) {
				int qtd = carrinho.get(p);
				valorCompra += p.getPreco() * qtd;
				System.out.println(p);
				System.out.println("Quantidade: "+ qtd);
			}
			System.out.println("\nO valor da compra é: "+ Dinheiro.doubleToString(valorCompra));
			carrinho.clear();
			System.out.println("\nLeva essa merda e enfia no CU! \n Volte sempre :)\n");
			menu();
		}
		
}


