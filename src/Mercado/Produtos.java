package Mercado;

public class Produtos {
	private static int contar = 1;
	private int id;
	private String nome;
	private Double preco;
	
	public Produtos(int id,String nome, Double preco) {
		this.id=contar;
		this.nome = nome;
		this.preco = preco;
		Produtos.contar += 1;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "Id:"+ this.getId()+
				"\n Nome: "+ this.getNome()+
				"\n Preço"+ Dinheiro.doubleToString(this.getPreco());
	}
}
