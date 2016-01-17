//Simple car class
class Car extends Vehicle{
    private int passangers;
    private Wheels wheels;
    private GasTank gasTank;
    private double fuel_per_km; 
    
    Car(){
    	setSpeed(0);
    	setDirection(0);
    	setMaxSpeed(200);
        engine = new Engine(1.8);
    	fuel_per_km = 0.1;
        passangers = 4;
    	wheels = new Wheels(4);
        gasTank = new GasTank(50);
    }

    @Override
    public void turn(int dir){
    	try {
    	    gasTank.useFuel();
    	    System.out.print("Your car is turning... ");
    	    if (getSpeed() > 0){
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
    	    System.out.println("Your car mooving " + getDirection() + " now.");
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
	System.out.println("Your car speed now is: " + getSpeed());
	if(getSpeed() == 0) wheels.setWheelsStop();
    }

    @Override
    public void accelerate(int incr){
        if(!engine.getStarted()) {
            System.out.println("Turn on a motor of your car.");
            return;
        }
        if(getSpeed() == 0){
            wheels.setWheelsSpinning();
        }
        try {
            gasTank.useFuel();
            super.accelerating(incr);
        } catch (SpeedLimitsException | FuelLimitException e) {
            System.out.println(e);
        }
	System.out.println("Your car speed now is: " + getSpeed());
    }

    int getMaxFill(){
    	return (int)gasTank.getMaxFuel();
    }
    
    void getDir(){
    	System.out.println("Your car mooving " + getDirection());
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
    	    System.out.println("Fill your car with gasoline.");
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

    int getWheelsCount(){
    	return wheels.getWheelsNum();
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
    	    System.out.println("Your car has rode to the " + getDirection() + " about " + (i-1) + " km.");
    	    float time = ((float)(i-1)) / ((float)getSpeed());
    	    System.out.println("It took about " + time + " hours. ");
    	} else {
    	    System.out.println("Accelerate a little.");
    	}
    }
}
