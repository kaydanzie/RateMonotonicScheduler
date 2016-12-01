
class Scheduler extends Thread{

	int count0= 0;
	int count1= 0;
	int count2= 0;
	int count3= 0;
	int overrun0= 0;
	int overrun1= 0;
	int overrun2= 0;
	int overrun3= 0;
	int period= 0;

	
	Thread0 t0;
	//Thread1 t1;
	


	Scheduler(){
		this.t0 = new Thread0(new Semaphore());
	}


	public static void main(String[] args){

		Scheduler s = new Scheduler();
		s.start();

	}


	public void run(){

		t0.start();

		while(period <= 160){

			try{Thread.sleep(1000);}
			catch(Exception e){}


			if(period % 16 ==0){//t0, t1, t2, t3

				t0.sem.signal();

			}
			else if(period % 4 == 0){//t0, t1, t2

			}
			else if(period % 2 == 0){//t0, t1

			}
			else{//t0 only

			}

			period += 1;
		}
	}


	public void start(){
		Thread t = new Thread(new Scheduler());
		t.start();
	}


	public void print(Object i){
		System.out.println(i);
	}


}