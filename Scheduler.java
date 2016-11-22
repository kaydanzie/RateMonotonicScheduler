
class Scheduler extends Thread{

	int count0= 0;
	int count1= 0;
	int count2= 0;
	int count3=0;
	Thread0 t0;
	Thread1 t1;
	int period = 0;


	Scheduler(){
		Thread t = new Thread(this);
		t.start();
	}

	
	public static void main(String[] args){

		//Thread0 t0 = new Thread0(true);

		Scheduler s = new Scheduler();

	}


	public void run(){
		//less than or equal?
		while(period <= 16){
			period += 1;
			switch(period){
				case 1: 
					t0 = new Thread0(true);
					count0++;
					break;
				case 2:
					t0 = new Thread0(true);
					count0++;
					t1 = new Thread1(true);
					count1++;
					break;
				default:
					break;
			}
		}
	}
}