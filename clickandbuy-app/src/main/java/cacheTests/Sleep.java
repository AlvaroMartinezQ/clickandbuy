package cacheTests;

public class Sleep {
	
	/*
	 * This class is used as a timeout.
	 * Demonstrates:
	 * -> Non previously accessed methods will wait (if used) the time in ms passed to the sleep function
	 * -> Previously accessed methods will load asap thanks to the internal spring cache
	 */
	
	public Sleep() {
		super();
	}
	
	public void sleep (int time) {
		try {
			Thread.sleep(time); // time in ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
