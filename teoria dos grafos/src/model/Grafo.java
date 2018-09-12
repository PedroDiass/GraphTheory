
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

import interfaces.GrafoInterface;

public class Grafo implements GrafoInterface{
	
	private ArrayList<Vertice> verticesList;
	private TreeMap<String, Vertice> verticesMap;
	private int arestaCount;
		
	public Grafo() {
		this.verticesList = new ArrayList<>();
		this.verticesMap = new TreeMap<>();
		this.arestaCount = 0;
	}
	
	public void addVertice(String id) {
		Vertice vertice = new Vertice(id);
		
		this.verticesList.add(vertice);
		this.verticesMap.put(vertice.getId(), vertice);
	}
	
	// adiciona aresta com peso 1
	public Aresta addAresta(String vertice1, String vertice2) {
		// obtem os vertices especificados e instancia a nova aresta entre eles.
		Vertice v1 = this.verticesMap.get(vertice1);
		Vertice v2 = this.verticesMap.get(vertice2);
		Aresta aresta = new Aresta(v1, v2);
		
		v1.addAresta(aresta);
		v2.addAresta(aresta);
		
		// incrementa a contagem de arestas do grafo.
		this.arestaCount++;
		
		return aresta;
	}
	
	// adiciona aresta com peso variado 
	public Aresta addAresta(String vertice1, String vertice2, int peso) {
		Aresta aresta = this.addAresta(vertice1, vertice2);
		aresta.setValor(peso);
		
		return aresta;
	}
	
	@Override
	public boolean isRegular() {
		final int PRIMEIRO_ELEMENTO = this.verticesList.get(0).grau();

		for (int i = 1; i < this.verticesList.size(); i++) {
			if (verticesList.get(i).grau() != PRIMEIRO_ELEMENTO) {
				return false;
			}
		}
			
		return true;
	}
	
	@Override
	public boolean isCompleto() {
		// numero de arestas para que o grafo possa ser completo.
		final int MIN_ARESTAS = ((this.verticesList.size() * (this.verticesList.size() - 3)) / 2) + 
				this.verticesList.size(); 
		
		if(this.arestaCount == MIN_ARESTAS) { 
			return true;
		}else { 
			return false;
		}
	}
	
	@Override
	public boolean isConexo() {
		LinkedList<Vertice> fila = new LinkedList<>();
		HashSet<Vertice> verticesVisitados = new HashSet<Vertice>((int)(this.verticesList.size() * 1.10), 1.0f);
		long contador = 1;
		
		fila.add(this.verticesList.get(0));
		verticesVisitados.add(this.verticesList.get(0));
		
		while(fila.size() > 0) {
			
			for(Vertice v: fila.getFirst().getAdjacentes()) {
				if(! verticesVisitados.contains(v)) {
					fila.add(v);
					verticesVisitados.add(v);
					contador++;
				}
			}
			
			fila.removeFirst();
		}
		 
		System.out.println("Contador = " + contador + " | numero de vertices = " + this.verticesList.size());
		
		return (this.verticesList.size() == contador);
	}

