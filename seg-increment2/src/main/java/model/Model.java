package model;

import controller.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

//import java.lang.reflect.Array;

/*
Backend that performs calculations
 */
public class Model{

    private Airport currentAirport = null;
    //The current runway object viewed by GUI
    private Runway currentRunway= null;
    private ArrayList<Airport> allAirports = new ArrayList<Airport>();
    private ArrayList<Controller> controllers = new ArrayList<Controller>();


    public ArrayList<Controller> getControllers() {
        return controllers;
    }

    public void setControllers(ArrayList<Controller> controllers) {
        this.controllers = controllers;
    }

    //The current logical runways viewed by GUI
    private HashMap<String,LogicalRunway> logicalRunways;

    public Model(String arg) {
    	try {
    		//Model m = XMLParser.getXMLDataFromFile(arg);
    		//this.currentAirport = m.currentAirport;
    		//this.allAirports.add(this.currentAirport);
    		//this.currentRunway = m.currentRunway;
    		//this.logicalRunways = m.logicalRunways;
    	} catch(Exception e) {
    		Model m = new Model();
    		this.currentAirport = m.currentAirport;
    		this.allAirports.add(this.currentAirport);
    		this.currentRunway = m.currentRunway;
    		//this.logicalRunways = m.logicalRunways;
    		}
    	}


    public Model() {//TODO change this method so that airport and runway arent created.
        allAirports= new ArrayList<>();
        currentAirport=null;

    }

    public void addController(Controller cont){
        controllers.add(cont);
    }

    public void update(){
        for(Controller cont: controllers){
            cont.update();
        }
    }

    public ArrayList<Runway> getRunways() {
        if(currentAirport.getRunways() == null){
            return new ArrayList<Runway>();
        }
        return currentAirport.getRunways();
    }


    public Obstacle getObstacle(Runway runway) {
        return runway.getObstacle();
    }


    public Number getLDA(LogicalRunway runway) {
        return runway.getLDA();
    }


