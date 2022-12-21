package model;

public class Plane {
	private String name;
	private String model;
	private String callsign;
	private Number length;
	private Number height;
	
	public Plane(String nm, String model, String call, Number len,Number height) throws Exception {
		this.setName(nm);
		this.setModel(model);
		this.setCallSign(call);
		this.setLength(len);
		this.setHeight(height);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nm) throws Exception {
		if (nm == ""){
			throw new Exception("Empty name given");
		}
		this.name = nm;
	}
	

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) throws Exception {
		if (model == ""){
			throw new Exception("Empty model given");
		}

		this.model = model;
	}

	public String getCallSign() {
		return callsign;
	}
	
	public void setCallSign(String call) throws Exception {
		if (call == ""){
			throw new Exception("Empty callsign given");
		}
		this.callsign = call;
	}

	public Number getLength() {
		return length;
	}
	
	public void setLength(Number len) throws Exception {
		if(len.intValue() < 0 ) {
			throw new Exception("negative length entered");
		}
		this.length = len;
	}

	public Number getHeight() {
		return height;
	}
	
	public void setHeight(Number height) throws Exception {
		if(height.intValue() < 0 ) {
			throw new Exception("negative height entered");
		}
		this.height = height;
	}
	/*
	public Obstacle getObsticle(Number distanceFromLeft, Number distanceFromRight, Number distanceFromCentre) {
		return new Obstacle(this.height,distanceFromLeft,distanceFromRight,distanceFromCentre);
	}

	 */
}
