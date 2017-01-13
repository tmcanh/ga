import java.util.ArrayList;
import java.util.Random;

/**
 * Population for the genetic algorithm
 *
 */
public class Population {
	private double mutateRate =0.2;
	private ArrayList<Schedule> schedule = new ArrayList<Schedule>();
	private Random random = new Random();
	int numberOfElement =0;
	double deadline =0;
	/**
	 * Creating the population.
	 * @param number  The number of individual in the population.
	 * @param deadLine  The deadline of the schedule.
	 */
	public Population(int number, double deadLine){
		numberOfElement = number;
		deadline = deadLine;
	}
	
	/**
	 * Initialize the population
	 */
	public void intitalize(){
		Schedule s;
		for (int i =0; i < numberOfElement; i++){
			// Generate the new schedule until it satisfies the deadline
			do{
				s = new Schedule();
				s.initialize();
				//s.setExample();
				s.execute();
			}
			while(s.makeSpan()>deadline);
			schedule.add(s);
		}
	}
	/**
	 * Extract the schedule at specified position
	 * @param id  The ID schedule
	 * @return The schedule object
	 */
	public Schedule getSchedule(int id){
		return schedule.get(id);
	}
	public void test(){
		for (int i =0; i < numberOfElement; i++){
			System.out.println(schedule.get(i).testString());
		}
	}
	/**
	 * Get the id of the schedule that have the maximum power consumption.
	 * @return ID of the schedule that have the maximum power consumption.
	 */
	public int getMaxConsumsion(){
		int id=0;
		for (int i =0; i < numberOfElement; i++){
			if (schedule.get(i).energy()>schedule.get(id).energy()){
				id =i;
			}
		}
		return id;
	}
	/**
	 * Get the id of the schedule that have the minimum power consumption.
	 * @return ID of the schedule that have the minimum power consumption.
	 */
	public int getMinConsumsion(){
		int id=0;
		for (int i =0; i < numberOfElement; i++){
			if (schedule.get(i).energy()<schedule.get(id).energy()){
				id =i;
			}
		}
		return id;
	}
	
	/**
	 * Execute one cycle of reproduction which generate a new child.
	 * Survival genetic algorithm. Every individual have the same chance for the reproduction.
	 * The schedule that consume most energy will be eliminated after each cycle of reproduction.
	 */
	public void reproduction(){
		int idA;
		int idB;
		Schedule partnerA;
		Schedule partnerB;
		Schedule child;
		idA = random.nextInt(numberOfElement);
		partnerA = schedule.get(idA);
		idB  = random.nextInt(numberOfElement);
		partnerB = schedule.get(idB);
		child = partnerA.crossover2(partnerB, mutateRate);
		child.execute();
		if (child.makeSpan()> deadline){
			return;
		}
		if (child.energy()<schedule.get(getMaxConsumsion()).energy()){
			schedule.set(getMaxConsumsion(), child);
		}
	}
	/**
	 * print the execution timing of the best schedule.
	 */
	public void printBestSchedule(){
		Schedule s = schedule.get(getMinConsumsion());
		System.out.println(s.testString());
	}
}
