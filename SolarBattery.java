class SolarBattery{
    private int power, maxPower;

    SolarBattery(){
    	maxPower = 40000;
    	power = 0;
    }

    SolarBattery(int maxPower){
    	this.maxPower = maxPower;
    	power = 0;
    }

    void addPower(int somePower) throws PowerLimitException{
    	System.out.println("Adding power... ");
    	int newPower = power + somePower;
    	if (newPower > maxPower) {
    	    power = maxPower;
    	    throw new PowerLimitException(newPower, maxPower);
    	}
    	power = newPower;
    }

    void usePower() throws PowerLimitException {
    	power--;
    	if(power < 0) {
    	    power = 0;
    	    throw new PowerLimitException(power, maxPower);
	}
    	System.out.println("Power level: " + power);
    }

    void usePower(int decrPower) throws PowerLimitException {
    	power -= decrPower;
    	if(power < 0) {
    	    power = 0;
    	    throw new PowerLimitException(power, maxPower);
	}
    	System.out.println("Power level: " + power);
    }

    int getPower(){
    	return power;
    }
    
    int getMaxPower(){
    	return maxPower;
    }
}
