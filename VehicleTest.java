import java.io.*;

//main class
class VehicleTest{
    static int menuSelect(int lowMeasure, int highMeasure, String msg){
    	String par;
        int num = 0;
        boolean goodnum = false;
	BufferedReader parametr = new BufferedReader(new InputStreamReader(System.in));
	
	do{
	    System.out.print(msg);
	    try {
	    	par = parametr.readLine();	//this line can throw IOException
 	        num = Integer.parseInt(par);	//this line can throw NumberFormatException
	        if ((num > highMeasure)||(num < lowMeasure)) {
		    System.out.println("\nSelect again.\n");
		    continue;
		}
	    } catch (NumberFormatException | IOException e){
	        System.out.println("\nTry again.\n");
	        continue;
	    }
	    goodnum = true;
	} while (!goodnum);
	System.out.println();
        return num;
    }
    
    static void actionMenu(Vehicle vehicle){
        final int FORWARD = 0;
    	final int LEFT = -1;
    	final int RIGHT = 1;
    	final int BACK = 2;
        int actSelect;
        Driveable drive = vehicle;
        String powerType, vehicleType = vehicle.getClass().getName();
        if(vehicle instanceof SolarCar) powerType = "power (mAh)";
         else powerType = "gasoline (liters)";
      
	//Select action
	do {
	    System.out.println("1 - Fill your " + vehicleType + " with " + powerType + ".");
	    System.out.println("2 - Start. ");
	    System.out.println("3 - Accelerate. ");
	    System.out.println("4 - Turn... ");
	    System.out.println("5 - Brake. ");
	    System.out.println("6 - Run N kilometers. ");
	    System.out.println("7 - Back to transport selection. ");
	    actSelect = menuSelect(1,7,"Select your action: ");
	    switch(actSelect){
	    	case 1: /*int addSome = menuSelect(0, vehicle.getMaxFill(), "Enter " + powerType +
	    					 " (Max=" + vehicle.getMaxFill() + "): ");*/
	    	        vehicle.doFill(menuSelect(0, vehicle.getMaxFill(), "Enter " + powerType +
	    					  " (Max=" + vehicle.getMaxFill() + "): "));
	    		break;
	    	case 2: vehicle.start();
	    		break;
	    	case 3: /*int addSpeed = menuSelect(0, 500, "Enter acceleration: ");*/
	    		drive.accelerate(menuSelect(0, 500, "Enter acceleration: "));
	    		break;
	    	case 4: System.out.println("1 - Left. ");
			System.out.println("2 - Right ");
			System.out.println("3 - Back. ");
			System.out.println("4 - Return to previous menu ");
			//int dir = menuSelect(1, 4, "Select direction you want to turn: ");
			switch(menuSelect(1, 4, "Select direction you want to turn: ")){
			    case 1: drive.turn(LEFT);
			    	    break;
			    case 2: drive.turn(RIGHT);
			    	    break;
			    case 3: drive.turn(BACK);
			            break;
			}
	    		break;
	    	case 5: /*int decSpeed = menuSelect(0, 500, "Enter speed you want to reduce: ");*/
	    		drive.brake(menuSelect(0, 500, "Enter speed you want to reduce: "));
	    		break;
	    	case 6:	vehicle.runNKilometers(menuSelect(0,10000, "Enter the number of kilometers: "));
	    		break;
	    }

	} while (actSelect != 7);
	System.out.println();
    }

    public static void main(String args[]){
    	Car car = new Car();
    	Boat boat = new Boat();
    	SolarCar solarCar = new SolarCar();
    	int mSelect;
      
	//Select your transport
	do {
	    System.out.println("1 - Car. ");
	    System.out.println("2 - Boat. ");
	    System.out.println("3 - Solar-powered car: ");
	    System.out.println("4 - Exit: ");
	    mSelect = menuSelect(1,4,"Select your transport: ");
	    switch(mSelect){
	    	case 1: System.out.println("\nYou selected a car.\n");
	    		actionMenu(car);
	    		break;
	    	case 2: System.out.println("\nYou selected a boat.\n");
	    		actionMenu(boat);
	    		break;
	    	case 3: System.out.println("\nYou selected a solar-car.\n");
	    		actionMenu(solarCar);
	    		break;
	    }

	} while (mSelect != 4);
	System.out.println("Bye-bye!!!");
    }
}