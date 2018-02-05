package lucas.webscraperproj.domain;

import java.util.ArrayList;
import java.util.List;

public class Anuncio extends EntidadeDominio{
	private String nome;
	private Integer qtdeAvaliacoes;
	private String nota;
	private String endereco;
	private List<Quarto> quartos;
	
	
	public Anuncio()
	{
		this.quartos = new ArrayList<Quarto>();
	}
	
	public List<Quarto> getQuartos() {
		return quartos;
	}
	public void setQuartos(List<Quarto> quartos) {
		this.quartos = quartos;
	}
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
