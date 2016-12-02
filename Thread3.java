import java.util.concurrent.*;

class Thread3 extends Thread{

    public MySemaphore sem;
    public static int counter;
    public static boolean running;
    public final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
    public Scheduler sched;

    Thread3(MySemaphore s, Scheduler sched){
        this.sched = sched;
        this.sem = s;
        running = false;
        counter = 0;
    }


    public void run(){
        
        while(true){

            //wait to be woken by scheduler
            try{this.sem.semWait();}
            catch(Exception e){}


            //start timer here
            final Runnable sendBack = new Runnable() {
                public void run() {
                    //signaling doesn't work
                    //scheduler using sleep
                    sched.schedSem.signal();
                }
            };
            timer.schedule(sendBack, 100, TimeUnit.MILLISECONDS);

            
            setRunning(true);
            for(int i=0; i<16; i++){
                doWork();
            }
            
            //lock
            setCounter(1);
            setRunning(false);
            //unlock

        }
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
        Thread t = new Thread(new Thread3(this.sem, this.sched));
        t.start();
    }


    public void setRunning(boolean newValue){
        this.running = newValue;
    }

    public void setCounter(int x){
        this.counter += x;
    }


    public void print(Object i){
        System.out.println(i);
    }
}