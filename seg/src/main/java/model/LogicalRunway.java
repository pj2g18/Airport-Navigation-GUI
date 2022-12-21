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


        if(isNumeric(stopway)){
            this.setStopway(Integer.valueOf(stopway));
        }else{
            throw new Exception("Stopway not a Number or less than Zero!");
        }


        if(isNumeric(clearway)){
            this.setClearway(Integer.valueOf(clearway));

            if(this.clearway.intValue() < this.stopway.intValue()){
                throw new Exception("Clearway less than Stopway!");
            }

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
    
    public LogicalRunway() {
    	this.setLDA(0);
        this.setTORA(0);
        this.setTODA(0);
        this.setASDA(0);
        this.setHeading(00);
        this.setLetter('C');
        this.setCombinedReference("00C");
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

    public void setDisplacedThreshold(Number displacedThreshold) {
		this.displacedThreshold = displacedThreshold;
		
	}

    public void setClearway(Number clearway) {
		if(clearway.intValue() >= 0 ) {
			this.clearway = clearway;
		}
		
		this.updateTODA();
		
	}

    public void updateTODA() {
		this.setTODA(tora.intValue() + clearway.intValue());
		
	}

    public void setStopway(Number stopway) {
		if(stopway.intValue() >= 0 ) {
			this.stopway = stopway;
		}
		
		this.updateASDA();
		
	}

    public void updateASDA() {
		this.setASDA(tora.intValue() + stopway.intValue());
		
	}

    public void setCombinedReference(String string) {
		if(string.contentEquals(heading.toString() +letter)) {
			this.combinedReference = string;
		} else { this.combinedReference = (heading.toString() +letter); }
		
	}

	public Calculation calculateTakeOffAway(Obstacle obstacle, Airport airport){
        Calculation calc = new Calculation(lda,asda,tora,toda);

        int redeclardedtora = tora.intValue() - obstacle.getObstaclePosition().get(combinedReference).intValue() - displacedThreshold.intValue();

        int blast = obstacle.getBlastProtection().intValue();
        if( blast < airport.getResa().intValue()){
            redeclardedtora = redeclardedtora -  (airport.getResa().intValue()) - (airport.getStripEnd().intValue());
            calc.setTora(redeclardedtora);
            calc.setToraCalculation(    redeclardedtora +" (R TORA) = " +
                                         tora.intValue() + " (TORA) - " +
                                        obstacle.getObstaclePosition().get(combinedReference).intValue() + " (Distance from Threshold) - " +
                                        displacedThreshold.intValue() + " (displaced threshold) - " +
                                        airport.getResa().intValue() + " (RESA) - " +
                                        airport.getStripEnd().intValue() + " (Strip End)");
        }else{
            redeclardedtora = redeclardedtora - blast;
            calc.setTora(redeclardedtora);
            calc.setToraCalculation(    redeclardedtora +" (R TORA) = " +
                    tora.intValue() + " (TORA) - " +
                    obstacle.getObstaclePosition().get(combinedReference).intValue() + " (Distance from Threshold) - " +
                    displacedThreshold.intValue() + " (displaced threshold) -" +
                    blast + " (Blast Protection)");
        }


        calc.setAsda(redeclardedtora + stopway.intValue());
        calc.setAsdaCalculation(redeclardedtora + stopway.intValue() + " (R ASDA) = " +
                                 redeclardedtora +" (R TORA) + " +
                                         stopway.intValue() + " (Stopway)" );


        calc.setToda(redeclardedtora + clearway.intValue());
        calc.setTodaCalculation(redeclardedtora + clearway.intValue() + " (R TODA) = " +
                redeclardedtora +" (R TORA) + " +
                clearway.intValue() + " (Clearway)" );

        return calc;
    }

    public Calculation calculateLandingTowards(Obstacle obstacle, Airport airport){
        Calculation calculation = new Calculation(lda,asda,tora,toda);

        int distance = obstacle.getObstaclePosition().get(combinedReference).intValue();

        int calc = distance - (airport.getResa().intValue()) - (airport.getStripEnd().intValue());

        calculation.setLda(calc);
        calculation.setLdaCalculation(calc+" (R LDA) = "+
                distance+" (Distance from Threshold) - "+
                airport.getResa().intValue()+" (RESA) - "+
                airport.getStripEnd().intValue()+" (Strip End)");

        return calculation;
    }

    public Calculation calculateLandingOver(Obstacle obstacle, Airport airport){
        Calculation calculation  = new Calculation(lda,asda,tora,toda);

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

        }else {
            ret = slope+stripEnd;
            calc = lda.intValue() - distance - ret;
            calculation.setLdaCalculation(calc+" (R LDA) = "+
                    lda.intValue()+" (LDA) -"+
                    distance+" (Distance From Threshold) - "+
                    slope+" (Slope) - "+
                    stripEnd+" (Strip End)");
        }

        if(ret < obstacle.getBlastProtection().intValue()) {
            ret = obstacle.getBlastProtection().intValue();
            calc = lda.intValue() - distance - ret;
            calculation.setLdaCalculation(calc+" (R LDA) = "+
                    lda.intValue()+" (LDA) -"+
                    distance+" (Distance From Threshold) - "+
                    ret+" (Blast Protection) ");

        }


        calculation.setLda(calc);
        return calculation;

    }

    public Calculation calculateTakeOffTowards (Obstacle obstacle, Airport airport){
        Calculation calculation = new Calculation(lda,asda,tora,toda);
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

        }else{
            ret = slope+stripEnd;
            calc = distance-ret+displacedThreshold.intValue();
            calculation.setToraCalculation(calc+" (R TORA) = "+
                    distance+" (Distance From Threshold) + "+
                    displacedThreshold.intValue()+" (Displaced Threshold) - "+
                    slope+" (Slope) - "+
                    stripEnd+" (Strip End)");
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

    public void setLDA(Number lda) {
    	if(lda.intValue() >= 0) {
        this.lda = lda;
    	} else { this.lda = 0; }
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

    public void setTODA(Number toda) {
        if(toda.intValue() >= 0) {
        	this.toda = toda;
        } else { this.toda = 0; }
    }

    public Number getASDA() {
        return asda;
    }

    public void setASDA(Number asda) {
        this.asda = asda;
        
        if(asda.intValue() >= 0) {
        	this.asda = asda;
        } else { this.asda = 0; }
    }


    public Number getHeading() {
        return heading;
    }

    public void setHeading(Number heading) {
    	if(0 <= heading.intValue() && heading.intValue() <= 36) {
        this.heading = heading;
    	} else { this.heading = -1; }
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
    	if(letter.equals('L')||letter.equals('R')||letter.equals('C')) {
    		this.letter = letter;
    	} else {this.letter = 'N'; }
        
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
}
