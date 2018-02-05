package lucas.web.scraperproj.domain;

public class Anuncio {
	private String nome;
	private Integer qtdeAvaliacoes;
	private String nota;
	private String endereco;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getQtdeAvaliacoes() {
		return qtdeAvaliacoes;
	}
	public void setQtdeAvaliacoes(Integer qtdeAvaliacoes) {
		this.qtdeAvaliacoes = qtdeAvaliacoes;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