	public TreeMap<String, Caminho> menoresCaminhosDijkstra(String vert) {
		PriorityQueue<Caminho> caminhos = new PriorityQueue<>();	   // armazena os caminhos encontrados 
		TreeMap<String, Caminho> menoresCaminhos = new TreeMap<>();    // armazena o menor caminho ate cada vertice
		TreeSet<String> verticesPercorridos = new TreeSet<>();		   // armazena os vertices percorridos (duuh)
		Caminho caminhoAux = new Caminho();    // armazena o caminho mais recentemente removido de "caminhos" 
		
		Vertice verticeAtual = this.verticesMap.get(vert);		// vertice para percorrer o grafo
		
		System.out.println("Ordem dos caminhos escolhidos pelo algoritmo: \n");
		
		verticesPercorridos.add(vert);
		while(verticesPercorridos.size() != this.verticesMap.size()) {
		
			for(Aresta aresta: verticeAtual.getListaArestas()) {
				// vertice oposta da vertice atual em relação a aresta atual (masuq ?).
				Vertice verticeOposta = aresta.getAdjacente(verticeAtual);
				
				// se a vertice atual ainda nao foi percorrida ( vertices que ja foram percorridas ja possuem seu caminho minimo ) 
				if(! verticesPercorridos.contains(verticeOposta.getId())) {
					
					@SuppressWarnings("unchecked")
					LinkedList<Aresta> arestasList = (LinkedList<Aresta>)caminhoAux.getCaminho().clone();
					arestasList.add(aresta);
					
					Caminho caminho = new Caminho(arestasList, caminhoAux.getValor() + aresta.getValor());
					
					// se nenhum caminho ate o verticeOposto existir até o momento
					if(menoresCaminhos.get(verticeOposta.getId()) == null) {
						caminhos.add(caminho);
						menoresCaminhos.put(verticeOposta.getId(), caminho);
					}else {
						// se o novo caminho for menor que o antigo
						if(menoresCaminhos.get(verticeOposta.getId()).getValor() > caminho.getValor()) { 
							caminhos.add(caminho);
							menoresCaminhos.put(verticeOposta.getId(), caminho);
						}
					}			
					
				}
				
			}
			
			caminhoAux = caminhos.poll();
			
			// Obs: prints usados para testar o que acontece durante a execução do algoritmo.
			for(Aresta a: caminhoAux.getCaminho()) {
				System.out.print("(" + a.getV1().getId() + ", " + a.getV2().getId() + ")\t");
			}
			System.out.print("Valor = " + caminhoAux.getValor() + "\n");
			
			// marca o vertice atual como percorrido 
			verticesPercorridos.add(verticeAtual.getId());	
			
			// atualiza "verticeAtual" para o proximo vertice a ser percorrido.
			verticeAtual = novoVerticeAtual(caminhoAux, verticesPercorridos);

		}
		
		return menoresCaminhos;
	}
	
	public Caminho menorCaminhoDijkstra(String vert1, String vert2) {
		
		PriorityQueue<Caminho> caminhos = new PriorityQueue<>();	   // armazena os caminhos encontrados 
		TreeMap<String, Caminho> menoresCaminhos = new TreeMap<>();    // armazena o menor caminho ate cada vertice
		TreeSet<String> verticesPercorridos = new TreeSet<>();		   // armazena os vertices percorridos (duuh)
		Caminho caminhoAux = new Caminho();    // armazena o caminho mais recentemente removido de "caminhos" 
		
		Vertice verticeAtual = this.verticesMap.get(vert1);		// vertice para percorrer o grafo
		Vertice verticeFim = this.verticesMap.get(vert2);
		
		System.out.println("Ordem dos caminhos escolhidos pelo algoritmo: \n");
		
		verticesPercorridos.add(vert1);
		while(verticeAtual != verticeFim) {
		
			for(Aresta aresta: verticeAtual.getListaArestas()) {
				// vertice oposta da vertice atual em relação a aresta atual (masuq ?).
				Vertice verticeOposta = aresta.getAdjacente(verticeAtual);
				
				// se a vertice atual ainda nao foi percorrida ( vertices que ja foram percorridas ja possuem seu caminho minimo ) 
				if(! verticesPercorridos.contains(verticeOposta.getId())) {
					
					@SuppressWarnings("unchecked")
					LinkedList<Aresta> arestasList = (LinkedList<Aresta>)caminhoAux.getCaminho().clone();
					arestasList.add(aresta);
					
					Caminho caminho = new Caminho(arestasList, caminhoAux.getValor() + aresta.getValor());
					
					// se nenhum caminho ate o verticeOposto existir até o momento
					if(menoresCaminhos.get(verticeOposta.getId()) == null) {
						caminhos.add(caminho);
						menoresCaminhos.put(verticeOposta.getId(), caminho);
					}else {
						// se o novo caminho for menor que o antigo
						if(menoresCaminhos.get(verticeOposta.getId()).getValor() > caminho.getValor()) { 
							caminhos.add(caminho);
							menoresCaminhos.put(verticeOposta.getId(), caminho);
						}
					}			
					
				}
				
			}
			
			caminhoAux = caminhos.poll();
			
			// Obs: prints usados para testar o que acontece durante a execução do algoritmo.
			for(Aresta a: caminhoAux.getCaminho()) {
				System.out.print("(" + a.getV1().getId() + ", " + a.getV2().getId() + ")\t");
			}
			System.out.print("Valor = " + caminhoAux.getValor() + "\n");
			
			// marca o vertice atual como percorrido 
			verticesPercorridos.add(verticeAtual.getId());	
			
			// atualiza "verticeAtual" para o proximo vertice a ser percorrido.
			verticeAtual = novoVerticeAtual(caminhoAux, verticesPercorridos);

		}
	
		return menoresCaminhos.get(vert2);
	
	}

