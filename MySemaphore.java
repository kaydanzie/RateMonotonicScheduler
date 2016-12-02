
class MySemaphore{

	public int value;
	
	MySemaphore(){
		this.value = 0;
	}

	public synchronized void signal(){
		notifyAll();
		this.value++;
	}


	public synchronized void semWait() throws InterruptedException{
		while(this.value<=0){
			wait();
		}
		this.value--;
	}


}