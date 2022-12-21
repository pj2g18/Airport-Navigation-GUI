package model;

import java.util.ArrayList;
import java.util.HashMap;

//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;

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
    private String name = "";

    //Direction number and L/R/C?
    public Runway(String name){
        this.name = name;
        logicalRunways = new HashMap<String, LogicalRunway>();
        obstacle = null;
    }

    public Runway(){
        this.name = "";
        logicalRunways = new HashMap<String, LogicalRunway>();
        obstacle = null;
    }
    
    public Runway(HashMap<String,LogicalRunway> logicalRunways,Obstacle obs) throws Exception{
    	if(logicalRunways.size() > 2) {
    		throw new Exception("Cannot have more than 2 logical Runways for a single runway");
    	}
        this.logicalRunways = logicalRunways;
        obstacle = obs;
    }
    public Runway(String name, HashMap<String,LogicalRunway> logicalRunways,Obstacle obs) throws Exception{
        if(logicalRunways.size() > 2) {
            throw new Exception("Cannot have more than 2 logical Runways for a single runway");
        }
        this.name = name;
        this.logicalRunways = logicalRunways;
        obstacle = obs;
    }

    public Runway(String name, HashMap<String,LogicalRunway> logicalRunways) throws Exception{
        if(logicalRunways.size() > 2) {
            throw new Exception("Cannot have more than 2 logical Runways for a single runway");
        }
        this.name = name;
        this.logicalRunways = logicalRunways;

    }

    public LogicalRunway addLogicalRunway(String tora, String toda, String asda, String lda, String heading, char letter, String stopway, String clearway, String displacedThreshold) throws Exception{ //TODO validation here
    	if(this.logicalRunways.size() >= 2) {
    		throw new Exception("Cannot have more than 2 logical Runways for a single runway");
        }
        int sum = 0;
        if(this.logicalRunways.size() == 1){
            for (String key : logicalRunways.keySet()){
                sum =Integer.parseInt(key.substring(0,2) );
            }
            sum = Math.abs(sum - Integer.parseInt(heading));

            if (sum != 18){
                throw new Exception("logical runways are not parallel and heading in opposite directions");
            }
        }

    	LogicalRunway log = new LogicalRunway(tora,toda,asda,lda,heading,letter,stopway,clearway,displacedThreshold);


        if(Integer.parseInt(heading) >= 0 && Integer.parseInt(heading) < 10 && heading.length() == 1) {
            name += " 0" + heading + letter;
        } else {name += " " + heading + letter;}

        logicalRunways.put(heading+letter,log);
        return log;
    }

    public String getName(){
        return name;
    }

    public LogicalRunway addLogicalRunway(LogicalRunway log) throws Exception{ //TODO validation here
    	if(this.logicalRunways.size() >= 2) {
            throw new Exception("Cannot have more than 2 logical Runways for a single runway");
        }

    	int sum = 0;
        if(this.logicalRunways.size() == 1){
            for (String key : logicalRunways.keySet()){
                if (log.getHeading().toString().length() == 2) {
                    sum = Integer.parseInt(key.substring(0, 2));
                }else{
                    sum = Integer.parseInt(key.substring(0, 1));
                }
            }
            if (log.getHeading().toString().length() == 2) {
                sum = Math.abs(sum - Integer.parseInt(log.getHeading().toString().substring(0, 2)));

                if (sum != 18) {
                    throw new Exception("logical runways are not parallel and heading in opposite directions");
                }
            }
            else{
                sum = Math.abs(sum - Integer.parseInt(log.getHeading().toString().substring(0, 1)));

                if (sum != 18) {
                    throw new Exception("logical runways are not parallel and heading in opposite directions");
                }

            }
        }

        //LogicalRunway log = new LogicalRunway(tora,toda,asda,lda,heading,letter,stopway,clearway,displacedThreshold);


        if(Integer.parseInt(log.getHeading().toString()) >= 0 && Integer.parseInt(log.getHeading().toString()) < 10 && log.getHeading().toString().length() == 1) {
            name += "0" + log.getHeading().toString();
            log.setHeading(Integer.parseInt(name));
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

    	}else{
            throw new Exception("No logical runways present");
        }

    }

    public void addObstacle(HashMap<String,Number> obstaclePosition, Number height, Number blastProtection,Number centre) throws Exception { //TODO validation here
        if (!logicalRunways.isEmpty()) {
            Obstacle ob = new Obstacle(obstaclePosition, height, blastProtection, centre);
            this.obstacle = ob;
            Integer smallest = Integer.MAX_VALUE;
            for (Number num : obstaclePosition.values()) {
                if (smallest > num.intValue()) {
                    smallest = num.intValue();
                }
            }

        }else{
            throw new Exception("No logical runways present");
        }
    }

    public LogicalRunway getLargestLogicalRunway(){
        LogicalRunway largest = null;
        for(LogicalRunway log : logicalRunways.values()){
            if (largest == null){
                largest = log;
            }else if(largest.getHeading().intValue() < log.getHeading().intValue()){
                largest = log;
            }
        }
        return largest;
    }

    public Calculation calculateTakeOffAway(LogicalRunway logical, Airport airport) throws Exception{
        if (obstacle == null){
            throw new Exception("No obstacle Present!");
        }
        return logical.calculateTakeOffAway(obstacle,airport);
    }

    public Calculation calculateLandingOver(LogicalRunway logical, Airport airport) throws Exception{
        if (obstacle == null){
            throw new Exception("No obstacle Present!");
        }
        return logical.calculateLandingOver(obstacle,airport);
    }

    public Calculation calculateLandingTowards(LogicalRunway logicalRunway, Airport airport) throws Exception{
        if (obstacle == null){
            throw new Exception("No obstacle Present!");
        }
        return logicalRunway.calculateLandingTowards(obstacle,airport);
    }

    public Calculation calculateTakeOffTowards(LogicalRunway logical, Airport airport) throws Exception{
        if (obstacle == null){
            throw new Exception("No obstacle Present!");
        }
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

    public boolean equals(Runway r) {
    	if(r == null) {
    		return false;
    	}
    	if(r.getClass() != Runway.class) {
    		return false;
    	}
    	
    	if(!r.getObstacle().equals(this.getObstacle())) {
    		return false;
    	}
    	for(String s: this.logicalRunways.keySet()) {
    		if(!r.logicalRunways.get(s).equals(this.logicalRunways.get(s))) {
    			return false;
    		}
    	}
    	
    	
    	
    	return true;
    }
    /*
    public boolean equals(Object o) {
		if(o instanceof Runway) {
			return this.equals((Runway) o);
		} else {
			return super.equals(o);
		}
    }


     */


}
