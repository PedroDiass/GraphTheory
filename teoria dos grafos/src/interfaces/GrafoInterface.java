package interfaces;

import java.util.ArrayList;
import java.util.TreeMap;

import model.Caminho;
import model.Vertice;

public interface GrafoInterface {
	
	public boolean isRegular();
	public boolean isCompleto();
	public boolean isConexo();
	
	public ArrayList<Vertice> getAdjacentes(String verticeId);
	
	public TreeMap<String, Caminho> menoresCaminhosDijkstra(String vertice);
	public Caminho menorCaminhoDijkstra(String vertice1, String vertice2);
	
}
