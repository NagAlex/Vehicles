//class of Solar-powered car
class SolarCar extends Vehicle{
    private int passangers, power_per_km;
    private Wheels wheels;
    private SolarBattery battery;
    
    SolarCar(){
    	setSpeed(0);
    	setDirection(2);
    	setMaxSpeed(250);
        engine = new Engine(2.0);
        power_per_km = 10;
        passangers = 2;
    	wheels = new Wheels(4);
        battery = new SolarBattery(30000);
    }

    @Override
    public void turn(int dir){
    	try {
    	    battery.usePower();
    	    System.out.print("Your solar-car is turning... ");
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
    	    System.out.println("Your solar-car mooving " + getDirection() + " now.");
    	} catch (PowerLimitException e) {
    	    System.out.println(e);
    	}
    }

    @Override
    public void brake(int decr){
    	try {
            super.braking(decr);
       	    battery.usePower();
        } catch (SpeedLimitsException | PowerLimitException e){
            System.out.println(e);
        }
	System.out.println("Your solar-car speed now is: " + getSpeed());
	if(getSpeed() == 0) wheels.setWheelsStop();
    }

    @Override
    public void accelerate(int incr){
        if(!engine.getStarted()) {
            System.out.println("Turn on a motor of your solar-car.");
            return;
        }
        if(getSpeed() == 0){
            wheels.setWheelsSpinning();
        }
        try {
    	    battery.usePower();
            super.accelerating(incr);
        } catch (SpeedLimitsException | PowerLimitException e) {
            System.out.println(e);
        }
	System.out.println("Your solar-car speed now is: " + getSpeed());
    }

    void getDir(){
    	System.out.println("Your solar-car mooving " + getDirection());
    }

    int getMaxFill(){
    	return battery.getMaxPower();
    }

    void doFill(int addPow){
    	try {
    	    battery.addPower(addPow);
    	} catch (PowerLimitException e) {
    	    System.out.println(e);
    	}
    	System.out.println("Power level now: " + battery.getPower() + " mAh");
    }
    
    void start(){
    	if(battery.getPower() <= 0){
    	    System.out.println("Recharge your solar-car's battery.");
    	    return;
    	}
    	try {
    	    battery.usePower();
            engine.setStart();
            battery.usePower();
        } catch (PowerLimitException e) {
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
    	    	    battery.usePower(power_per_km);
    	    	}
    	    } catch (PowerLimitException e) {
    	    	i++;
    	    	System.out.println(e);
    	    }
    	    System.out.println("Your solar-car has rode to the " + getDirection() + " about " + (i-1) + " km.");
    	    float time = ((float)(i-1)) / ((float)getSpeed());
    	    System.out.println("It took about " + time + " hours.");
    	} else {
    	    System.out.println("Accelerate a little.");
    	}
    }
}
