package model;

import model.Obstacle;
import model.ObstacleWrapper;
import java.util.ArrayList;
import java.util.HashMap;

public class PredefinedObstacles {

    static ArrayList<ObstacleWrapper> os = new ArrayList<>();

    public static ArrayList<ObstacleWrapper>  getPredefObs() {
        return os;
    }

    public static void add(ObstacleWrapper obs){
        os.add(obs);
    }

    public static void addPredefined(){
        add(getObs1());
        add(getObs2());
        add(getObs3());
        add(getObs4());
        add(getObs5());
        add(getObs6());
        add(getObs7());
    }

    public static ObstacleWrapper getObs1() {
        Obstacle o = null;
        try {
            o = new Obstacle(new HashMap<String,Number>(),40,500);
        } catch (Exception e) {

        }
        return new ObstacleWrapper("Boeing 747", o);
    }
    public static ObstacleWrapper getObs2() {
        Obstacle o = null;
        try {
            o = new Obstacle(new HashMap<String,Number>(),30,200);
        } catch (Exception e) {

        }
        return new ObstacleWrapper("Propeller Plane", o);
    }
    public static ObstacleWrapper getObs3() {
        Obstacle o = null;
        try {
            o = new Obstacle(new HashMap<String,Number>(),2,400);
        } catch (Exception e) {

        }
        return new ObstacleWrapper("Airstairs", o);
    }
    public static ObstacleWrapper getObs4() {
        Obstacle o = null;
        try {
            o = new Obstacle(new HashMap<String,Number>(),1,100);
        } catch (Exception e) {

        }
        return new ObstacleWrapper("Airport Buggy", o);
    }
    public static ObstacleWrapper getObs5() {
        Obstacle o = null;
        try {
            o = new Obstacle(new HashMap<String,Number>(),3,250);
        } catch (Exception e) {

        }
        return new ObstacleWrapper("Aircraft Debris", o);
    }
    public static ObstacleWrapper getObs6() {
        Obstacle o = null;
        try {
            o = new Obstacle(new HashMap<String,Number>(),6,0);
        } catch (Exception e) {

        }
        return new ObstacleWrapper("Catering Truck", o);
    }
    public static ObstacleWrapper getObs7() {
        Obstacle o = null;
        try {
            o = new Obstacle(new HashMap<String,Number>(),8,1000);
        } catch (Exception e) {

        }
        return new ObstacleWrapper("Refueling Lorry", o);
    }

    public static Obstacle searchList(String s) {
        for(ObstacleWrapper ow :getPredefObs()) {
            if(ow.getName().equals(s)) {
                return ow.getObstacle();
            }
        }
        return null;
    }

}
