
class Scheduler extends Thread{

	int overrun0= 0;
	int overrun1= 0;
	int overrun2= 0;
	int overrun3= 0;
	int period= 0;
	MySemaphore schedSem;

	
	Thread0 t0;
	Thread1 t1;
	Thread2 t2;


	Scheduler(){
		this.t0 = new Thread0(new MySemaphore(), this);
		this.t1 = new Thread1(new MySemaphore(), this);
		this.t2 = new Thread2(new MySemaphore(), this);
	}


	public static void main(String[] args){

		Scheduler s = new Scheduler();
		s.schedSem = new MySemaphore();
		s.start();

	}


	public void run(){

		t0.start();
		t1.start();
		t2.start();

		//runs for 16 units, 10 times
		while(period <= 160){

			try{Thread.sleep(20);}
			catch(Exception e){}

			//all 4 threads run every 16 units
			if(period % 16 ==0){//t0, t1, t2, t3

				//start thread0
				if(!t0.running){
					t0.sem.signal();

					//wait doesn't work, use sleep at beginning of loop instead
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun0++;

				//start thread1
				if(!t1.running){
					t1.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun1++;

				//start thread2
				if(!t2.running){
					t2.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun2++;

			}
			
			else if(period % 4 == 0){//t0, t1, t2
				//start thread0
				if(!t0.running){
					t0.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun0++;

				//start thread1
				if(!t1.running){
					t1.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun1++;

				//start thread2
				if(!t2.running){
					t2.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun2++;
			}
			
			else if(period % 2 == 0){//t0, t1
				//start thread0
				if(!t0.running){
					t0.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun0++;

				//start thread1
				if(!t1.running){
					t1.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun1++;
			}
			
			else{//t0 only
				//start thread0
				if(!t0.running){
					t0.sem.signal();
					try{this.schedSem.semWait();}
					catch(Exception e){}
				}
				else overrun0++;

			}
			period += 1;
		}

		//write results to file, t0.counter
		//terminate program immediately after 160 periods
		System.exit(0);	
	}


	public void start(){
		Thread t = new Thread(new Scheduler());
		t.start();
	}


	public void print(Object i){
		System.out.println(i);
	}


}