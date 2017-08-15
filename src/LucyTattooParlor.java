
//Max Barkow
//TA: Shelby Warnock
//Section Number: 1A64

import java.util.Scanner; 

public class LucyTattooParlor {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);		

		int artistAmount = Integer.parseInt(args[0]);
		int waitlist = Integer.parseInt(args[1]);

		TattooCustomer lineup [][] = new TattooCustomer [artistAmount][waitlist];//initializes array based on parsed args comman line inputs//

		/**The main method consists of a while statement to check to see if boolean end is still false, meaning that 
		 * Print Waitlist has not been printed. If so, the loop starts with a welcome and prompts the user to press enter
		 * merely for asthetics. The user is asked to enter their name. And if statement checks if the name is Print Waitlist
		 * If it is, printList method is called. If not, it continues. An if else statement is used to determine which overloaded method
		 * the customer needs to be passed to. This is done by having the user select an input for first available or a specific artist
		 * Based off of that information, a new customer object is created using the addCustomer method 
		 */
		boolean end = false;//boolean to check if the program is at the end due to the use of "Print Waitlist"//
		while (end == false){
			System.out.println("Welcome to Lucy's Tattoo Parlor, the best parlor in all of Gainesville!");
			System.out.println("Main menu:\nPress Enter to continue");
			input.nextLine();
			System.out.println("Please enter your name: ");
			String name = input.nextLine();
			if(name.equals("Print Waitlist")){
				end = true;
				printList(lineup);
			}
			if(name!=("Print Waitlist") && end == false){
				System.out.println("Welcome "+ name + "."+ " What tattoo would you like today?: ");
				String tattoo = input.nextLine();
				System.out.println("That's a great idea! Enter 1 to choose your artist "
						+ "or enter 2 for first available");
				int selection = input.nextInt();
				if(selection ==1){
					System.out.println("How long do you think the tattoo will take(in minutes)?");
					int minutes = input.nextInt();
					System.out.print("Wonderful. Please enter the artists' number starting from 0 to " + (artistAmount -1));
					int artistNum = input.nextInt();
					input.nextLine();
					while(artistNum>artistAmount -1){
						System.out.println("Sorry, that artist is not available today. Please enter an artist between 0 and " + (artistAmount -1));
						artistNum = input.nextInt();
						input.nextLine();
					}
					TattooCustomer customer = new TattooCustomer(name, tattoo, minutes);
					addCustomer(lineup, customer, artistNum);

				}
				else{
					System.out.println("How long do you think the tattoo will take(in minutes)?");
					int minutes = input.nextInt();
					input.nextLine();
					TattooCustomer customer = new TattooCustomer(name, tattoo, minutes);
					addCustomer(lineup, customer);
				}
			}
		}
		input.close(); 
	}
	
	/**
	 * Computes the minutes of work for a desired tattoo artist. An int totalTime stores the totalTime for
	 * that artist. A for loop traverses through the array that is passed in. An if statement checks to see 
	 * if there is a value in the a[i] value, and if so, uses a getter to get the minutes from that customer 
	 * object and adds it to the total time
	 * @param a
	 * @return totalTime, an int value equal to the time in the desired artists row
	 */
	public static int computeMinutesOfWork(TattooCustomer [] a) {
		int totalTime= 0;
		for(int i=0; i < a.length;i++){
			if(a[i]!=null){
				totalTime += a[i].getMinutes();
			}
		}
		return totalTime;
	}
	
	/**
	 * This addCustomer method is for customers who choose a specific artist by entering their number as an int.
	 * boolean check checks to see if the artist can hold another person based on both time and wait list length 
	 * a while loop begins to traverse the array by checking if the i counter is less than the length of the specific 
	 * artists array length. It also checks that check is still false. Inside the loop, and if statement first checks to 
	 * see if the wait list length is full or the time of the required tattoo plus the time already in the wait list 
	 * exceeds 8 hours. If it is true, it prints an error message and exits the loop. If it is not true, it passes to an else statement
	 * that checks if that array column spot is taken, and if so increases i. If not, check becomes true. Finally, and if statement checks 
	 * to see if at that spot, the time will not exceed 8 hours, but can be equal, and adds the customer to that spot in the array for that artist
	 * @param a the array of artist and customer objects
	 * @param c the customer object
	 * @param artistNum the desired artist chosen by the customer 
	 * @return
	 */
	public static boolean addCustomer( TattooCustomer [][] a, TattooCustomer c, int artistNum) {
		int i = 0;
		boolean check = false;
		while(i<a[artistNum].length && check == false){
			if(i == a[artistNum].length && a[artistNum][i]!=null || (computeMinutesOfWork(a[artistNum]) + c.getMinutes()) > 480){  //checks if statement to see if the length of that artists list is full
				System.out.println("Sorry, they are full today, please try again later!\n");
				return false;
			}
			else if(a[artistNum][i]!=null){
				i++;
			}
			else{
				check = true;
			}
		}
		if ( (computeMinutesOfWork(a[artistNum]) + c.getMinutes()) <= 480){ //if theres a spot, this adds them to it
			System.out.println("Great Choice. Artist "+ artistNum+ " will be with you shortly\n");
			a[artistNum][i]= c;
		}
		else{
			return false;
		}
		return true;
	}
