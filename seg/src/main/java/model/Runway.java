package model;

import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
import java.util.HashMap;

/*
All parameters can be from the left and right.
Think about how left, right and centre runways represented.
For each direction calculate both Take Off Towards, Landing Towards and Take off Away, Landing Over to give 4 calculations.
Perhaps more variables could be added to calculate these each time so original parameters also show.
Or runway could store new runway with calculations done to it.
 */
//The current runway being viewed by the GUI
public class Runway {
    //These define the original runway?
    private Obstacle obstacle;
    private HashMap<String,LogicalRunway> logicalRunways;
    
    //Direction number and L/R/C?
    public Runway(){
        logicalRunways = new HashMap<>();
        obstacle = null;
    }
    
    public Runway(HashMap<String,LogicalRunway> logicalRunways,Obstacle obs) throws Exception{
    	if(logicalRunways.size() > 2) {
    		throw new Exception("Cannot have more than 2 logical Runways for a single runway");
    	}
        this.logicalRunways = logicalRunways;
        obstacle = obs;
    }

    public LogicalRunway addLogicalRunway(String tora, String toda, String asda, String lda, String heading, char letter, String stopway, String clearway, String displacedThreshold) throws Exception{ //TODO validation here
    	if(this.logicalRunways.size() >= 2) {
    		throw new Exception("Cannot have more than 2 logical Runways for a single runway");
    	}
    	
    	LogicalRunway log = new LogicalRunway(tora,toda,asda,lda,heading,letter,stopway,clearway,displacedThreshold);
        logicalRunways.put(log.getCombinedReference(),log);
        return log;
    }
    
    public LogicalRunway addLogicalRunway(LogicalRunway log) throws Exception{ //TODO validation here
    	if(this.logicalRunways.size() >= 2) {
    		throw new Exception("Cannot have more than 2 logical Runways for a single runway");
    	}
    	logicalRunways.put(log.getCombinedReference(),log);
        return log;
    }

    //If no logical runways are present, throw exception.
    public void addObstacle(HashMap<String,Number> obstaclePosition, Number height, Number blastProtection) throws Exception{ //TODO validation here
    	if(!logicalRunways.isEmpty()) {
        Obstacle ob = new Obstacle(obstaclePosition,height,blastProtection);
        this.obstacle = ob;
            Integer smallest = Integer.MAX_VALUE;
            for(Number num : obstaclePosition.values()){
                if (smallest > num.intValue()){
                    smallest = num.intValue();
                }
            }

            /*
            for(LogicalRunway logical : logicalRunways.values()){
               
                System.out.println(logical.getCombinedReference());
                Integer number = (obstaclePosition.get(logical.getCombinedReference()).intValue());
                if (number.equals(smallest)){
                    System.out.println(logical.calculateTOALO(ob).intValue());
                } else {
                    System.out.println(logical.calculateTOTLT(ob).intValue());
                }

                 

            }
            */

    	}

    }

    public Calculation calculateTakeOffAway(LogicalRunway logical, Airport airport){
        return logical.calculateTakeOffAway(obstacle,airport);
    }

    public Calculation calculateLandingOver(LogicalRunway logical, Airport airport){
        return logical.calculateLandingOver(obstacle,airport);
    }

    public Calculation calculateLandingTowards(LogicalRunway logicalRunway, Airport airport){
        return logicalRunway.calculateLandingTowards(obstacle,airport);
    }

    public Calculation calculateTakeOffTowards(LogicalRunway logical, Airport airport){
        return logical.calculateTakeOffTowards(obstacle,airport);
    }
    public Obstacle getObstacle(){
        return obstacle;
    }
    
    public ArrayList<Calculation> calculateTakeOffAway(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateTakeOffAway(obstacle,airport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateLandingOver(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateLandingOver(obstacle,airport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateLandingTowards(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateLandingTowards(obstacle,airport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateTakeOffTowards(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateTakeOffTowards(obstacle,airport));
    	}
    	return ret;
    }

    public HashMap<String,LogicalRunway> getLogicalRunways(){
        return logicalRunways;
    }

}
