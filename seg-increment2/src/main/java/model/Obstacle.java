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

    public Obstacle(HashMap<String,Number> position, Number height,Number blastProtection,Number centre) throws Exception{//TODO Validation in here
        setObstaclePosition(position);
        setHeight(height);
        setBlastProtection(blastProtection);
        this.distanceFromCentre = centre;
        //TODO = calc l,r,c distances
    }


    public Obstacle(HashMap<String,Number> position, Number height,Number blastProtection) throws Exception {//TODO Validation in here


		setObstaclePosition(position);
		setBlastProtection(blastProtection);
		setHeight(height);
		//TODO = calc l,r,c distances
    }

    public Obstacle(Number height, Number distanceFromLeft, Number distanceFromRight, Number distanceFromCentre, Number blastProtection) throws Exception{
    	this.setHeight(height);
        this.setDistanceFromRight(distanceFromRight);
        this.setDistanceFromLeft(distanceFromLeft);
        this.setDistanceFromCentre(distanceFromCentre);
        this.setBlastProtection(blastProtection);
        
        //TODO = calc obsPos
    }
    
	public Number getHeight() {
		return height;
	}

	public void setHeight(Number height) throws Exception {
		if(height.intValue() < 0 ) {
			throw new Exception("negative value for height given");
		}
		this.height = height;
	}
	
	public HashMap<String,Number> getObstaclePosition() {
		return obstaclePosition;
	}
	
	public void setObstaclePosition(HashMap<String,Number>  pos) throws Exception {
		HashMap<String, Number> newPos = new HashMap<String, Number>();

		for (String key : pos.keySet()){
			if (key.length() > 3 || key.length() < 2){
				throw new Exception("invalid obstacle position given");
			}else{
				if (key.charAt(key.length()-1) != 'L' && key.charAt(key.length()-1) !='R'  && key.charAt(key.length()-1) !='C' ){
					throw new Exception("invalid obstacle position given");
				}else{
					if (Integer.parseInt(key.substring(0,key.length()-1)) > 36 || Integer.parseInt(key.substring(0,key.length()-1)) < 1) {
						throw new Exception("obstacle position out of bounds");
					}
					if (key.length() == 2){
						newPos.put("0" + key, pos.get(key));
					}else{
						newPos.put(key, pos.get(key));
					}
				}
			}
		}
		this.obstaclePosition = newPos;

	}
	
	public Number getDistanceFromLeft() {
		return distanceFromLeft;
	}

	public void setDistanceFromLeft(Number left) throws Exception {
		if(left.intValue() >= 0 ) {
			this.distanceFromLeft = left;
		}else{
			throw new Exception("negative value given for distanceFromLeft");
		}

	}
	
	public Number getDistanceFromRight() {
		return distanceFromRight;
	}


	public void setDistanceFromRight(Number right) throws Exception {
		if(right.intValue() >= 0 ) {
			this.distanceFromRight = right;
		}else{
			throw new Exception("invalid distanceFrom right value given");
		}
	}


	public Number getDistanceFromCentre() {
		return distanceFromCentre;
	}

	public void setDistanceFromCentre(Number cen) throws Exception {
		if (cen.intValue() < 0){
			throw new Exception("negative value for distanceFromCentre given");
		}
		this.distanceFromCentre = cen;
	}

	public Number getBlastProtection(){
        return blastProtection;
    }
	public boolean equals(Obstacle o) {
		if(o == null) {
    		return false;
    	}
    	if(o.getClass() != Obstacle.class) {
    		return false;
    	}
    	
    	if(!o.getHeight().equals(this.getHeight())) {
        	return false;
        }
    	if(!o.getBlastProtection().equals(this.getBlastProtection())) {
        	return false;
        }
    	if((o.getDistanceFromCentre() == null) && (!o.getObstaclePosition().isEmpty())) {
    		for(String s: this.getObstaclePosition().keySet()) {
        		if(!o.getObstaclePosition().get(s).equals(this.getObstaclePosition().get(s))) {
        			return false;
        		}
        	}
    	} else if(o.getObstaclePosition().isEmpty()) {
	    	if(!o.getDistanceFromRight().equals(this.getDistanceFromRight())) {
	        	return false;
	        }
	    	if(!o.getDistanceFromLeft().equals(this.getDistanceFromLeft())) {
	        	return false;
	        }
	    	if(!o.getDistanceFromCentre().equals(this.getDistanceFromCentre())) {
	        	return false;
	        }
    	} else {
    		return false;
    	}
    	
        
    	
    	
    	return true;
	}
	
    public boolean equals(Object o) {
    	if(o instanceof Obstacle) {
    		return this.equals((Obstacle) o);
    	} else {
    		return super.equals(o);
    	}
    }


	public void setBlastProtection(Number blast) throws Exception {
		if (blast.intValue() < 0){
			throw new Exception("negative value given for blastProtection");
		}
		this.blastProtection = blast;
	}
}
