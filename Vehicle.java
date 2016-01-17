interface Driveable {
    void turn(int direct);
    void brake(int decr);
    void accelerate(int incr);
}

//Speed Exception class
class SpeedLimitsException extends Exception{
    private int speed, maxSpeed;
    
    SpeedLimitsException(int s, int maxs){		// s - speed below limits
    	speed = s;
    	maxSpeed = maxs;
    }
    
    public String toString(){
    	if(speed <= 0) {
    	    return "Stop braking. You have stoped already.";
    	}
    	else {
    	    return "Can't accelerate more. Your speed " + speed + 
    	           " is more than maximum speed " + maxSpeed;
    	}
    }
}

//Fuel limit Exception class
class FuelLimitException extends Exception {
    private double fuel, maxFuel;

    FuelLimitException(double f, double maxf){
    	fuel = f;
    	maxFuel = maxf;
    }

    public String toString(){
    	if(fuel <= 0) {
    	    return "No more fuel. Add some fuel.";
    	}
    	else {
    	    return "Too much fuel: " + fuel + ". Maximum " + maxFuel + " liters. ";
    	}
    }
}

//Power limit Exception class
class PowerLimitException extends Exception {
    private double power, maxPower;

    PowerLimitException(double p, double maxp){
    	power = p;
    	maxPower = maxp;
    }

    public String toString(){
    	if (power <= 0) {
    	    return "No more power. Recharge a battary.";
    	}
    	else {
    	    return "Stop charging. Can't charge to " + power + ". Maximum " + maxPower + " mAh is reached. ";
    	}
    }
}


//A class for all vehicles
abstract class Vehicle implements Driveable{
    private int speed, maxSpeed, direction;
    final String MOVE_DIRECTION[] = {"west", "north", "east", "south"};
    Engine engine;
//    GasTank gasTank;
//    SolarBattery battery;

    abstract void start();
    abstract void doFill(int addSome);
    abstract int getMaxFill();
    abstract void getDir();
    abstract void runNKilometers(int km);

    String getDirection(){
        return MOVE_DIRECTION[direction];
    }
    
    void accelerating(int incrSpeed) throws SpeedLimitsException{
        System.out.println("Accelerating...");
    	int newSpeed = speed + incrSpeed;
	if (newSpeed > maxSpeed) {
	    speed = maxSpeed;
	    throw new SpeedLimitsException(newSpeed, maxSpeed);
	}
	speed = newSpeed;
    }

    void braking(int decSpeed) throws SpeedLimitsException{
        System.out.println("Braking...");
    	speed -= decSpeed;
	if (speed <= 0){
	    speed = 0;
	    throw new SpeedLimitsException(speed, maxSpeed);
	}
    }

    void turnning(int direction){
    	this.direction += direction;
    	if (this.direction > 3) this.direction %= 4;
    	if (this.direction < 0) this.direction = 4 + this.direction;
    }

    void setSpeed(int speed){
    	this.speed = speed;
    }

    void setMaxSpeed(int mspeed){
    	maxSpeed = mspeed;
    }
    
    int getSpeed(){
    	return speed;
    }
    
    void setDirection(int dir){
    	direction = dir;
    }

    boolean isRiding(){
       	if(speed != 0) return true;
    	else return false;
    }
}
