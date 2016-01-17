class GasTank{
    private double fuel, maxFuel;

    GasTank(){
    	maxFuel = 40.0;
    	fuel = 0;
    }

    GasTank(double maxFuel){
    	this.maxFuel = maxFuel;
    	fuel = 0;
    }

    void addFuel(int someFuel) throws FuelLimitException {
    	System.out.println("Adding fuel... ");
    	double newFuel = fuel + someFuel;
    	if (newFuel > maxFuel) {
    	    fuel = maxFuel;
    	    throw new FuelLimitException(newFuel, maxFuel);
    	}
    	fuel = newFuel;
    }

    void useFuel() throws FuelLimitException {
    	fuel -= 0.1;
    	if(fuel < 0) {
    	    fuel = 0;
    	    throw new FuelLimitException(fuel, maxFuel);
	}
    	System.out.println("Fuel level: " + (float)fuel + ".");
    }

    void useFuel(double decrFuel) throws FuelLimitException {
    	fuel -= decrFuel;
    	if(fuel < 0) {
    	    fuel = 0;
    	    throw new FuelLimitException(fuel, maxFuel);
	}
    	System.out.println("Fuel level: " + (float)fuel + ".");
    }

    double getFuel(){
    	return fuel;
    }
    
    double getMaxFuel(){
    	return maxFuel;
    }
}
