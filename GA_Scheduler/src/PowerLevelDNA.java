import java.util.Random;

/**
 * This is the the class for the DNA object that define the power level of the cell
 * 
 *
 */
public class PowerLevelDNA {
	private Random random = new Random();
	private double[] genes;		// the array of the core frequency
	private double[] voltage;	// the array of the operating voltage
	/**
	 * Constructor of the object
	 * @param numberOfCore The number of core in the system
	 */
	public PowerLevelDNA(int numberOfCore){
		genes= new double[numberOfCore];
		voltage = new double[numberOfCore];
	}
	/**
	 * set example for testing
	 */
	public void setExample(){
		genes= new double[3];
		genes[0]=1;
		genes[1]=1;
		genes[2]=0.5;
		voltage = new double[]{5,5,2.5};
	}
	/**
	 * get the array of the core frequency
	 * @return the genes array
	 */
	public double[] getGenes(){
		return genes;
	}
	public double[] getVoltage(){
		return voltage;
	}
	/**
	 * Initialize a random DNA
	 */
	public void initalize(){
		for (int i =0; i< genes.length;i++){
			int j = random.nextInt(10);
			genes[i] = 1-(j * 0.1);
			voltage[i] = 5-(j*0.5);
		}
	}
	
	/**
	 * set the genes array
	 * @param in  The input array
	 */
	public void setGenes(double[] in){
		genes = in;
	}
	/**
	 * set the voltage array
	 * @param in  The input array
	 */
	public void setVoltage(double[] in){
		voltage = in;
	}
}
