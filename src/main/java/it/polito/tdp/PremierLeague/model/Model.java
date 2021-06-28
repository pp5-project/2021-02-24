package it.polito.tdp.PremierLeague.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	private Map<Integer, Player> idMap;
	private PremierLeagueDAO dao;
	private SimpleDirectedWeightedGraph<Player, DefaultWeightedEdge> grafo;
	
	public Model(){
		dao=new PremierLeagueDAO();
		idMap=new HashMap<Integer,Player>();
		
					
				}
	
	public void CreaGrafo(int matchID) {
		this.grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		//aggiungo vertici
		Graphs.addAllVertices(grafo,dao.getPlayers(matchID));
		
		//aggiungo archi
		for(Player p:dao.getPlayers(matchID)) {
			for(Player a:dao.getPlayers(matchID)) {
				if(p.Team>a.Team) {
					if(p.efficiency>a.efficiency) {
			Graphs.addEdge(grafo, p, a, Math.abs(p.efficiency-a.efficiency));
		} else {
			Graphs.addEdge(grafo, a, p, Math.abs(a.efficiency-p.efficiency));
		}
				}
			}
		}
	}
	public String MigliorGiocatore(Set<Player> set) {
		String s=" MIGLIOR GIOCATORE: ";
		double diffmax=0;
		Player max=null;
		for(Player p:set) {
			double sommaint=0;
			double sommaout=0;
		for(DefaultWeightedEdge e:grafo.outgoingEdgesOf(p)) {
			sommaout+=grafo.getEdgeWeight(e);
		}
		
		for(DefaultWeightedEdge e:grafo.incomingEdgesOf(p)) {
			sommaint+=grafo.getEdgeWeight(e);
			}
		double diff=sommaout-sommaint;
		if(diff>diffmax) {
			max=p;
			diffmax=diff;
		}
		}
		s+=max.name+" "+diffmax;
		return s;
	}
	
	public Player MigliorGiocatore2(Set<Player> set) {
		double diffmax=0;
		Player max=null;
		for(Player p:set) {
			double sommaint=0;
			double sommaout=0;
		for(DefaultWeightedEdge e:grafo.outgoingEdgesOf(p)) {
			sommaout+=grafo.getEdgeWeight(e);
		}
		
		for(DefaultWeightedEdge e:grafo.incomingEdgesOf(p)) {
			sommaint+=grafo.getEdgeWeight(e);
			}
		double diff=sommaout-sommaint;
		if(diff>diffmax) {
			max=p;
			diffmax=diff;
		}
		}
		return max;
	}
	
	public String simula(Match m, int N) {
		Simulatore s=new Simulatore();
		s.init(N, m);
		s.run();
		return s.Result();
	}

	public int nVertici() {
		// TODO Auto-generated method stub
		return this.grafo.vertexSet().size();
	}
	public int NArchi() {
		// TODO Auto-generated method stub
		return this.grafo.edgeSet().size();
	}
	
	public Set<Player> getVertici(){
		return this.grafo.vertexSet();
	}
	public List<Match> getMatches(){
		return dao.listAllMatches();
	}
	
	
}
