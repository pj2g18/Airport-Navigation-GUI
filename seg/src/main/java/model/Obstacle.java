package model;

import java.util.HashMap;

public class Obstacle {
    private Number height;
    //Takes a String which is the description of the runway, e.g. 09L and returns the distance that the object is from that threshold
    private HashMap<String,Number> obstaclePosition;
    private Number distanceFromLeft;
    private Number distanceFromRight;
    //Negative if it is north of centre line, positive if it is south.
    private Number distanceFromCentre;
    private Number blastProtection;

    public Obstacle(HashMap<String,Number> position, Number height,Number blastProtection){//TODO Validation in here
        this.obstaclePosition = position;
        this.height = height;
        this.blastProtection = blastProtection;
        
        //TODO = calc l,r,c distances
    }

    public Obstacle(Number height, Number distanceFromLeft, Number distanceFromRight, Number distanceFromCentre, Number blastProtection) throws Exception{
    	this.setHeight(height);
        this.setDistanceFromRight(distanceFromRight);
        this.setDistanceFromLeft(distanceFromLeft);
        this.setDistanceFromCentre(distanceFromCentre);
        this.blastProtection = blastProtection;
        
        //TODO = calc obsPos
    }
    
	public Number getHeight() {
		return height;
	}
	
	public void setHeight(Number height) {
		if(height.intValue() < 0 ) {
			height = 0;
		}
		this.height = height;
	}
	
	public HashMap<String,Number> getObstaclePosition() {
		return obstaclePosition;
	}
	
	public void setObstaclePosition(HashMap<String,Number>  pos) {
		this.obstaclePosition = pos;
	}
	
	public Number getDistanceFromLeft() {
		return distanceFromLeft;
	}
	
	public void setDistanceFromLeft(Number left) {
		if(left.intValue() > 0 ) {
			left = 0;
		}
		this.distanceFromLeft = left;
	}
	
	public Number getDistanceFromRight() {
		return distanceFromRight;
	}
	
	public void setDistanceFromRight(Number right) {
		if(right.intValue() > 0 ) {
			right = 0;
		}
		this.distanceFromRight = right;
	}
	
	public Number getDistanceFromCentre() {
		return distanceFromCentre;
	}
	
	public void setDistanceFromCentre(Number cen) {
		this.distanceFromCentre = cen;
	}

	public Number getBlastProtection(){
        return blastProtection;
    }
	
	
}
