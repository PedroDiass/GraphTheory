package model;

import java.util.TreeMap;

public class Menu {
	public static void main(String[] args) {
		
		Grafo grafo = dijkstraTest2();
		
		TreeMap<String, Caminho> caminhos = grafo.menoresCaminhosDijkstra("D");
		
		Caminho c = caminhos.get("E");	// obtem o menor caminho até C ( C pode ser substituido por qqr outro vertice do grafo )
		
		System.out.print("\nMenor Caminho = ");
		for(Aresta v: c.getCaminho()) {
			System.out.print("( " + v.getV1().getId() + ", " + v.getV2().getId() + " )" + "\t");
		}
		
		System.out.println("\nPeso do menor caminho = " + c.getValor());
		
	}
	
	static Grafo dijkstraTest2() {
		Grafo grafo = new Grafo();
		
		grafo.addVertice("A");
		grafo.addVertice("B");
		grafo.addVertice("C");
		grafo.addVertice("D");
		grafo.addVertice("E");
		grafo.addVertice("F");
		grafo.addVertice("G");
		grafo.addVertice("H");
		
		grafo.addAresta("A", "B", 3);
		grafo.addAresta("A", "C", 5);
		grafo.addAresta("A", "D", 2);
		grafo.addAresta("A", "H", 10);
		
		grafo.addAresta("B", "C", 5);
		grafo.addAresta("B", "D", 8);
		grafo.addAresta("B", "E", 4);
		grafo.addAresta("B", "G", 6);
		grafo.addAresta("B", "H", 6);
		
		grafo.addAresta("C", "E", 1);
		grafo.addAresta("C", "F", 7);
		grafo.addAresta("C", "G", 9);
		
		grafo.addAresta("D", "E", 12);
		grafo.addAresta("D", "H", 14);
		
		grafo.addAresta("E", "G", 15);
		
		grafo.addAresta("F", "H", 9);
		
		grafo.addAresta("G", "H", 3);
		
		return grafo;
	}
	
	// gera o grafo utilizado no slide do algoritmo de dijkstra.
	static Grafo dijkstraTest() {
		Grafo grafo = new Grafo();
		
		grafo.addVertice("v1");
		grafo.addVertice("v2");
		grafo.addVertice("v3");
		grafo.addVertice("v4");
		grafo.addVertice("v5");
		grafo.addVertice("v6");
		
		grafo.addAresta("v1", "v2", 10);
		grafo.addAresta("v1", "v3", 5);
		grafo.addAresta("v2", "v3", 3);
		grafo.addAresta("v2", "v4", 1);
		grafo.addAresta("v3", "v5", 2);
		grafo.addAresta("v4", "v5", 4);
		grafo.addAresta("v3", "v4", 9);
		grafo.addAresta("v5", "v6", 2);
		
		return grafo;
	}
	
	static Grafo isCompletoTest() {
		
		Grafo grafo = new Grafo();
		
		for(int i = 0; i < 5; i++) {
			grafo.addVertice(((Integer)(i + 1)).toString());
		}
		
		grafo.addAresta("1", "2");
		grafo.addAresta("1", "3");
		grafo.addAresta("1", "4");
		grafo.addAresta("1", "5");
		
		grafo.addAresta("2", "3");
		grafo.addAresta("2", "4");
		grafo.addAresta("2", "5");
		
		grafo.addAresta("3", "4");
		grafo.addAresta("3", "5");
		
		grafo.addAresta("4", "5");
		
		return grafo;
	}
}






