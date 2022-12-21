package test;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
class LogicalRunwayTest {


    /**
     * 	Method:  public Runway(Number tora, Number toda, Number asda, Number lda){
     */
    //error testing
    // LogicalRunway(String tora, String toda, String asda, String lda, String heading, Character letter, String stopway, String clearway, String displacedThreshold)



    //displaced threshold has to be smaller than lda
    //lda - displaced threshold has to be less than tora
    //lda cant be larger than tora






    @Test
    //id 1a
    public void testDisplacedThresholdWithLda1() throws Exception {
        //tests change in lda when displaced threshold changes
        LogicalRunway displacedLDA1 = new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "0", "0");
        assertEquals(1, displacedLDA1.getLDA());
        assertEquals(0,displacedLDA1.getDisplacedThreshold());
    }
    @Test
    //id 1b
    public void testDisplacedThresholdWithLda2() throws Exception {
        //tests change in lda when displaced threshold changes
        LogicalRunway displacedLDA2 = new LogicalRunway("2", "3", "3", "2", "1", 'L', "1", "1", "1");
        assertEquals(2, displacedLDA2.getLDA());
        assertEquals(1,displacedLDA2.getDisplacedThreshold());
    }
    @Test
    //id 1c
    public void testConstructorASDA() throws Exception {
        //test asda change when stopway changes
        LogicalRunway asdaTODA1 = new LogicalRunway("2", "3", "2", "1", "1", 'L', "0", "1", "0");
        assertEquals(2,asdaTODA1.getASDA());
        assertEquals(0,asdaTODA1.getStopway());
    }
    @Test
    //id 1d
    public void testConstructorToda() throws Exception {
        //test asda change when stopway changes
        LogicalRunway asdaTODA1 = new LogicalRunway("2", "3", "3", "1", "1", 'L', "1", "1", "0");
        assertEquals(3,asdaTODA1.getTODA());
        assertEquals(1,asdaTODA1.getClearway());
    }
    @Test
    //id 1e
    public void testInvalidConstructorToda() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway asdaTODA1 = new LogicalRunway("2", "2", "3", "1", "1", 'L', "1", "0", "0");

        });
    }

    @Test
    //id 2
    public void testToraBoundary() throws Exception {
        LogicalRunway logic1 = new LogicalRunway(String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MAX_VALUE), "1", "1", 'L', "0", "0", "0");
        assertEquals(Integer.MAX_VALUE,logic1.getTORA() );
    }


    @Test
    //id 3a
    public void testTooLargeAsda() throws Exception {

        LogicalRunway exampleRunway = new LogicalRunway();
        exampleRunway.setTORA(Integer.MAX_VALUE);
        Assertions.assertThrows(Exception.class, () -> {
            exampleRunway.setStopway(1);
            exampleRunway.updateASDA();
        });
    }

    @Test
    //id 3b
    public void testTooLargeToda() throws Exception {
        LogicalRunway exampleRunway = new LogicalRunway();
        exampleRunway.setTORA(Integer.MAX_VALUE);
        exampleRunway.setLDA(1);
        exampleRunway.setDisplacedThreshold(1);
        exampleRunway.setHeading(1);
        Assertions.assertThrows(Exception.class, () -> {
            exampleRunway.setClearway(1);
            exampleRunway.updateTODA();
        });
    }


    @Test
    //id 4a
    public void testInvalidLogicalRunway1() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway logic = new LogicalRunway("0", "1", "1", "1", "1", 'L', "0", "0", "0");
        });
    }

    @Test
    //id 4b
    public void testInvalidLogicalRunway2() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway logic = new LogicalRunway("1", "0", "1", "1", "1", 'L', "0", "0", "0");
        });
    }

    @Test
    //id 4c
    public void testInvalidLogicalRunway3() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway logic = new LogicalRunway("1", "1", "2", "1", "1", 'L', "0", "0", "0");
        });
    }

    @Test
    //id 4d
    public void testInvalidLogicalRunway4() throws Exception {

            LogicalRunway logic = new LogicalRunway("1", "1", "1", "1", "1", 'L', "1", "0", "0");
        assertEquals(2, logic.getASDA());
    }
    @Test
    //id 4e
    public void testInvalidLogicalRunway5() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway logic = new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "1", "0");
        });
    }
    @Test
    //id 4f
    public void testInvalidLogicalRunway6() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway logic = new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "0", "1");
        });
    }
    @Test
    //id 4g
    public void testInvalidLogicalRunway7() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway logic = new LogicalRunway("1", "1", "1", "0", "1", 'L', "0", "0", "1");
        });
    }
    @Test
    //id 4h
    public void testInvalidLogicalRunway8() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            LogicalRunway logic = new LogicalRunway("3", "3", "3", "0", "1", 'L', "0", "0", "-1");
        });
    }



    @Test
    //id 5a
    public void testInvalidDisplacedThreshold() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> {
            logic.setDisplacedThreshold(-1);
        });
    }
    @Test
    //id 5b
    public void testInvalidClearway() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> {
            logic.setClearway(-1);
        });
    }

    @Test
    //id 5c
    public void testInvalidStopway() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> {
            logic.setStopway(-1);
        });
    }

    @Test
    //id 6a
    public void testValidCombinedReference1() throws Exception {
        LogicalRunway reference = new LogicalRunway();
        reference.setCombinedReference("01L");
        assertEquals("01L", reference.getCombinedReference());



    }

    @Test
    //id 6b
    public void tetValidCombinedReference2() throws Exception {
        LogicalRunway reference = new LogicalRunway();
        reference.setCombinedReference("36R");
        assertEquals("36R", reference.getCombinedReference());
    }

    @Test
    //id 6c
    public void onlyLetterCombRef() throws Exception {
        LogicalRunway reference = new LogicalRunway();

        Assertions.assertThrows(Exception.class, () -> {
            reference.setCombinedReference("PL");
        });
       ;
    }
    @Test
    //id 6d
    public void onlyNumberCombRef() throws Exception {
        LogicalRunway reference = new LogicalRunway();

        Assertions.assertThrows(Exception.class, () -> {
            reference.setCombinedReference("015");
        });
    }

    @Test
    //id 6e
    public void noCombRef() throws Exception {
        LogicalRunway reference = new LogicalRunway();

        Assertions.assertThrows(Exception.class, () -> { reference.setCombinedReference("0"); });

    }
    @Test
    //id 6f
    public void wrongLetterCombRef() throws Exception {
        LogicalRunway reference = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> { reference.setCombinedReference("01P"); });

    }
    @Test
    //id 6g
    public void wrongNumberCombRef() throws Exception {
        LogicalRunway reference = new LogicalRunway();

        Assertions.assertThrows(Exception.class, () -> { reference.setCombinedReference("PL"); });
    }

    @Test
    //id 6h
    public void noLetterCombRef() throws Exception {
        LogicalRunway reference = new LogicalRunway();

        Assertions.assertThrows(Exception.class, () -> { reference.setCombinedReference("01"); });
    }
    @Test
    //id 6i
    public void noNumberCombRef() throws Exception {
        LogicalRunway reference = new LogicalRunway();

        Assertions.assertThrows(Exception.class, () -> { reference.setCombinedReference("R"); });
    }



    @Test
    //id 7a
    public void testValidCalculateTakeOffAway() throws Exception {

        LogicalRunway runway = new LogicalRunway("5", "6", "6", "1", "1", 'R', "1", "1", "0");
        Airport airport = new Airport("2","1","air");


        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);



        assertEquals("1 (R TORA) = 5 (TORA) - 1 (Distance from Threshold) - 0 (displaced threshold) - 2 (RESA) - 1 (Strip End)", runway.calculateTakeOffAway(obstacle, airport).getToraCalculation());

    }

    @Test
    //id 7b
    public void testValidCalculateTakeOffAway2() throws Exception {

        LogicalRunway runway2 = new LogicalRunway("5", "6", "6", "1", "1", 'R', "1", "1", "0");
        Airport airport2 = new Airport("2","1","air");


        HashMap<String, Number> map2 = new HashMap<String, Number>();
        map2.put("01R", 1);
        Obstacle obstacle2 = new Obstacle(map2, 1, 2);

        assertEquals(2 +" (R TORA) = " + 5 + " (TORA) - " + 1 + " (Distance from Threshold) - " + 0 + " (displaced threshold) - " + 2 + " (Blast Protection)",  runway2.calculateTakeOffAway(obstacle2, airport2).getToraCalculation());
    }


    @Test
    //id 7c
    public void testBoundaryCalculateTakeoffAway() throws Exception {
        Model mod = new Model();
        mod.addAirport("1","2","air");

        LogicalRunway runway = new LogicalRunway(String.valueOf(Integer.MAX_VALUE),String.valueOf(Integer.MAX_VALUE),String.valueOf(Integer.MAX_VALUE),"1","01",'R',"0","0","0");

        mod.addRunway(new Runway());


        Airport airport = new Airport();
        airport.setResa(2);
        airport.setStripEnd(1);

        runway.setCombinedReference("01R");


        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);

        assertEquals((Integer.MAX_VALUE-4) +" (R TORA) = " + (Integer.MAX_VALUE) + " (TORA) - " + 1 + " (Distance from Threshold) - " + 0 + " (displaced threshold) - " + 2 + " (RESA) - " + 1 + " (Strip End)",  runway.calculateTakeOffAway(obstacle, airport).getToraCalculation());


    }

    @Test
    //id 7d
    public void testBoundaryCalculateTakeOfAway2() throws Exception {
        LogicalRunway runway2 = new LogicalRunway("4", "5", "5", "1", "1", 'R', "1", "1", "0");
        Airport airport2 = new Airport();
        airport2.setResa(2);
        HashMap<String, Number> map2 = new HashMap<String, Number>();

        airport2.setStripEnd(1);
        runway2.setCombinedReference("01R");

        map2.put("01R", 1);
        Obstacle obstacle2 = new Obstacle(map2, 1, 1);

        assertEquals(0 +" (R TORA) = " + 4 + " (TORA) - " + 1 + " (Distance from Threshold) - " + 0 + " (displaced threshold) - " + 2 + " (RESA) - " + 1 + " (Strip End)",  runway2.calculateTakeOffAway(obstacle2, airport2).getToraCalculation());
    }

    @Test
    //id 7e
    public void testNegativeCalculateTakeoffAway1() throws Exception {
        LogicalRunway runway = new LogicalRunway("3", "4", "4", "1", "1", 'R', "1", "1", "0");
        Airport airport = new Airport();
        airport.setResa(2);
        airport.setStripEnd(1);
        runway.setCombinedReference("01R");

        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        assertEquals(-1, runway.calculateTakeOffAway(obstacle, airport).getTora());
        assertEquals(null, runway.calculateTakeOffAway(obstacle, airport).getBlastProtection());
        assertEquals(1, runway.calculateTakeOffAway(obstacle, airport).getStripEnd());
        assertEquals(2, runway.calculateTakeOffAway(obstacle, airport).getResa());


    }

    @Test
    // 7f
    public void testNegativeTakeOffAWay2() throws Exception {
        LogicalRunway runway2 = new LogicalRunway("4", "5", "5", "1", "1", 'L', "1", "1", "0");
        Airport airport2 = new Airport();
        airport2.setResa(2);
        airport2.setStripEnd(1);

        HashMap<String, Number> map2 = new HashMap<String, Number>();
        map2.put("01R", 1);
        Obstacle obstacle2 = new Obstacle(map2, 1, 4);
        runway2.setCombinedReference("01R");

        assertEquals(-1,runway2.calculateTakeOffAway(obstacle2,airport2).getTora() );
        assertEquals(4,runway2.calculateTakeOffAway(obstacle2,airport2).getBlastProtection() );
        assertEquals(null,runway2.calculateTakeOffAway(obstacle2,airport2).getStripEnd() );
        assertEquals(null,runway2.calculateTakeOffAway(obstacle2,airport2).getResa() );


    }
    @Test
    //8a
    public void testValidLandingTowards() throws Exception {

    //    int distance = obstacle.getObstaclePosition().get(combinedReference).intValue();

     //   int calc = distance - (airport.getResa().intValue()) - (airport.getStripEnd().intValue());
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("1", "1", "name");
        LogicalRunway logic = new LogicalRunway("4", "5", "5", "1", "1", 'L', "1", "1", "0");
logic.setCombinedReference("01R");
        assertEquals(-1,logic.calculateLandingTowards(obstacle, airport).getLda());
        assertEquals(1,logic.calculateLandingTowards(obstacle, airport).getStripEnd());
        assertEquals(1,logic.calculateLandingTowards(obstacle, airport).getResa());

    }
    @Test
    //id 8b
    public void testBoundaryLandingTowards() throws Exception {

        //    int distance = obstacle.getObstaclePosition().get(combinedReference).intValue();

        //   int calc = distance - (airport.getResa().intValue()) - (airport.getStripEnd().intValue());
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("1", "0", "name");

        LogicalRunway logic = new LogicalRunway("4", "5", "5", "1", "1", 'L', "1", "1", "0");
        logic.setCombinedReference("01R");

        assertEquals(0,logic.calculateLandingTowards(obstacle, airport).getLda());
        assertEquals(0,logic.calculateLandingTowards(obstacle, airport).getStripEnd());
        assertEquals(1,logic.calculateLandingTowards(obstacle, airport).getResa());
    }
    @Test
    //id 8c
    public void testNegativeLandingTowards() throws Exception {

        //    int distance = obstacle.getObstaclePosition().get(combinedReference).intValue();

        //   int calc = distance - (airport.getResa().intValue()) - (airport.getStripEnd().intValue());
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("3", "1", "name");

        LogicalRunway logic = new LogicalRunway("4", "5", "5", "1", "1", 'L', "1", "1", "0");
        logic.setCombinedReference("01R");

        assertEquals(-3,logic.calculateLandingTowards(obstacle, airport).getLda());
        assertEquals(1,logic.calculateLandingTowards(obstacle, airport).getStripEnd());
        assertEquals(3,logic.calculateLandingTowards(obstacle, airport).getResa());
    }


    @Test
    //id 9a
    public void testValidLandingOver1() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("51", "1", "name");
        LogicalRunway logic = new LogicalRunway("53", "53", "53", "53", "01", 'R', "0", "0", "0");

        assertEquals(0, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(51, logic.calculateLandingOver(obstacle,airport).getResa());
    }
    @Test
    //id 9b
    public void testNegativeLandingOver1() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("51", "1", "name");
        LogicalRunway logic = new LogicalRunway("51", "51", "51", "51", "01", 'R', "0", "0", "0");

        assertEquals(-2, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(51, logic.calculateLandingOver(obstacle,airport).getResa());

    }
    @Test
    //id 9c
    public void testBoundaryLandingOver1() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("51", "1", "name");
        LogicalRunway logic = new LogicalRunway("52", "52", "52", "52", "01", 'R', "0", "0", "0");

        assertEquals(-1, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(51, logic.calculateLandingOver(obstacle,airport).getResa());
    }
    @Test
    //id 9d
    public void testValidLandingOver2() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 53);
        Airport airport = new Airport("51", "1", "name");
        LogicalRunway logic = new LogicalRunway("55", "55", "55", "55", "01", 'R', "0", "0", "0");

        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getResa());

    }
    @Test
    //id 9e
    public void testBoundaryLandingOver2() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 53);
        Airport airport = new Airport("51", "1", "name");
        LogicalRunway logic = new LogicalRunway("54", "54", "54", "54", "01", 'R', "0", "0", "0");

        assertEquals(0, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getResa());

    }

    @Test
    //id 9f
    public void testNegativeLandingOver2() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 53);
        Airport airport = new Airport("51", "1", "name");
        LogicalRunway logic = new LogicalRunway("53", "53", "53", "53", "01", 'R', "0", "0", "0");

        assertEquals(-1, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getResa());

    }

    @Test
    //id 9g
    public void testValidLandingOver3() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("1", "1", "name");
        LogicalRunway logic = new LogicalRunway("53", "53", "53", "53", "01", 'R', "0", "0", "0");

        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getResa());
        assertEquals(50, logic.calculateLandingOver(obstacle,airport).getSlope());


    }

    @Test
    //id 9h
    public void testBoundaryLandingOver3() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("1", "1", "name");
        LogicalRunway logic = new LogicalRunway("52", "52", "52", "52", "01", 'R', "0", "0", "0");

        assertEquals(0, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getResa());
        assertEquals(50, logic.calculateLandingOver(obstacle,airport).getSlope());


    }

    @Test
    //id 9i
    public void testNegativeLandingOver3() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obstacle = new Obstacle(map1, 1, 1);
        Airport airport = new Airport("1", "1", "name");
        LogicalRunway logic = new LogicalRunway("51", "51", "51", "51", "01", 'R', "0", "0", "0");

        assertEquals(-1, logic.calculateLandingOver(obstacle,airport).getLda());
        assertEquals(1, logic.calculateLandingOver(obstacle,airport).getStripEnd());
        assertEquals(null, logic.calculateLandingOver(obstacle,airport).getResa());
        assertEquals(50, logic.calculateLandingOver(obstacle,airport).getSlope());


    }

    @Test
    //id 10a
    public void testNegativeLDA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> {   logic.setLDA(-1);});

    }

    @Test
    //id 10b
    public void testValidLDA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setLDA(1);
        assertEquals(1, logic.getLDA());
    }
    @Test
    //id 10c
    public void testBoundaryLDA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setCombinedReference("01R");
        logic.setLDA(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, logic.getLDA());
    }
    @Test
    //id 11a
    public void testNegativeTODA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> {  logic.setTODA(-1); });


    }

    @Test
    //id 11b
    public void testValidTODA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setTODA(1);
    }
    @Test
    //id 11c
    public void testBoundaryTODA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setCombinedReference("01R");
        logic.setTODA(Integer.MAX_VALUE);
    }
    @Test
    //id 12a
    public void testNegativeASDA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> { logic.setASDA(-1); });


    }

    @Test
    //id 12b
    public void testValidASDA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setASDA(1);
    }
    @Test
    //id 12c
    public void testBoundaryASDA() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setCombinedReference("01R");

        logic.setASDA(Integer.MAX_VALUE);
    }

    @Test
    public void testValidHeading1() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setHeading(1);
    }
    @Test
    public void testBoundaryHeading1() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setHeading(1);
    }
    @Test
    public void testBoundaryHeading2() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setHeading(36);
    }
    @Test
    public void testBelowBoundaryHeading1() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> {  logic.setHeading(-1);  });


    }
    @Test
    public void testAboveBoundaryHeading1() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> { logic.setHeading(37); });

    }

    @Test
    public void testValidLetter1() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setLetter('L');
    }
    @Test
    public void testValidLetter2() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setLetter('R');
    }
    @Test
    public void testValidLetter3() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        logic.setLetter('C');
    }
    @Test
    public void testInvalidLetter1() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> {  logic.setLetter('P'); });

    }
    @Test
    public void testInvalidLetter2() throws Exception {
        LogicalRunway logic = new LogicalRunway();
        Assertions.assertThrows(Exception.class, () -> { logic.setLetter('1'); });

    }
}
