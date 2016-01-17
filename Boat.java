//A boat class
class Boat extends Vehicle{
    private int passangers;
    private GasTank gasTank;
    private double fuel_per_km; 

    Boat(){
    	setSpeed(0);
    	setDirection(1);
    	setMaxSpeed(50);
        engine = new Engine(10.5);
        fuel_per_km = 0.5;
        gasTank = new GasTank(300);
        passangers = 8;
    }

    @Override
    public void turn(int dir){
    	try {
    	    gasTank.useFuel();
    	    System.out.print("Your boat is turning... ");
    	    if (getSpeed() > 0) {
    	    	switch(dir){
    	    	    case 1 : System.out.println(" right.");
    	    	     	     break;
    	    	    case -1: System.out.println(" left.");
    	    	     	     break;
    	    	    case 2 : System.out.println(" back.");
    	    	     	     break;
    	    	    default: System.out.println(" No, going forward.");
    	    	}
    	     	super.turnning(dir);
    	    }
    	    System.out.println("Your boat swiming " + getDirection() + " now.");
    	} catch (FuelLimitException e) {
    	    System.out.println(e);
    	}
    }

    @Override
    public void brake(int decr){
    	try {
            super.braking(decr);
       	    gasTank.useFuel();
        } catch (SpeedLimitsException | FuelLimitException e){
            System.out.println(e);
        }
	System.out.println("Your boat speed now is: " + getSpeed());
    }

    @Override
    public void accelerate(int incr){
        if(!engine.getStarted()) {
            System.out.println("Turn on a motor of your boat.");
            return;
        }
        try {
    	    gasTank.useFuel();
            super.accelerating(incr);
        } catch (SpeedLimitsException | FuelLimitException e) {
            System.out.println(e);
        }

	System.out.println("Your boat speed now is: " + getSpeed());
    }

    void getDir(){
    	System.out.println("Your boat swiming " + getDirection());
    }

    int getMaxFill(){
    	return (int)gasTank.getMaxFuel();
    }
    
    void doFill(int addfuel){
    	try {
    	    gasTank.addFuel(addfuel);
    	} catch (FuelLimitException e) {
    	    System.out.println(e);
    	}
    	System.out.println("Fuel level now: " + (float)gasTank.getFuel() + " liters");
    }
    
    void start(){
    	if(gasTank.getFuel() <= 0){
    	    System.out.println("Fill your boat with gasoline.");
    	    return;
    	}
    	try {
    	    gasTank.useFuel();
            engine.setStart();
            gasTank.useFuel();
        } catch (FuelLimitException e) {
            System.out.println(e);
        }
    }

    void runNKilometers(int km){
    	int i = 0;

    	if (getSpeed() > 0) {
    	    try {
    	    	for(i=0; i<=km; i++){
    	    	    gasTank.useFuel(fuel_per_km);
    	    	}
    	    } catch (FuelLimitException e) {
    	    	i++;
    	    	System.out.println(e);
    	    }
    	    System.out.println("Your boat has sailed to the " + getDirection() + " about " + (i-1) + " km.");
    	    float time = ((float)(i-1)) / ((float)getSpeed());
    	    System.out.println("It took about " + time + " hours. ");
    	} else {
    	    System.out.println("Accelerate a little.");
    	}
    }
}
