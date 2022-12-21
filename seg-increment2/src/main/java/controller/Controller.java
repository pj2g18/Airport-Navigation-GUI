//To eliminate "WARNING: Loading FXML document with JavaFX API..." remove the version from the path in the fxml files tags xmlns & xmlns:fx

package controller;
//TORA = 612 pixels which corresponds to a distance given. 1 pixel = x metres. Always this makes pixels
//Make methods that will easily disable all parts - pane does this but for only 1 logical runway etc
//If stopway/clearway are 0 make them disappear
//Move arrows based on distances

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import javax.swing.*;

import java.awt.*;
import java.awt.MenuItem;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.*;
import java.util.List;

import java.lang.Math;

public class Controller {

	public Stage primaryStage, tempStage, currentStage;
	private LogicalRunway currentLogical;


	@FXML
    private ChoiceBox<?> loadBtn;


    @FXML
    private Text notifications;

	@FXML
    private TextField letter;

	@FXML
    private Button saveBtn;

	@FXML
    private GridPane logicalInputsOne;

    Runway currentRunway;
	@FXML
    private TextField designationLeft;

	@FXML
    private TextField airport;

	@FXML
    private TextField toraLeft;

	@FXML
    private TextField asdaLeft;

	@FXML
    private TextField todaLeft;

	@FXML
    private TextField ldaLeft;

	@FXML
    private TextField clearwayLeft;

	@FXML
    private TextField blast;

	@FXML
    private TextField displacedLeft;

	@FXML
    private TextField stopwayLeft;

	@FXML
    private TextField stripEndLeft;

	@FXML
    private GridPane logicalInputsTwo;

	@FXML
    private TextField designationRight;

	@FXML
    private TextField toraRight;

	@FXML
    private TextField asdaRight;

	@FXML
    private Pane landingDirectionLTR;

	@FXML
    private TextField todaRight;

    @FXML
    private Label leftThresholdText;

    @FXML
    private Label rightThresholdText;

    @FXML
    private ComboBox<String> physicalSelector;


    @FXML
    private TextField ldaRight;

	 @FXML
     private TextField clearwayRight;

	 @FXML
     private TextField displacedRight;

	 @FXML
     private TextField stopwayRight;

	 @FXML
     private TextField stripEndRight;


	 @FXML
     private TextField obsID;

	 @FXML
     private TextField obsHeight;

	 @FXML
     private TextField obsLThreshold;

    @FXML
    private TextField obsRThreshold;

	 @FXML
     private TextField obsHeading;

	 @FXML
     private TextField obsCentreline;

    @FXML
    private ComboBox<String> selection;

	 @FXML
     private Button logicalBtn;

	 @FXML
     private Button obstacleBtn;

    @FXML
    private Pane viewPane;
    
    @FXML
    private Pane viewSide;

    @FXML
    private TextField runwayName;

    @FXML
    private ToggleGroup viewing;

    @FXML
    private TextArea outputArea;

    @FXML
    private Text calculationHeader;

    @FXML
    private ToggleGroup calculation;

    @FXML
    private RadioButton showLogicalOne;

    @FXML
    private RadioButton showLogicalTwo;

    @FXML
    private ToggleButton toggleCalculations;

    @FXML
    private ToggleGroup runway;


    @FXML
    private Button calculate;

    private static Model mod = new Model();

    private Boolean obstacleAdded = false;
    private Boolean secondLogicalAdded = false;

    private LogicalRunway currentLogicalRunway;

    //All components linked to physical runway
    @FXML
    private Rectangle runwayRectangle;
    @FXML
    private Line runwayLine;

    //Pane for whole top down view
    @FXML
    private Pane topDown;

    //Represents obstacle
    @FXML
    private Rectangle object;

    //All components linked to left to right logical runway
    @FXML
    private VBox leftChecks;
    @FXML
    private Text leftName;
    @FXML
    private Rectangle leftStopway;
    @FXML
    private Rectangle leftClearway;
    @FXML
    private Text leftStopwayText;
    @FXML
    private Text leftClearwayText;
    @FXML
    private Text leftToraText;
    @FXML
    private Text leftLdaText;
    @FXML
    private Text leftAsdaText;
    @FXML
    private Text leftTodaText;
    @FXML
    private Line leftDisplacedThreshold;
    @FXML
    private Line leftToraStartLine;
    @FXML
    private Line leftToraEndLine;
    @FXML
    private Rectangle todaRect1;
    @FXML
    private FixedTooltip todaRect1TT = new FixedTooltip();
    @FXML
    private Line leftToraLine;
    @FXML
    private Line leftLdaStartLine;
    @FXML
    private Line leftLdaEndLine;
    @FXML
    private Line leftLdaLine;
    @FXML
    private Line leftAsdaStartLine;
    @FXML
    private Line leftAsdaEndLine;
    @FXML
    private Line leftAsdaLine;
    @FXML
    private Line leftTodaStartLine;
    @FXML
    private Line leftTodaEndLine;
    @FXML
    private Line leftTodaLine;

    //All components linked to right to left logical runway
    @FXML
    private VBox rightChecks;
    @FXML
    private Text rightName;
    @FXML
    private Rectangle rightStopway;
    @FXML
    private Rectangle rightClearway;
    @FXML
    private Text rightStopwayText;
    @FXML
    private Text rightClearwayText;
    @FXML
    private Text rightToraText;
    @FXML
    private Text rightLdaText;
    @FXML
    private Text rightAsdaText;
    @FXML
    private Text rightTodaText;
    @FXML
    private Line rightDisplacedThreshold;
    @FXML
    private Line rightToraStartLine;
    @FXML
    private Line rightToraEndLine;
    @FXML
    private Line rightToraLine;
    @FXML
    private Line rightLdaStartLine;
    @FXML
    private Line rightLdaEndLine;
    @FXML
    private Line rightLdaLine;
    @FXML
    private Line rightAsdaStartLine;
    @FXML
    private Line rightAsdaEndLine;
    @FXML
    private Line rightAsdaLine;
    @FXML
    private Line rightTodaStartLine;
    @FXML
    private Line rightTodaEndLine;
    @FXML
    private Line rightTodaLine;
    @FXML
    private ComboBox<String> airportSelector;

    @FXML
    private ToggleButton rotateButton;

//SIDE COMPONENTS
    @FXML
    private Rectangle sideRunway, sideClearway, sideStopway;
    @FXML
    private Text sideName;
    @FXML
    private Rectangle sideObstacle;
    @FXML
    private Polygon sideArrowHead;
    @FXML
    private Rectangle sideArrowBar;
    @FXML
    private Text sideLDAText, sideLDAExtraTextOne, sideLDAExtraTextTwo;
    @FXML
    private Line sideLDAStart, sideLDALine, sideLDAEnd;
    @FXML
    private Line sideLDAExtraMarkOne, sideLDAExtraMarkTwo, sideLDAExtraLineOne, sideLDAExtraLineTwo;
    @FXML
    private Text sideTORAText, sideTORAExtraTextOne, sideTORAExtraTextTwo;
    @FXML
    private Line sideTORAStart, sideTORALine, sideTORAEnd;
    @FXML
    private Line sideTORAExtraMarkOne, sideTORAExtraMarkTwo, sideTORAExtraLineOne, sideTORAExtraLineTwo;
    @FXML
    private Text sideASDAText;
    @FXML
    private Line sideASDAStart, sideASDALine, sideASDAEnd;
    @FXML
    private Text sideTODAText;
    @FXML
    private Line sideTODAStart, sideTODALine, sideTODAEnd;
    @FXML
    private Line sideDisplacedThreshold;
    @FXML
    private RadioButton sideWithObstacle, sideWithoutObstacle;
    @FXML
    private ToggleGroup sideToggleObstacle;
    @FXML
    private Double sidePixelRatio;
    @FXML
    private Tooltip sideLDATooltip = new Tooltip(), sideTORATooltip = new Tooltip(), sideASDATooltip = new Tooltip(), sideTODATooltip = new Tooltip();
    @FXML
    private RadioButton sideLogicalOne, sideLogicalTwo;
    @FXML
    private ToggleGroup sideLogicals;
    @FXML
    private Text sideDirectionText, sideLandingDirection, sideTakeDirection;
    @FXML
    private Line sideSlopeLine, sideSlopeBase, sideSlopeTop;
    @FXML
    private Rectangle sideSlopeBlockerOne, sideSlopeBlockerTwo, sideSlopeBlockerThree, sideSlopeBlockerFour;
    @FXML
    private Text sideSlopeText;
    @FXML
    private HBox sideLDALabelPane, sideTORALabelPane;
    @FXML
    private FixedTooltip sideLDAExtraLineOneTooltip = new FixedTooltip(), sideLDAExtraLineTwoTooltip = new FixedTooltip();
    @FXML
    private FixedTooltip sideTORAExtraLineOneTooltip = new FixedTooltip(), sideTORAExtraLineTwoTooltip = new FixedTooltip();
    @FXML
    private FixedTooltip sideObstacleTooltip = new FixedTooltip(), sideSlopeTooltip = new FixedTooltip(), sideStopwayTooltip = new FixedTooltip(), sideClearwayTooltip = new FixedTooltip(), sideDisplacedThresholdTooltip = new FixedTooltip();
    @FXML
    private Rectangle sideLDATooltipBox, sideLDAExtraTooltipBoxOne, sideLDAExtraTooltipBoxTwo;
    @FXML
    private Rectangle sideTORATooltipBox, sideTORAExtraTooltipBoxOne, sideTORAExtraTooltipBoxTwo;
    @FXML
    private Rectangle sideASDATooltipBox, sideTODATooltipBox;
    @FXML
    private Text sideOutOfBounds;
// END OF SIDE COMPONENTS
    
    @FXML
    private TextField airportName;

    @FXML
    private TextField stripEnd;


    @FXML
    private TextField resa;
    @FXML
    private Line rightLdaMarker2;
    @FXML
    private Line rightLdaMarker1;    
    @FXML
    private Line leftToraMarker1;
    @FXML
    private Line leftToraMarker2;    
    @FXML
    private RadioButton viewOriginalCalcs;

    @FXML
    private Pane landingDirectionRTL;

    @FXML
    private ToggleGroup topdown;

    @FXML
    private RadioButton viewCalculated;

    @FXML
    private Line leftLdaMarker2;

    @FXML
    private Line leftLdaMarker1;

    @FXML
    private StackPane stack;

    @FXML
    private Text rightRunwayDirection;
    @FXML
    private Line rightToraMarker1;
    @FXML
    private Line rightToraMarker2;

    @FXML
    private Text leftRunwayDirection;


    Alert a = new Alert(Alert.AlertType.NONE);

    private int toraConstPixels = (int) 619;

    @FXML
    private Rectangle todaRect2;
    @FXML
    private FixedTooltip todaRect2TT = new FixedTooltip();
    @FXML
    private Rectangle asdaRect1;
    @FXML
    private FixedTooltip asdaRect1TT = new FixedTooltip();
    @FXML
    private Rectangle leftToraRect1;
    @FXML
    private Rectangle leftToraRect2;
    @FXML
    private Rectangle leftToraRect3;
    @FXML
    private FixedTooltip leftToraRect1TT = new FixedTooltip();
    @FXML
    private FixedTooltip leftToraRect2TT = new FixedTooltip();
    @FXML
    private FixedTooltip leftToraRect3TT = new FixedTooltip();
    @FXML
    private Rectangle leftLdaRect2;
    @FXML
    private Rectangle leftLdaRect1;
    @FXML
    private Rectangle leftLdaRect3;
    @FXML
    private FixedTooltip leftLdaRect1TT = new FixedTooltip();
    @FXML
    private FixedTooltip leftLdaRect2TT = new FixedTooltip();
    @FXML
    private FixedTooltip leftLdaRect3TT = new FixedTooltip();

    @FXML
    private Rectangle asdaRect2;
    @FXML
    private FixedTooltip asdaRect2TT = new FixedTooltip();
    @FXML
    private Rectangle rightToraRect1;
    @FXML
    private Rectangle rightToraRect2;
    @FXML
    private Rectangle rightToraRect3;
    @FXML
    private FixedTooltip rightToraRect1TT = new FixedTooltip();
    @FXML
    private FixedTooltip rightToraRect2TT = new FixedTooltip();
    @FXML
    private FixedTooltip rightToraRect3TT = new FixedTooltip();
    @FXML
    private Rectangle rightLdaRect2;
    @FXML
    private Rectangle rightLdaRect1;
    @FXML
    private Rectangle rightLdaRect3;
    @FXML
    private FixedTooltip rightLdaRect1TT = new FixedTooltip();
    @FXML
    private FixedTooltip rightLdaRect2TT = new FixedTooltip();
    @FXML
    private FixedTooltip rightLdaRect3TT = new FixedTooltip();
    @FXML
    private FixedTooltip obTT = new FixedTooltip();
    @FXML
    private FixedTooltip leftStopwayTT = new FixedTooltip();
    @FXML
    private FixedTooltip rightStopwayTT = new FixedTooltip();
    @FXML
    private FixedTooltip leftClearwayTT = new FixedTooltip();
    @FXML
    private FixedTooltip rightClearwayTT = new FixedTooltip();


    @FXML
    private ComboBox predefobs;
    @FXML
    private Button logicalRunwayInverse;


    @FXML
    private Line leftToraGreenLine;

    @FXML
    private Line leftToraRedLine;

    @FXML
    private Line leftLdaGreenLine;

    @FXML
    private Line leftLdaRedLine;

    @FXML
    private Line rightToraGreenLine;

    @FXML
    private Line rightToraRedLine;

    @FXML
    private Line rightLdaGreenLine;

    @FXML
    private Line rightLdaRedLine;

    @FXML
    private Text leftLdaExtra1;

    @FXML
    private Text leftLdaExtra2;

    @FXML
    private Text leftToraExtra1;

    @FXML
    private Text leftToraExtra2;

    @FXML
    private HBox leftLdaTextBox;

    @FXML
    private HBox leftToraTextBox;

    @FXML
    private Text rightLdaExtra1;

    @FXML
    private Text rightLdaExtra2;

    @FXML
    private Text rightToraExtra1;

    @FXML
    private Text rightToraExtra2;

    @FXML
    private HBox rightLdaTextBox;

    @FXML
    private HBox rightToraTextBox;

    @FXML
    private Text none1;

    @FXML
    private Text none2;

    @FXML
    private ImageView compass;

    @FXML
    private Button zoomInButton;
    @FXML
    private Button zoomOutButton;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;


    public Controller() {
    }

    public void initialize() throws Exception {
        mod.addController(this);
        toggleLeftLogicalRunway(false);
        togglePhysicalRunway(false);
        toggleRightLogicalRunway(false);
        hideSideComponents();
        installSideTooltips();
        installTopTooltips();
        PredefinedObstacles.addPredefined();

        compass.setVisible(false);
        zoomInButton.setDisable(true);
        zoomOutButton.setDisable(true);
        upButton.setDisable(true);
        downButton.setDisable(true);
        leftButton.setDisable(true);
        rightButton.setDisable(true);

        object.setVisible(false);
        //mod.addAirport("240","60","air");
        //mod.addRunway(new Runway("hello"));
        //mod.addLogicalRunway("3660","3860","3760","3660","18",'R',"100","200","0");
        //mod.addLogicalRunway("3660","3860","3760","3660","00",'L',"100","200","0");
        //mod.update();





    }

