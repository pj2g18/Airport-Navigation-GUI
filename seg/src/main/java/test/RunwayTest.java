package test;

//import static org.junit.jupiter.api.Assertions.*;
//import model.*;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;

class RunwayTest {

	
    /**
 	 * 	Method:  public Runway(Number tora, Number toda, Number asda, Number lda){
 	 */
 	//error testing
	@Test
	void constructorNegtest() {
	    /*
		Runway run1 = new Runway(-1,1,1,1);
		Runway run2 = new Runway(1,-1,1,1);
		Runway run3 = new Runway(1,1,-1,1);
		Runway run4 = new Runway(1,1,1,-1);
		*/

		
		//however system fails a runway
		//assertEquals(run1,run2);
	}
	
	@Test
	void constructorZerotest() {
	    /*
		Runway run1 = new Runway(0,1,1,1);
		Runway run2 = new Runway(1,0,1,1);
		Runway run3 = new Runway(1,1,0,1);
		Runway run4 = new Runway(1,1,1,0);

	     */
		
		//however system fails a runway
		//assertEquals(run1,run2);
	}

}
