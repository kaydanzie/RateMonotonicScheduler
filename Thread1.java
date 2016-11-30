
class Thread1 extends Thread{

	public Semaphore sem;

	Thread1(Semaphore sem){

	}


	public void run(){
		//sem.semWait();
		for(int i=0; i<2; i++){
			doWork();
		}
		synchronized(this){
			this.notifyAll();
		}
		sem.signal();
	}


    public void doWork(){
    	int[][] workMatrix = new int[10][10];
    	for(int x=0; x<=9; x++){
    		for(int y=0; y<=9; y++){
    			workMatrix[x][y] = 1;
    		}
    	}


    	int product = 1;
    	int[] cols = {0, 5, 1, 6, 2, 7, 3, 8, 4, 9};
    	for(int x=0; x<=9; x++){
    		for(int y=0; y<=9; y++){
    			product *= workMatrix[cols[x]][y];
    		}
    	}
    }



}