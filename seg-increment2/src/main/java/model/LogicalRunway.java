package model;

//import java.util.HashMap;


/*
Find a way of storing both lots of calcs so that is there is only 1 heading it calculates all.
 */
public class LogicalRunway {
    private Number lda;
    private Number tora;
    private Number toda;
    private Number asda;
    private Number stopway;
    private Number clearway;
    private Number displacedThreshold;

    //If heading is less than 18 then it will be on the left hand side
    private Number heading;
    private Character letter;
    private String combinedReference;
    //Represents whether TOA,LO or TOT,LT has been chosen based on object position
    //"TOA,LO" or "TOT,LT"
    private String takeoffType;
    //private Runway runway;

    public LogicalRunway(String tora, String toda, String asda, String lda, String heading, Character letter, String stopway, String clearway, String displacedThreshold) throws Exception{ //TODO Validation in here
        if(isNumeric(toda)){
            this.setTODA(Integer.valueOf(toda));
        }else{
            throw new Exception("TODA not a Number or less than Zero!");
        }

        if(isNumeric(asda)){
            this.setASDA(Integer.valueOf(asda));
            if(this.toda.intValue() >= this.asda.intValue()){

            }else{
                throw new Exception("ASDA larger than TODA!");
            }
        }else{
            throw new Exception("ASDA not a Number or less than Zero!");
        }

        if(isNumeric(tora)){
            this.setTORA(Integer.valueOf(tora));
            if(this.asda.intValue() >= this.tora.intValue()){

            }else{
                throw new Exception("TORA larger than ASDA!");
            }
        }else{
            throw new Exception("TORA not a Number or less than Zero!");
        }

        if(isNumeric(lda)){
            setLDA(Integer.valueOf(lda));
            if(this.lda.intValue() > this.tora.intValue()){
                throw new Exception("LDA larger than TORA!");
            }

        }else{
            throw new Exception("LDA not a Number or less than Zero!");
        }

        if(isNumeric(displacedThreshold)){
            setDisplacedThreshold(Integer.valueOf(displacedThreshold));
            if(this.displacedThreshold.intValue() >= this.lda.intValue()) {
                throw new Exception("Displaced Threshold not less than LDA!");
            }

            if(this.displacedThreshold.intValue() > 0){
                if ((this.lda.intValue() - this.displacedThreshold.intValue()) >= this.tora.intValue()){
                    throw new Exception("LDA - Displaced Threshold not less than TORA!");
                }
            }

        }else {
            throw new Exception("Displaced Threshold not a Number or less than Zero!");
        }


        this.setHeading(Integer.valueOf(heading));

        this.setLetter(letter);

        this.setCombinedReference(String.valueOf(heading)+letter);
        //System.out.println(combinedReference);


        if(isNumeric(stopway)){
            this.setStopway(Integer.valueOf(stopway));
        }else{
            throw new Exception("Stopway not a Number or less than Zero!");
        }


        if(isNumeric(clearway)){
            this.setClearway(Integer.valueOf(clearway));


        }else{
            throw new Exception("Clearway not a Number or less than Zero!");
        }

        if(this.toda.intValue() != this.tora.intValue()+this.clearway.intValue()){
            throw new Exception("TODA != TORA + Clearway");
        }

        if(this.asda.intValue() != this.tora.intValue()+this.stopway.intValue()){
            throw new Exception("ASDA != TORA + Stopway");
        }



    }
    
    public LogicalRunway() throws Exception {
    	this.setLDA(0);
        this.setTORA(0);
        this.setTODA(0);
        this.setASDA(0);
        this.setHeading(01);
        this.setLetter('C');
        this.setStopway(0);
        this.setClearway(0);
        this.setDisplacedThreshold(0);
    }

