package it.polito.tdp.PremierLeague.model;

public class Evento {
	int Gteam1;
    int nEspulsi;
    boolean best;
	
	
	
	public Evento(int gteam1, int nEspulsi, boolean best) {
		super();
		Gteam1 = gteam1;
		this.nEspulsi = nEspulsi;
		this.best = best;
	}



	public enum EventType{
		GOAL,
		ESPULSIONE,
		INFORTUNIO
	}

}
