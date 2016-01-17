class Wheels{
    private int wheelsNum;
    private boolean isSpinning = false;

    Wheels(){
    	wheelsNum = 0;
    }

    Wheels(int num){
    	wheelsNum = num;
    }

    int getWheelsNum(){
    	return wheelsNum;
    }

    void setWheelsSpinning(){
    	isSpinning = true;
    	System.out.println("Wheels start spinning.");
    }
    
    void setWheelsStop(){
    	System.out.println("Wheels stop spinning.");
    	isSpinning = false;
    }
    boolean getWheelsSpinning(){
    	return isSpinning;
    }
}
