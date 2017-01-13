import java.util.ArrayList;
import java.util.Random;

/**
 * Class of the task
 */
public class Task {
	private int ID;							//ID of the task, start from 0
	private static int numberOfTask =0;
	private ArrayList<Integer> predecessorID = new ArrayList<Integer>();
	private ArrayList<Integer> successorID = new ArrayList<Integer>	();
	private int weight;
	private int topologyLevel = 0;
	private boolean finished = false;
	private double finishTime = 0;
	
	/**
	 * initializing task object.
	 * 
	 * @param id the ID of the task
	 * @param w the processing weight of the task
	 */
	public Task(int id, int w){
		ID =id;
		weight= w;
		numberOfTask++;
	}
	
	/**
	 * Add the ID of a successor task to the successor list.
	 * 
	 * @param id   ID of the successor
	 */
	public void addSuccessor(int id){
		successorID.add(new Integer(id));
	}
	
	/**
	 * Add the ID of a successor task to the predecessor list.
	 * 
	 * @param id   ID of the predecessor
	 */
	public void addPredecessor(int id){
		predecessorID.add(new Integer(id));
	}
	
	/**
	 * 
	 * @return ID of the task
	 */
	public int ID(){
		return ID;
	}
	
	/**
	 * Get the list of predecessor
	 * @return Array list of predecessor
	 */
	public ArrayList<Integer> predecessorID(){
		return predecessorID;
	}
	
	/**
	 * Get the list of successor
	 * @return Array list of successor
	 */
	public ArrayList<Integer> successorID(){
		return successorID;
	}
	/**
	 * Set the topology level of the task
	 * @param level  The topology level of the task
	 */
	public void setTopologyLevel(int level){
		topologyLevel = level;
	}
	
	/**
	 * 
	 * @return processing weight of the task
	 */
	public int weight(){
		return weight;
	}
	/**
	 * 
	 * @return Topology level of the task
	 */
	public int topologyLevel(){
		return topologyLevel;
	}
	/**
	 * set the complete status of the task
	 * @param time the finish time of the the task
	 */
	public void completeAt(double time){
		finished = true;
		finishTime = time;
	}
	/**
	 * Checking if the task is executed
	 * @return boolean variable of finishing status
	 */
	public boolean isFinished(){
		return finished;
	}
	/**
	 * get the finish time of the task.
	 * @return
	 */
	public double finishTime(){
		return finishTime;
	}
	
}
