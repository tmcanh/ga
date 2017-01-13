import java.util.Random;
/**
 * The class of the schedule object that represent a scheduled process to a multiple core system
 * Each schedule object will be an individual in the genetic algorithm population
 *
 */
public class Schedule {
	private String testString = "taskID\tcoreID\tstart\tfinish\tpower\n";
	private int numberOfCore =3;
	private Process process = new Process();
	/**
	 * DNA dedicating for the task allocation and priority of the task in one topology level
	 * each topology level have one scheduling DNA
	 */
	private TaskSchedulingDNA[] taskSchedulingDNA = new TaskSchedulingDNA[process.maxTopologyLevel()];
	/**
	 * The DNA for the power level of each core
	 */
	private PowerLevelDNA powerLevelDNA = new PowerLevelDNA(numberOfCore);
	private double makeSpan =0;
	private double energy = 0;
	private Processor[] core = new Processor[numberOfCore];
	private Random random = new Random();
	/**
	 * constructing the object
	 */
	public Schedule (){
		for (int i =0; i<process.maxTopologyLevel(); i++){
			taskSchedulingDNA[i] = new TaskSchedulingDNA(process.tasksIDAtLevel(i+1).length,numberOfCore);
		}
		for (int i =0; i<numberOfCore; i++){
			core[i] = new Processor(i);
		}
	}
	/**
	 * initialize a schedule with random DNA
	 */
	public void initialize(){
		for (int i =0; i< taskSchedulingDNA.length;i++){
			taskSchedulingDNA[i].intializeDNA();
		}
		powerLevelDNA.initalize();
	}
	public void test(){
		System.out.println(taskSchedulingDNA.length);
	}
	public void setExample(){
		int[] core = new int[]{0,1,2,0};
		int[] priority=new int[]{1,2,3,4};
		taskSchedulingDNA[0].setExample(core,priority);
		core = new int[]{1,2,0,1};
		taskSchedulingDNA[1].setExample(core,priority);
		core=new int[]{2};
		priority = new int[]{1};
		taskSchedulingDNA[2].setExample(core,priority);
		core=new int[]{0};
		taskSchedulingDNA[3].setExample(core,priority);
		powerLevelDNA.setExample();
	}
	/** 
	 * @return task scheduling DNA
	 */
	public TaskSchedulingDNA[] getTaskSchedulingDNA(){
		return taskSchedulingDNA;
	}
	
	/**
	 * Set the task scheduling DNA array
	 * @param in  input DNA array
	 */
	public void setTaskSchedulingDNA(TaskSchedulingDNA[] in){
		taskSchedulingDNA =in;
	}
	
	/**
	 * 
	 * @return power level DNA
	 */
	public PowerLevelDNA getPowerLevelDNA(){
		return powerLevelDNA;
	}
	
	/**
	 * set the power level DNA
	 * @param in the input DNA
	 */
	public void setPowerLevelDNA(PowerLevelDNA in){
		powerLevelDNA = in;
	}
	
	/**
	 * Calculate the execution of the process basing on the DNA
	 * Conducting the make span and energy consumption 
	 */
	public void execute(){
		double startTime=0;
		for (int i =0; i<process.maxTopologyLevel(); i++){
			for (int k =0; k < process.tasksIDAtLevel(i+1).length;k++){
				for (int j =0; j < process.tasksIDAtLevel(i+1).length;j++){
					if ((taskSchedulingDNA[i].getGenesPriority()[j])==(k+1)){
						int taskID = process.tasksIDAtLevel(i+1)[j];
						double finishTime =0;
						int coreID = taskSchedulingDNA[i].getGenesCore()[j];
						if (core[coreID].currentTime()<process.earliestStartTime(taskID)){
							finishTime = process.earliestStartTime(taskID);
						}
						else{
							finishTime = core[coreID].currentTime();
						}
						startTime = finishTime;
						finishTime +=  process.getTask(taskID).weight()/powerLevelDNA.getGenes()[coreID];
						process.getTask(taskID).completeAt(finishTime);
						core[coreID].setCurrentTime(finishTime);
						double a=process.getTask(taskID).weight() * powerLevelDNA.getVoltage()[coreID] *powerLevelDNA.getVoltage()[coreID];
						energy += process.getTask(taskID).weight() * powerLevelDNA.getVoltage()[coreID] *powerLevelDNA.getVoltage()[coreID];
						//System.out.println(taskID+"\t"+coreID+"\t"+startTime+"\t"+finishTime+"\t"+a);
						testString += taskID+"\t"+coreID+"\t"+startTime+"\t"+finishTime+"\t"+a+"\n";
					}
				}
			}
		}
		testString +="\nmakespan\tconsumption\n";
		testString += makeSpan() + "\t\t" + energy+"\n______________________________________\n";
	}
	public double energy(){
		return energy;
	}
	public String testString(){
		return testString;
	}
	
	public double makeSpan(){
		double max =0;
		for (int i =0; i< core.length;i++){
			if (core[i].currentTime()>max){
				max =core[i].currentTime();
			}
			//System.out.print(core[i].currentTime()+" ");
		}
		//System.out.println();
		return max;
	}
	public double fitness(){
		double score =0;
		for (int i=0; i< process.numberOfTask();i++){
			score += process.getTask(i).weight();
		}
		score = score * 25;
		score = 1 - (score/energy());
		return score;
	}
	/**
	 * First try. Please don't care about this one
	 */
	public Schedule crossover(Schedule partner,double mutateRate){
		Schedule child= new Schedule();
		if (random.nextDouble()<mutateRate){
			child.initialize();
		}
		else{
			child.setTaskSchedulingDNA(taskSchedulingDNA);
			child.setPowerLevelDNA(partner.getPowerLevelDNA());
		}
		return child;
	}
	
	/**
	 * conducting the crossover process of the genetic algorithm. Verssion 2
	 * @param partner   the parter object that is crossover with
	 * @param mutateRate  The mutation rate of crossover
	 * @return the child schedule
	 */
	public Schedule crossover2(Schedule partner,double mutateRate){
		Schedule child= new Schedule();
		if (random.nextDouble()<mutateRate){
			child.initialize();
		}
		else{
			child.initialize();
			int midpoint = random.nextInt(numberOfCore+1);
			double[] genesA = getPowerLevelDNA().getGenes();
			double[] voltageA = getPowerLevelDNA().getVoltage();
			double[] genesB = partner.getPowerLevelDNA().getGenes();
			double[] voltageB = partner.getPowerLevelDNA().getVoltage();
			for (int i =midpoint; i<genesA.length;i++){
				genesA[i]=genesB[i];
				voltageA[i]=voltageB[i];
			}
			child.getPowerLevelDNA().setGenes(genesA);
			child.getPowerLevelDNA().setVoltage(voltageA);
			int a;
			TaskSchedulingDNA[] childDNA = new TaskSchedulingDNA[process.maxTopologyLevel()];
			for (int i=0; i<process.maxTopologyLevel();i++){
				a = random.nextInt(2);
				if (a == 0){
					childDNA[i]=taskSchedulingDNA[i];
				}
				else{
					childDNA[i]= partner.getTaskSchedulingDNA()[i];
				}
			}
			child.setTaskSchedulingDNA(childDNA);
		}
		return child;
	}
	
}
