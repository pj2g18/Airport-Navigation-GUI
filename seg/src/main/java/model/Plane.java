package model;

public class Plane {
	private String name;
	private String model;
	private String callsign;
	private Number length;
	private Number height;
	
	public Plane(String nm, String model, String call, Number len,Number height) {
		this.setName(nm);
		this.setModel(model);
		this.setCallSign(call);
		this.setLength(len);
		this.setHeight(height);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String nm) {
		this.name = nm;
	}
	

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public String getCallSign() {
		return callsign;
	}
	
	public void setCallSign(String call) {
		this.callsign = call;
	}

	public Number getLength() {
		return length;
	}
	
	public void setLength(Number len) {
		if(len.intValue() < 0 ) {
			len = 0;
		}
		this.length = len;
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
	/*
	public Obstacle getObsticle(Number distanceFromLeft, Number distanceFromRight, Number distanceFromCentre) {
		return new Obstacle(this.height,distanceFromLeft,distanceFromRight,distanceFromCentre);
	}

	 */
}
