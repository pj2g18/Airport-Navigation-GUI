package model;

import java.util.ArrayList;
import java.util.HashMap;

/*
The interface that the Model implements, GUI calls methods in this interface to display simulation.
 */
public interface ModelInterface {

    public ArrayList<Runway> getRunways();

    public Obstacle getObstacle(Runway runway);

    public Number getLDA(Runway runway);

    public void setLDA(Number lda, Runway runway);

    public Number getTORA(Runway runway);

    public void setTORA(Number tora, Runway runway);

    public Number getTODA(Runway runway);

    public void setTODA(Number toda, Runway runway);

    public Number getASDA(Runway runway);

    public void setASDA(Number asda, Runway runway);

    public void addRunway(Number tora, Number toda, Number asda, Number lda);

    public void removeRunway(Runway runway);


    public void addobstacle (HashMap<String,Number> position, Number height, Runway runway);


}
