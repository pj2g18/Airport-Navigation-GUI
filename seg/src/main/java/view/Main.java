package view;

import model.*;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;

public class Main {
    public static void main(String[] args) {


        //TODO - model.Calculation object, XML Parser, Default Obstacles

        //Get it to show what it has calculated with letters

        while (true) {
            Model mod = new Model();
            Runway runway = mod.addRunway(new Runway());
            Scanner scanner = new Scanner(System.in);
            Boolean flag = null;
            LogicalRunway logical = null;
            System.out.println("Press 1 to load default values for demo");
            System.out.println("Press 2 to manually enter values");
            int manual = scanner.nextInt();
            if (manual == 1) {
                flag = true;
                try {
                    logical = mod.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
                    HashMap<String, Number> map = new HashMap<>();
                    map.put(logical.getCombinedReference(), 3646);
                    runway.addObstacle(map, 12, 0);
                    System.out.println("LOADED");

                } catch (Exception e) {
                    System.out.println("Default fails");
                    flag = false;
                }
            } else {

                System.out.println("PLEASE INPUT INITIAL VALUES TO CALCULATE REDECLARATION");

                System.out.print("TORA: ");
                String tora = scanner.next();

                System.out.print("TODA: ");
                String toda = scanner.next();

                System.out.print("ASDA: ");
                String asda = scanner.next();

                System.out.print("LDA: ");
                String lda = scanner.next();

                System.out.print("Stopway (If not present, enter 0) : ");
                String stopway = scanner.next();

                System.out.print("Clearway (If not present, enter 0) : ");
                String clearway = scanner.next();

                System.out.print("Displaced Threshold (If not present, enter 0) : ");
                String displaced = scanner.next();

                System.out.print("Heading (2 characters) : ");
                String heading = scanner.next();

                System.out.print("Runway Letter (If Present) : ");
                String let = scanner.next();
                Character letter = let.toCharArray()[0];

                try {
                    logical = mod.addLogicalRunway(tora, toda, asda, lda, heading, letter, stopway, clearway, displaced);


                    System.out.println("Obstacle Details...");
                    System.out.print("Height: ");
                    int height = scanner.nextInt();

                    System.out.print("Distance from " + logical.getCombinedReference() + " threshold: ");
                    int distance = scanner.nextInt();
                    HashMap<String, Number> map = new HashMap<>();

                    map.put(logical.getCombinedReference(), distance);

                    System.out.print("Blast Protection for Specific Aircraft (If not applicable enter 0): ");
                    int blast = scanner.nextInt();


                    runway.addObstacle(map, height, blast);

                    flag = true;
                } catch (Exception e) {
                    System.out.println("Invalid Input : " + e.getMessage());
                    flag = false;

                }
            }


            while (flag) {
                System.out.println();
                System.out.println("Now Enter...");
                System.out.println("1 to calculate Take Off Away");
                System.out.println("2 to calculate Take Off Towards");
                System.out.println("3 to calculate Landing Towards");
                System.out.println("4 to calculate Landing Over");
                System.out.println("5 to enter new values");
                System.out.println("6 to quit");


                String option = scanner.next();

                if (option.equals("1")) {
                    Calculation values = runway.calculateTakeOffAway(logical, mod.getAirport());
                    System.out.println(values.getToraCalculation());
                    System.out.println(values.getPreviousTora());
                    System.out.println();
                    System.out.println(values.getTodaCalculation());
                    System.out.println(values.getPreviousToda());
                    System.out.println();
                    System.out.println(values.getAsdaCalculation());
                    System.out.println(values.getPreviousAsda());
                    System.out.println();
                    System.out.println(values.getLdaCalculation());
                    System.out.println(values.getPreviousLda());

                } else if (option.equals("2")) {
                    Calculation values = runway.calculateTakeOffTowards(logical, mod.getAirport());
                    System.out.println(values.getToraCalculation());
                    System.out.println(values.getPreviousTora());
                    System.out.println();
                    System.out.println(values.getTodaCalculation());
                    System.out.println(values.getPreviousToda());
                    System.out.println();
                    System.out.println(values.getAsdaCalculation());
                    System.out.println(values.getPreviousAsda());
                    System.out.println();
                    System.out.println(values.getLdaCalculation());
                    System.out.println(values.getPreviousLda());

                } else if (option.equals("3")) {
                    Calculation values = runway.calculateLandingTowards(logical, mod.getAirport());
                    System.out.println(values.getToraCalculation());
                    System.out.println(values.getPreviousTora());
                    System.out.println();
                    System.out.println(values.getTodaCalculation());
                    System.out.println(values.getPreviousToda());
                    System.out.println();
                    System.out.println(values.getAsdaCalculation());
                    System.out.println(values.getPreviousAsda());
                    System.out.println();
                    System.out.println(values.getLdaCalculation());
                    System.out.println(values.getPreviousLda());

                } else if (option.equals("4")) {
                    Calculation values = runway.calculateLandingOver(logical, mod.getAirport());
                    System.out.println(values.getToraCalculation());
                    System.out.println(values.getPreviousTora());
                    System.out.println();
                    System.out.println(values.getTodaCalculation());
                    System.out.println(values.getPreviousToda());
                    System.out.println();
                    System.out.println(values.getAsdaCalculation());
                    System.out.println(values.getPreviousAsda());
                    System.out.println();
                    System.out.println(values.getLdaCalculation());
                    System.out.println(values.getPreviousLda());

                } else if (option.equals("5")) {
                    flag = false;

                } else if (option.equals("6")) {
                    scanner.close();
                    System.out.println("**QUITTING**");

                    try {
                        XMLParser.saveDataToFileAsXML("test.xml", mod);
                    } catch (Exception e) {
                        System.out.println("SAVE FAILED");
                    }

                    try {
                        XMLParser.getXMLDataFromFile("test.xml");
                        System.out.println("LOADED!");
                    } catch (Exception e) {
                        System.out.println("LOAD FAILED");
                    }


                    System.exit(0);

                }

            }




      /*
        LogicalRunway logical = mod.addLogicalRunway(3660,3660,3660,3353, 9,"R",0,0,307);


        HashMap<String,Number> map = new HashMap<>();
        map.put("9R",2853);
        map.put("27R",3646);
        runway.addObstacle(map,25,0);

        HashMap<String,Number> values = runway.calculateTakeOffTowards(logical,mod.getAirport());
        for(String val : values.keySet()){
            System.out.println(val+": "+values.get(val));
        }



        //System.out.println(runway.calculateLandingOver(logical));


     */


        }
    }
}


