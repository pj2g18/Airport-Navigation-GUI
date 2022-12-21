package model;

import java.util.ArrayList;

public class Airport {

    private ArrayList<Runway> runways;
    private Number resa = 240;
    private Number stripEnd = 60;

    public Airport() {
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

    public void addRunways(ArrayList<Runway> runs) {
        for (Runway r : runs) {
            this.addRunway(r);
        }
    }

    public void removeRunway(Runway runway) {
        runways.remove(runway);
    }

    public Number getResa() {
        return resa;
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


    public void setStripEnd(Number n) {
        this.stripEnd = n;
    }

}
