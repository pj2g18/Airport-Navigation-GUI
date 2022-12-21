package model;

import java.util.HashMap;

public class ObstacleWrapper {
    public String name;
    public Obstacle obstacle;

    public ObstacleWrapper(String name, Obstacle obstacle) {
        this.name = name;
        this.obstacle = obstacle;
    }

    public ObstacleWrapper(String name, HashMap<String,Number> position, Number height, Number blastProtection) throws Exception{
        this.name = name;
        this.obstacle = new Obstacle(position,height,blastProtection);
    }

    public ObstacleWrapper(String name, Number height, Number distanceFromLeft, Number distanceFromRight, Number distanceFromCentre, Number blastProtection) throws Exception{
        this.name = name;
        this.obstacle = new Obstacle(height,distanceFromLeft,distanceFromRight,distanceFromCentre,blastProtection);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }



}
