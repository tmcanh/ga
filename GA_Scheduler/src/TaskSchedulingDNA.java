import java.util.Random;

/**
 * The DNA that define the core that the task is executed on and the scheduling priority
 *
 */
public class TaskSchedulingDNA {
	private int[] genesCore;
	private int[] genesPriority;
	private int numberOfCore;
	private Random random = new Random();
	public TaskSchedulingDNA(int numberOfTask, int numberOfCoreIn){
		genesCore = new int[numberOfTask];
		genesPriority = new int[numberOfTask];
		numberOfCore = numberOfCoreIn;
		//System.out.println(genesCore.length);
	}
	public void intializeDNA(){
		boolean duplicated = true;
		for(int i = 0; i < genesCore.length; i++){
			genesCore[i]= random.nextInt(numberOfCore);
			duplicated = true;
			while(duplicated){
				duplicated = false;
				genesPriority[i] = random.nextInt(genesCore.length)+1;
				for(int j =0; j< i; j++ ){
					if (genesPriority[i] == genesPriority[j]){
						duplicated = true;
					}
				}
			}
		}
		/*for(int i = 0; i < genesCore.length; i++){
			System.out.print(genesPriority[i]+" ");
		}
		System.out.println();*/
	}
	public void setExample(int[] core, int[] priority){
		genesCore = core;
		genesPriority = priority;
	}
	public int[] getGenesPriority(){
		return genesPriority;
	}
	public int[] getGenesCore(){
		return genesCore;
	}
}