/**
 * This addCustomer method is used for the customers that want the first available aritst. 
 * The first for loop traverses the entire array and increases tooFull every time a spot is returned with a value 
 * In the next if statement, the entire size of the 2D array is compared to the value of tooFull, and if it is true
 * it is because all spots are taken and it return false and exits the loop. Another for loop then begins to traverse the array
 * to see if the artist with the least amount of time in their list. It compares the value at i to the value of shortestRow by calling 
 * the computeMintesOfWork method. If it is not a shorter wait list, it checks to see if the two have the same time because if so, the length of the 
 * wait list determines the location of the customer. This is done by using a for loop with two if statements inside that count the amount of taken (non-null)
 * spots in each array row. If the array at i is shorter, the shortestRow value gets overwritten by the i value 
 * Finally, a while loop very similar to the one in the other addCustomer method traverses the array and makes sure that it does not violate the length and 
 * time rules. Refer to the above addCustomer method for more detailed information, but swtich [artistNum] for [shortestRow] 
 * @param a A 2D array 
 * @param c a TattooCustomer object
 * @return
 */
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c) {
		int i = 0;
		int shortestRow = 0 ;
		int tooFull = 0;
		int k = 0;
		for(i=0; i<a.length; i++){
			for(k=0; k<a[i].length; k++){
				if(a[i][k]!=null){
					tooFull++;
				}
			}
		}
		if((tooFull == ( a.length*a[0].length))){
			System.out.println("Sorry, all spots are currently filled.");
			return false;
		}
		for(i = 1; i< a.length; i++){
			if(computeMinutesOfWork(a[i])< computeMinutesOfWork(a[shortestRow])){
				shortestRow = i;
			}
			else if (computeMinutesOfWork(a[i]) == computeMinutesOfWork(a[shortestRow])){
				int ai = 0;
				int aShortest = 0;
				for(k=0; k < a[i].length; k++){
					if(a[i][k]!= null){
						ai++;}
					if(a[shortestRow][k]!=null){
						aShortest++;				
					}	
				}
				if(ai<aShortest){
					shortestRow = i ;
				}
			}
		}
		boolean check = false;
		i=0;
		while(i<a[shortestRow].length && check == false){
			if(i == a[shortestRow].length && a[shortestRow][i]!=null || (computeMinutesOfWork(a[shortestRow]) + c.getMinutes()) > 480){  //checks if statement to see if the length of that artists list is full
				System.out.println("Sorry, all artists are busy right now, please try again later!\n");
				return false;
			}
			else if(a[shortestRow][i]!=null){
				i++;
			}
			else{
				check = true;
			}
		}
		if ( (computeMinutesOfWork(a[shortestRow]) + c.getMinutes()) <= 480){ //if theres a spot, this adds them to it
			System.out.println("Awesome. The first available artist will be with you shortly!\n");

			a[shortestRow][i]= c;
		}
		else{
			return false;
		}
		return true;
	}
	/**
	 * the printList method is only called in the main method when the user inputs the name as Print Waitlist. This method then 
	 * prints out the 2D array of all the artists and the customer objects. A for loop traverses the array going through each line.
	 * First, a the artist number is printed. Then the getters are used to get the information of the customer object stored in each 
	 * column of the array. They are printed line by line.
	 * @param a
	 */
	public static void printList( TattooCustomer [][] a){

		for(int row = 0; row < a.length; row++){
			System.out.print("Waitlist for tattoo artist: "+ row + "\n");
			for(int col = 0; col < a[row].length; col++){
				if(a[row][col]!=null){
					System.out.println(" " + a[row][col].getName());
					System.out.println(" " + a[row][col].getTattoo());
					System.out.println(" " + a[row][col].getMinutes() + "\n");
				}
			}
		}
	}
}