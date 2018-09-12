package model;

import java.util.LinkedList;

public class Caminho implements Comparable<Caminho>{
	
	private LinkedList<Aresta> caminho;
	private int valor;
	
	public Caminho() {
		this.caminho = new LinkedList<>();
		this.valor = 0;
	}
	
	public Caminho(int valor) {
		this.valor = valor;
	}
	
	public Caminho(LinkedList<Aresta> caminho, int valor) {
		this.caminho = caminho;
		this.valor = valor;
	}

	public void addAresta(Aresta aresta) { this.caminho.add(aresta); }
	
	public void incrementValor(int valor) { this.valor += valor; }

	public LinkedList<Aresta> getCaminho() { return caminho; }
	public void setCaminho(LinkedList<Aresta> caminho) { this.caminho = caminho; }
	public int getValor() { return valor; }
	public void setValor(int valor) { this.valor = valor; }

	@Override
	public int compareTo(Caminho o) {
		return Integer.compare(this.valor, o.getValor());
	}

	@Override
	public boolean equals(Object obj) {
		Caminho caminho = (Caminho) obj;
		
		if(this.valor != caminho.getValor()) {
			return false;
		}
		
		return this.caminho.equals(caminho.getCaminho());
	}
	
	@Override
	public String toString() {
		return "Caminho [caminho=" + caminho + ", valor=" + valor + "]";
	}
	
	
}
