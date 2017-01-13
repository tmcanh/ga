/**
 * Main class
 * @author MinhCanh
 *
 */
public class Test {

	public static void main(String[] args) {
		/*Process process = new Process();
		Schedule schedule = new Schedule();
		process.calculateTopology();
		process.printTask();
		System.out.println("max level: "+ process.maxTopologyLevel());
		System.out.println("level 1: "+ process.numberOfTasksAtLevel(1));
		System.out.println("level 2: "+ process.numberOfTasksAtLevel(2));
		System.out.println("level 3: "+ process.numberOfTasksAtLevel(3));
		System.out.println("level 4: "+ process.numberOfTasksAtLevel(4));
		int[] id = new int[process.numberOfTasksAtLevel(1)];
		id = process.tasksIDAtLevel(1);
		for (int i = 0; i<id.length;i++){
			System.out.print(id[i] + " ");
		}
		System.out.println();
		id = new int[process.numberOfTasksAtLevel(2)];
		id = process.tasksIDAtLevel(2);
		for (int i = 0; i<id.length;i++){
			System.out.print(id[i] + " ");
		}
		System.out.println();
		id = new int[process.numberOfTasksAtLevel(3)];
		id = process.tasksIDAtLevel(3);
		for (int i = 0; i<id.length;i++){
			System.out.print(id[i] + " ");
		}
		System.out.println();
		id = new int[process.numberOfTasksAtLevel(4)];
		id = process.tasksIDAtLevel(4);
		for (int i = 0; i<id.length;i++){
			System.out.print(id[i] + " ");
		}
		System.out.println();
		//schedule.test();
		schedule.setExample();
		schedule.execute();
		System.out.println(schedule.testString());
		System.out.println(schedule.makeSpan());
		System.out.println(schedule.energy());*/
		
		
		// Create the population of 30 individual and the dead time of 40
		Population population = new Population(40,60);
		population.intitalize();
		population.printBestSchedule();
		
		//go through 1 mil reproduction
		long i=0;
		//population.test();
		while (i<1000000){
			i++;
			population.reproduction();
			//population.printBestSchedule();
			//System.out.println(i);
		}
		population.printBestSchedule();
	}

}
