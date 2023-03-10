package model;

public class Calculation {
	private LogicalRunway log;
    private int lda;
    private int asda;
    private int tora;
    private int toda;

    public Number getBlastProtection() {
        return blastProtection;
    }

    public void setBlastProtection(Number blastProtection) {
        this.blastProtection = blastProtection;
    }

    private Number blastProtection;

    public Number getResa() {
        return resa;
    }

    public void setResa(Number resa) {
        this.resa = resa;
    }

    private Number resa;


    private Number slope;
    private Number stripEnd;

    //Values before calculation
    private String previousLda;
    private String previousAsda;
    private String previousTora;
    private String previousToda;
    private String ldaCalculation;
    private String asdaCalculation;
    private String toraCalculation;
    private String todaCalculation;


    public Number getPreviousLdaNum() {
        return previousLdaNum;
    }

    public void setPreviousLdaNum(Number previousLdaNum) {
        this.previousLdaNum = previousLdaNum;
    }

    public Number getPreviousAsdaNum() {
        return previousAsdaNum;
    }

    public void setPreviousAsdaNum(Number previousAsdaNum) {
        this.previousAsdaNum = previousAsdaNum;
    }

    public Number getPreviousToraNum() {
        return previousToraNum;
    }

    public void setPreviousToraNum(int previousToraNum) {
        this.previousToraNum = previousToraNum;
    }

    public Number getPreviousTodaNum() {
        return previousTodaNum;
    }

    public void setPreviousTodaNum(int previousTodaNum) {
        this.previousTodaNum = previousTodaNum;
    }
    private Number previousLdaNum;
    private Number previousAsdaNum;
    private Number previousToraNum;
    private Number previousTodaNum;

    public Calculation(LogicalRunway log, Number previousLda, Number previousAsda, Number previousTora, Number previousToda){
    	this.log = log;
        this.previousAsda = "Previous ASDA = "+previousAsda.intValue();
        this.previousLda = "Previous LDA = "+previousLda.intValue();
        this.previousToda = "Previous TODA = "+previousToda.intValue();
        this.previousTora = "Previous TORA = "+previousTora.intValue();

        this.ldaCalculation = previousLda + " (UNMODIFIED LDA)";
        this.asdaCalculation = previousAsda + " (UNMODIFIED ASDA)";
        this.toraCalculation = previousTora + " (UNMODIFIED TORA)";
        this.todaCalculation = previousToda + " (UNMODIFIED  TODA)";

        this.previousAsdaNum = previousAsda.intValue();
        this.previousLdaNum = previousLda.intValue();
        this.previousTodaNum = previousToda.intValue();
        this.previousToraNum = previousTora.intValue();

    }
    
    public LogicalRunway getLogRunway( ) {
    	return this.log;
    }

    public int getLda() {
        return lda;
    }

    public void setLda(int lda) {
        this.lda = lda;
    }

    public int getAsda() {
        return asda;
    }

    public void setAsda(int asda) {
        this.asda = asda;
    }

    public int getTora() {
        return tora;
    }

    public void setTora(int tora) {
        this.tora = tora;
    }

    public int getToda() {
        return toda;
    }

    public void setToda(int toda) {
        this.toda = toda;
    }

    public Number getSlope() {
        return slope;
    }

    public void setSlope(Number slope) {
        this.slope = slope;
    }

    public Number getStripEnd() {
        return stripEnd;
    }

    public void setStripEnd(Number stripEnd) {
        this.stripEnd = stripEnd;
    }


    public String getLdaCalculation() {
        return ldaCalculation;
    }

    public void setLdaCalculation(String ldaCalculation) {
        this.ldaCalculation = ldaCalculation;
    }

    public String getAsdaCalculation() {
        return asdaCalculation;
    }

    public void setAsdaCalculation(String asdaCalculation) {
        this.asdaCalculation = asdaCalculation;
    }

    public String getToraCalculation() {
        return toraCalculation;
    }

    public void setToraCalculation(String toraCalculation) {
        this.toraCalculation = toraCalculation;
    }

    public String getTodaCalculation() {
        return todaCalculation;
    }

    public void setTodaCalculation(String todaCalculation) {
        this.todaCalculation = todaCalculation;
    }

    public String getPreviousLda() {
        return previousLda;
    }

    public void setPreviousLda(String previousLda) {
        this.previousLda = previousLda;
    }

    public String getPreviousAsda() {
        return previousAsda;
    }

    public void setPreviousAsda(String previousAsda) {
        this.previousAsda = previousAsda;
    }

    public String getPreviousTora() {
        return previousTora;
    }

    public void setPreviousTora(String previousTora) {
        this.previousTora = previousTora;
    }

    public String getPreviousToda() {
        return previousToda;
    }

    public void setPreviousToda(String previousToda) {
        this.previousToda = previousToda;
    }
}