	private Vertice novoVerticeAtual(Caminho caminhoAux, TreeSet<String> verticesPercorridos) {

		Aresta aresta = caminhoAux.getCaminho().getLast();
		
		Vertice v1 = aresta.getV1();
		Vertice v2 = aresta.getV2();
		
		if(verticesPercorridos.contains(v1.getId())) {
			return v2;
		}else if(verticesPercorridos.contains(v2.getId())){
			return v1;
		}
		
		return null;
		
	}
	
	@Override
	public ArrayList<Vertice> getAdjacentes(String id) { return this.getVertice(id).getAdjacentes(); }
	public Vertice getVertice(String id) { return this.verticesMap.get(id); }
	
}


//TreeMap<Caminho> caminhos = new TreeMap<>();
//TreeMap<String, Caminho> caminhosPercorridos = new TreeMap<>();
//TreeSet<String> verticesPercorridos = new TreeSet<>();
//Caminho caminhoAux = new Caminho(vert1);
//
//Vertice verticeAtual = this.verticesMap.get(vert1);		// vertice para percorrer o grafo
//Vertice verticeFim = this.verticesMap.get(vert2);
//
//System.out.println("Ordem dos caminhos escolhidos pelo algoritmo: \n");
//while(verticeAtual != verticeFim) {
//
//	for(Aresta aresta: verticeAtual.getListaArestas()) {
//		Vertice verticeOposta = aresta.getAdjacente(verticeAtual);
//		
//		if(! verticesPercorridos.contains(verticeOposta.getId())) {
//			
//			@SuppressWarnings("unchecked")
//			LinkedList<Aresta> arestasList = (LinkedList<Aresta>)caminhoAux.getCaminho().clone();
//			arestasList.add(aresta);
//			
//			Caminho caminho = new Caminho(arestasList, caminhoAux.getValor() + aresta.getValor());
//			
//			// se nenhum caminho ate o verticeOposto existir até o momento
//			if(caminhos.get(verticeOposta.getId()) == null) {
//				caminhos.put(verticeOposta.getId(), caminho);
//			}else {
//				// se o novo caminho for menor que o antigo
//				if(caminhos.get(verticeOposta.getId()).getValor() > caminho.getValor()) { 
//					caminhos.put(verticeOposta.getId(), caminho);
//				}
//			}			
//			
//		}
//		
//	}
//	
//	Entry<String, Caminho> entry = caminhos.pollFirstEntry();
//	System.out.println("Caminho Removido " + entry.getValue());
//	caminhosPercorridos.put(entry.getKey(), entry.getValue());
//	caminhoAux = entry.getValue();
//	
////	System.out.print("Arestas do caminho");
////	for(Aresta a: caminhoAux.getCaminho()) {
////		System.out.print("(" + a.getV1().getId() + ", " + a.getV2().getId() + ")");
////	}
//
//	
//	verticesPercorridos.add(verticeAtual.getId());
//	
//	verticeAtual = novoVerticeAtual(caminhoAux, verticesPercorridos);
//
//}
//System.out.println("\n\n\nProximo menor caminho = " + caminhos.pollFirstEntry().getValue() + "\n");
//return caminhosPercorridos.get(vert2);