    public void update(){
        resetAll();

        try {
            if (mod.getAirport() != null && mod.getCurrentRunway() != null && mod.getLogicalRunways() != null) {
                if (mod.getLogicalRunways().keySet().size() == 1) {
                    showLogicalOne.setVisible(true);
                    showLogicalTwo.setVisible(false);
                    toggleCalculations.setVisible(true);
                    showLogicalOne.setText(mod.getLogicalRunways().values().iterator().next().getCombinedReference());
                } else if (mod.getLogicalRunways().keySet().size() == 2){
                    showLogicalOne.setVisible(true);
                    showLogicalTwo.setVisible(true);
                    toggleCalculations.setVisible(true);
                    int logCount = 1;
                    for (LogicalRunway logical : mod.getLogicalRunways().values()) {
                        if (logCount == 1) {
                            showLogicalOne.setText(logical.getCombinedReference());
                        } else if (logCount == 2) {
                            showLogicalTwo.setText(logical.getCombinedReference());
                        }
                        logCount += 1;
                    }
                }
            } else {
                showLogicalOne.setVisible(false);
                showLogicalTwo.setVisible(false);
                toggleCalculations.setVisible(false);
                viewOriginalCalcs.setDisable(true);
                viewCalculated.setSelected(true);
            }
        } catch (Exception e) {

        }

        try {
			if (mod.getAirport() != null && mod.getLogicalRunways() != null && mod.getCurrentRunway().getObstacle() == null) {
				toggleCalculations.setSelected(false);
				toggleCalculations.setDisable(true);

				if (toggleCalculations.isVisible()) {
					calculationHeader.setVisible(true);
				} else {
					calculationHeader.setVisible(false);
				}
			} else {
				toggleCalculations.setDisable(false);
				calculationHeader.setVisible(false);

			}
		} catch (Exception e1) {e1.printStackTrace();}
        runCalcs(null);

        if(!physicalSelector.getItems().isEmpty()){
            physicalSelector.getItems().clear();
        }

        ArrayList<String> runNames = new ArrayList<String>();
        for(Runway run : mod.getRunways()){
            if(!(run.getName() == null)){
                runNames.add(run.getName());
            }
            
        }

        physicalSelector.getItems().addAll(runNames);

        try {
            Runway run = mod.getCurrentRunway();
            physicalSelector.getSelectionModel().select(run.getName());
        }catch(Exception e){

        }

        airportSelector.getItems().clear();
        ArrayList<String> airNames = new ArrayList<String>();
        for(Airport a : mod.getAllAirports()){
            if(!(a.getName() == null)){
                airNames.add(a.getName());
            }
        }
        if(!airNames.contains(mod.getAirport().getName())) {
            airNames.add(mod.getAirport().getName());
        }
        airportSelector.getItems().addAll(airNames);
        try {
            Airport air = mod.getAirport();
            airportSelector.getSelectionModel().select(air.getName());
        }catch(Exception e){

        }



        RadioButton s = (RadioButton) viewing.getSelectedToggle();
        if(s.getText().equals("Side-On")){
            viewPane.setVisible(false);
        }else{
            viewPane.setVisible(true);
        }

        //Decides if Physical Runway should be shown on screen
        Runway currentRunway = null;
        try {
            currentRunway = mod.getCurrentRunway();
        } catch (Exception e) {

        }
        if(currentRunway != null){
            togglePhysicalRunway(true);
            //Decides how many logical runways should be present on screen
            //TODO Expand so uses values to calculate properly where lines should be and clearway/stopway

            RadioButton ss = (RadioButton) viewing.getSelectedToggle();
            if(ss.getText().equals("Side-On")){
                viewPane.setVisible(false);
            }else{
                viewPane.setVisible(true);
            }

            try {
                Collection<LogicalRunway> logicals = currentRunway.getLogicalRunways().values();
                if(currentRunway != null){
                    logicals = currentRunway.getLogicalRunways().values();
                }else{
                    logicals = new ArrayList<LogicalRunway>();
                }

                double shiftPixels = 0;
                if (logicals.size() == 0) {
                    toggleRightLogicalRunway(false);
                    toggleLeftLogicalRunway(false);
                    object.setVisible(false);

                    viewCalculated.setSelected(true);
                    viewOriginalCalcs.setSelected(false);
                    viewOriginalCalcs.setDisable(true);
                } else if (logicals.size() == 1) {
                    toggleRightLogicalRunway(false);
                    toggleLeftLogicalRunway(true);
                    for (LogicalRunway log : logicals) {
                        RadioButton button = (RadioButton) topdown.getSelectedToggle();
                        String selected = button.getText();
                        Obstacle ob = mod.getCurrentRunway().getObstacle();
                        boolean obstPos = false;
                        if(ob == null){
                            viewOriginalCalcs.setDisable(true);
                            viewCalculated.setSelected(true);
                            viewOriginalCalcs.setSelected(false);
                        }else {


                            for (Number num : ob.getObstaclePosition().values()) {
                                if (num.doubleValue() < -60) {
                                    obstPos = true;
                                }
                            }

                        }


                        if (selected.equals("View Without Obstacle")) {
                            updateLeftLogicalRunway(log);
                            object.setVisible(false);
                        } else if((ob.getDistanceFromCentre().doubleValue()>75 || ob.getDistanceFromCentre().doubleValue() < -75) || obstPos) {
                            updateLeftLogicalRunway(log);

                            object.setVisible(true);
                            none1.setVisible(true);
                            none2.setVisible(true);

                            double shiftPixels2;
                            shiftPixels2 = updateObstacle(log, ob, shiftPixels);
                            double shiftDisplacedLeft = (log.getDisplacedThreshold().doubleValue() / log.getTORA().doubleValue()) * toraConstPixels;
                            //updateLeftLogicalObstacle(log, shiftPixels2, shiftDisplacedLeft);
                        } else {
                            //TODO here calls methods to show correct updated line instead.
                            //This is where shift pixels happens using displaced threshold amount.
                            double toraPixels = Math.abs(leftToraLine.getStartX() - leftTodaLine.getEndX());
                            shiftPixels = (log.getDisplacedThreshold().doubleValue() / log.getTORA().doubleValue()) * toraConstPixels;

                            double shiftPixels2;

                            try {
                                ob = currentRunway.getObstacle();

                                if (ob == null) {
                                    object.setVisible(false);
                                    viewOriginalCalcs.setDisable(true);
                                    viewCalculated.setSelected(true);
                                    viewOriginalCalcs.setSelected(false);

                                } else {
                                    object.setVisible(true);
                                    viewOriginalCalcs.setDisable(false);
                                    shiftPixels2 = updateObstacle(log, ob, shiftPixels);
                                    double shiftDisplacedLeft = (log.getDisplacedThreshold().doubleValue() / log.getTORA().doubleValue()) * toraConstPixels;
                                    updateLeftLogicalObstacle(log, shiftPixels2, shiftDisplacedLeft);
                                }

                            } catch (Exception e) {
                                viewOriginalCalcs.setDisable(true);
                                viewCalculated.setSelected(true);
                                viewOriginalCalcs.setSelected(false);
                                object.setVisible(false);

                            }
                        }
                    }

                } else if (logicals.size() == 2) {
                    toggleRightLogicalRunway(true);
                    toggleLeftLogicalRunway(true);
                    int count = 0;
                    LogicalRunway leftLogical = null;
                    LogicalRunway rightLogical = null;
                    for (LogicalRunway log : logicals) {
                        if (count == 0) {
                            leftLogical = log;
                        } else if (leftLogical.getHeading().intValue() > log.getHeading().intValue()) {
                            rightLogical = leftLogical;
                            leftLogical = log;
                        } else {
                            rightLogical = log;
                        }
                        count++;

                    }
                    RadioButton button = (RadioButton) topdown.getSelectedToggle();
                    String selected = button.getText();
                    Obstacle ob = mod.getCurrentRunway().getObstacle();
                    boolean obstPos = false;
                    if(ob == null){

                        viewOriginalCalcs.setDisable(true);
                        viewCalculated.setSelected(true);
                        viewOriginalCalcs.setSelected(false);
                    }else {

                        for (Number num : ob.getObstaclePosition().values()) {
                            if (num.doubleValue() < -60) {
                                obstPos = true;
                            }
                        }
                    }

                    if (selected.equals("View Without Obstacle")) {

                        shiftPixels = updateLeftLogicalRunway(leftLogical);
                        updateRightLogicalRunway(rightLogical);

                    } else if((ob.getDistanceFromCentre().doubleValue()>75 || ob.getDistanceFromCentre().doubleValue() < -75) || obstPos) {

                        shiftPixels = updateLeftLogicalRunway(leftLogical);
                        updateRightLogicalRunway(rightLogical);

                        object.setVisible(true);
                        none1.setVisible(true);
                        none1.setVisible(true);
                        double shiftPixels2;
                        shiftPixels = (leftLogical.getDisplacedThreshold().doubleValue() / leftLogical.getTORA().doubleValue()) * toraConstPixels;
                        double shiftDisplacedLeft = (leftLogical.getDisplacedThreshold().doubleValue() / leftLogical.getTORA().doubleValue()) * toraConstPixels;
                        shiftPixels2 = updateObstacle(leftLogical, ob, shiftPixels);
                    }else{

                        //TODO here calls methods to show correct updated line instead, using calculation object and resetting translates to 0.
                        double shiftPixels2;
                        try {
                            ob = currentRunway.getObstacle();
                            if (ob == null) {
                                object.setVisible(false);
                                viewOriginalCalcs.setDisable(true);

                                viewCalculated.setSelected(true);
                                viewOriginalCalcs.setSelected(false);
                            } else {
                                shiftPixels = (leftLogical.getDisplacedThreshold().doubleValue() / leftLogical.getTORA().doubleValue()) * toraConstPixels;
                                double shiftDisplacedLeft = (leftLogical.getDisplacedThreshold().doubleValue() / leftLogical.getTORA().doubleValue()) * toraConstPixels;
                                shiftPixels2 = updateObstacle(leftLogical, ob, shiftPixels);
                                updateLeftLogicalObstacle(leftLogical, shiftPixels2, shiftDisplacedLeft);

                                Obstacle obst = mod.getCurrentRunway().getObstacle();
                                double distanceRight = obst.getObstaclePosition().get(rightLogical.getCombinedReference()).doubleValue();
                                double shiftPixels3 = ((distanceRight / rightLogical.getTORA().doubleValue()) * Double.valueOf(toraConstPixels));
                                double shiftDisplaced = (rightLogical.getDisplacedThreshold().doubleValue() / rightLogical.getTORA().doubleValue()) * toraConstPixels;
                                shiftPixels3 += (rightLogical.getDisplacedThreshold().doubleValue() / rightLogical.getTORA().doubleValue()) * toraConstPixels;
                                updateRightLogicalObstacle(rightLogical, shiftPixels3, shiftDisplaced);
                                object.setVisible(true);
                                viewOriginalCalcs.setDisable(false);


                            }

                        } catch (Exception e) {

                            object.setVisible(false);
                            viewOriginalCalcs.setDisable(true);
                            viewCalculated.setSelected(true);
                            viewOriginalCalcs.setSelected(false);
                        }

                    }

                }
                if (currentRunway != null && currentRunway.getLogicalRunways().values().size() > 0) {
                	updateSide(currentRunway);
            	} else {
            		hideSideComponents();
            	}
    	    } catch (NullPointerException e){
    	    	viewOriginalCalcs.setDisable(true);
                viewCalculated.setSelected(true);
                viewOriginalCalcs.setSelected(false);
                mod.update();


            } catch (Exception e) {

            }
        }else{
            togglePhysicalRunway(false);
            hideSideComponents();

        }
    }

    public void installTopTooltips(){
        FixedTooltip.install(todaRect1,todaRect1TT);
        todaRect1TT.setFont(new Font(13.0));
        todaRect1TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(asdaRect1,asdaRect1TT);
        asdaRect1TT.setFont(new Font(13.0));
        asdaRect1TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(leftToraRect1,leftToraRect1TT);
        leftToraRect1TT.setFont(new Font(13.0));
        leftToraRect1TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(leftToraRect2,leftToraRect2TT);
        leftToraRect2TT.setFont(new Font(13.0));
        leftToraRect2TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(leftToraRect3,leftToraRect3TT);
        leftToraRect3TT.setFont(new Font(13.0));
        leftToraRect3TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(leftLdaRect1,leftLdaRect1TT);
        leftLdaRect1TT.setFont(new Font(13.0));
        leftLdaRect1TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(leftLdaRect2,leftLdaRect2TT);
        leftLdaRect2TT.setFont(new Font(13.0));
        leftLdaRect2TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(leftLdaRect3,leftLdaRect3TT);
        leftLdaRect3TT.setFont(new Font(13.0));
        leftLdaRect3TT.setShowDelay(Duration.seconds(0));

        FixedTooltip.install(todaRect2,todaRect2TT);
        todaRect2TT.setFont(new Font(13.0));
        todaRect2TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(asdaRect2,asdaRect2TT);
        asdaRect2TT.setFont(new Font(13.0));
        asdaRect2TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(rightToraRect1,rightToraRect1TT);
        rightToraRect1TT.setFont(new Font(13.0));
        rightToraRect1TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(rightToraRect2,rightToraRect2TT);
        rightToraRect2TT.setFont(new Font(13.0));
        rightToraRect2TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(rightToraRect3,rightToraRect3TT);
        rightToraRect3TT.setFont(new Font(13.0));
        rightToraRect3TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(rightLdaRect1,rightLdaRect1TT);
        rightLdaRect1TT.setFont(new Font(13.0));
        rightLdaRect1TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(rightLdaRect2,rightLdaRect2TT);
        rightLdaRect2TT.setFont(new Font(13.0));
        rightLdaRect2TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(rightLdaRect3,rightLdaRect3TT);
        rightLdaRect3TT.setFont(new Font(13.0));
        rightLdaRect3TT.setShowDelay(Duration.seconds(0));
        FixedTooltip.install(object,obTT);
        obTT.setFont(new Font(13.0));
        obTT.setShowDelay(Duration.seconds(0));

        FixedTooltip.install(leftStopway,leftStopwayTT);
        leftStopwayTT.setFont(new Font(13));
        leftStopwayTT.setShowDelay(new Duration(0));

        FixedTooltip.install(rightStopway,rightStopwayTT);
        rightStopwayTT.setFont(new Font(13));
        rightStopwayTT.setShowDelay(new Duration(0));

        FixedTooltip.install(leftClearway,leftClearwayTT);
        leftClearwayTT.setFont(new Font(13));
        leftClearwayTT.setShowDelay(new Duration(0));

        FixedTooltip.install(rightClearway,rightClearwayTT);
        rightClearwayTT.setFont(new Font(13));
        rightClearwayTT.setShowDelay(new Duration(0));



    }

    public void resetAll(){



        viewOriginalCalcs.setDisable(false);
        toggleLeftLogicalRunway(false);
        toggleRightLogicalRunway(false);
        leftToraStartLine.setTranslateX(0);
        leftLdaStartLine.setTranslateX(0);
        leftAsdaStartLine.setTranslateX(0);
        leftTodaStartLine.setTranslateX(0);
        leftTodaEndLine.setTranslateX(0);
        leftAsdaEndLine.setTranslateX(0);
        leftAsdaEndLine.setStartX(944);
        leftAsdaEndLine.setEndX(944);
        leftTodaEndLine.setStartX(1057);
        leftTodaEndLine.setEndX(1057);
        rightAsdaEndLine.setStartX(149.0858612060547);
        rightAsdaEndLine.setEndX(149.0858612060547);
        rightTodaEndLine.setStartX(42.08586120605469);
        rightTodaEndLine.setEndX(42.08586120605469);
        leftLdaEndLine.setTranslateX(0);
        leftToraEndLine.setTranslateX(0);
        leftToraLine.setStartX(243.5);
        leftToraLine.setEndX(857);
        leftLdaLine.setStartX(243.5);
        leftLdaLine.setEndX(857);
        leftAsdaLine.setStartX(244);
        leftAsdaLine.setEndX(942);
        leftTodaLine.setStartX(245);
        leftTodaLine.setEndX(1054);
        leftDisplacedThreshold.setTranslateX(0);
        object.setTranslateX(0);
        object.setTranslateY(0);
        object.setVisible(false);
        rightToraStartLine.setTranslateX(0);
        rightLdaStartLine.setTranslateX(0);
        rightAsdaStartLine.setTranslateX(0);
        rightTodaStartLine.setTranslateX(0);
        rightTodaEndLine.setTranslateX(0);
        rightAsdaEndLine.setTranslateX(0);
        rightLdaEndLine.setTranslateX(0);
        rightToraEndLine.setTranslateX(0);
        rightToraLine.setStartX(239.3);
        rightToraLine.setEndX(851);
        rightLdaLine.setStartX(239.4);
        rightLdaLine.setEndX(852);
        rightAsdaLine.setStartX(151.3);
        rightAsdaLine.setEndX(850);
        rightTodaLine.setStartX(44.88);
        rightTodaLine.setEndX(850);
        rightDisplacedThreshold.setTranslateX(0);
        leftLdaText.setVisible(false);
        leftLdaMarker2.setVisible(false);
        leftLdaMarker1.setVisible(false);
        leftLdaMarker2.setTranslateX(0);
        leftLdaMarker1.setTranslateX(0);
        rightLdaMarker1.setVisible(false);
        rightLdaMarker2.setVisible(false);
        rightLdaMarker2.setTranslateX(0);
        rightLdaMarker1.setTranslateX(0);
        landingDirectionLTR.setVisible(false);
        landingDirectionRTL.setVisible(false);
        leftToraMarker2.setVisible(false);
        leftToraMarker1.setVisible(false);
        leftStopway.setVisible(false);
        leftStopwayText.setVisible(false);
        leftClearwayText.setVisible(false);
        leftClearway.setVisible(false);
        rightStopwayText.setVisible(false);
        rightStopway.setVisible(false);
        rightClearway.setVisible(false);
        rightClearwayText.setVisible(false);
        leftRunwayDirection.setText("");
        rightRunwayDirection.setText("");
        rightToraMarker2.setTranslateX(0);
        rightToraMarker1.setTranslateX(0);
        rightToraMarker2.setVisible(false);
        rightToraMarker1.setVisible(false);

        todaRect1.setWidth(819);
        todaRect1.setHeight(24);
        todaRect1.setX(241);
        todaRect1.setY(384);
        asdaRect1.setWidth(702);
        asdaRect1.setHeight(24);
        asdaRect1.setX(240);
        asdaRect1.setY(350);
        leftToraRect1.setWidth(618);
        leftToraRect1.setHeight(24);
        leftToraRect1.setX(242);
        leftToraRect1.setY(288);
        leftToraRect2.setWidth(0);
        leftToraRect2.setHeight(24);
        leftToraRect2.setX(242);
        leftToraRect2.setY(288);
        leftToraRect3.setWidth(0);
        leftToraRect3.setHeight(24);
        leftToraRect3.setX(242);
        leftToraRect3.setY(288);
        leftLdaRect1.setWidth(618);
        leftLdaRect1.setHeight(24);
        leftLdaRect1.setX(242);
        leftLdaRect1.setY(321);

        leftLdaGreenLine.setStartX(242);
        leftLdaGreenLine.setEndX(242);


        leftLdaRedLine.setStartX(242);
        leftLdaRedLine.setEndX(242);

        leftToraRedLine.setStartX(242);
        leftToraRedLine.setEndX(242);

        leftToraGreenLine.setStartX(242);
        leftToraGreenLine.setEndX(242);

        rightLdaGreenLine.setStartX(853);
        rightLdaGreenLine.setEndX(853);


        rightLdaRedLine.setStartX(853);
        rightLdaRedLine.setEndX(853);

        rightToraRedLine.setStartX(853);
        rightToraRedLine.setEndX(853);

        rightToraGreenLine.setStartX(853);
        rightToraGreenLine.setEndX(853);



        leftLdaRect2.setWidth(0);
        leftLdaRect2.setHeight(24);
        leftLdaRect2.setX(242);
        leftLdaRect2.setY(320);
        leftLdaRect3.setWidth(0);
        leftLdaRect3.setHeight(24);
        leftLdaRect3.setX(242);
        leftLdaRect3.setY(320);

        todaRect2.setWidth(813);
        todaRect2.setHeight(24);
        todaRect2.setX(40);
        todaRect2.setY(109);
        asdaRect2.setWidth(702);
        asdaRect2.setHeight(24);
        asdaRect2.setX(150);
        asdaRect2.setY(77);
        rightToraRect1.setTranslateX(0);
        rightToraRect1.setWidth(616);
        rightToraRect1.setHeight(24);
        rightToraRect1.setX(237);
        rightToraRect1.setY(12);
        rightToraRect2.setTranslateX(0);
        rightToraRect2.setWidth(0);
        rightToraRect2.setHeight(24);
        rightToraRect2.setX(853);
        rightToraRect2.setY(13);
        rightToraRect3.setTranslateX(0);
        rightToraRect3.setWidth(0);
        rightToraRect3.setHeight(24);
        rightToraRect3.setX(853);
        rightToraRect3.setY(13);
        rightLdaRect1.setWidth(616);
        rightLdaRect1.setHeight(24);
        rightLdaRect1.setX(237);
        rightLdaRect1.setY(49);
        rightLdaRect2.setTranslateX(0);
        rightLdaRect2.setWidth(0);
        rightLdaRect2.setHeight(24);
        rightLdaRect2.setX(853);
        rightLdaRect2.setY(49);
        rightToraRect3.setTranslateX(0);
        rightLdaRect3.setWidth(0);
        rightLdaRect3.setHeight(24);
        rightLdaRect3.setX(853);
        rightLdaRect3.setY(49);

        leftLdaExtra1.setVisible(false);
        leftLdaExtra2.setVisible(false);
        leftToraExtra1.setVisible(false);
        leftToraExtra2.setVisible(false);

        rightLdaText.setVisible(false);
        rightLdaExtra1.setVisible(false);
        rightToraExtra1.setVisible(false);
        rightToraText.setVisible(false);

        rightLdaTextBox.setVisible(true);
        rightToraTextBox.setVisible(true);
        leftLdaTextBox.setVisible(true);
        leftToraTextBox.setVisible(true);

        rightLdaTextBox.setTranslateX(0);
        rightToraTextBox.setTranslateX(0);
        rightLdaExtra2.setText("LDA");
        rightLdaExtra2.setFill(Color.WHITE);

        rightToraExtra2.setText("TORA");
        rightToraExtra2.setFill(Color.WHITE);

        rightAsdaText.setTranslateX(0);
        rightTodaText.setTranslateX(0);

        leftLdaTextBox.setTranslateX(0);
        leftToraTextBox.setTranslateX(0);
        leftLdaText.setText("LDA");
        leftLdaText.setFill(Color.WHITE);

        leftToraText.setText("TORA");
        leftToraText.setFill(Color.WHITE);

        leftAsdaText.setTranslateX(0);
        leftTodaText.setTranslateX(0);

        leftLdaGreenLine.setVisible(false);
        leftLdaRedLine.setVisible(false);
        leftToraRedLine.setVisible(false);
        leftToraGreenLine.setVisible(false);

        rightLdaGreenLine.setVisible(false);
        rightLdaRedLine.setVisible(false);
        rightToraRedLine.setVisible(false);
        rightToraGreenLine.setVisible(false);

        none1.setVisible(false);
        none2.setVisible(false);


    }

