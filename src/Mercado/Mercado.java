package Mercado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Produtos> produtos;
	private static Map<Produtos, Integer> carrinho;
	
	public static void main(String[]args) {
		produtos = new ArrayList<>();
		carrinho = new HashMap<>();
		menu();
	}
	
	private static void menu() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("--------------------Bem-Vindo ao Supermercado------------------");
		System.out.println("----------------*Selecione o que deseja realizar*--------------");
		System.out.println("---------------------------------------------------------------");
		System.out.println("|   1 - Cadastar item    |");
		System.out.println("|   2 - Listar           |");
		System.out.println("|   3 - Comprar          |");
		System.out.println("|   4 - Carrinho         |");
		System.out.println("|   6 - Finalizar Compra |");
		System.out.println("|   5 - Sair             |");
		System.out.println("---------------------------------------------------------------");
		System.out.println("----------------*Digite a seguir a opção escolhida:------------");
		
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
			System.out.println("Obrigado pela preferencia!");
			System.exit(0);
		default:
			System.out.println("Opção inválida!");
			menu();
		}
	}
	private static int id = 1;
	private static void cadastrarProdutos() {
		System.out.println("Nome do produto: ");
		String nome = input.next();
		
		System.out.println("Preço do produto: ");
		Double preco = input.nextDouble();
		
		Produtos produto = new Produtos (id, nome, preco);
		produtos.add(produto);
		
		System.out.println(produto.getNome() + " cadastrado com sucesso!");
		menu();
	}
	
	private static void listarProdutos() {
		if (produtos.size() > 0) {
			System.out.println("Lista de produtos \n");
			
			for(Produtos p: produtos) { //Para cada produto (p) dentro da lista de produtos
				System.out.println(p);
			}
		}else {
			System.out.println("Nenhum produto cadastrado!");
		}
		menu();
	}
	
	private static void comprarProdutos() {
		if(produtos.size() > 0) {
			System.out.println("Código do Produto: \n");
			System.out.println("-----------------------Produtos Disponíveis--------------------");
	
			for(Produtos p: produtos) {
				System.out.println(p +"\n");
			}
			System.out.println("Digite a seguir o Id do produto para adcionar ao carrinho: ");
	
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
				System.out.println("\nDeseja adicionar outro produto ao carrinho ");
				System.out.println("\nDigite 1 para sim \nDigite 2 para ir ao menu \nDigite 0 para finalizar a compra. \n");
				int opcao = Integer.parseInt(input.next());
	
				if(opcao == 1) {
					comprarProdutos();
				}else if(opcao == 2){
					menu();
				}else{
					finalizarCompra();
				}
			} else {
				System.out.println("Produto não encontrado!");
				menu();
			}
		}else {
				System.out.println("Não existem produtos cadastrados!");
				menu();
		}
	}
	
			
		private static void verCarrinho() {
			System.out.println("***Produtos no seu carrinho!***");
			if(carrinho.isEmpty()) {
				System.out.println("Carrinho vazio");
				menu();
			}else {
				
				for( Produtos p : carrinho.keySet()) {
					System.out.println("Produto: "+ p +"\n Quantidade: "+ carrinho.get(p));
				}
				System.out.println("\nDigite 1 se deseja Finalizar a Compra? \nDigite qualquer dígito para ir ao Menu");
				int opcao = Integer.parseInt(input.next());
				if(opcao == 1){
					finalizarCompra();
				}else{
					menu();
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
			System.out.println("O valor da compra é: "+ Dinheiro.doubleToString(valorCompra));
			carrinho.clear();
			System.out.println("Obrigado pela preferencia! \n Aproveite suas compras!");
			menu();
		}
		
}