    //Method adapted from https://www.baeldung.com/java-check-string-number
    public boolean isNumeric(String num) {
        if (num == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(num);
            if(0 > i){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isNumericOther(String num) {
        if (num == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void setDisplacedThreshold(Number displacedThreshold) throws Exception {
        if (displacedThreshold.intValue() >= 0) {
            this.displacedThreshold = displacedThreshold;
        }else{
            throw new Exception("negative displaced threshold entered");
        }
	}

    public void setClearway(Number clearway) throws Exception {
		if(clearway.intValue() >= 0 ) {
			this.clearway = clearway;
		}else{
		    throw new Exception("clearway value out of bounds");
        }
		

	}

    public void updateTODA() throws Exception {

        if ( (tora.intValue() + clearway.intValue()) < 0 && tora.intValue() != 0){
            throw new Exception("tora + clearway exceeds maximum allowed value for TODA");
        }
		this.setTODA(tora.intValue() + clearway.intValue());
		
	}

    public void setStopway(Number stopway) throws Exception {
		if(stopway.intValue() >= 0 ) {
			this.stopway = stopway;
		}else{
            throw new Exception("clearway value out of bounds");
        }
		
		this.updateASDA();
		
	}

    public void updateASDA() throws Exception {
        //System.out.println(tora);
        if ( (tora.intValue() + stopway.intValue()) < 0 && tora.intValue() != 0){
            throw new Exception("tora + stopway exceeds maximum allowed value for ASDA");
        }
		this.setASDA(tora.intValue() + stopway.intValue());
		
	}

    public void setCombinedReference(String string) throws Exception {
        if (string.charAt(string.length()-1) != 'L' && string.charAt(string.length()-1) != 'R' && string.charAt(string.length()-1) != 'C'){
            throw new Exception("invalid Letter given for logical runway");
        }
        if (string.length() < 2 || string.length() > 3){
            throw new Exception("invalid reference to logical runway set");
        }
        if(string.length() < 3){
            string = "0"+string;
        }
        if (Integer.parseInt(string.substring(0,2)) > 36  || Integer.parseInt(string.substring(0,2)) < 1){
            throw new Exception("bearing of runway out of bounds");
        }

        setHeading(Integer.parseInt(string.substring(0,2)));
        setLetter(string.charAt(2));
        combinedReference = string;



	}

	public Calculation calculateTakeOffAway(Obstacle obstacle, Airport airport){
        Calculation calc = new Calculation(this,lda,asda,tora,toda);

        if(obstacle != null) {

            int redeclardedtora = tora.intValue() - obstacle.getObstaclePosition().get(combinedReference).intValue() - displacedThreshold.intValue();

            int blast = obstacle.getBlastProtection().intValue();
            if (blast < airport.getResa().intValue()) {
                redeclardedtora = redeclardedtora - (airport.getResa().intValue()) - (airport.getStripEnd().intValue());
                calc.setTora(redeclardedtora);
                calc.setToraCalculation(redeclardedtora + " (R TORA) = " +
                        tora.intValue() + " (TORA) - " +
                        obstacle.getObstaclePosition().get(combinedReference).intValue() + " (Distance from Threshold) - " +
                        displacedThreshold.intValue() + " (displaced threshold) - " +
                        airport.getResa().intValue() + " (RESA) - " +
                        airport.getStripEnd().intValue() + " (Strip End)");
                calc.setBlastProtection(null);
                calc.setStripEnd(airport.getStripEnd().intValue());
                calc.setResa(airport.getResa().intValue());

            } else {
                redeclardedtora = redeclardedtora - blast;
                calc.setTora(redeclardedtora);
                calc.setToraCalculation(redeclardedtora + " (R TORA) = " +
                        tora.intValue() + " (TORA) - " +
                        obstacle.getObstaclePosition().get(combinedReference).intValue() + " (Distance from Threshold) - " +
                        displacedThreshold.intValue() + " (displaced threshold) - " +
                        blast + " (Blast Protection)");
                calc.setBlastProtection(blast);
                calc.setStripEnd(null);
                calc.setResa(null);
            }


            calc.setAsda(redeclardedtora + stopway.intValue());
            calc.setAsdaCalculation(redeclardedtora + stopway.intValue() + " (R ASDA) = " +
                    redeclardedtora + " (R TORA) + " +
                    stopway.intValue() + " (Stopway)");


            calc.setToda(redeclardedtora + clearway.intValue());
            calc.setTodaCalculation(redeclardedtora + clearway.intValue() + " (R TODA) = " +
                    redeclardedtora + " (R TORA) + " +
                    clearway.intValue() + " (Clearway)");
        }

        return calc;
    }

    public Calculation calculateLandingTowards(Obstacle obstacle, Airport airport){
        Calculation calculation = new Calculation(this,lda,asda,tora,toda);

        int distance = obstacle.getObstaclePosition().get(combinedReference).intValue();

        int calc = distance - (airport.getResa().intValue()) - (airport.getStripEnd().intValue());

        calculation.setLda(calc);
        calculation.setLdaCalculation(calc+" (R LDA) = "+
                distance+" (Distance from Threshold) - "+
                airport.getResa().intValue()+" (RESA) - "+
                airport.getStripEnd().intValue()+" (Strip End)");
        calculation.setResa(airport.getResa());
        calculation.setStripEnd(airport.getStripEnd());

        return calculation;
    }

    public Calculation calculateLandingOver(Obstacle obstacle, Airport airport){

        Calculation calculation  = new Calculation(this,lda,asda,tora,toda);

        //System.out.println(combinedReference);
        int distance = obstacle.getObstaclePosition().get(combinedReference).intValue();

        int resa = airport.getResa().intValue();
        int slope = obstacle.getHeight().intValue()*50;
        int stripEnd = airport.getStripEnd().intValue();
        int ret;
        int calc;

        if(resa > slope) {
            ret = resa+stripEnd;
            calc = lda.intValue() - distance - ret;
            calculation.setLdaCalculation(calc+" (R LDA) = "+
                    lda.intValue()+" (LDA) -"+
                    distance+" (Distance From Threshold) - "+
                    resa+" (RESA) - "+
                    stripEnd+" (Strip End)");
            calculation.setStripEnd(stripEnd);
            calculation.setResa(resa);

        }else {
            ret = slope+stripEnd;
            calc = lda.intValue() - distance - ret;
            calculation.setLdaCalculation(calc+" (R LDA) = "+
                    lda.intValue()+" (LDA) -"+
                    distance+" (Distance From Threshold) - "+
                    slope+" (Slope) - "+
                    stripEnd+" (Strip End)");
            calculation.setSlope(slope);
            calculation.setResa(null);
            calculation.setStripEnd(stripEnd);
        }

        if(ret < obstacle.getBlastProtection().intValue()) {
            ret = obstacle.getBlastProtection().intValue();
            calc = lda.intValue() - distance - ret;
            calculation.setLdaCalculation(calc+" (R LDA) = "+
                    lda.intValue()+" (LDA) -"+
                    distance+" (Distance From Threshold) - "+
                    ret+" (Blast Protection) ");
            calculation.setSlope(null);
            calculation.setStripEnd(null);
            calculation.setResa(null);
            calculation.setBlastProtection(ret);

        }


        calculation.setLda(calc);
        return calculation;

    }

    public Calculation calculateTakeOffTowards (Obstacle obstacle, Airport airport){
        Calculation calculation = new Calculation(this,lda,asda,tora,toda);
        int distance = obstacle.getObstaclePosition().get(combinedReference).intValue();

        int resa = airport.getResa().intValue();
        int slope = obstacle.getHeight().intValue()*50;
        int stripEnd = airport.getStripEnd().intValue();

        int ret;
        int calc;

        if(resa > slope){
            ret = resa+stripEnd;
            calc = distance-ret+displacedThreshold.intValue();
            calculation.setToraCalculation(calc+" (R TORA) = "+
                    distance+" (Distance From Threshold) + "+
                    displacedThreshold.intValue()+" (Displaced Threshold) - "+
                    resa+" (RESA) - "+
                    stripEnd+" (Strip End)");
            calculation.setResa(resa);
            calculation.setStripEnd(stripEnd);
            calculation.setSlope(null);

        }else{
            ret = slope+stripEnd;
            calc = distance-ret+displacedThreshold.intValue();
            calculation.setToraCalculation(calc+" (R TORA) = "+
                    distance+" (Distance From Threshold) + "+
                    displacedThreshold.intValue()+" (Displaced Threshold) - "+
                    slope+" (Slope) - "+
                    stripEnd+" (Strip End)");
            calculation.setSlope(slope);
            calculation.setStripEnd(stripEnd);
            calculation.setResa(null);
        }

        calculation.setTora(calc);
        calculation.setAsda(calc);
        calculation.setAsdaCalculation(calc+" (ASDA) = (R TORA)");
        calculation.setToda(calc);
        calculation.setTodaCalculation(calc+" (TODA) = (R TORA)");

        return calculation;

    }

    public Number getLDA() {
        return lda;
    }

    public void setLDA(Number lda) throws Exception {
    	if(lda.intValue() >= 0) {
        this.lda = lda;
    	} else { throw new Exception("negative LDA entered");
    	}
    }

    public Number getTORA() {
        return tora;
    }

    public void setTORA(Number tora) {
        if(tora.intValue() >= 0) {
        	this.tora = tora;
        } else { this.tora = 0; }
    }

    public Number getTODA() {
        return toda;
    }

    public void setTODA(Number toda) throws Exception {
        if(toda.intValue() >= 0) {
        	this.toda = toda;
        } else {
            throw new Exception("toda value less than 0");
        }
    }

    public Number getASDA() {
        return asda;
    }

    public void setASDA(Number asda) throws Exception {
        this.asda = asda;
        
        if(asda.intValue() >= 0) {
        	this.asda = asda;
        } else {
            throw new Exception("asda value less than 0");
        }
    }


    public Number getHeading() {
        return heading;
    }

    public void setHeading(Number heading) throws Exception{
    	if(1 <= heading.intValue() && heading.intValue() <= 36) {
        this.heading = heading;
    	} else {
    	    throw new Exception("bearing of runway out of bounds");
        }
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) throws Exception {
    	if(letter.equals('L')||letter.equals('R')||letter.equals('C')) {
    		this.letter = letter;
    	} else {
    	    throw new Exception("incorrect letter given");
    	}

    }

    public String getTakeoffType() {
        return takeoffType;
    }

    public void setTakeoffType(String takeoffType) {
        this.takeoffType = takeoffType;
    }

    public String getCombinedReference(){
        return combinedReference;
    }
    
    public Number getStopway() {
    	return this.stopway;
    }
    
    public Number getClearway() {
    	return this.clearway;
    }
    
    public Number getDisplacedThreshold() {
    	return this.displacedThreshold;
    }
    
    public boolean equals(LogicalRunway log) {
    	if(log == null) {
    		return false;
    	}
    	if(log.getClass() != LogicalRunway.class) {
    		return false;
    	}
    	if(!log.getLDA().equals(this.getLDA())) {
    		return false;
    	}
    	if(!log.getTORA().equals(this.getTORA())) {
    		return false;
    	}
        if(!log.getTODA().equals(this.getTODA())) {
    		return false;
    	}
        if(!log.getASDA().equals(this.getASDA())) {
        	return false;
        }
        if(!log.getStopway().equals(this.getStopway())) {
    		return false;
    	}
        if(!log.getClearway().equals(this.getClearway())) {
    		return false;
    	}
        if(!log.getDisplacedThreshold().equals(this.getDisplacedThreshold())) {
    		return false;
    	}

      
        if(!log.getHeading().equals(this.getHeading())) {
    		return false;
    	}
        if(!log.getLetter().equals(this.getLetter())) {
    		return false;
    	}
        if(!log.getCombinedReference().equals(this.getCombinedReference())) {
    		return false;
    	}

    	
    	return true;
    }
    public boolean equals(Object o) {
		if(o instanceof LogicalRunway) {
			return this.equals((LogicalRunway) o);
		} else {
			return super.equals(o);
		}
    }
}