    public void updateRightLogicalObstacle(LogicalRunway log, double shiftPixels,double shiftDisplaced) throws Exception {

        //Landing
        Calculation lo = mod.getCurrentRunway().calculateLandingOver(log,mod.getAirport());
        Calculation lt = mod.getCurrentRunway().calculateLandingTowards(log,mod.getAirport());
        landingDirectionRTL.setVisible(true);

        int lda = Math.max(lo.getLda(),lt.getLda());

        if(lda == lo.getLda()){
            int previousLda = lo.getPreviousLdaNum().intValue();

            int difference = previousLda - lo.getLda();

            rightLdaStartLine.setTranslateX(-shiftPixels);
            rightLdaLine.setEndX(rightLdaLine.getEndX()-shiftPixels);

            rightLdaTextBox.setTranslateX(-shiftPixels);

            rightLdaMarker1.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels)-shiftDisplaced);
            rightLdaMarker1.setVisible(true);
            rightDisplacedThreshold.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels)-shiftDisplaced);

            rightLdaRect1.setWidth(((double) lo.getLda())/log.getTORA().doubleValue()*toraConstPixels);
            rightLdaRect1TT.setText("(R) LDA = "+(double) lo.getLda());

            if(lo.getResa() != null){
                rightLdaMarker2.setVisible(true);
                rightLdaMarker2.setTranslateX(((lo.getStripEnd().doubleValue())/(lo.getResa().doubleValue()+lo.getStripEnd().doubleValue()))*-(rightLdaStartLine.getTranslateX()-rightLdaMarker1.getTranslateX())-shiftPixels);

                rightLdaRect2.setTranslateX(((lo.getStripEnd().doubleValue())/(lo.getResa().doubleValue()+lo.getStripEnd().doubleValue()))*-(rightLdaStartLine.getTranslateX()-rightLdaMarker1.getTranslateX())-shiftPixels);
                rightLdaRect2.setWidth(lo.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightLdaRect2TT.setText("Strip End = "+lo.getStripEnd());

                rightLdaExtra1.setVisible(true);
                rightLdaExtra1.setText("RESA");
                rightLdaExtra1.setFill(Color.LIME);

                rightLdaRedLine.setVisible(true);
                rightLdaRedLine.setStartX(rightLdaRect2.getX()+rightLdaRect2.getTranslateX());
                rightLdaRedLine.setEndX(rightLdaRedLine.getStartX()+rightLdaRect2.getWidth());

                rightLdaRect3.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels)-shiftDisplaced);
                rightLdaRect3.setWidth(lo.getResa().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightLdaRect3TT.setText("RESA = "+lo.getResa());

                rightLdaExtra2.setVisible(true);
                rightLdaExtra2.setText("LDA");
                rightLdaExtra2.setFill(Color.WHITE);

                rightLdaText.setText("STRIP-END");
                rightLdaText.setVisible(true);
                rightLdaText.setFill(Color.RED);

                rightLdaGreenLine.setVisible(true);
                rightLdaGreenLine.setStartX(rightLdaRect3.getX()+rightLdaRect3.getTranslateX());
                rightLdaGreenLine.setEndX(rightLdaGreenLine.getStartX()+rightLdaRect3.getWidth());

            }else if(lo.getSlope() != null){
                rightLdaMarker2.setVisible(true);
                rightLdaMarker2.setTranslateX(((lo.getStripEnd().doubleValue())/(lo.getSlope().doubleValue()+lo.getStripEnd().doubleValue()))*-(rightLdaStartLine.getTranslateX()-rightLdaMarker1.getTranslateX())-shiftPixels);

                rightLdaRect2.setTranslateX(((lo.getStripEnd().doubleValue())/(lo.getSlope().doubleValue()+lo.getStripEnd().doubleValue()))*-(rightLdaStartLine.getTranslateX()-rightLdaMarker1.getTranslateX())-shiftPixels);
                rightLdaRect2.setWidth(lo.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightLdaRect2TT.setText("Strip End = "+lo.getStripEnd());



                rightLdaExtra2.setVisible(true);
                rightLdaExtra2.setText("LDA");
                rightLdaExtra2.setFill(Color.WHITE);

                rightLdaText.setText("STRIP-END");
                rightLdaText.setVisible(true);
                rightLdaText.setFill(Color.RED);

                rightLdaExtra1.setVisible(true);
                rightLdaExtra1.setText("SLOPE");
                rightLdaExtra1.setFill(Color.LIME);

                rightLdaRedLine.setVisible(true);
                rightLdaRedLine.setStartX(rightLdaRect2.getX()+rightLdaRect2.getTranslateX());
                rightLdaRedLine.setEndX(rightLdaRedLine.getStartX()+rightLdaRect2.getWidth());

                rightLdaRect3.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels)-shiftDisplaced);
                rightLdaRect3.setWidth(lo.getSlope().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightLdaRect3TT.setText("Slope = h x 50 = "+lo.getSlope());

                rightLdaGreenLine.setVisible(true);
                rightLdaGreenLine.setStartX(rightLdaRect3.getX()+rightLdaRect3.getTranslateX());
                rightLdaGreenLine.setEndX(rightLdaGreenLine.getStartX()+rightLdaRect3.getWidth());

            }else if(lo.getBlastProtection() != null) {

                rightLdaRect3.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels)-shiftDisplaced);
                rightLdaRect3.setWidth(lo.getBlastProtection().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightLdaRect3TT.setText("Blast Protection = "+lo.getBlastProtection());

                rightLdaRedLine.setVisible(true);
                rightLdaRedLine.setStartX(rightLdaRect3.getX()+rightLdaRect3.getTranslateX());
                rightLdaRedLine.setEndX(rightLdaRedLine.getStartX()+rightLdaRect3.getWidth());

                rightLdaExtra1.setVisible(true);
                rightLdaExtra1.setText("BLAST");
                rightLdaExtra1.setFill(Color.RED);

                rightLdaMarker2.setVisible(false);

                rightLdaExtra2.setVisible(true);
                rightLdaExtra2.setText("LDA");

                rightLdaExtra2.setFill(Color.WHITE);
            }

            rightRunwayDirection.setText(log.getCombinedReference()+" Runway Direction\nLanding Over");

        }else {
            rightLdaLine.setVisible(true);
            rightLdaText.setVisible(true);
            rightLdaStartLine.setVisible(true);
            rightLdaMarker2.setVisible(true);
            rightLdaMarker1.setVisible(true);

            if(log.getDisplacedThreshold().intValue()> 0){
                double newshiftPixels;
                double toraEnd = rightToraLine.getEndX();
                newshiftPixels = (log.getDisplacedThreshold().doubleValue()/log.getTORA().doubleValue())*toraConstPixels;
                rightDisplacedThreshold.setTranslateX(-newshiftPixels);
                rightLdaLine.setEndX(toraEnd-newshiftPixels);
                rightLdaStartLine.setTranslateX(-newshiftPixels);
                rightLdaText.setTranslateX(-newshiftPixels);
            }



            rightLdaLine.setStartX(rightLdaLine.getStartX()+(toraConstPixels-shiftPixels));
            rightLdaEndLine.setTranslateX((toraConstPixels-shiftPixels));

            rightLdaTextBox.setTranslateX((toraConstPixels-shiftPixels));

            rightLdaMarker1.setTranslateX(-shiftDisplaced-((((double)lt.getLda())/log.getTORA().doubleValue())*toraConstPixels)+4);
            rightLdaMarker2.setTranslateX(-shiftDisplaced-(((((double)lt.getLda())+lt.getResa().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+4);
            rightRunwayDirection.setText(log.getCombinedReference()+" Runway Direction\nLanding Towards");

            rightLdaRect1.setWidth(-1*rightLdaMarker1.getTranslateX());
            rightLdaRect1.setTranslateX(toraConstPixels+rightLdaMarker1.getTranslateX());
            rightLdaRect1TT.setText("(R) LDA = "+lt.getLda());

            rightLdaRect2.setWidth(lt.getResa().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
            rightLdaRect2.setTranslateX(rightLdaMarker2.getTranslateX());
            rightLdaRect2TT.setText("RESA = "+lt.getResa());

            rightLdaExtra2.setVisible(true);
            rightLdaExtra2.setText("STRIP-END");
            rightLdaExtra2.setFill(Color.RED);


            rightLdaRect3.setWidth(lt.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
            rightLdaRect3.setTranslateX((-shiftDisplaced-(((((double)lt.getLda())+lt.getResa().doubleValue()+lt.getStripEnd().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+4));
            rightLdaRect3TT.setText("Strip End = "+lt.getStripEnd());

            rightLdaExtra1.setVisible(true);
            rightLdaExtra1.setText("RESA");
            rightLdaExtra1.setFill(Color.LIME);

            rightLdaText.setVisible(true);
            rightLdaText.setText("LDA");
            rightLdaText.setFill(Color.WHITE);

            rightLdaRedLine.setVisible(true);
            rightLdaRedLine.setStartX(rightLdaRect3.getX()+rightLdaRect3.getTranslateX());
            rightLdaRedLine.setEndX(rightLdaRedLine.getStartX()+rightLdaRect3.getWidth());

            rightLdaGreenLine.setVisible(true);
            rightLdaGreenLine.setStartX(rightLdaRect2.getX()+rightLdaRect2.getTranslateX());
            rightLdaGreenLine.setEndX(rightLdaGreenLine.getStartX()+rightLdaRect2.getWidth());

        }

        //Take Off
        Calculation ta = mod.getCurrentRunway().calculateTakeOffAway(log,mod.getAirport());
        Calculation tt = mod.getCurrentRunway().calculateTakeOffTowards(log,mod.getAirport());

        int tora = Math.max(ta.getTora(),tt.getTora());

        if(tora == ta.getTora()){
            int previousTora = ta.getPreviousToraNum().intValue();

            int difference = previousTora - ta.getTora();

            rightToraStartLine.setTranslateX(-shiftPixels);
            rightToraLine.setEndX(rightToraLine.getEndX()-shiftPixels);

            rightToraTextBox.setTranslateX(-shiftPixels);

            rightToraMarker1.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightToraMarker1.setVisible(true);

            rightToraRect1.setWidth(rightToraRect1.getWidth() - ((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightToraRect1TT.setText("(R) TORA = "+ta.getTora());

            if(ta.getResa() != null){
                rightToraMarker2.setVisible(true);
                rightToraMarker2.setTranslateX(-(((difference-ta.getResa().doubleValue())/log.getTORA().doubleValue())*toraConstPixels));

                rightToraRect2.setTranslateX(-(((difference-ta.getResa().doubleValue())/log.getTORA().doubleValue())*toraConstPixels));
                rightToraRect2.setWidth(ta.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightToraRect2TT.setText("Strip End = "+ta.getStripEnd());

                rightToraText.setVisible(true);
                rightToraText.setText("STRIP-END");
                rightToraText.setFill(Color.RED);
                rightToraRedLine.setVisible(true);
                rightToraRedLine.setStartX(rightToraRect2.getX()+rightToraRect2.getTranslateX());
                rightToraRedLine.setEndX(rightToraRedLine.getStartX()+rightToraRect2.getWidth());

                rightToraRect3.setTranslateX(-(((difference)/log.getTORA().doubleValue())*toraConstPixels));
                rightToraRect3.setWidth(ta.getResa().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightToraRect3TT.setText("RESA = "+ta.getResa());

                rightToraExtra1.setVisible(true);
                rightToraExtra1.setText("RESA");
                rightToraExtra1.setFill(Color.LIME);

                rightToraExtra2.setVisible(true);
                rightToraExtra2.setText("TORA");
                rightToraExtra2.setFill(Color.WHITE);

                rightToraGreenLine.setVisible(true);
                rightToraGreenLine.setStartX(rightToraRect3.getX()+rightToraRect3.getTranslateX());
                rightToraGreenLine.setEndX(rightToraGreenLine.getStartX()+rightToraRect3.getWidth());

            }else if(ta.getBlastProtection() != null){
                rightToraMarker2.setVisible(false);
                rightToraRect2.setTranslateX(-(((difference)/log.getTORA().doubleValue())*toraConstPixels));
                rightToraRect2.setWidth(ta.getBlastProtection().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightToraRect2TT.setText("Blast Protection = "+ta.getBlastProtection());

                rightToraExtra1.setVisible(true);
                rightToraExtra1.setText("BLAST");
                rightToraExtra1.setFill(Color.RED);

                rightToraExtra2.setVisible(true);
                rightToraExtra2.setText("TORA");
                rightToraExtra2.setFill(Color.WHITE);

                rightToraRedLine.setVisible(true);
                rightToraRedLine.setStartX(rightToraRect2.getX()+rightToraRect2.getTranslateX());
                rightToraRedLine.setEndX(rightToraRedLine.getStartX()+rightToraRect2.getWidth());

            }

            rightRunwayDirection.setText(rightRunwayDirection.getText()+"\nTake Off Away");

            showStopwayClearwayRight(ta.getAsda()-ta.getTora(),ta.getToda()-ta.getTora());
            rightAsdaStartLine.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightAsdaLine.setEndX(rightAsdaLine.getEndX()-((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightTodaStartLine.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightTodaLine.setEndX(rightTodaLine.getEndX()-((difference/log.getTORA().doubleValue())*toraConstPixels));

            rightAsdaText.setTranslateX(rightAsdaStartLine.getTranslateX());
            rightTodaText.setTranslateX(rightTodaStartLine.getTranslateX());

            todaRect2TT.setText("(R) TODA = (R) TORA + Clearway ("+log.getClearway()+") = "+String.valueOf(ta.getToda()));
            asdaRect2TT.setText(" (R) ASDA = (R) TORA + Stopway ("+log.getStopway()+") = "+String.valueOf(ta.getAsda()));
            showStopwayClearwayRight(ta.getAsda()-ta.getTora(),ta.getToda()-ta.getTora());



        }else{
            int previousTora = tt.getPreviousToraNum().intValue();

            int difference = previousTora - tt.getTora();

            rightToraLine.setStartX(rightToraLine.getStartX()+(toraConstPixels-shiftPixels));
            rightToraEndLine.setTranslateX((toraConstPixels-shiftPixels));

            rightToraMarker1.setVisible(true);
            rightToraMarker1.setTranslateX((-toraConstPixels+4)+((difference/log.getTORA().doubleValue())*toraConstPixels));

            rightToraRect1.setWidth(rightToraRect1.getWidth()-((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightToraRect1.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightToraRect1TT.setText("(R) TORA = "+tt.getTora());

            double newpixs = 0;
            if(tt.getResa() != null){
                rightToraMarker2.setVisible(true);
                rightToraMarker2.setTranslateX(-toraConstPixels+(((difference-tt.getResa().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+3);
                newpixs = (tt.getResa().doubleValue()+tt.getStripEnd().doubleValue());

                rightToraRect2.setTranslateX(-toraConstPixels+(((difference-tt.getResa().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+3);
                rightToraRect2.setWidth(tt.getResa().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightToraRect2TT.setText("RESA = "+tt.getResa());

                rightToraExtra1.setVisible(true);
                rightToraExtra1.setText("RESA");
                rightToraExtra1.setFill(Color.LIME);

                rightToraText.setVisible(true);
                rightToraText.setText("TORA");
                rightToraText.setFill(Color.WHITE);




                rightToraRect3.setTranslateX(-toraConstPixels+(((difference-tt.getResa().doubleValue()-tt.getStripEnd().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+3);
                rightToraRect3.setWidth(tt.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightToraRect3TT.setText("Strip End = "+tt.getStripEnd());

                rightToraExtra2.setVisible(true);
                rightToraExtra2.setText("STRIP-END");
                rightToraExtra2.setFill(Color.RED);

                rightToraRedLine.setVisible(true);
                rightToraRedLine.setStartX(rightToraRect3.getX()+rightToraRect3.getTranslateX());
                rightToraRedLine.setEndX(rightToraRedLine.getStartX()+rightToraRect3.getWidth());

                rightToraGreenLine.setVisible(true);
                rightToraGreenLine.setStartX(rightToraRect2.getX()+rightToraRect2.getTranslateX());
                rightToraGreenLine.setEndX(rightToraGreenLine.getStartX()+rightToraRect2.getWidth());



            }else if(tt.getSlope() != null){
                rightToraMarker2.setVisible(true);
                rightToraMarker2.setTranslateX(-toraConstPixels+(((difference-tt.getSlope().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+3);
                newpixs = (tt.getSlope().doubleValue()+tt.getStripEnd().doubleValue());

                rightToraRect2.setTranslateX(-toraConstPixels+(((difference-tt.getSlope().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+3);
                rightToraRect2.setWidth(tt.getSlope().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightToraRect2TT.setText("Slope = h x 50 = "+tt.getSlope());

                rightToraExtra1.setVisible(true);
                rightToraExtra1.setText("SLOPE");
                rightToraExtra1.setFill(Color.LIME);

                rightToraText.setVisible(true);
                rightToraText.setText("TORA");
                rightToraText.setFill(Color.WHITE);



                rightToraRect3.setTranslateX(-toraConstPixels+(((difference-tt.getSlope().doubleValue()-tt.getStripEnd().doubleValue())/log.getTORA().doubleValue())*toraConstPixels)+3);
                rightToraRect3.setWidth(tt.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                rightToraRect3TT.setText("Strip End = "+tt.getStripEnd());

                rightToraExtra2.setVisible(true);
                rightToraExtra2.setText("STRIP-END");
                rightToraExtra2.setFill(Color.RED);

                rightToraRedLine.setVisible(true);
                rightToraRedLine.setStartX(rightToraRect3.getX()+rightToraRect3.getTranslateX());
                rightToraRedLine.setEndX(rightToraRedLine.getStartX()+rightToraRect3.getWidth());

                rightToraGreenLine.setVisible(true);
                rightToraGreenLine.setStartX(rightToraRect2.getX()+rightToraRect2.getTranslateX());
                rightToraGreenLine.setEndX(rightToraGreenLine.getStartX()+rightToraRect2.getWidth());
            }

            rightRunwayDirection.setText(rightRunwayDirection.getText()+"\nTake Off Towards");

            showStopwayClearwayRight(0,0);
            rightAsdaEndLine.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightAsdaLine.setStartX(rightAsdaLine.getStartX()+((newpixs/log.getTORA().doubleValue())*toraConstPixels));
            rightTodaEndLine.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels));
            rightTodaLine.setStartX(rightTodaLine.getStartX()+((newpixs/log.getTORA().doubleValue())*toraConstPixels));

            asdaRect2TT.setText("(R) ASDA = (R) TORA = "+tt.getAsda());
            todaRect2TT.setText(" (R) TODA = (R) TODA = "+tt.getToda());

        }
        todaRect2.setWidth(Math.abs(rightTodaLine.getEndX()-rightTodaLine.getStartX()));
        todaRect2.setX(rightTodaLine.getStartX());
        asdaRect2.setWidth(Math.abs(rightAsdaLine.getEndX()-rightAsdaLine.getStartX()));
        asdaRect2.setX(rightAsdaLine.getStartX());

    }

    public void updateLeftLogicalObstacle(LogicalRunway log,double shiftPixels,double shiftDisplaced) throws Exception {

        //Landing
        Calculation lo = mod.getCurrentRunway().calculateLandingOver(log,mod.getAirport());
        Calculation lt = mod.getCurrentRunway().calculateLandingTowards(log,mod.getAirport());
        landingDirectionLTR.setVisible(true);
        int lda = Math.max(lo.getLda(),lt.getLda());

        if (lda == lo.getLda()){
            int previousLda = lo.getPreviousLdaNum().intValue();

            int difference = previousLda - lo.getLda();

            leftLdaStartLine.setTranslateX(shiftPixels);
            leftLdaLine.setStartX(leftLdaLine.getStartX()+shiftPixels);
            leftLdaTextBox.setTranslateX(shiftPixels);
            leftLdaTextBox.setVisible(true);

            leftLdaMarker1.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels)+shiftDisplaced);
            leftLdaMarker1.setVisible(true);
            leftDisplacedThreshold.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels)+shiftDisplaced);
            leftLdaRect1.setWidth(((lo.getLda()/log.getTORA().doubleValue())*toraConstPixels));
            leftLdaRect1.setX(leftLdaRect1.getX()+leftLdaMarker1.getTranslateX());
            leftLdaRect1TT.setText("(R)LDA = "+lo.getLda());

            //Resa and Strip End
            if(lo.getResa() != null){
                leftLdaMarker2.setVisible(true);
                leftLdaMarker2.setTranslateX((((lo.getStripEnd().doubleValue())/(lo.getResa().doubleValue()+lo.getStripEnd().doubleValue()))*(leftLdaMarker1.getTranslateX()-leftLdaStartLine.getTranslateX()))+shiftPixels);

                leftLdaRect2.setX(leftLdaRect2.getX()+shiftPixels);
                leftLdaRect2.setWidth(lo.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftLdaRect2TT.setText("Strip End = "+lo.getStripEnd());

                leftLdaRedLine.setStartX(leftLdaRect2.getX());
                leftLdaRedLine.setEndX(leftLdaRedLine.getStartX()+leftLdaRect2.getWidth());
                leftLdaExtra2.setVisible(true);
                leftLdaExtra2.setText("STRIP-END");
                leftLdaExtra2.setFill(Color.RED);


                leftLdaRect3.setWidth(lo.getResa().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftLdaRect3.setX(leftLdaRect3.getX()+leftLdaMarker2.getTranslateX());
                leftLdaRect3TT.setText("RESA = "+lo.getResa());

                leftLdaRedLine.setVisible(true);
                leftLdaGreenLine.setVisible(true);
                leftLdaGreenLine.setStartX(leftLdaRect3.getX());
                leftLdaGreenLine.setEndX(leftLdaGreenLine.getStartX()+leftLdaRect3.getWidth());

                leftLdaExtra1.setVisible(true);
                leftLdaExtra1.setText("RESA");
                leftLdaExtra1.setFill(Color.LIME);

            }else if (lo.getSlope() != null){
                leftLdaMarker2.setVisible(true);
                leftLdaMarker2.setTranslateX((((lo.getStripEnd().doubleValue())/(lo.getSlope().doubleValue()+lo.getStripEnd().doubleValue()))*(leftLdaMarker1.getTranslateX()-leftLdaStartLine.getTranslateX()))+shiftPixels);

                leftLdaRect2.setX(leftLdaRect2.getX()+shiftPixels);
                leftLdaRect2.setWidth(lo.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftLdaRect2TT.setText("Strip End = "+lo.getStripEnd());

                leftLdaExtra2.setVisible(true);
                leftLdaExtra2.setText("STRIP-END");
                leftLdaExtra2.setFill(Color.RED);

                leftLdaRedLine.setStartX(leftLdaRect2.getX());
                leftLdaRedLine.setEndX(leftLdaRedLine.getStartX()+leftLdaRect2.getWidth());

                leftLdaRect3.setWidth(lo.getSlope().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftLdaRect3.setX(leftLdaRect3.getX()+leftLdaMarker2.getTranslateX());
                leftLdaRect3TT.setText("Slope = h x 50 = "+lo.getSlope());

                leftLdaGreenLine.setStartX(leftLdaRect3.getX());
                leftLdaGreenLine.setEndX(leftLdaGreenLine.getStartX()+leftLdaRect3.getWidth());

                leftLdaExtra1.setVisible(true);
                leftLdaExtra1.setText("SLOPE");
                leftLdaExtra1.setFill(Color.LIME);

                leftLdaRedLine.setVisible(true);
                leftLdaGreenLine.setVisible(true);


            }else if(lo.getBlastProtection() != null) {
                leftLdaMarker2.setVisible(false);

                leftLdaRect2.setX(leftLdaRect2.getX()+shiftPixels);
                leftLdaRect2.setWidth(lo.getBlastProtection().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftLdaRect2TT.setText("Blast Protection = "+lo.getBlastProtection());

                leftLdaRedLine.setStartX(leftLdaRect2.getX());
                leftLdaRedLine.setEndX(leftLdaRedLine.getStartX()+leftLdaRect2.getWidth());

                leftLdaRedLine.setVisible(true);


                leftLdaExtra1.setVisible(true);
                leftLdaExtra1.setText("BLAST");
                leftLdaExtra1.setFill(Color.RED);
            }

            //leftLdaText.setX(leftLdaLine.getStartX()+10);
            //leftLdaText.setTranslateY(-10);
            leftRunwayDirection.setText(log.getCombinedReference()+" Runway Direction\nLanding Over");

        }else{
            leftLdaLine.setVisible(true);
            leftLdaText.setVisible(true);
            leftLdaStartLine.setVisible(true);
            leftLdaMarker2.setVisible(true);
            leftLdaMarker1.setVisible(true);
            double newshiftPixels = 0.0;
            if(log.getDisplacedThreshold().intValue()> 0){
                double toraStart = leftToraLine.getStartX();
                newshiftPixels = (log.getDisplacedThreshold().doubleValue()/log.getTORA().doubleValue())*toraConstPixels;
                leftDisplacedThreshold.setTranslateX(newshiftPixels);
                leftLdaLine.setStartX(toraStart+newshiftPixels);
                leftLdaStartLine.setTranslateX(newshiftPixels);
                //.setTranslateX(newshiftPixels);
            }

            //leftLdaText.setTranslateY(-10);
            leftLdaEndLine.setTranslateX(-(toraConstPixels-shiftPixels));
            leftLdaLine.setEndX(leftLdaLine.getEndX()-(toraConstPixels-shiftPixels));

            leftLdaExtra2.setVisible(true);
            leftLdaExtra2.setText("LDA");
            leftLdaExtra2.setFill(Color.WHITE);


            leftLdaMarker1.setTranslateX(newshiftPixels+((((double) lt.getLda())/log.getTORA().doubleValue())*toraConstPixels));
            leftLdaMarker2.setTranslateX(newshiftPixels+(((((double) lt.getLda())+lt.getResa().doubleValue())/log.getTORA().doubleValue()))*toraConstPixels);
            leftRunwayDirection.setText(log.getCombinedReference()+" Runway Direction\nLanding Towards");

            leftLdaRect1.setWidth(leftLdaMarker1.getTranslateX()-newshiftPixels);
            leftLdaRect1.setX(leftLdaRect1.getX() + newshiftPixels);
            leftLdaRect1TT.setText(" (R) LDA = "+lt.getLda());

            leftLdaRect2.setWidth(lt.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
            leftLdaRect2.setX(leftLdaRect2.getX() + leftLdaMarker2.getTranslateX());
            leftLdaRect2TT.setText("Strip End = "+lt.getStripEnd());

            leftLdaText.setVisible(true);
            leftLdaText.setText("STRIP-END");
            leftLdaText.setFill(Color.RED);

            leftLdaRedLine.setStartX(leftLdaRect2.getX());
            leftLdaRedLine.setEndX(leftLdaRedLine.getStartX()+leftLdaRect2.getWidth());

            leftLdaRect3.setWidth(lt.getResa().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
            leftLdaRect3.setX(leftLdaRect3.getX() + leftLdaMarker1.getTranslateX());
            leftLdaRect3TT.setText("RESA = "+lt.getResa());

            leftLdaExtra1.setVisible(true);
            leftLdaExtra1.setText("RESA");
            leftLdaExtra1.setFill(Color.LIME);

            leftLdaGreenLine.setStartX(leftLdaRect3.getX());
            leftLdaGreenLine.setEndX(leftLdaGreenLine.getStartX()+leftLdaRect3.getWidth());

            leftLdaRedLine.setVisible(true);
            leftLdaGreenLine.setVisible(true);


        }

        //Take Off
        Calculation ta = mod.getCurrentRunway().calculateTakeOffAway(log,mod.getAirport());
        Calculation tt = mod.getCurrentRunway().calculateTakeOffTowards(log,mod.getAirport());

        int tora = Math.max(ta.getTora(),tt.getTora());

        if(tora == ta.getTora()){
            int previousTora = ta.getPreviousToraNum().intValue();

            int difference = previousTora - ta.getTora();

            leftToraLine.setStartX(leftToraLine.getStartX()+shiftPixels);
            leftToraStartLine.setTranslateX(shiftPixels);
            leftToraTextBox.setTranslateX(shiftPixels);
            leftToraTextBox.setVisible(true);

            leftToraMarker1.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels));
            leftToraMarker1.setVisible(true);
            leftToraRect1.setWidth(Math.abs(leftToraLine.getEndX()-(leftToraMarker1.getTranslateX()+leftToraMarker1.getStartX())));

            if(ta.getResa() != null){
                leftToraMarker2.setVisible(true);
                leftToraMarker2.setTranslateX((((difference-ta.getResa().doubleValue())/log.getTORA().doubleValue())*toraConstPixels));

                leftToraRect2.setWidth(ta.getStripEnd().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftToraRect2.setX(leftToraRect2.getX()+shiftPixels);
                leftToraRect2TT.setText("Strip End = "+String.valueOf(ta.getStripEnd()));

                leftToraExtra2.setVisible(true);
                leftToraExtra2.setText("STRIP-END");
                leftToraExtra2.setFill(Color.RED);

                leftToraRedLine.setStartX(leftToraRect2.getX());
                leftToraRedLine.setEndX(leftToraRedLine.getStartX()+leftToraRect2.getWidth());

                leftToraRect3.setWidth(ta.getResa().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftToraRect3.setX(leftToraRect3.getX()+shiftPixels+leftToraRect2.getWidth());
                leftToraRect3TT.setText("RESA = "+String.valueOf(ta.getResa()));

                leftToraExtra1.setVisible(true);
                leftToraExtra1.setText("RESA");
                leftToraExtra1.setFill(Color.LIME);


                leftToraGreenLine.setStartX(leftToraRect3.getX());
                leftToraGreenLine.setEndX(leftToraGreenLine.getStartX()+leftToraRect3.getWidth());

                leftToraRedLine.setVisible(true);
                leftToraGreenLine.setVisible(true);

            }else if(ta.getBlastProtection() != null){
                leftToraMarker2.setVisible(false);

                leftToraRect2.setWidth(ta.getBlastProtection().doubleValue()/log.getTORA().doubleValue()*toraConstPixels);
                leftToraRect2.setX(leftToraRect2.getX()+shiftPixels);
                leftToraRect2TT.setText("Blast Protection = "+ta.getBlastProtection());

                leftToraRedLine.setStartX(leftToraRect2.getX());
                leftToraRedLine.setEndX(leftToraRedLine.getStartX()+leftToraRect2.getWidth());
                leftToraRedLine.setVisible(true);

                leftToraExtra1.setVisible(true);
                leftToraExtra1.setText("BLAST");
                leftToraExtra1.setFill(Color.RED);
            }
            leftRunwayDirection.setText(leftRunwayDirection.getText()+"\nTake Off Away");


            leftAsdaStartLine.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels));
            leftAsdaLine.setStartX(leftAsdaLine.getStartX()+((difference/log.getTORA().doubleValue())*toraConstPixels));
            leftTodaStartLine.setTranslateX(((difference/log.getTORA().doubleValue())*toraConstPixels));
            leftTodaLine.setStartX(leftTodaLine.getStartX()+((difference/log.getTORA().doubleValue())*toraConstPixels));
            todaRect1TT.setText("(R) TODA = (R) TORA + Clearway ("+log.getClearway()+") = "+String.valueOf(ta.getToda()));
            asdaRect1TT.setText(" (R) ASDA = (R) TORA + Stopway ("+log.getStopway()+") = "+String.valueOf(ta.getAsda()));
            leftToraRect1.setX(leftToraRect1.getX()+leftToraMarker1.getTranslateX());
            leftToraRect1TT.setText(" (R) TORA = "+String.valueOf(ta.getTora()));
            showStopwayClearwayLeft(ta.getAsda()-ta.getTora(),ta.getToda()-ta.getTora());

            leftAsdaText.setTranslateX(leftAsdaStartLine.getTranslateX());
            leftTodaText.setTranslateX(leftTodaStartLine.getTranslateX());



        }else{
            int previousTora = tt.getPreviousToraNum().intValue();

            int difference = previousTora - tt.getTora();

            leftToraLine.setEndX(leftToraLine.getEndX()-(toraConstPixels-shiftPixels));
            leftToraEndLine.setTranslateX(-(toraConstPixels-shiftPixels));

            leftToraExtra2.setVisible(true);
            leftToraExtra2.setText("TORA");
            leftToraExtra2.setFill(Color.WHITE);

            leftToraMarker1.setVisible(true);
            leftToraMarker1.setTranslateX(toraConstPixels-((difference/log.getTORA().doubleValue())*toraConstPixels));
            leftToraRect1.setWidth(leftToraMarker1.getTranslateX());

            double newpixs = 0;
            if(tt.getResa() != null){
                leftToraMarker2.setVisible(true);
                leftToraMarker2.setTranslateX(shiftPixels-(tt.getStripEnd().doubleValue()/log.getTORA().doubleValue())*toraConstPixels);
                newpixs = (tt.getResa().doubleValue()+tt.getStripEnd().doubleValue());

                leftToraRect2.setX(leftToraRect2.getX()+leftToraMarker1.getTranslateX());
                leftToraRect2.setWidth((tt.getResa().doubleValue()/log.getTORA().doubleValue())*toraConstPixels);
                leftToraRect2TT.setText("RESA = "+tt.getResa());

                leftToraExtra1.setVisible(true);
                leftToraExtra1.setText("RESA");
                leftToraExtra1.setFill(Color.LIME);



                leftToraRect3.setX(leftToraRect3.getX()+leftToraMarker2.getTranslateX());
                leftToraRect3.setWidth((tt.getStripEnd().doubleValue()/log.getTORA().doubleValue())*toraConstPixels);
                leftToraRect3TT.setText("Strip End "+tt.getStripEnd());

                leftToraText.setVisible(true);
                leftToraText.setText("STRIP-END");
                leftToraText.setFill(Color.RED);

                leftToraRedLine.setStartX(leftToraRect3.getX());
                leftToraRedLine.setEndX(leftToraRedLine.getStartX()+leftToraRect3.getWidth());

                leftToraGreenLine.setStartX(leftToraRect2.getX());
                leftToraGreenLine.setEndX(leftToraGreenLine.getStartX()+leftToraRect2.getWidth());

                leftToraRedLine.setVisible(true);
                leftToraGreenLine.setVisible(true);

            }else if(tt.getSlope() != null){
                leftToraMarker2.setVisible(true);
                leftToraMarker2.setTranslateX(shiftPixels-(tt.getStripEnd().doubleValue()/log.getTORA().doubleValue())*toraConstPixels);
                newpixs = (tt.getSlope().doubleValue()+tt.getStripEnd().doubleValue());

                leftToraRect3.setX(leftToraRect3.getX()+leftToraMarker2.getTranslateX());
                leftToraRect3.setWidth((tt.getStripEnd().doubleValue()/log.getTORA().doubleValue())*toraConstPixels);
                leftToraRect3TT.setText("Strip End = "+tt.getStripEnd());

                leftToraText.setVisible(true);
                leftToraText.setText("STRIP-END");
                leftToraText.setFill(Color.RED);




                leftToraRect2.setX(leftToraRect2.getX()+leftToraMarker1.getTranslateX());
                leftToraRect2.setWidth((tt.getSlope().doubleValue()/log.getTORA().doubleValue())*toraConstPixels);
                leftToraRect2TT.setText("Slope = h x 50 = "+tt.getSlope());

                leftToraExtra1.setVisible(true);
                leftToraExtra1.setText("SLOPE");
                leftToraExtra1.setFill(Color.LIME);


                leftToraGreenLine.setStartX(leftToraRect2.getX());
                leftToraGreenLine.setEndX(leftToraGreenLine.getStartX()+leftToraRect2.getWidth());

                leftToraRedLine.setStartX(leftToraRect3.getX());
                leftToraRedLine.setEndX(leftToraRedLine.getStartX()+leftToraRect3.getWidth());

                leftToraRedLine.setVisible(true);
                leftToraGreenLine.setVisible(true);


            }

            showStopwayClearwayLeft(0,0);
            leftAsdaEndLine.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels));
            leftAsdaLine.setEndX(leftAsdaLine.getEndX()-(newpixs/log.getTORA().doubleValue())*toraConstPixels);
            leftTodaEndLine.setTranslateX(-((difference/log.getTORA().doubleValue())*toraConstPixels));
            leftTodaLine.setEndX(leftTodaLine.getEndX()-(newpixs/log.getTORA().doubleValue())*toraConstPixels);

            leftRunwayDirection.setText(leftRunwayDirection.getText()+"\nTake Off Towards");
            todaRect1TT.setText("(R) TODA = (R) TORA = "+String.valueOf(tt.getToda()));
            asdaRect1TT.setText(" (R) ASDA = (R) TORA = "+String.valueOf(tt.getAsda()));
            leftToraRect1TT.setText("(R) TORA = "+tt.getTora());
        }

        todaRect1.setWidth(Math.abs(leftTodaLine.getEndX()-leftTodaLine.getStartX()));
        todaRect1.setX(leftTodaLine.getStartX());
        asdaRect1.setWidth(Math.abs(leftAsdaLine.getEndX()-leftAsdaLine.getStartX()));
        asdaRect1.setX(leftAsdaLine.getStartX());

    }



    public double updateObstacle(LogicalRunway left,Obstacle ob,double displacedShiftPixels) throws Exception {
        double shiftPixels;
        Obstacle obst = mod.getCurrentRunway().getObstacle();
        double distanceLeft = obst.getObstaclePosition().get(left.getCombinedReference()).doubleValue();
        shiftPixels = ((distanceLeft/left.getTORA().doubleValue())*Double.valueOf(toraConstPixels));


        object.setTranslateX(shiftPixels+displacedShiftPixels);
        object.setTranslateY(ob.getDistanceFromCentre().doubleValue()/75.0*124.5);
        object.setVisible(true);

        String tool = obst.getObstaclePosition().toString().replace("}","");
        obTT.setText(tool.replace("{","")+", "+obst.getDistanceFromCentre());
        return (shiftPixels+displacedShiftPixels);



    }



    public double updateLeftLogicalRunway(LogicalRunway logical){
        double shiftPixels = 0;
        double toraPixels = Math.abs(leftToraLine.getStartX() - leftTodaLine.getEndX());
        showStopwayClearwayLeft(logical.getStopway(),logical.getClearway());

        if(logical.getDisplacedThreshold().intValue()> 0){
            double toraStart = leftToraLine.getStartX();
            shiftPixels = (logical.getDisplacedThreshold().doubleValue()/logical.getTORA().doubleValue())*toraConstPixels;
            leftDisplacedThreshold.setTranslateX(shiftPixels);
            leftLdaLine.setStartX(toraStart+shiftPixels);
            leftLdaStartLine.setTranslateX(shiftPixels);
            leftLdaRect1.setWidth(toraConstPixels-shiftPixels);
            leftLdaRect1.setX(leftLdaRect1.getX()+leftLdaStartLine.getTranslateX());
            //leftLdaText.setTranslateX(shiftPixels);
        }

        leftName.setText(logical.getCombinedReference());
        todaRect1TT.setText("TODA = "+logical.getTODA());
        asdaRect1TT.setText("ASDA = "+logical.getASDA());
        leftToraRect1TT.setText("TORA = "+logical.getTORA());
        leftLdaRect1TT.setText("LDA = "+logical.getLDA());


        return shiftPixels;

    }

    public void updateRightLogicalRunway(LogicalRunway logical){

        double toraPixels = 613.6;
        showStopwayClearwayRight(logical.getStopway(),logical.getClearway());

        if(logical.getDisplacedThreshold().intValue()> 0){
            double shiftPixels;
            double toraEnd = rightToraLine.getEndX();
            shiftPixels = (logical.getDisplacedThreshold().doubleValue()/logical.getTORA().doubleValue())*toraConstPixels;
            rightDisplacedThreshold.setTranslateX(-shiftPixels);
            rightLdaLine.setEndX(toraEnd-shiftPixels);
            rightLdaStartLine.setTranslateX(-shiftPixels);
            rightLdaText.setTranslateX(-shiftPixels);
        }

        todaRect2TT.setText("TODA = "+logical.getTODA());
        asdaRect2TT.setText("ASDA = "+logical.getASDA());
        rightToraRect1TT.setText("TORA = "+logical.getTORA());
        rightLdaRect1TT.setText("LDA = "+logical.getLDA());

        rightName.setText(logical.getCombinedReference());
    }

    public void showStopwayClearwayRight(Number stopway, Number clearway){
        if(stopway.equals(0)){
            rightStopway.setVisible(false);
            rightStopwayText.setVisible(false);
            rightAsdaLine.setStartX(rightToraLine.getStartX());
            rightAsdaEndLine.setStartX(rightToraEndLine.getStartX());
            rightAsdaEndLine.setEndX(rightToraEndLine.getEndX());

        }else{
            rightStopway.setVisible(true);
            rightStopwayText.setVisible(true);
        }

        if(clearway.equals(0)){
            rightClearway.setVisible(false);
            rightClearwayText.setVisible(false);
            rightTodaLine.setStartX(rightToraLine.getStartX());
            rightTodaEndLine.setEndX(rightToraEndLine.getStartX());
            rightTodaEndLine.setStartX(rightToraEndLine.getEndX());

        }else{
            rightClearwayText.setVisible(true);
            rightClearway.setVisible(true);
        }
        todaRect2.setWidth(Math.abs(rightTodaLine.getEndX()-rightTodaLine.getStartX()));
        todaRect2.setX(rightTodaEndLine.getStartX());

        asdaRect2.setWidth(Math.abs(rightAsdaLine.getEndX()-rightAsdaLine.getStartX()));
        asdaRect2.setX(rightAsdaEndLine.getStartX());

        rightStopwayTT.setText("Stopway = "+String.valueOf(stopway));
        rightClearwayTT.setText("Clearway = "+String.valueOf(clearway));

    }

    public void showStopwayClearwayLeft(Number stopway, Number clearway){
        if(stopway.equals(0)){
            leftStopway.setVisible(false);
            leftStopwayText.setVisible(false);
            leftAsdaLine.setEndX(leftToraLine.getEndX());
            leftAsdaEndLine.setStartX(leftToraEndLine.getStartX());
            leftAsdaEndLine.setEndX(leftToraEndLine.getEndX());

        }else{
            leftStopway.setVisible(true);
            leftStopwayText.setVisible(true);

        }

        if(clearway.equals(0)){
            leftClearway.setVisible(false);
            leftClearwayText.setVisible(false);
            leftTodaLine.setEndX(leftToraLine.getEndX());
            leftTodaEndLine.setEndX(leftToraEndLine.getStartX());
            leftTodaEndLine.setStartX(leftToraEndLine.getEndX());

        }else{
            leftClearway.setVisible(true);
            leftClearwayText.setVisible(true);
        }
        todaRect1.setWidth(Math.abs(leftTodaLine.getEndX()-leftTodaLine.getStartX()));
        asdaRect1.setWidth(Math.abs(leftAsdaLine.getEndX()-leftAsdaLine.getStartX()));

        leftStopwayTT.setText("Stopway = "+String.valueOf(stopway));
        leftClearwayTT.setText("Clearway = "+String.valueOf(clearway));

    }



    public void togglePhysicalRunway(boolean visible){
        runwayRectangle.setVisible(visible);
        sideRunway.setVisible(visible);
        runwayLine.setVisible(visible);



    }

    public void toggleLeftLogicalRunway(boolean visible){
        leftChecks.setVisible(visible);
        leftName.setVisible(visible);
        leftStopway.setVisible(visible);
        leftClearway.setVisible(visible);
        leftStopwayText.setVisible(visible);
        leftClearwayText.setVisible(visible);
        leftToraText.setVisible(visible);
        leftLdaText.setVisible(visible);
        leftAsdaText.setVisible(visible);
        leftTodaText.setVisible(visible);
        leftDisplacedThreshold.setVisible(visible);
        leftToraStartLine.setVisible(visible);
        leftToraEndLine.setVisible(visible);
        leftToraLine.setVisible(visible);
        leftLdaStartLine.setVisible(visible);
        leftLdaEndLine.setVisible(visible);
        leftLdaLine.setVisible(visible);
        leftAsdaStartLine.setVisible(visible);
        leftAsdaEndLine.setVisible(visible);
        leftAsdaLine.setVisible(visible);
        leftTodaStartLine.setVisible(visible);
        leftTodaEndLine.setVisible(visible);
        leftTodaLine.setVisible(visible);
        todaRect1.setVisible(visible);
        asdaRect1.setVisible(visible);
        leftToraRect1.setVisible(visible);
        leftToraRect2.setVisible(visible);
        leftToraRect3.setVisible(visible);
        leftLdaRect1.setVisible(visible);
        leftLdaRect2.setVisible(visible);
        leftLdaRect3.setVisible(visible);
        leftRunwayDirection.setVisible(visible);


    }

    public void toggleRightLogicalRunway(boolean visible){
        rightChecks.setVisible(visible);
        rightName.setVisible(visible);
        rightStopway.setVisible(visible);
        rightClearway.setVisible(visible);
        rightStopwayText.setVisible(visible);
        rightClearwayText.setVisible(visible);
        rightToraExtra2.setVisible(visible);
        rightLdaExtra2.setVisible(visible);
        rightAsdaText.setVisible(visible);
        rightTodaText.setVisible(visible);
        rightDisplacedThreshold.setVisible(visible);
        rightToraStartLine.setVisible(visible);
        rightToraEndLine.setVisible(visible);
        rightToraLine.setVisible(visible);
        rightLdaStartLine.setVisible(visible);
        rightLdaEndLine.setVisible(visible);
        rightLdaLine.setVisible(visible);
        rightAsdaStartLine.setVisible(visible);
        rightAsdaEndLine.setVisible(visible);
        rightAsdaLine.setVisible(visible);
        rightTodaStartLine.setVisible(visible);
        rightTodaEndLine.setVisible(visible);
        rightTodaLine.setVisible(visible);
        todaRect2.setVisible(visible);
        asdaRect2.setVisible(visible);
        rightToraRect1.setVisible(visible);
        rightToraRect2.setVisible(visible);
        rightToraRect3.setVisible(visible);
        rightLdaRect1.setVisible(visible);
        rightLdaRect2.setVisible(visible);
        rightLdaRect3.setVisible(visible);
    }
    
    
//------------------------------
//
//START OF SIDE SPECIFIC METHODS
//
//------------------------------
    public void hideSideComponents() { //Hides all side components from view. 
    	sideName.setVisible(false);
    	sideClearway.setVisible(false);
    	sideStopway.setVisible(false);
    	sideObstacle.setVisible(false);
    	sideArrowHead.setVisible(false);
    	sideArrowBar.setVisible(false);
    	sideDirectionText.setVisible(false);
    	sideLandingDirection.setVisible(false);
    	sideTakeDirection.setVisible(false);
    	sideDisplacedThreshold.setVisible(false);
    	sideSlopeLine.setVisible(false);
    	sideSlopeTop.setVisible(false);
    	sideSlopeBase.setVisible(false);
    	sideSlopeBlockerOne.setVisible(false);
    	sideSlopeBlockerTwo.setVisible(false);
    	sideSlopeBlockerThree.setVisible(false);
    	sideSlopeBlockerFour.setVisible(false);
    	sideSlopeText.setVisible(false);
    	sideOutOfBounds.setVisible(false);

    	sideLDAText.setVisible(false);
    	sideLDAExtraTextOne.setVisible(false);
    	sideLDAExtraTextTwo.setVisible(false);
    	sideLDAStart.setVisible(false);
    	sideLDALine.setVisible(false);
    	sideLDAEnd.setVisible(false);
    	sideLDATooltipBox.setVisible(false);
    	sideLDAExtraTooltipBoxOne.setVisible(false);
    	sideLDAExtraTooltipBoxTwo.setVisible(false);
    	sideLDAExtraMarkOne.setVisible(false);
    	sideLDAExtraMarkTwo.setVisible(false);
    	sideLDAExtraLineOne.setVisible(false);
    	sideLDAExtraLineTwo.setVisible(false);
    	sideTORAText.setVisible(false);
    	sideTORAExtraTextOne.setVisible(false);
    	sideTORAExtraTextTwo.setVisible(false);
    	sideTORAStart.setVisible(false);
    	sideTORALine.setVisible(false);
    	sideTORAEnd.setVisible(false);
    	sideTORATooltipBox.setVisible(false);
    	sideTORAExtraTooltipBoxOne.setVisible(false);
    	sideTORAExtraTooltipBoxTwo.setVisible(false);
    	sideTORAExtraMarkOne.setVisible(false);
    	sideTORAExtraMarkTwo.setVisible(false);
    	sideTORAExtraLineOne.setVisible(false);
    	sideTORAExtraLineTwo.setVisible(false);
    	sideASDAText.setVisible(false);
    	sideASDAStart.setVisible(false);
    	sideASDALine.setVisible(false);
    	sideASDAEnd.setVisible(false);
    	sideASDATooltipBox.setVisible(false);
    	sideTODAText.setVisible(false);
    	sideTODAStart.setVisible(false);
    	sideTODALine.setVisible(false);
    	sideTODAEnd.setVisible(false);
    	sideTODATooltipBox.setVisible(false);

    	sideLogicalOne.setVisible(false);
    	sideLogicalTwo.setVisible(false);
    }
    
    public void sideShowLogical(Runway runway, LogicalRunway logical) { //Displays all the relevant components, in the correct places, for the given logical runway
    	sideResetLocations();
    	Calculation landingAwayCalcs = null;
    	Calculation landingTowardsCalcs = null;
    	Calculation takeAwayCalcs = null;
    	Calculation takeTowardsCalcs = null;

    	sidePixelRatio = sideRunway.getWidth() / logical.getTORA().doubleValue();
    	sideName.setText(logical.getCombinedReference());
    	sideName.setVisible(true);
    	if (runway.getObstacle() != null) {
			viewOriginalCalcs.setDisable(false);
		} else {
			viewOriginalCalcs.setDisable(true);
		}

    	if(runway.getObstacle() == null || runway.getObstacle().getObstaclePosition().get(logical.getCombinedReference()) == null) {
    		topdown.selectToggle(viewCalculated);
    		viewOriginalCalcs.setDisable(true);
    	}
    	
    	//Displays clearway and stopway with relative sizes
		if (logical != null) {
			if (logical.getClearway().doubleValue() > 0) {
				sideClearway.setVisible(true);
				sideClearway.setWidth(sidePixelRatio * logical.getClearway().doubleValue());
				sideTODAEnd.setTranslateX(sidePixelRatio * logical.getClearway().doubleValue());
			} else {
				sideClearway.setVisible(false);
				sideClearway.setWidth(0);
				sideTODAEnd.setTranslateX(0);
			}
			if (logical.getStopway().doubleValue() > 0) {
				sideStopway.setVisible(true);
				sideStopway.setWidth(sidePixelRatio * logical.getStopway().doubleValue());
				sideASDAEnd.setTranslateX(sidePixelRatio * logical.getStopway().doubleValue());
			} else {
				sideStopway.setVisible(false);
				sideStopway.setWidth(0);
				sideASDAEnd.setTranslateX(0);
			}
			if (logical.getDisplacedThreshold().doubleValue() > 0) {
				sideDisplacedThreshold.setVisible(true);
				sideDisplacedThreshold.setTranslateX(sidePixelRatio * logical.getDisplacedThreshold().doubleValue());
				sideLDAStart.setTranslateX(sidePixelRatio * logical.getDisplacedThreshold().doubleValue());
			} else {
				sideDisplacedThreshold.setVisible(false);
				sideDisplacedThreshold.setTranslateX(0);
				sideLDAStart.setTranslateX(0);
			}
		}
		
		//Displays obstacle and recalculated lines
    	if (viewOriginalCalcs.isSelected() && sideCheckInBounds(runway, logical)) {
			try {
				Airport airport = mod.getAirport();
				landingAwayCalcs = runway.calculateLandingOver(logical, airport);
				landingTowardsCalcs = runway.calculateLandingTowards(logical, airport);
				takeAwayCalcs = runway.calculateTakeOffAway(logical, airport);
				takeTowardsCalcs = runway.calculateTakeOffTowards(logical, airport);
			} catch (Exception e) {

			}
			sideLandingDirection.setVisible(true);
			sideTakeDirection.setVisible(true);
    		sideObstacle.setVisible(true);
    		double obsHeight = runway.getObstacle().getHeight().doubleValue();
            sideObstacleTooltip.setText("Obstacle Height: " + Math.round(runway.getObstacle().getHeight().doubleValue()));
    		if (obsHeight < 21) {
    			sideObstacle.setHeight(21); // -- Constant Height
    		} else {
    			//sideObstacle.setHeight(runway.getObstacle().getHeight().doubleValue()); // -- Somewhat relative height
        		sideObstacle.setHeight(sidePixelRatio * runway.getObstacle().getHeight().doubleValue()); // -- Fully relative height
    		}
    		sideObstacle.setTranslateY(- sideObstacle.getHeight());
    		sideObstacle.setTranslateX(sidePixelRatio * (runway.getObstacle().getObstaclePosition().get(logical.getCombinedReference()).doubleValue() + logical.getDisplacedThreshold().doubleValue()));

    		//Positions the LDA and related components
    		if (landingTowardsCalcs.getLda() >= landingAwayCalcs.getLda()) {
    			sideLandingDirection.setText("Landing Towards");
    			sideLDAEnd.setTranslateX(sidePixelRatio * (landingTowardsCalcs.getLda() - logical.getLDA().doubleValue()));
    			sideLDAExtraMarkOne.setVisible(true);
    			sideLDAExtraMarkTwo.setVisible(true);
    			sideLDAExtraLineOne.setVisible(true);
    			sideLDAExtraTooltipBoxOne.setVisible(true);
    			sideLDAExtraLineTwo.setVisible(true);
    			sideLDAExtraTooltipBoxTwo.setVisible(true);
    			sideLDAExtraMarkOne.setTranslateX(sideObstacle.getTranslateX());
    	    	sideLDALabelPane.setTranslateX(sideLDAStart.getTranslateX());
    	    	sideLDAText.setText("STRIP-END");
    	    	sideLDAText.setFill(Color.RED);
    	    	sideLDAExtraLineOneTooltip.setText("Strip-End: " + landingTowardsCalcs.getStripEnd());
				sideLDAExtraTextOne.setText("RESA");
				sideLDAExtraTextOne.setFill(Color.LIME);
				sideLDAExtraLineTwoTooltip.setText("RESA: " + landingTowardsCalcs.getResa());
				sideLDAExtraTextTwo.setText("LDA");
				sideLDAExtraTextTwo.setFill(Color.WHITE);
    			sideLDAExtraMarkTwo.setTranslateX(sideObstacle.getTranslateX() - sidePixelRatio * landingTowardsCalcs.getStripEnd().doubleValue());
    		} else {
    			sideLandingDirection.setText("Landing Over");
    			sideDisplacedThreshold.setTranslateX(sidePixelRatio * (logical.getLDA().doubleValue() - landingAwayCalcs.getLda() + logical.getDisplacedThreshold().doubleValue()));
    			sideLDAStart.setTranslateX(sidePixelRatio * (logical.getLDA().doubleValue() - landingAwayCalcs.getLda() + logical.getDisplacedThreshold().doubleValue()));
    			sideLDAExtraMarkOne.setVisible(true);
    			sideLDAExtraLineOne.setVisible(true);
    			sideLDAExtraTooltipBoxOne.setVisible(true);
    			sideLDAExtraMarkOne.setTranslateX(sideObstacle.getTranslateX());
    			sideLDAText.setText("LDA");
    			sideLDAText.setFill(Color.WHITE);
    			sideLDALabelPane.setTranslateX(sideLDAExtraMarkOne.getTranslateX());
    			if (landingAwayCalcs.getResa() != null) {
    				sideLDAExtraMarkTwo.setVisible(true);
    				sideLDAExtraLineTwo.setVisible(true);
    				sideLDAExtraTooltipBoxTwo.setVisible(true);
    				sideLDAExtraMarkTwo.setTranslateX(sideLDAExtraMarkOne.getTranslateX() + sidePixelRatio * landingAwayCalcs.getStripEnd().doubleValue());
    				sideLDAExtraTextOne.setText("RESA");
    				sideLDAExtraTextOne.setFill(Color.LIME);
    				sideLDAExtraLineTwoTooltip.setText("RESA: " + landingAwayCalcs.getResa());
    				sideLDAExtraTextTwo.setText("STRIP-END");
    				sideLDAExtraTextTwo.setFill(Color.RED);
    				sideLDAExtraLineOneTooltip.setText("Strip-End: " + landingAwayCalcs.getStripEnd());
    			} else if (landingAwayCalcs.getSlope() != null) {
    				sideLDAExtraMarkTwo.setVisible(true);
    				sideLDAExtraLineTwo.setVisible(true);
    				sideLDAExtraTooltipBoxTwo.setVisible(true);
    				sideLDAExtraMarkTwo.setTranslateX(sideLDAExtraMarkOne.getTranslateX() + sidePixelRatio * landingAwayCalcs.getStripEnd().doubleValue());
    				sideLDAExtraTextOne.setText("SLOPE");
    				sideLDAExtraTextOne.setFill(Color.LIME);
    				sideLDAExtraLineTwoTooltip.setText("Slope: " + landingAwayCalcs.getSlope());
    				sideLDAExtraTextTwo.setText("STRIP-END");
    				sideLDAExtraTextTwo.setFill(Color.RED);
    				sideLDAExtraLineOneTooltip.setText("Strip-End: " + landingAwayCalcs.getStripEnd());
    			} else {
    				sideLDAExtraTextOne.setText("BLAST PROTECTION");
    				sideLDAExtraTextOne.setFill(Color.RED);
    				sideLDAExtraLineOneTooltip.setText("Blast Protection: " + landingAwayCalcs.getBlastProtection());
    				sideLDAExtraTextTwo.setText("");
    			}
    		}

    		//Positions the TORA, ASDA and TODA and related components
    		if (takeAwayCalcs.getTora() >= takeTowardsCalcs.getTora()) {
    			sideTakeDirection.setText("Take-Off Away");
    			sideTORAStart.setTranslateX(sidePixelRatio * (logical.getTORA().doubleValue() - takeAwayCalcs.getTora()));
        		sideASDAStart.setTranslateX(sidePixelRatio * (logical.getTORA().doubleValue() - takeAwayCalcs.getAsda() + logical.getStopway().doubleValue()));
        		sideTODAStart.setTranslateX(sidePixelRatio * (logical.getTORA().doubleValue() - takeAwayCalcs.getToda() + logical.getClearway().doubleValue()));
        		sideTORAExtraMarkOne.setVisible(true);
        		sideTORAExtraLineOne.setVisible(true);
        		sideTORAExtraTooltipBoxOne.setVisible(true);
        		sideTORAExtraMarkOne.setTranslateX(sideObstacle.getTranslateX());
        		sideTORAText.setText("TORA");
        		sideTORAText.setFill(Color.WHITE);
        		sideTORALabelPane.setTranslateX(sideTORAExtraMarkOne.getTranslateX());
        		if (takeAwayCalcs.getResa() != null) {
        			sideTORAExtraMarkTwo.setVisible(true);
        			sideTORAExtraLineTwo.setVisible(true);
        			sideTORAExtraTooltipBoxTwo.setVisible(true);
        			sideTORAExtraMarkTwo.setTranslateX(sideTORAExtraMarkOne.getTranslateX() + sidePixelRatio * takeAwayCalcs.getStripEnd().doubleValue());
        			sideTORAExtraTextOne.setText("RESA");
        			sideTORAExtraTextOne.setFill(Color.LIME);
        			sideTORAExtraLineTwoTooltip.setText("RESA: " + takeAwayCalcs.getResa());
        			sideTORAExtraTextTwo.setText("STRIP-END");
        			sideTORAExtraTextTwo.setFill(Color.RED);
        			sideTORAExtraLineOneTooltip.setText("Strip-End: " + takeAwayCalcs.getStripEnd());
        		} else {
        			sideTORAExtraTextOne.setText("BLAST PROTECTION");
        			sideTORAExtraTextOne.setFill(Color.RED);
        			sideTORAExtraLineOneTooltip.setText("Blast Protection: " + takeAwayCalcs.getBlastProtection());
        			sideTORAExtraTextTwo.setText("");
        		}
        	} else {
    			sideTakeDirection.setText("Take-Off Towards");
    			sideTORAEnd.setTranslateX(sidePixelRatio * (takeTowardsCalcs.getTora() - logical.getTORA().doubleValue()));
    			sideASDAEnd.setTranslateX(sidePixelRatio * (takeTowardsCalcs.getAsda() - logical.getTORA().doubleValue()));
    			sideTODAEnd.setTranslateX(sidePixelRatio * (takeTowardsCalcs.getToda() - logical.getTORA().doubleValue()));
    			sideTORAExtraMarkOne.setVisible(true);
    			sideTORAExtraLineOne.setVisible(true);
    			sideTORAExtraTooltipBoxOne.setVisible(true);
    			sideTORAExtraMarkOne.setTranslateX(sideObstacle.getTranslateX());
    			sideTORAExtraTextTwo.setText("TORA");
    			sideTORAExtraTextTwo.setFill(Color.WHITE);
    			sideTORALabelPane.setTranslateX(sideTORAStart.getTranslateX());
    			if (takeTowardsCalcs.getResa() != null) {
    				sideTORAExtraMarkTwo.setVisible(true);
    				sideTORAExtraLineTwo.setVisible(true);
    				sideTORAExtraTooltipBoxTwo.setVisible(true);
    				sideTORAExtraMarkTwo.setTranslateX(sideTORAExtraMarkOne.getTranslateX() - sidePixelRatio * takeTowardsCalcs.getStripEnd().doubleValue());
    				sideTORAExtraTextOne.setText("RESA");
        			sideTORAExtraTextOne.setFill(Color.LIME);
        			sideTORAExtraLineTwoTooltip.setText("RESA: " + takeTowardsCalcs.getResa());
        			sideTORAText.setText("STRIP-END");
        			sideTORAText.setFill(Color.RED);
        			sideTORAExtraLineOneTooltip.setText("Strip-End: " + takeTowardsCalcs.getStripEnd());
    			} else if (takeTowardsCalcs.getSlope() != null) {
    				sideTORAExtraMarkTwo.setVisible(true);
    				sideTORAExtraLineTwo.setVisible(true);
    				sideTORAExtraTooltipBoxTwo.setVisible(true);
    				sideTORAExtraMarkTwo.setTranslateX(sideTORAExtraMarkOne.getTranslateX() - sidePixelRatio * takeTowardsCalcs.getStripEnd().doubleValue());
    				sideTORAExtraTextOne.setText("SLOPE");
        			sideTORAExtraTextOne.setFill(Color.LIME);
        			sideTORAExtraLineTwoTooltip.setText("Slope: " + takeTowardsCalcs.getSlope());
        			sideTORAText.setText("STRIP-END");
        			sideTORAText.setFill(Color.RED);
        			sideTORAExtraLineOneTooltip.setText("Strip-End: " + takeTowardsCalcs.getStripEnd());
    			}
    		}

    		//Places the slope
    		if (takeAwayCalcs.getTora() < takeTowardsCalcs.getTora() && takeTowardsCalcs.getSlope() != null) {
    			sideSlopeLine.setVisible(true);
    			sideSlopeText.setVisible(true);
    			sideSlopeText.setText("TOCS");
    			sideSlopeBase.setTranslateX((sideTORAEnd.getLayoutX() + sideTORAEnd.getStartX() + sideTORAEnd.getTranslateX()) - (sideSlopeBase.getLayoutX() + sideSlopeBase.getStartX()));
    			sideSlopeTop.setTranslateX((sidePixelRatio * (sideSlopeBase.getEndY() - sideSlopeTop.getStartY()) * 50) + sideSlopeBase.getTranslateX());
    			sideSlopeText.setTranslateX(sideSlopeBase.getTranslateX());
				sideSlopeTooltip.setText("TOCS 1:50 Slope: " + takeTowardsCalcs.getSlope());
    		} else if (landingTowardsCalcs.getLda() < landingAwayCalcs.getLda() && landingAwayCalcs.getSlope() != null) {
    			sideSlopeLine.setVisible(true);
    			sideSlopeText.setVisible(true);
    			sideSlopeText.setText("ALS");
    			sideSlopeBase.setTranslateX((sideLDAStart.getLayoutX() + sideLDAStart.getStartX() + sideLDAStart.getTranslateX()) - (sideSlopeBase.getLayoutX() + sideSlopeBase.getStartX()));
    			sideSlopeTop.setTranslateX((sidePixelRatio * (sideSlopeTop.getStartY() - sideSlopeBase.getEndY()) * 50) + sideSlopeBase.getTranslateX());
    			sideSlopeText.setTranslateX(sideSlopeBase.getTranslateX());
				sideSlopeTooltip.setText("ALS 1:50 Slope: " + landingAwayCalcs.getSlope());
    		} else {
    			sideSlopeLine.setVisible(false);
    			sideSlopeBase.setVisible(false);
    			sideSlopeTop.setVisible(false);
        		sideSlopeTop.setTranslateX(0);
        		sideSlopeBase.setTranslateX(0);
        		sideSlopeText.setTranslateX(0);
    		}
    	} else if (viewOriginalCalcs.isSelected() && !sideCheckInBounds(runway, logical)) {
    		sideOutOfBounds.setVisible(true);
    		resetSideLabels();
    	} else {
    		resetSideLabels();
		}

    	sideArrowHead.setVisible(true);
    	sideArrowBar.setVisible(true);	
    	sideDirectionText.setVisible(true);
    	sideLDAStart.setVisible(true);
    	sideLDAText.setVisible(true);
    	sideLDAExtraTextOne.setVisible(true);
    	sideLDAExtraTextTwo.setVisible(true);
    	sideLDALine.setVisible(true);
    	sideLDATooltipBox.setVisible(true);
    	sideLDAEnd.setVisible(true);
    	sideTORAText.setVisible(true);
    	sideTORAExtraTextOne.setVisible(true);
    	sideTORAExtraTextTwo.setVisible(true);
    	sideTORAStart.setVisible(true);
    	sideTORALine.setVisible(true);
    	sideTORATooltipBox.setVisible(true);
    	sideTORAEnd.setVisible(true);
    	sideASDAText.setVisible(true);
    	sideASDAText.setTranslateX(sideASDAStart.getTranslateX());
    	sideASDAStart.setVisible(true);
    	sideASDALine.setVisible(true);
    	sideASDATooltipBox.setVisible(true);
    	sideASDAEnd.setVisible(true);
    	sideTODAText.setVisible(true);
    	sideTODAText.setTranslateX(sideTODAStart.getTranslateX());
    	sideTODAStart.setVisible(true);
    	sideTODALine.setVisible(true);
    	sideTODATooltipBox.setVisible(true);
    	sideTODAEnd.setVisible(true);

    	sideSlopeBlockerOne.setVisible(true);
    	sideSlopeBlockerTwo.setVisible(true);
    	sideSlopeBlockerThree.setVisible(true);
    	sideSlopeBlockerFour.setVisible(true);

    	updateSideTooltips();
    	sideSetLines();
    }
    
    public void updateSide(Runway currentRunway) { //Updates the view with the correct amount of logicals
    	hideSideComponents();

    	int logCount = 1;
    	if (currentRunway.getLogicalRunways().values().size() == 1) {
    		sideLogicalOne.setVisible(false);
    		sideLogicalTwo.setVisible(false);
    		for (LogicalRunway logical : currentRunway.getLogicalRunways().values()){
    			sideShowLogical(currentRunway, logical);
    		}
    	} else {
    		for (LogicalRunway logical : currentRunway.getLogicalRunways().values()) {
    			if (logCount == 1) {
    				sideLogicalOne.setText(logical.getCombinedReference());
    			} else if (logCount == 2) {
    				sideLogicalTwo.setText(logical.getCombinedReference());
    			}
    			logCount += 1;
    		}
    		sideLogicalOne.setVisible(true);
    		sideLogicalTwo.setVisible(true);
    		if (sideLogicalOne.isSelected()) {
    			sideShowLogical(currentRunway, currentRunway.getLogicalRunways().get(sideLogicalOne.getText()));
    		} else if (sideLogicalTwo.isSelected()) {
    			sideShowLogical(currentRunway, currentRunway.getLogicalRunways().get(sideLogicalTwo.getText()));
    		}
    	}
    }
    
    @FXML
    public void sideResetLocations() { //Resets the location of all markers and tooltip boxes
    	sideLDAStart.setTranslateX(0);
    	sideLDAEnd.setTranslateX(0);
    	sideLDATooltipBox.setWidth(0);
    	sideLDATooltipBox.setTranslateX(0);
    	sideLDAExtraMarkOne.setTranslateX(0);
    	sideLDAExtraTooltipBoxOne.setWidth(0);
    	sideLDAExtraTooltipBoxOne.setTranslateX(0);
    	sideLDAExtraMarkTwo.setTranslateX(0);
    	sideLDAExtraTooltipBoxTwo.setWidth(0);
    	sideLDAExtraTooltipBoxTwo.setTranslateX(0);
    	sideTORAStart.setTranslateX(0);
    	sideTORAEnd.setTranslateX(0);
    	sideTORATooltipBox.setWidth(0);
    	sideTORATooltipBox.setTranslateX(0);
    	sideTORAExtraMarkOne.setTranslateX(0);
    	sideTORAExtraTooltipBoxOne.setWidth(0);
    	sideTORAExtraTooltipBoxOne.setTranslateX(0);
    	sideTORAExtraMarkTwo.setTranslateX(0);
    	sideTORAExtraTooltipBoxTwo.setWidth(0);
    	sideTORAExtraTooltipBoxTwo.setTranslateX(0);
    	sideASDAStart.setTranslateX(0);
    	sideASDAEnd.setTranslateX(0);
    	sideASDATooltipBox.setWidth(0);
    	sideASDATooltipBox.setTranslateX(0);
    	sideTODAStart.setTranslateX(0);
    	sideTODAEnd.setTranslateX(0);
    	sideTODATooltipBox.setWidth(0);
    	sideTODATooltipBox.setTranslateX(0);
    	sideSlopeTop.setTranslateX(0);
    	sideSlopeBase.setTranslateX(0);
    }
    
    @FXML
    public void sideSetLines() { //Positions each distance line between it's endmarkers and matching tooltip box
    	sideLDALine.setStartX(sideLDAStart.getLayoutX() + sideLDAStart.getStartX() + sideLDAStart.getTranslateX());
    	sideLDALine.setEndX(sideLDAEnd.getLayoutX() + sideLDAEnd.getStartX() + sideLDAEnd.getTranslateX());
    	sideLDATooltipBox.setTranslateX(sideLDAStart.getTranslateX());
    	sideLDATooltipBox.setWidth(sideLDALine.getEndX() - sideLDALine.getStartX());
    	sideTORALine.setStartX(sideTORAStart.getLayoutX() + sideTORAStart.getStartX() + sideTORAStart.getTranslateX());
    	sideTORALine.setEndX(sideTORAEnd.getLayoutX() + sideTORAEnd.getStartX() + sideTORAEnd.getTranslateX());
    	sideTORATooltipBox.setTranslateX(sideTORAStart.getTranslateX());
    	sideTORATooltipBox.setWidth(sideTORALine.getEndX() - sideTORALine.getStartX());
    	sideASDALine.setStartX(sideASDAStart.getLayoutX() + sideASDAStart.getStartX() + sideASDAStart.getTranslateX());
    	sideASDALine.setEndX(sideASDAEnd.getLayoutX() + sideASDAEnd.getStartX() + sideASDAEnd.getTranslateX());
    	sideASDATooltipBox.setTranslateX(sideASDAStart.getTranslateX());
    	sideASDATooltipBox.setWidth(sideASDALine.getEndX() - sideASDALine.getStartX());
    	sideTODALine.setStartX(sideTODAStart.getLayoutX() + sideTODAStart.getStartX() + sideTODAStart.getTranslateX());
    	sideTODALine.setEndX(sideTODAEnd.getLayoutX() + sideTODAEnd.getStartX() + sideTODAEnd.getTranslateX());
    	sideTODATooltipBox.setTranslateX(sideTODAStart.getTranslateX());
    	sideTODATooltipBox.setWidth(sideTODALine.getEndX() - sideTODALine.getStartX());
    	sideSlopeLine.setStartX(sideSlopeTop.getLayoutX() + sideSlopeTop.getStartX() + sideSlopeTop.getTranslateX());
    	sideSlopeLine.setEndX(sideSlopeBase.getLayoutX() + sideSlopeBase.getStartX() + sideSlopeBase.getTranslateX());
    	sideLDAExtraLineOne.setStartX(sideLDAExtraMarkOne.getLayoutX() + sideLDAExtraMarkOne.getStartX() + sideLDAExtraMarkOne.getTranslateX());
    	sideLDAExtraLineOne.setEndX(sideLDAStart.getLayoutX() + sideLDAStart.getStartX() + sideLDAStart.getTranslateX());
    	sideLDAExtraTooltipBoxOne.setTranslateX(Math.min(sideLDAStart.getTranslateX(), sideLDAExtraMarkOne.getTranslateX()));
    	sideLDAExtraTooltipBoxOne.setWidth(Math.max(sideLDAStart.getTranslateX(), sideLDAExtraMarkOne.getTranslateX()) - sideLDAExtraTooltipBoxOne.getTranslateX());
    	sideLDAExtraLineTwo.setStartX(sideLDAExtraMarkTwo.getLayoutX() + sideLDAExtraMarkTwo.getStartX() + sideLDAExtraMarkTwo.getTranslateX());
    	sideLDAExtraLineTwo.setEndX(sideLDAStart.getLayoutX() + sideLDAStart.getStartX() + sideLDAStart.getTranslateX());
    	sideLDAExtraTooltipBoxTwo.setTranslateX(Math.min(sideLDAStart.getTranslateX(), sideLDAExtraMarkTwo.getTranslateX()));
    	sideLDAExtraTooltipBoxTwo.setWidth(Math.max(sideLDAStart.getTranslateX(), sideLDAExtraMarkTwo.getTranslateX()) - sideLDAExtraTooltipBoxTwo.getTranslateX());
    	sideTORAExtraLineOne.setStartX(sideTORAExtraMarkOne.getLayoutX() + sideTORAExtraMarkOne.getStartX() + sideTORAExtraMarkOne.getTranslateX());
    	sideTORAExtraLineOne.setEndX(sideTORAStart.getLayoutX() + sideTORAStart.getStartX() + sideTORAStart.getTranslateX());
    	sideTORAExtraTooltipBoxOne.setTranslateX(Math.min(sideTORAStart.getTranslateX(), sideTORAExtraMarkOne.getTranslateX()));
    	sideTORAExtraTooltipBoxOne.setWidth(Math.max(sideTORAStart.getTranslateX(), sideTORAExtraMarkOne.getTranslateX()) - sideTORAExtraTooltipBoxOne.getTranslateX());
    	sideTORAExtraLineTwo.setStartX(sideTORAExtraMarkTwo.getLayoutX() + sideTORAExtraMarkTwo.getStartX() + sideTORAExtraMarkTwo.getTranslateX());
    	sideTORAExtraLineTwo.setEndX(sideTORAStart.getLayoutX() + sideTORAStart.getStartX() + sideTORAStart.getTranslateX());
    	sideTORAExtraTooltipBoxTwo.setTranslateX(Math.min(sideTORAStart.getTranslateX(), sideTORAExtraMarkTwo.getTranslateX()));
    	sideTORAExtraTooltipBoxTwo.setWidth(Math.max(sideTORAStart.getTranslateX(), sideTORAExtraMarkTwo.getTranslateX()) - sideTORAExtraTooltipBoxTwo.getTranslateX());
    }
    
    @FXML
    public void sideSwitch(ActionEvent event){
    	event.consume();
    	Runway currentRunway;
		try {
			currentRunway = mod.getCurrentRunway();
			updateSide(currentRunway);
		} catch (Exception e) {

		}
    }

    private void sideToggleObstacle() {

    	try {
			updateSide(mod.getCurrentRunway());
		} catch (Exception e) {

		}
    }
   
    private void installSideTooltips() {
    	FixedTooltip.install(sideLDAStart, sideLDATooltip);

    	FixedTooltip.install(sideLDALine, sideLDATooltip);
    	FixedTooltip.install(sideLDAEnd, sideLDATooltip);
    	FixedTooltip.install(sideLDATooltipBox, sideLDATooltip);
        sideLDATooltip.setFont(new Font(13.0));
        sideLDATooltip.setShowDelay(Duration.seconds(0));


    	FixedTooltip.install(sideTORAStart, sideTORATooltip);

    	FixedTooltip.install(sideTORALine, sideTORATooltip);
    	FixedTooltip.install(sideTORAEnd, sideTORATooltip);
    	FixedTooltip.install(sideTORATooltipBox, sideTORATooltip);
        sideTORATooltip.setFont(new Font(13.0));
        sideTORATooltip.setShowDelay(Duration.seconds(0));


    	FixedTooltip.install(sideASDAStart, sideASDATooltip);
    	FixedTooltip.install(sideASDALine, sideASDATooltip);
    	FixedTooltip.install(sideASDAEnd, sideASDATooltip);
    	FixedTooltip.install(sideASDATooltipBox, sideASDATooltip);
        sideASDATooltip.setFont(new Font(13.0));
        sideASDATooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideTODAStart, sideTODATooltip);
    	FixedTooltip.install(sideTODALine, sideTODATooltip);
    	FixedTooltip.install(sideTODAEnd, sideTODATooltip);
    	FixedTooltip.install(sideTODATooltipBox, sideTODATooltip);
        sideTODATooltip.setFont(new Font(13.0));
        sideTODATooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideStopway, sideStopwayTooltip);
        sideStopwayTooltip.setFont(new Font(13.0));
        sideStopwayTooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideClearway, sideClearwayTooltip);
        sideClearwayTooltip.setFont(new Font(13.0));
        sideClearwayTooltip.setShowDelay(Duration.seconds(0));


    	FixedTooltip.install(sideDisplacedThreshold, sideDisplacedThresholdTooltip);
        sideDisplacedThresholdTooltip.setFont(new Font(13.0));
        sideDisplacedThresholdTooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideObstacle, sideObstacleTooltip);
        sideObstacleTooltip.setFont(new Font(13.0));
        sideObstacleTooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideSlopeLine, sideSlopeTooltip);
        sideSlopeTooltip.setFont(new Font(13.0));
        sideSlopeTooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideLDAExtraLineOne, sideLDAExtraLineOneTooltip);
    	FixedTooltip.install(sideLDAExtraTooltipBoxOne, sideLDAExtraLineOneTooltip);
        sideLDAExtraLineOneTooltip.setFont(new Font(13.0));
        sideLDAExtraLineOneTooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideLDAExtraLineTwo, sideLDAExtraLineTwoTooltip);
    	FixedTooltip.install(sideLDAExtraTooltipBoxTwo, sideLDAExtraLineTwoTooltip);
        sideLDAExtraLineTwoTooltip.setFont(new Font(13.0));
        sideLDAExtraLineTwoTooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideTORAExtraLineOne, sideTORAExtraLineOneTooltip);
    	FixedTooltip.install(sideTORAExtraTooltipBoxOne, sideTORAExtraLineOneTooltip);
        sideTORAExtraLineOneTooltip.setFont(new Font(13.0));
        sideTORAExtraLineOneTooltip.setShowDelay(Duration.seconds(0));

    	FixedTooltip.install(sideTORAExtraLineTwo, sideTORAExtraLineTwoTooltip);
    	FixedTooltip.install(sideTORAExtraTooltipBoxTwo, sideTORAExtraLineTwoTooltip);
        sideTORAExtraLineTwoTooltip.setFont(new Font(13.0));
        sideTORAExtraLineTwoTooltip.setShowDelay(Duration.seconds(0));
    }

    private void updateSideTooltips() {
    	sideLDATooltip.setText("LDA: " + Math.round((sideLDAEnd.getLayoutX() + sideLDAEnd.getTranslateX() - sideLDAStart.getTranslateX()) / sidePixelRatio));
    	sideTORATooltip.setText("TORA: " + Math.round((sideTORAEnd.getLayoutX() + sideTORAEnd.getTranslateX() - sideTORAStart.getTranslateX()) / sidePixelRatio));
    	sideASDATooltip.setText("ASDA: " + Math.round((sideASDAEnd.getLayoutX() + sideASDAEnd.getTranslateX() - sideASDAStart.getTranslateX()) / sidePixelRatio));
    	sideTODATooltip.setText("TODA: " + Math.round((sideTODAEnd.getLayoutX() + sideTODAEnd.getTranslateX() - sideTODAStart.getTranslateX()) / sidePixelRatio));
    	sideStopwayTooltip.setText("Stopway: " + Math.round(sideStopway.getWidth() / sidePixelRatio));
    	sideClearwayTooltip.setText("Clearway: " + Math.round(sideClearway.getWidth() / sidePixelRatio));
    	sideDisplacedThresholdTooltip.setText("Displaced Threshold: " + Math.round(sideDisplacedThreshold.getTranslateX() / sidePixelRatio));

    }
    
    private void resetSideLabels() {
		sideLDALabelPane.setTranslateX(sideLDAStart.getTranslateX());
		sideLDAText.setText("LDA");
		sideLDAText.setFill(Color.WHITE);
		sideLDAExtraTextOne.setText("");
		sideLDAExtraTextTwo.setText("");
		sideTORALabelPane.setTranslateX(sideTORAStart.getTranslateX());
		sideTORAText.setText("TORA");
		sideTORAText.setFill(Color.WHITE);
		sideTORAExtraTextOne.setText("");
		sideTORAExtraTextTwo.setText("");
    }

    private Boolean sideCheckInBounds(Runway runway, LogicalRunway logical) {
    	Boolean inLeft = (runway.getObstacle().getObstaclePosition().get(logical.getCombinedReference()).doubleValue() + logical.getDisplacedThreshold().doubleValue()) >= -60;
    	Boolean inRight = (runway.getObstacle().getObstaclePosition().get(logical.getCombinedReference()).doubleValue() - logical.getDisplacedThreshold().doubleValue() - logical.getTORA().doubleValue()) <= 60;
    	Boolean inCentre = (runway.getObstacle().getDistanceFromCentre().doubleValue() <= 75) && (runway.getObstacle().getDistanceFromCentre().doubleValue() >= -75);
    	return (inLeft && inRight && inCentre);
    }
//------------------------------
//
//END OF SIDE SPECIFIC METHODS
//
//------------------------------
    
    
    @FXML
    private void changePhysical(ActionEvent event){
        String selected = physicalSelector.getSelectionModel().getSelectedItem();
        if (selected == null) {

            alert("No Physical Runway Selected");

        }else{
            mod.changeRunway(selected);
            alertInfo("Physical Runway "+selected+" selected!");
            mod.update();

        }



    }

	@FXML
    private void openConfigRunway(ActionEvent event) throws IOException  {
        event.consume();
        //refAutofill();
        try {
            if(mod.getCurrentRunway() == null){

            }

            Stage stage = new Stage();
            stage.setTitle("Add Logical Runway");
            stage.getIcons().add(new Image("pngtree-vector-airplane-icon-png-image_1024816.jpg"));
            Parent root = FXMLLoader.load(getClass().getResource("/AddLogicalRunway.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(mod.getCurrentRunway().getName());

            logicalRunwayInverse = (Button) stage.getScene().lookup("#logicalRunwayInverse");
            letter = (TextField) stage.getScene().lookup("#letter");
            designationLeft = (TextField) stage.getScene().lookup("#designationLeft");
            toraLeft = (TextField) stage.getScene().lookup("#toraLeft");
            if(mod.getCurrentRunway().getLogicalRunways().size() == 1) {
                logicalRunwayInverse.setVisible(true);
                LogicalRunway lr = mod.getCurrentRunway().getLogicalRunways().values().iterator().next();
                logicalRunwayInverse.setText("Mirror " + lr.getCombinedReference()+"?");
                if (lr.getLetter().equals('L')) {
                	letter.setText("R");
                } else if (lr.getLetter().equals('R')) {letter.setText("L");
                } else if(lr.getLetter().equals('C')) {letter.setText("C");}
                int num = lr.getHeading().intValue();
                if (num > 18) {
                	if (num - 18 < 10) {designationLeft.setText("0" + Integer.toString(num - 18));}
                	else {designationLeft.setText(Integer.toString(num - 18));}
                } else {designationLeft.setText(Integer.toString(num + 18));}
                toraLeft.setText(Integer.toString(lr.getTORA().intValue()));
            } else { logicalRunwayInverse.setVisible(false);}

            stage.show();

        } catch (Exception e) {
            alert(e.getMessage());
        }

    }

	@FXML
	private void logAutofill() {
		logDistancesAutofill();
		logFeaturesAutofill();
	}
	@FXML
	private void logDistancesAutofill() {
		try {
			if (!stopwayLeft.getText().isEmpty() && !toraLeft.getText().isEmpty()) {
				asdaLeft.setText(Integer.toString(Integer.parseInt(stopwayLeft.getText()) + Integer.parseInt(toraLeft.getText())));
			}
		} catch (Exception e) {
			alert("Stopway must be a postive integer!");
			asdaLeft.setText(null);
			stopwayLeft.setText(null);
		}
		try {
			if (!clearwayLeft.getText().isEmpty() && !toraLeft.getText().isEmpty()) {
				todaLeft.setText(Integer.toString(Integer.parseInt(clearwayLeft.getText()) + Integer.parseInt(toraLeft.getText())));
			}
		} catch (Exception e) {
			alert("Clearway must be a postive integer!");
			clearwayLeft.setText(null);
			todaLeft.setText(null);
		}
		try {
			if (!displacedLeft.getText().isEmpty() && !toraLeft.getText().isEmpty()) {
				ldaLeft.setText(Integer.toString(Integer.parseInt(toraLeft.getText()) - Integer.parseInt(displacedLeft.getText())));
			}
		} catch (Exception e) {
			alert("Displaced Threshold distance must be a positive integer!");
			displacedLeft.setText(null);
			ldaLeft.setText(null);
		}
	}

	@FXML
	private void logFeaturesAutofill() {
		try {
			if (!asdaLeft.getText().isEmpty() && !toraLeft.getText().isEmpty()) {
				stopwayLeft.setText(Integer.toString(Integer.parseInt(asdaLeft.getText()) - Integer.parseInt(toraLeft.getText())));
			}
		} catch (Exception e) {
			alert("ASDA must be a positive integer!");
			asdaLeft.setText(null);
			stopwayLeft.setText(null);
		}
		try {
			if (!todaLeft.getText().isEmpty() && !toraLeft.getText().isEmpty()) {
				clearwayLeft.setText(Integer.toString(Integer.parseInt(todaLeft.getText()) - Integer.parseInt(toraLeft.getText())));
			}
		} catch (Exception e) {
			alert("TODA must be a positive integer!");
			todaLeft.setText(null);
			clearwayLeft.setText(null);
		}
		try {
			if (!ldaLeft.getText().isEmpty() && !toraLeft.getText().isEmpty()) {
				displacedLeft.setText(Integer.toString(Integer.parseInt(toraLeft.getText()) - Integer.parseInt(ldaLeft.getText())));
			}
		} catch (Exception e) {
			alert("LDA must be a positive integer!");
			ldaLeft.setText(null);
			displacedLeft.setText(null);
		}
	}

    @FXML
    private void logicalRunwayInverseClick(ActionEvent event) {
        event.consume();

        try {
            if (mod.getCurrentRunway().getLogicalRunways().size() == 1) {
                logicalRunwayInverse.setVisible(true);
                LogicalRunway lr = mod.getCurrentRunway().getLogicalRunways().values().iterator().next();

                Number head = lr.getHeading();
                if(head.intValue() <= 18 && head.intValue() >= 0) {
                    head = 18 + head.intValue();
                } else if(head.intValue() > 18 && head.intValue() <=36) {
                    head = head.intValue() - 18;
                }
                //if(head.intValue() == 36) {
                    //head = 0;
                //}
                if(head.intValue() < 0) {
                    designationLeft.setText("");
                } else if(head.intValue() < 10) {
                    designationLeft.setText("0"+head.toString());
                } else { designationLeft.setText(head.toString());}

                Character let = lr.getLetter();
                if(let.equals('L')) {
                    let = 'R';
                } else if(let.equals('R')) {let = 'L';}
                letter.setText(let.toString());

                toraLeft.setText(lr.getTORA().toString());
                todaLeft.setText(lr.getTODA().toString());
                asdaLeft.setText(lr.getASDA().toString());
                ldaLeft.setText(lr.getLDA().toString());
                clearwayLeft.setText(lr.getClearway().toString());
                stopwayLeft.setText(lr.getStopway().toString());
                displacedLeft.setText(lr.getDisplacedThreshold().toString());

            }
        }catch (Exception e) {}
    }



    @FXML
    private void configPhysical(ActionEvent event){

        String name = runwayName.getText();
        for(Runway run : mod.getRunways()){
            String testName = run.getName();
            if(!(testName == null) || !testName.equals("")){
                testName = testName.split(" ")[0];
                if(testName.equals(name)) {
                    this.alert("There already exists a runway by that name!\nPlease select another");
                    return;
                }
            }

        }


        Runway runway = new Runway(name);
        mod.addRunway(runway);

        tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.close(tempStage);
        alertInfo("Physical Runway "+name+" Added!");

        mod.update();
    }

    @FXML
    private void addPhysical(ActionEvent event) throws IOException {
        event.consume();
        try {

            if(mod.getAirport() == null){
                throw new Exception("Please Select an Airport first");
            }

            Stage stage = new Stage();
            stage.setTitle("Add Physical Runway");
            stage.getIcons().add(new Image("pngtree-vector-airplane-icon-png-image_1024816.jpg"));
            Parent root = FXMLLoader.load(getClass().getResource("/AddPhysicalRunway.fxml"));
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            alert(e.getMessage());
        }
    }

    @FXML
    private void addAirport(ActionEvent event){
        event.consume();
        try {

            Stage stage = new Stage();
            stage.setTitle("Add Airport");
            stage.getIcons().add(new Image("pngtree-vector-airplane-icon-png-image_1024816.jpg"));
            Parent root = FXMLLoader.load(getClass().getResource("/ConfigAirport.fxml"));
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            alert(e.getMessage());
        }
    }
	@FXML
    private void configAirport(ActionEvent event){
        event.consume();
        try {


            for (Airport a : mod.getAllAirports()) {
                if (a.getName().equals(airportName.getText().trim())) {
                    this.alert("There already exists an by that name!\nPlease select another");
                    return;
                }
            }
        }catch(NullPointerException e){

        }


        try {
            mod.addAirport(resa.getText(), stripEnd.getText(), airportName.getText());
            tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.close(tempStage);
            alertInfo("Airport "+airportName.getText()+" Added!");

            mod.update();
        }catch(Exception e){
            alert(e.getMessage());
        }
    }

    @FXML
    private void changeAirport(ActionEvent event){
        event.consume();
        String selected = airportSelector.getSelectionModel().getSelectedItem();
        if(selected == null){
            alert("No Airport Selected!");
        }else {
            mod.changeAirport(selected);
            alertInfo("Airport " + selected + " selected!");
            mod.update();
        }

    }

	@FXML
    private void openConfigObs(ActionEvent event) throws IOException {
        event.consume();


        try {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            stage.setTitle("Add Obstacle");
            stage.getIcons().add(new Image("pngtree-vector-airplane-icon-png-image_1024816.jpg"));
            Parent root = fxmlLoader.load(getClass().getResource("/ConfigObs.fxml"));
            stage.setScene(new Scene(root));
            Controller cont = fxmlLoader.getController();

            ComboBox cb = (ComboBox) stage.getScene().lookup("#predefobs");
            predefobs = cb;
            //cb.getItems().add("0");

            for(ObstacleWrapper ow : PredefinedObstacles.getPredefObs()) {
                cb.getItems().add(ow.getName());
            }
            cb.getItems().add("None");

            if (mod.getLogicalRunways().keySet().size() < 2) {
                stage.getScene().lookup("#rightThresholdText").setVisible(false);
                stage.getScene().lookup("#obsRThreshold").setVisible(false);
                Label lbl = (Label) stage.getScene().lookup("#leftThresholdText");
                lbl.setText("Distance from " + mod.getLogicalRunways().keySet().toArray()[0] + " threshold");



            } else {
                Label lbl = (Label) stage.getScene().lookup("#leftThresholdText");
                lbl.setText("Distance from " + mod.getLogicalRunways().keySet().toArray()[0] + " threshold");

                lbl = (Label) stage.getScene().lookup("#rightThresholdText");
                lbl.setText("Distance from " + mod.getLogicalRunways().keySet().toArray()[1] + " threshold");

            }


            stage.show();

        }catch(Exception e){
            alert("Cannot add an obstacle as no Logical Runway present!");
        }
    }

	public void obsAutofill() {
		try {
			if (!obsLThreshold.getText().isEmpty()) {
				if (!obsLThreshold.getText().equals("-")) {
					Iterator<LogicalRunway> logs = mod.getLogicalRunways().values().iterator();
					LogicalRunway L1 = logs.next();
					int TORA = (int) Math.round(L1.getTORA().doubleValue());
					int DT1 = (int) Math.round(L1.getDisplacedThreshold().doubleValue());
					int DT2;
					if (logs.hasNext()) {
						LogicalRunway L2 = logs.next();
						DT2 = (int) Math.round(L2.getDisplacedThreshold().doubleValue());
					} else {
						DT2 = 0;
					}
					obsRThreshold.setText(Integer.toString(TORA - DT1 - DT2- Integer.parseInt(obsLThreshold.getText())));
				}
			} else {
				obsRThreshold.setText(null);
			}
		} catch (Exception e) {
			alert("Threshold distance must be an integer!");
			obsLThreshold.setText(null);
			obsRThreshold.setText(null);
		}
	}

    @FXML
    public void predefobsselected(ActionEvent e) {
        e.consume();

        String s = (String) predefobs.getItems().get(predefobs.getSelectionModel().getSelectedIndex());
        if(s.equals("None")) {
            obsCentreline.setText("");
            obsLThreshold.setText("");
            obsRThreshold.setText("");
            obsHeight.setText("");
            obsID.setText("");
            blast.setText("");
            return;
        }

        Obstacle tmp = PredefinedObstacles.searchList(s);
        if(tmp == null) {
            return;
        }

        obsID.setText(s);
        obsHeight.setText(tmp.getHeight().toString());
        blast.setText(tmp.getBlastProtection().toString());
        if(tmp.getDistanceFromLeft() != null) {
            obsLThreshold.setText(tmp.getDistanceFromLeft().toString());
        }
        if(tmp.getDistanceFromRight() != null) {
            obsRThreshold.setText(tmp.getDistanceFromRight().toString());
        }
        if(tmp.getDistanceFromCentre() != null) {
            obsCentreline.setText(tmp.getDistanceFromCentre().toString());
        }
    }
	
	@FXML
    private void openViewCalculations(ActionEvent event) throws IOException  {
        event.consume();
        Stage stage = new Stage();
        tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();


        Parent root = fxmlLoader.load(getClass().getResource("/ViewCalculations.fxml"));
        Controller cont = fxmlLoader.getController();
        stage.setScene(new Scene(root));




        stage.show();

        this.close(tempStage);

    }
	
	@FXML
    private void openSettings(ActionEvent event) throws IOException  {
        event.consume();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Settings.fxml"));
        stage.setScene(new Scene (root));
        stage.show();
    }

	public void close(Stage stage) {

		stage.close();

        //mod.update();
	}

    @FXML
    void mouseEnteredLDA(MouseEvent event) throws Exception {
        HashMap<String,LogicalRunway> logicals = mod.getLogicalRunways();

        rightLdaText.setText(mod.getCurrentRunway().getLargestLogicalRunway().getLDA().toString());
    }

    @FXML
    void mouseExitLDA(MouseEvent event) {
        rightLdaText.setText("LDA");
    }
	
	public void closeSettings(ActionEvent event) throws IOException {
		event.consume();

		tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		this.close(tempStage);
	}
	
	public void toggleLogical(ActionEvent event) throws IOException {
		event.consume();

		Scene scene = ((Node) event.getSource()).getScene();
		GridPane pne = (GridPane) scene.lookup("#logicalInputsTwo");
		Button btn = (Button) scene.lookup("#logicalBtn");
		if (pne.isVisible()) {
			pne.setVisible(false);
			secondLogicalAdded = false;
			btn.setText("Add Logical Runway");
		} else {
			pne.setVisible(true);
            secondLogicalAdded = true;
			btn.setText("Remove Logical Runway");	
		}
	}
	
	public void toggleObstacle(ActionEvent event) throws IOException {
		event.consume();

		Scene scene = ((Node) event.getSource()).getScene();
		GridPane pne = (GridPane) scene.lookup("#obstacleInputs");
		Button btn = (Button) scene.lookup("#obstacleBtn");		
		if (pne.isVisible()) {
			pne.setVisible(false);
			obstacleAdded = false;
			btn.setText("Add Obstacle");
		} else {
			pne.setVisible(true);
            obstacleAdded = true;
			btn.setText("Remove Obstacle");	
		}
	}
//Placeholder methods to check radio button functionality
	@FXML
	private void viewTop(ActionEvent event) throws IOException {
		event.consume();

		Scene scene = ((Node) event.getSource()).getScene();
		Pane top = (Pane) scene.lookup("#viewPane");
		Pane side = (Pane) scene.lookup("#viewSide");
		top.setVisible(true);
		side.setVisible(false);
	}

	@FXML
    private void viewException(ActionEvent event) throws IOException{

    }
	
	@FXML
	private void viewSide(ActionEvent event) throws IOException {
		event.consume();

		Scene scene = ((Node) event.getSource()).getScene();
		Pane top = (Pane) scene.lookup("#viewPane");
		Pane side = (Pane) scene.lookup("#viewSide");
		top.setVisible(false);
		side.setVisible(true);
	}

    @FXML
    public void openHelp(ActionEvent event) throws IOException  {
        if(event != null){
            event.consume();
        }


        Stage stage = new Stage();
        stage.setTitle("Help Screen");
        stage.getIcons().add(new Image("pngtree-vector-airplane-icon-png-image_1024816.jpg"));
        Parent root = FXMLLoader.load(getClass().getResource("/Help.fxml"));
        stage.setScene(new Scene (root));
        stage.show();
    }


	@FXML
	private void viewDual(ActionEvent event) throws IOException {
		event.consume();

		Scene scene = ((Node) event.getSource()).getScene();
		Pane pne = (Pane) scene.lookup("#viewPane");
		pne.setStyle("-fx-background-color: #f000f0");
	}

	@FXML
    private void configureParameters(ActionEvent event) {

        event.consume();

        try {
            if(mod.getCurrentRunway().getObstacle()!=null){
                throw new Exception("Cannot add a Logical Runway after Obstacle added to Physical one\nPlease configure all runways before adding an Obstacle");
            }
            char headingLetter;
            try{
                headingLetter = letter.getText().toCharArray()[0];
            }catch(Exception e){
                headingLetter = ' ';
            }

            mod.getCurrentRunway().addLogicalRunway(toraLeft.getText(), todaLeft.getText(), asdaLeft.getText(), ldaLeft.getText(), designationLeft.getText(), headingLetter, stopwayLeft.getText(), clearwayLeft.getText(), displacedLeft.getText());
            tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.close(tempStage);
            alertInfo("Logical Runway Added!");
            mod.update();

        } catch (Exception e) {
            alert(e.getMessage()+"\nPlease try again!");
        }




    }

    @FXML
    void configObstacle(ActionEvent event) {
        try{
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        HashMap<String, LogicalRunway> logs = mod.getLogicalRunways();
        String designation = (String) logs.keySet().toArray()[0];
        if(designation.length() < 3){
            designation = "0"+designation;
        }
        map1.put(designation,Integer.valueOf(obsLThreshold.getText()));


        if(logs.keySet().size() > 1){
            designation = (String) logs.keySet().toArray()[1];
            if(designation.length() < 3){
                designation = "0"+designation;
            }
            map1.put(designation,Integer.valueOf(obsRThreshold.getText()));
        }


            mod.getCurrentRunway().addObstacle(map1, Integer.valueOf(obsHeight.getText()), Integer.valueOf(blast.getText()),Integer.valueOf(obsCentreline.getText()));
            tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.close(tempStage);
            alertInfo("Obstacle Added!");
            mod.update();
        } catch (Exception e) {

            alert(e.getMessage());

        }

    }

    @FXML
    void runCalcs(ActionEvent event) {
    	try {
            if (mod.getLogicalRunways() == null || mod.getLogicalRunways().values().size() <= 0) {
                outputArea.clear();
            } else {
                String ref = ((RadioButton) calculation.getSelectedToggle()).getText();
                Runway runway = mod.getCurrentRunway();
                LogicalRunway logical = mod.getLogicalRunways().get(ref);
                Airport airport = mod.getAirport();

                if (runway.getObstacle() == null || runway.getObstacle().getObstaclePosition().get(logical.getCombinedReference()) == null) {
                    toggleCalculations.setSelected(false);
                    toggleCalculations.setDisable(true);
                    if (toggleCalculations.isVisible()) {
                        calculationHeader.setVisible(true);
                    } else {
                        calculationHeader.setVisible(false);
                    }
                } else {
                    toggleCalculations.setDisable(false);
                    calculationHeader.setVisible(false);
                }
                if (toggleCalculations.isSelected()) {
                    Calculation TOA = runway.calculateTakeOffAway(logical, airport);
                    Calculation TOT = runway.calculateTakeOffTowards(logical, airport);
                    Calculation LO = runway.calculateLandingOver(logical, airport);
                    Calculation LT = runway.calculateLandingTowards(logical, airport);
                    if (LO.getLda() > LT.getLda()) {
                        outputArea.setText(LO.getLdaCalculation() + "\n" + LO.getPreviousLda() + "\n");
                    } else {
                        outputArea.setText(LT.getLdaCalculation() + "\n" + LT.getPreviousLda() + "\n");
                    }
                    if (TOA.getTora() > TOT.getTora()) {
                        outputArea.setText(outputArea.getText() + TOA.getToraCalculation() + "\n" + TOA.getPreviousTora() + "\n");
                        outputArea.setText(outputArea.getText() + TOA.getAsdaCalculation() + "\n" + TOA.getPreviousAsda() + "\n");
                        outputArea.setText(outputArea.getText() + TOA.getTodaCalculation() + "\n" + TOA.getPreviousToda());
                    } else {
                        outputArea.setText(outputArea.getText() + TOT.getToraCalculation() + "\n" + TOT.getPreviousTora() + "\n");
                        outputArea.setText(outputArea.getText() + TOT.getAsdaCalculation() + "\n" + TOT.getPreviousAsda() + "\n");
                        outputArea.setText(outputArea.getText() + TOT.getTodaCalculation() + "\n" + TOT.getPreviousToda());
                    }
                } else {
                    outputArea.setText("Initial LDA: " + logical.getLDA() + "\n");
                    outputArea.setText(outputArea.getText() + "Initial TORA: " + logical.getTORA() + "\n");
                    outputArea.setText(outputArea.getText() + "Initial ASDA: " + logical.getASDA() + "\n");
                    outputArea.setText(outputArea.getText() + "Initial TODA: " + logical.getTODA());
                }
            }
        }catch(NullPointerException e){

        }catch (Exception e){
            alert(e.getMessage()+"\nPlease try again!");

        }
    }

    @FXML
    void viewWithObstacle(ActionEvent event) {
        mod.update();

        sideToggleObstacle();

    }

    @FXML
    void viewWithoutObstacle(ActionEvent event) {
        mod.update();

        sideToggleObstacle();


    }

    void alert(String message) {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText(message);
        a.show();
    }

    void alertInfo(String message) {
        try {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText(message);
            a.show();
        }catch(Exception e){

        }
    }

    public void openImportAirObs(ActionEvent event) throws IOException {
        event.consume();

        Stage stage = new Stage();
        stage.setTitle("Load Airports/Obstacles from XML");
        stage.getIcons().add(new Image("pngtree-vector-airplane-icon-png-image_1024816.jpg"));
        Parent root = FXMLLoader.load(getClass().getResource("/XML.fxml"));
        stage.setScene(new Scene (root));
        stage.show();
    }

    @FXML
    public void openImportObs(ActionEvent event) throws  IOException{
        event.consume();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File","*.xml"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        try {
            String selected = selectedFile.getAbsolutePath();


            if (selected == null || selected.equals("")) {
                this.alert("Cannot open file");
                return;
            } else {
                try {
                    XMLParser.importObstacles(selected);


                } catch (Exception e) {

                    this.alert("Cannot open File\n"+e.getMessage());
                    return;
                }
            }

        }catch (NullPointerException e){
            alert("No file selected");
            return;
        }

        tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.close(tempStage);
        alertInfo("Obstacles Imported");
    }


    public void openImport(ActionEvent event) {
        event.consume();
        ArrayList<Airport> airs;

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File","*.xml"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        try {
            String selected = selectedFile.getAbsolutePath();


            if (selected == null || selected.equals("")) {
                this.alert("Cannot open file");
                return;
            } else {
                try {
                    airs = XMLParser.getAirportXMLDataFromFile(selected);
                    if (airs == null) {
                        throw new Exception();
                    }

                } catch (Exception e) {

                    this.alert("Cannot open file\n"+e.getMessage());
                    return;
                }
            }


            ArrayList<Controller> c = mod.getControllers();
            mod.addAirports(airs);
            mod.changeRunway("");


            mod.update();
        }catch (NullPointerException e){
            alert("No file selected");


        }
        tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.close(tempStage);
        alertInfo("Airports Imported");
    }

    public void openAirportXSD(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/open?id=1mGtn2zUlKvhTq8stzDN-j35aw_zgA5mb"));
    }

    public void openObstacleXSD(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/open?id=14USUrglnglj9WaNMVbcCCn9UBTKCy6T6"));
    }

    public void openExport(ActionEvent event) {
        event.consume();

        if(mod == null) {
            this.alert("cannot export");
            return;
        }
        FileChooser fileChooser = new FileChooser();

        try {


            Date d = new Date();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File","*.xml"));
            //fileChooser.setInitialDirectory();
            fileChooser.setInitialFileName(d.getTime()+".xml");

            File selectedFile = fileChooser.showSaveDialog(new Stage());
            String selected = selectedFile.getAbsolutePath();


            /*

            if(s.contains(".") && !s.endsWith(".xml")) {
                s = s.split(".")[0] +".xml";
            }
            if(!s.endsWith(".xml")) {
                s += ".xml";
            }

             */

            if (selected.isBlank() || selected.isEmpty() || selected.equals(".xml")) {
                this.alert("Cannot save file");
            } else {
                try {
                    mod.saveDataToXML(selected, mod);
                } catch (Exception e) {
                    this.alert("Cannot save file, make sure extension is .xml");
                    return;
                }
            }

            mod.update();
        }catch(NullPointerException e){
            alert("No file selected");
            return;
        }
        alertInfo("Airports Exported");
    }

    @FXML
    private void rotate(ActionEvent event) throws IOException {
        event.consume();
        if(rotateButton.isSelected()) {
            try {
                compass.setVisible(true);
                zoomInButton.setDisable(false);
                zoomOutButton.setDisable(false);
                upButton.setDisable(false);
                downButton.setDisable(false);
                leftButton.setDisable(false);
                rightButton.setDisable(false);
                Runway run = mod.getCurrentRunway();

                Integer heading = run.getLargestLogicalRunway().getHeading().intValue();
                int rotHeading = heading;
                if(rotHeading<18 && run.getLogicalRunways().size()<2){
                    rotHeading+=18;
                }else if(run.getLogicalRunways().size()<2){
                    rotHeading-=18;
                }

                Integer rotation = (rotHeading * 10)-270;

                viewPane.setRotate(rotation);

                if(heading.equals(26)||heading.equals(28)){
                    for(int x = 0;x<5;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(25)||heading.equals(29)){
                    for(int x = 0;x<8;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(24)||heading.equals(30)){
                    for(int x = 0;x<10;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(23)||heading.equals(31)){
                    for(int x = 0;x<11;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(22)||heading.equals(32)){
                    for(int x = 0;x<12;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(21)||heading.equals(33)){
                    for(int x = 0;x<12;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(20)||heading.equals(34)){
                    for(int x = 0;x<12;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(19)||heading.equals(35)){
                    for(int x = 0;x<12;x++){
                        zoomOut(null);
                    }

                }else if(heading.equals(18)||heading.equals(36)){
                    for(int x = 0;x<12;x++){
                        zoomOut(null);
                    }

                }


                //rotateText(-rotation);
            } catch (Exception e) {

            }
        }else{
            compass.setVisible(false);
            zoomInButton.setDisable(true);
            zoomOutButton.setDisable(true);
            upButton.setDisable(true);
            downButton.setDisable(true);
            leftButton.setDisable(true);
            rightButton.setDisable(true);


            viewPane.setRotate(0);
            rotateText(0);
            viewPane.setScaleX(1);
            viewPane.setScaleY(1);
            viewPane.setTranslateY(0);
            viewPane.setTranslateX(0);


            viewSide.setRotate(0);

        }


    }

    private void rotateText(Integer rotation){

        rightClearwayText.setRotate(rotation);
        rightStopwayText.setRotate(rotation);
        leftAsdaText.setRotate(rotation);
        leftTodaText.setRotate(rotation);
        leftToraTextBox.setRotate(rotation);
        leftLdaTextBox.setRotate(rotation);
        leftStopwayText.setRotate(rotation);
        leftClearwayText.setRotate(rotation);
        rightAsdaText.setRotate(rotation);
        rightTodaText.setRotate(rotation);
        landingDirectionLTR.setRotate(rotation);
        landingDirectionRTL.setRotate(rotation);
        sideDirectionText.setRotate(rotation);
        sideTakeDirection.setRotate(rotation);
        sideLandingDirection.setRotate(rotation);
        sideOutOfBounds.setRotate(rotation);
        sideLDALabelPane.setRotate(rotation);
        sideTORALabelPane.setRotate(rotation);
        sideASDAText.setRotate(rotation);
        sideTODAText.setRotate(rotation);
        sideName.setRotate(rotation);
        sideSlopeText.setRotate(rotation);

    }

    @FXML
    private void zoomIn(ActionEvent event){
        if(rotateButton.isSelected()) {
            double scale = viewPane.getScaleX();
            double newscale = scale + 0.05;

            if (newscale < 0) {
                newscale = 0;
            }

            viewPane.setScaleX(newscale);
            viewPane.setScaleY(newscale);
        }else{
            alertInfo("Cannot Zoom In - not in rotate mode");
        }

    }

    @FXML
    private void zoomOut(ActionEvent event){
        if(rotateButton.isSelected()) {
            double scale = viewPane.getScaleX();
            double newscale = scale - 0.05;

            if (newscale < 0) {
                newscale = 0;
            }

            viewPane.setScaleX(newscale);
            viewPane.setScaleY(newscale);
        }else{
            alertInfo("Cannot Zoom Out - not in rotate mode");
        }


    }

    @FXML
    private void moveUp(ActionEvent event){
        if(rotateButton.isSelected()) {
            viewPane.setTranslateY(viewPane.getTranslateY() - 165);
        }else{
            alertInfo("Cannot Move Up - not in rotate mode");
        }
    }

    @FXML
    private void moveDown(ActionEvent event){
            if(rotateButton.isSelected()) {

                viewPane.setTranslateY(viewPane.getTranslateY() + 165);
            }else{
                alertInfo("Cannot Move Down - not in rotate mode");
            }
    }

    @FXML
    private void moveLeft(ActionEvent event){

                if(rotateButton.isSelected()) {
                    viewPane.setTranslateX(viewPane.getTranslateX() - 200);
                }else{
                    alertInfo("Cannot Move Left - not in rotate mode");
                }
    }

    @FXML
    private void moveRight(ActionEvent event){
        if(rotateButton.isSelected()) {
            viewPane.setTranslateX(viewPane.getTranslateX() + 200);
        }else{
            alertInfo("Cannot Move Right - not in rotate mode");
        }
    }

    @FXML
    private void drag(MouseEvent event){
        Node n = (Node)event.getSource();
        n.setTranslateX(n.getTranslateX()+event.getX());
        n.setTranslateY(n.getTranslateY()+event.getX());
    }
}