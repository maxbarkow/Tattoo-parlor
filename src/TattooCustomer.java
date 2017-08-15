//Max Barkow
//TA: Shelby Warnock
//Section Number: 1A64
public class TattooCustomer {

	
	/**The private strings ensure that the values cannot be changed out of this class and they are predefined
	 * 
	 */
	private String name ;
	private String tattoo;
	private int minutes;
	
	/**This constructor shows the paramters required to create a TatooCustomer object
	 * 
	 * @param name of customer
	 * @param tattoo information
	 * @param minutes required to make tattoo
	 */
	TattooCustomer(String name, String tattoo, int minutes){
		this.name= name;
		this.tattoo= tattoo;
		this.minutes= minutes;
	}
	/*Returns the name of the customer and has a getter method so it 
	 * can be called from the main method
	 */
	public String getName () {	
		
		return name;
		/*Returns the tattoo of the customer and has a getter method so it 
		 * can be called from the main method
		 */
	}
	public String getTattoo (){

		return tattoo;
		/*Returns the length to create the tattooand has a getter method so it 
		 * can be called from the main method
		 */
	}
	public int getMinutes (){

		return minutes;
	}
}	
