import java.util.ArrayList;

/**
 * The class represent a process that contain a collection of task
 *
 */
public class Process {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	public Process(){
		// Setting up the process
		Task t =new Task(0,1);
		t.addSuccessor(1);
		tasks.add(t);
		t =new Task(1,3);
		t.addSuccessor(5);
		t.addSuccessor(6);
		t.addSuccessor(9);
		tasks.add(t);
		t = new Task(2,4);
		t.addSuccessor(7);
		tasks.add(t);
		t = new Task(3,9);
		t.addSuccessor(9);
		tasks.add(t);
		t = new Task(4,1);
		t.addSuccessor(7);
		t.addPredecessor(0);
		tasks.add(t);
		t = new Task(5,2);
		t.addSuccessor(8);
		t.addPredecessor(1);
		tasks.add(t);
		t = new Task(6,5);
		t.addSuccessor(8);
		t.addPredecessor(1);
		tasks.add(t);
		t = new Task(7,3);
		t.addSuccessor(8);
		t.addPredecessor(2);
		t.addPredecessor(4);
		tasks.add(t);
		t = new Task(8,2);
		t.addPredecessor(5);
		t.addPredecessor(6);
		t.addPredecessor(7);
		tasks.add(t);
		t = new Task(9,4);
		t.addPredecessor(1);
		tasks.add(t);
	}
	
	/**
	 * Calculate the the topology level of each task.
	 */
	public void calculateTopology(){
		int finished = 0;
		while(finished<tasks.size()){
			//finished =0;
			for(int i =0; i<tasks.size();i++){
				if (tasks.get(i).topologyLevel() == 0){
					if (tasks.get(i).predecessorID().size() == 0){
						tasks.get(i).setTopologyLevel(1);
					}
					else{
						int cnt =0;
						int maxTopology =0;
						for(int j =0; j<tasks.get(i).predecessorID().size();j++){
							if (tasks.get(tasks.get(i).predecessorID().get(j).intValue()).topologyLevel()!=0){
								cnt++;
								if (maxTopology<tasks.get(tasks.get(i).predecessorID().get(j).intValue()).topologyLevel()){
									maxTopology = tasks.get(tasks.get(i).predecessorID().get(j).intValue()).topologyLevel();
								}
							}
							if (cnt == tasks.get(i).predecessorID().size()){
								tasks.get(i).setTopologyLevel(maxTopology+1);
							}
						}
					}
				}
				else{
					finished++;
				}
				
			}
		}	
	}
	public int numberOfTask(){
		return tasks.size();
	}
	/**
	 * Extract the highest topology level of the process.
	 * @return The highest topology level
	 */
	public int maxTopologyLevel(){
		calculateTopology();
		int max =0;
		for (int i=0;i<tasks.size();i++){
			if (max<tasks.get(i).topologyLevel()){
				max =tasks.get(i).topologyLevel();
			}
		}
		return max;
	}
	/**
	 * Extract the task with specified ID
	 * @param id  ID of the desired task
	 * @return the desired task
	 */
	public Task getTask(int id)
	{
		return tasks.get(id);
	}
	/**
	 * Extract the number of task at a specified topology level.
	 * @param level  The input topology level
	 * @return The number of task at the input level
	 */
	public int numberOfTasksAtLevel(int level){
		int cnt =0;
		for (int i=0;i<tasks.size();i++){
			if (tasks.get(i).topologyLevel() == level){
				cnt++;
			}
		}
		return cnt;
	}
	/**
	 * Extract the array contain the IDs of the task at the specified topology level.
	 * @param level  Input topology level.
	 * @return the array of ID.
	 */
	public int[] tasksIDAtLevel(int level){
		int[] id = new int[numberOfTasksAtLevel(level)];
		int j =0;
		for (int i = 0; i<tasks.size(); i++){
			if (tasks.get(i).topologyLevel() == level){
				id[j]= i;
				j++;
			}
		}
		return id;
	}
	
	public void printTask(){
		for(int i =0; i<tasks.size();i++){
			System.out.println(tasks.get(i).ID()+"\t"+tasks.get(i).topologyLevel());
		}
	}
	
	/**
	 * Checking the earliest time that the task can start the execution basing on the finish time of the predecessors.
	 * @param id  The ID of the task that need to be check
	 * @return The earliest start time of the task with the input ID.
	 */
	public double earliestStartTime(int id){
		double time=0;
		if (tasks.get(id).topologyLevel()==1){
			return 0;
		}
		for (int i =0; i< tasks.get(id).predecessorID().size();i++){
			int predecessorID = tasks.get(id).predecessorID().get(i);
			if (tasks.get(predecessorID).finishTime()>time){
				time = tasks.get(predecessorID).finishTime();
			}
		}
		return time;
	}
}
