package it.polito.tdp.PremierLeague.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


public class Simulatore {

	private PriorityQueue<Evento> queue ;
	
	private int N;
    Match m;
    int Gteam1;
    int Gteam2;
    int nEspulsi1;
    int nEspulsi2;
    int T=0;
    Map<Integer,Evento> mappa;
    Model model;
	
	
	public void init(int N, Match m) {
		this.m=m;
		this.N=N;
		this.Gteam1=0;
		this.Gteam2=0;
		this.nEspulsi1=0;
		this.nEspulsi2=0;
		queue=new PriorityQueue<Evento>();
		model= new Model();
		model.CreaGrafo(m.getMatchID());
		mappa=new HashMap<Integer,Evento>();
		if(model.MigliorGiocatore2(model.getVertici()).Team==m.getTeamHomeID()){
			mappa.put(1, new Evento(0,0,true));
			mappa.put(0, new Evento(0,0,false));
			}
			else {
				mappa.put(0, new Evento(0,0,false));
				mappa.put(1, new Evento(0,0,true));
			}
		}
	
	public void run() {
		while(T<N) {	
		double num_1=Math.random()*10;
			if(num_1<=5) {
				if(mappa.get(1).nEspulsi>mappa.get(0).nEspulsi) {
					mappa.get(0).Gteam1++;
				}
				if(mappa.get(1).nEspulsi<mappa.get(0).nEspulsi) {
					mappa.get(1).Gteam1++;
				}
				if(nEspulsi1==nEspulsi2) {
					//giocatore migliore
					mappa.get(1).Gteam1++;
					
				}
			}
			if(num_1>5 && num_1<9) {
				//30%
				double num=Math.random();
				if(num<0.6) {
					mappa.get(1).nEspulsi++;
				}
				else
					mappa.get(0).nEspulsi++;
			}
			if(num_1>9) {
				//20%
				double num_2=Math.random();
				if(num_2<0.5) {
					this.N+=2;
				} else {
					this.N+=3;
				}
				
			}
			T++;
		}		
	}
	public String Result() {
		String s=m.teamHomeNAME+"-"+m.teamAwayNAME;
		if(model.MigliorGiocatore2(model.getVertici()).Team==m.getTeamHomeID()){
		s+="RISULTATO: "+mappa.get(1).Gteam1+"-"+mappa.get(0).Gteam1++;
		s+="ESPULSIONI:"+mappa.get(1).nEspulsi+"-"+mappa.get(0).nEspulsi++;
		}else {
			s+="RISULTATO: "+mappa.get(0).Gteam1+"-"+mappa.get(1).Gteam1++;
			s+="ESPULSIONI:"+mappa.get(0).nEspulsi+"-"+mappa.get(1).nEspulsi++;
		}
		return s;
		
	}
	
	
}
