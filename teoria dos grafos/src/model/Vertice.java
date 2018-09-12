package model;

import java.util.ArrayList;
import java.util.TreeMap;

public class Vertice implements Comparable<Vertice>{
	
	private final String id;
	private ArrayList<Aresta> listaArestas;
	private TreeMap<Integer, Aresta> arvoreArestas;
	
	public Vertice(String nome) {
		this.id = nome;
		this.listaArestas = new ArrayList<>();
		this.arvoreArestas = new TreeMap<>();
	}
	
	public void addAresta(Aresta aresta) {
		this.listaArestas.add(aresta);
		this.arvoreArestas.put(aresta.getValor(), aresta);
	}
	
	public int grau() { return this.listaArestas.size(); }
	
	public ArrayList<Vertice> getAdjacentes() {
		ArrayList<Vertice> listaAdjacentes = new ArrayList<>();
		
		for(Aresta aresta: this.listaArestas) 
			listaAdjacentes.add(aresta.getAdjacente(this));
		
		return listaAdjacentes;
	}
	
	
	
	@Override
	public int compareTo(Vertice o) {
		return this.id.compareTo(o.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Vertice) {
			if(this.id.equals(((Vertice)obj).getId())) {
				return true;
			}else {
				return false;
			}
		}
		
		return false;
	}
	
	public String getId() { return id; }
	public ArrayList<Aresta> getListaArestas() { return listaArestas; }
	public void setListaArestas(ArrayList<Aresta> listaArestas) { this.listaArestas = listaArestas; }

}
