package model;

import java.util.ArrayList;

public class Airport {

    private ArrayList<Runway> runways;
    private Number resa = 240;
    private Number stripEnd = 60;
    private String name;

    public Airport() {
        runways = new ArrayList<Runway>();
    }

    public Airport(String resa, String stripEnd ,String name) throws Exception{//TODO Add validation for inputs here
        this.setStripEnd(Integer.valueOf(stripEnd));
        this.setResa(Integer.valueOf(resa));
        this.name = name;
        runways = new ArrayList<Runway>();

    }

    public Airport(Runway[] runs) {
        runways = new ArrayList<Runway>();

        for (Runway r : runs) {
            this.addRunway(r);
        }

    }

    public Airport(ArrayList<Runway> runs) {
        this.runways = runs;
    }


    public ArrayList<Runway> getRunways() {
        return runways;
    }

    public void addRunway(Runway runway) {
    	if(!runways.contains(runway)) {
    		runways.add(runway);
    	}
    }

    public String getName(){
        return name;
    }

    public void addRunways(ArrayList<Runway> runs) {
        for (Runway r : runs) {
            this.addRunway(r);
        }
    }

    public void removeRunway(Runway runway) {
        runways.remove(runway);
    }


    public void setResa(Number resa){
        this.resa = resa;
    }

    public Number getStripEnd() {
        return stripEnd;
    }


    public LogicalRunway addLogicalRunway(Runway run, String tora, String toda, String asda, String lda, String heading, char letter, String stopway, String clearway, String displacedThreshold) throws Exception {
        if (runways.contains(run)) {

            return run.addLogicalRunway(tora, toda, asda, lda, heading, letter, stopway, clearway, displacedThreshold);
        } else {
            return null;
        }
    }


    public void setStripEnd(Number n) throws Exception {
        if (n.intValue() < 0){
            throw new Exception("negative strip end value entered");
        }

        this.stripEnd = n;
    }
    public Number getResa() {
        return resa;
    }



    public boolean equals(Airport a) {
    	if(a == null) {
            return false;
    	}
    	if(a.getClass() != Airport.class) {
    		return false;
    	}
    	if(!a.getResa().equals(this.getResa())) {
    		return false;
    	}
    	if(!a.getStripEnd().equals(this.getStripEnd())) {
    		return false;
    	}
    	for(Runway r :this.getRunways()) {
    		if(!a.getRunways().contains(r)) {
    			return false;
    		}
    	}
        return true;
    }

/*
    public boolean equals(Object o) {
    	if(o instanceof Airport) {
    		return this.equals((Airport) o);
    	} else {
    		return super.equals(o);
    	}
    }
*/
    public void setName(String nm) {
        this.name = nm;
    }

}
