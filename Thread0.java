import java.util.concurrent.*;

class Thread0 extends Thread{

	public Semaphore sem;
	private static int counter;
	private static boolean running, flag;
	public final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);

	Thread0(Semaphore s){
		this.sem = s;
		running = false;
		counter = 0;
	}


	public void run(){
		
		while(true){

			//wait to be woken by scheduler
			try{this.sem.semWait();}
			catch(Exception e){}
			flag = false;


			//start timer here
			final Runnable sendBack = new Runnable() {
                public void run() { 
                	try{setFlag();}
			 		catch(Exception e){}
			 		print(running + " running");
			 	}
            };
            timer.schedule(sendBack, 1, TimeUnit.MILLISECONDS);
			

			// new Thread(new Runnable(){
			// 	public void run(){
			// 		try{setFlag();}
			// 		catch(Exception e){}
			// 	}
			// }).start();

            
			//this.running = true;
			this.setRunning(true);
			for(int i=0; i<1000000; i++){
				if(flag) break;
				doWork();
			}
			

			//lock
			this.counter++;
			//this.running = false;
			this.setRunning(false);
			//unlock

		}
	}


	public void setFlag() throws InterruptedException{
		flag = true;
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

	public void start(){
		Thread t = new Thread(new Thread0(this.sem));
		t.start();
	}


	public boolean getRunning(){
		return this.running;
	}


	public void setRunning(boolean newValue){
		this.running = newValue;
	}


	public int getCounter(){
		return this.counter;
	}


    public void print(Object i){
		System.out.println(i);
	}
}