package lucas.webscraperproj.domain;

public class Quarto {
	private String nome;
	private String qtdePessoas;
	private Double preco;
	private String nota;
	
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQtdePessoas() {
		return qtdePessoas;
	}
	public void setQtdePessoas(String qtdePessoas) {
		this.qtdePessoas = qtdePessoas;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
