import java.util.concurrent.TimeUnit;

/**
 * @author A. Tzani
**/


public class TimeDelay {

/**
 * Add time delay at the execution of the code
 * @param delay the desirable delay time in seconds
 **/
	
	public TimeDelay(int delay) throws InterruptedException {
		
		TimeUnit.SECONDS.sleep(delay);
		
	}

}
