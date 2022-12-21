package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/*
Backend that performs calculations
 */
public class Model{
    private Airport currentAirport;
    //The current runway object viewed by GUI
    private Runway currentRunway;
    private ArrayList<Airport> allAirports = new ArrayList<Airport>(); 

    
    //The current logical runways viewed by GUI
    private HashMap<String,LogicalRunway> logicalRunways;

    public Model(String arg) {
    	try {
    		Model m = XMLParser.getXMLDataFromFile(arg);
    		this.currentAirport = m.currentAirport;
    		this.allAirports.add(this.currentAirport);
    		this.currentRunway = m.currentRunway;
    		//this.logicalRunways = m.logicalRunways;
    	} catch(Exception e) {
    		Model m = new Model();
    		this.currentAirport = m.currentAirport;
    		this.allAirports.add(this.currentAirport);
    		this.currentRunway = m.currentRunway;
    		//this.logicalRunways = m.logicalRunways;
    		}
    	}


    public Model() {
        this.currentAirport = new Airport();
        this.allAirports.add(this.currentAirport);
        
    }

    public ArrayList<Runway> getRunways() {
        return currentAirport.getRunways();
    }

    
    public Obstacle getObstacle(Runway runway) {
        return runway.getObstacle();
    }

    
    public Number getLDA(LogicalRunway runway) {
        return runway.getLDA();
    }

    
    public void setLDA(Number lda, LogicalRunway runway) {
        runway.setLDA(lda);
    }

    
    public Number getTORA(LogicalRunway runway) {
        return runway.getTORA();
    }

    
    public void setTORA(Number tora, LogicalRunway runway) {
        runway.setTORA(tora);
    }

    
    public Number getTODA(LogicalRunway runway) {
        return runway.getTORA();
    }

    
    public void setTODA(Number toda, LogicalRunway runway) {
        runway.setTODA(toda);
    }

    
    public Number getASDA(LogicalRunway runway) {
        return runway.getASDA();
    }

    
    public void setASDA(Number asda, LogicalRunway runway) {
        runway.setASDA(asda);
    }

    public Runway addRunwayToAirport(){
        Runway runway = new Runway();
        //this.currentRunway = runway;
        currentAirport.addRunway(runway);
        
        HashMap<String,LogicalRunway> toadd = runway.getLogicalRunways();
        for(String s : toadd.keySet()) {
        	this.logicalRunways.put(s,toadd.get(s));
        }    
        
        return runway;
    }
    
    public Runway addNewCurrentRunway(){
    	this.currentRunway = this.addRunwayToAirport();
        return this.currentRunway;
    }
    
    public void setCurrentAirportAndRunway(Airport a,Runway r) {
    	a.addRunway(r);
    	this.currentAirport = a;
    	this.currentRunway = r;
    }
    
    
    public LogicalRunway addLogicalRunway(String tora,String toda, String asda, String lda, String heading, Character letter, String stopway, String clearway, String displacedThreshold) throws Exception{
        return currentRunway.addLogicalRunway(tora,toda,asda,lda,heading,letter,stopway,clearway,displacedThreshold);
    }

    public Runway addRunway(Runway r) {
    	this.currentRunway = r;
    	currentAirport.addRunway(r);
    	//HashMap<String,LogicalRunway> toadd = runway.getLogicalRunways();
    	return r;
    }



    public LogicalRunway addLogicalRunway(LogicalRunway log) throws Exception {
    	this.logicalRunways.put(log.getCombinedReference(), log);
        return currentRunway.addLogicalRunway(log);
    }

    public HashMap<String,LogicalRunway> getLogicalRunways(Runway runway){
        return runway.getLogicalRunways();
    }

    //TODO check to see if logical runway is part of real runway


    public void removeRunway(Runway runway){
        currentAirport.removeRunway(runway);
    }

    
    public void addObstacle(HashMap<String, Number> position, Number height, Runway runway, Number blastProtection) throws Exception{
        runway.addObstacle(position,height,blastProtection);
    }

    public Runway getCurrentRunway(){
        return currentRunway;
    }

    public Airport getAirport(){
        return currentAirport;
    }

    public void setAirport(Airport a) {
    	this.currentAirport = a;
    	if(!this.allAirports.contains(a)) {
    		this.allAirports.add(a);
    	}
    }

    public Model loadNewModelFromXML(String s) throws InvalidClassException, FileNotFoundException, IOException {
    	Model m = XMLParser.getXMLDataFromFile(s);
		this.currentAirport = m.currentAirport;
		this.currentRunway = m.currentRunway;
		this.logicalRunways = m.logicalRunways;
		
		return m;
    }
    
    public void saveDataToXML(String s,Model m) throws FileNotFoundException, IOException {
    	XMLParser.saveDataToFileAsXML(s,m);
    }


}
