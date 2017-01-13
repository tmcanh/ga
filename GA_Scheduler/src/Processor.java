/**
 * Class of the processing core.
 */
public class Processor {
	private int ID;
	private static int numberOfProcessor =0;
	private double currentTime = 0;
	public Processor(int id){
		ID = id;
	}
	public int ID(){
		return ID;
	}
	public void setCurrentTime(double time){
		currentTime = time;
	}
	public double currentTime(){
		return currentTime;
	}
}