    public void setLDA(Number lda, LogicalRunway runway) throws Exception {
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


    public void setTODA(Number toda, LogicalRunway runway) throws Exception {
        runway.setTODA(toda);
    }


    public Number getASDA(LogicalRunway runway) {
        return runway.getASDA();
    }


    public void setASDA(Number asda, LogicalRunway runway) throws Exception {
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

    public Airport addAirport(String resa, String stripEnd, String name)throws Exception{
        Airport airport = new Airport(resa,stripEnd,name);
        allAirports.add(airport);
        currentAirport = airport;
        currentRunway = null;
        return airport;
    }

    public void changeAirport(String name){
        for(Airport air : allAirports){
            if (air.getName().equals(name)){
                currentAirport = air;
            }
        }
        currentRunway = null;
        this.update();
    }

    public void changeRunway(String name) {
        Runway newRunway = null;
        for(Runway run : currentAirport.getRunways()){
                if(Objects.equals(run.getName(), name)){

                    currentRunway = run;
                }
        }

    }



    public LogicalRunway addLogicalRunway(LogicalRunway log) throws Exception {
    	this.logicalRunways.put(log.getCombinedReference(), log);
        return currentRunway.addLogicalRunway(log);
    }

    public HashMap<String,LogicalRunway> getLogicalRunways() {
        if(currentRunway != null) {
            return currentRunway.getLogicalRunways();
        } else {
            if (this.getRunways().size() > 0) {
                currentRunway = this.getRunways().get(0);
                return currentRunway.getLogicalRunways();

            } else {
                return null;
            }

        }
    }

    //TODO check to see if logical runway is part of real runway


    public void removeRunway(Runway runway){
        currentAirport.removeRunway(runway);
    }


    public void addObstacle(HashMap<String, Number> position, Number height, Runway runway, Number blastProtection) throws Exception{
        runway.addObstacle(position,height,blastProtection);
    }

    public void addObstacle(HashMap<String, Number> position, Number height, Runway runway, Number blastProtection, Number centre) throws Exception{
        runway.addObstacle(position,height,blastProtection,centre);
    }

    public Runway getCurrentRunway() throws Exception{
        if(currentRunway == null){
            return null;
        }
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


    public void saveDataToXML(String s,Model m) throws FileNotFoundException, IOException {
    	XMLParser.saveDataToFileAsXML(s,m);
    }
    /*
    public void saveDataToXML(File f, Model m) throws FileNotFoundException, IOException {
        XMLParser.saveDataToFileAsXML(f,m);
    }

     */
    public ArrayList<Calculation> calculateTakeOffAway(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateTakeOffAway(r.getObstacle(),airport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateLandingOver(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateLandingOver(r.getObstacle(),airport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateLandingTowards(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateLandingTowards(r.getObstacle(),airport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateTakeOffTowards(Runway r, Airport airport){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :r.getLogicalRunways().values()) {
    		ret.add(logical.calculateTakeOffTowards(r.getObstacle(),airport));
    	}
    	return ret;
    }


    public Calculation calculateTakeOffAway(Runway r, LogicalRunway log, Airport airport){
    	for(Calculation c : this.calculateTakeOffAway(r, airport)) {
    		if(log.equals(c.getLogRunway())) {
    			return c;
    		}
    	}
    	return null;
    }

    public Calculation calculateLandingOver(Runway r, LogicalRunway log, Airport airport){
    	for(Calculation c : this.calculateLandingOver(r, airport)) {
    		if(log.equals(c.getLogRunway())) {
    			return c;
    		}
    	}
    	return null;
    }

    public Calculation calculateLandingTowards(Runway r, LogicalRunway log, Airport airport){
    	for(Calculation c : this.calculateLandingTowards(r, airport)) {
    		if(log.equals(c.getLogRunway())) {
    			return c;
    		}
    	}
    	return null;
    }

    public Calculation calculateTakeOffTowards(Runway r, LogicalRunway log, Airport airport){
    	for(Calculation c : this.calculateTakeOffTowards(r, airport)) {
    		if(log.equals(c.getLogRunway())) {
    			return c;
    		}
    	}
    	return null;
    }

    public ArrayList<Calculation> calculateTakeOffAway(){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :currentRunway.getLogicalRunways().values()) {
    		ret.add(logical.calculateTakeOffAway(currentRunway.getObstacle(),currentAirport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateLandingOver(){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :currentRunway.getLogicalRunways().values()) {
    		ret.add(logical.calculateLandingOver(currentRunway.getObstacle(),currentAirport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateLandingTowards(){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :currentRunway.getLogicalRunways().values()) {
    		ret.add(logical.calculateLandingTowards(currentRunway.getObstacle(),currentAirport));
    	}
    	return ret;
    }

    public ArrayList<Calculation> calculateTakeOffTowards(){
    	ArrayList<Calculation> ret = new ArrayList<Calculation>();
    	for(LogicalRunway logical :currentRunway.getLogicalRunways().values()) {
    		ret.add(logical.calculateTakeOffTowards(currentRunway.getObstacle(),currentAirport));
    	}
    	return ret;
    }
    public ArrayList<Airport> getAllAirports() {
    	if(!allAirports.contains(currentAirport)) {
    		allAirports.add(currentAirport);
    	}
    	if(allAirports.contains(null)){
    	    allAirports.remove(null);
        }
    	return this.allAirports;
    }
    
    public void addAirport(Airport a) {
    	this.allAirports.add(a);
    	setAirport(a);
    }
    
    public boolean equals(Model m) {
    	if(m == null) {
    		return false;
    	}
    	if(m.getClass() != Model.class) {
    		return false;
    	}
    	/*
    	for(Airport a:this.allAirports) {
    		if(!m.getAllAirports().contains(a)) {
    			return false;
    		}
    	}
    	 */
/*
    	if(this.allAirports.size() != m.allAirports.size()) {
    	    return false;
        }
    	for(int i = 0; i<this.allAirports.size();i++) {
    	    if(!this.getAllAirports().get(i).getStripEnd().equals(m.getAllAirports().get(i).getStripEnd()) ||!this.getAllAirports().get(i).getName().equals(m.getAllAirports().get(i).getName()) ||!this.getAllAirports().get(i).getResa().equals(m.getAllAirports().get(i).getResa())) {
    	        System.out.println("basic fail");
    	        return false;
            }
    	    if(this.getAllAirports().get(i).getRunways().size() != m.getAllAirports().get(i).getRunways().size()) {
                System.out.println("2 fail");
    	        return false;
            }
            for(int j = 0; j<this.getAllAirports().get(i).getRunways().size();i++) {
                if(!this.getAllAirports().get(i).getRunways().get(j).equals(m.getAllAirports().get(i).getRunways().get(j))) {
                    System.out.println("3 fail");

                    return false;
                }
            }


        }
*/

    	if(!m.currentAirport.equals(this.currentAirport)) {
    		return false;
    	}
    	if(!m.currentRunway.equals(this.currentRunway)) {
    		return false;
    	}
    	if( (this.getLogicalRunways().isEmpty()) && (m.getLogicalRunways().isEmpty() )) {
    		return true;
    	} else if((this.getLogicalRunways().isEmpty()) || (m.getLogicalRunways().isEmpty() )) {
    		return false;
    	} else {
	    	for(String s: this.getLogicalRunways().keySet()) {
	    		if((m.getLogicalRunways().get(s) == null) || (this.getLogicalRunways().get(s) ==null)) {
	    			return false;
	    		}
	    		if(!this.getLogicalRunways().get(s).equals(m.getLogicalRunways().get(s))) {
	    			return false;
	    		}
	    	}
    	}
    	return true;
    	
    }

    public void addDifferencesFromNewModel(Model mod) {
        if(this.equals(mod)) {
            return;
        }

        this.addAirports(mod.getAllAirports());
        this.addAirport(mod.currentAirport);
        this.addRunway(mod.currentRunway);


        try {
            this.setCurrentAirportAndRunway(mod.getAirport(), mod.currentRunway);
        } catch(Exception e) {}
    }

    public void addAirports(ArrayList<Airport> airs) {

        for(Airport air:airs){
            addAirport(air);
            try {
                currentRunway = air.getRunways().get(0);
            }catch(IndexOutOfBoundsException e){

            }
        }


    }

    public boolean equals(Object o) {
    	if(o instanceof Model) {
    		return this.equals((Model) o);
    	} else {
    		return super.equals(o);
    	}
    }

    public void togglePhysicalRunway(boolean visible){
        for(Controller cont: controllers){
            cont.togglePhysicalRunway(visible);
        }
    }

    public void toggleLeftLogicalRunway(boolean visible){
        for(Controller cont: controllers){
            cont.toggleLeftLogicalRunway(visible);
        }
    }

    public void toggleRightLogicalRunway(boolean visible) {
        for (Controller cont : controllers) {
            cont.toggleRightLogicalRunway(visible);
        }
    }



}

