
class WorkThread implements Runnable{

	public boolean mutex;
	public Thread t;
	
	WorkThread(boolean mutex){
		this.mutex = mutex;
	}


	public void run(){

	}


	public void join(){
        try{
            Thread.sleep(100, 500);
        }
        catch(InterruptedException e){
            
        }
    }
    
    
    public void start(){
        if(t==null){
            t = new Thread(this, mutex);
            t.start();
        }
    }


    public void doWork(){
    	
    }
}