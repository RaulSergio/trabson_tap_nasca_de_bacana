package model;

public class Produto {
	
	private Integer id;
	private String nome;
	private double valor;
	
	public Produto(){
		
	};
	
	public Produto(String nome,double valor){
		this.nome = nome;
		this.valor = valor;
	};
	
	
	public Integer getId() {
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	

}
