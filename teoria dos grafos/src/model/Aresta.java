package model;

public class Aresta implements Comparable<Aresta>{
	private final Vertice v1;
	private final Vertice v2;
	private int valor;
	
	public Aresta(Vertice v1, Vertice v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.valor = 1;
	}
	
	public Aresta(Vertice v1, Vertice v2, int valor) {
		this.v1 = v1;
		this.v2 = v2;
		this.valor = valor;
	}

	public boolean contains(Vertice v) { return (this.v1.equals(v2) || this.v2.equals(v2)); }
	
	public Vertice getAdjacente(Vertice v) {
		if (v == v1) {
			return v2;
		}else if (v == v2){
			return v1;
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Aresta other = (Aresta) obj;
		
		if( (this.getV1().getId().equals(other.getV1().getId())) && (this.getV2().getId().equals(other.getV2().getId())) ) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int compareTo(Aresta o) {
		
		if(this.equals(o)) {
			return 0;
		}else {
			if(Integer.compare(this.getValor(), o.getValor()) == 0) {
				return 1;
			}else {
				return Integer.compare(this.getValor(), o.getValor());
			}
		}
		
	}
	
	@Override
	public String toString() {
		return "Aresta [v1=" + v1.getId() + ", v2=" + v2.getId() + ", valor=" + valor + "]";
	}

	public Vertice getV1() { return v1; }
	public Vertice getV2() { return v2; }
	public int getValor() { return valor; }
	public void setValor(int valor) { this.valor = valor; }
	
}
