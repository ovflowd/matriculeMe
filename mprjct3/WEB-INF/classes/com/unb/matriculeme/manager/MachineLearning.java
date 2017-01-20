package com.unb.matriculeme.manager;

public class MachineLearning extends Thread {

    public void run() {
        //@TODO What the Fuck is this?
        //Looping from 1 to 10 to display numbers from 1 to 10
        for (int i = 1; i <= 10; i++) {
            //Displaying the numbers from this thread
            System.out.println("Messag from First Thread : " + i);

	        /*taking a delay of one second before displaying next number 
            *
	        * "Thread.sleep(1000);" - when this  statement is executed, 
	        * this thread will sleep for 1000  milliseconds (1 second) 
	        * before executing the next statement. 
	        * 
	        * Since  we are making this thread to sleep for one second, 
	        * we need to handle  "InterruptedException". Our thread 
	        * may throw this exception if it is  interrupted while it 
	        * is sleeping. 
	        * 
	        */
        }
    }
}
