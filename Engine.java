class Engine{
    private boolean isStarted = false;
    private double power;

    Engine(){
    	power = 1.5;
    }

    Engine(double power){
    	this.power = power;
    }

    void setStart(){
    	System.out.println("Engine started.");
     	isStarted = true;
    }

    void setStop(){
    	System.out.println("Engine stoped.");
    	isStarted = false;
    }

    boolean getStarted(){
    	return isStarted;
    }
}
