import java.io.*;

public class Worker implements Runnable{

	protected Request request = null;
	public static int requestsProcessed = 0;
	public Worker(Request req) {
		request = req;
	}

	public Worker() {
	}

	public void run() {
			int x = 0;
			synchronized(this){
				x=requestsProcessed;
				requestsProcessed++;
			}
			long time = System.currentTimeMillis();
			request.startTime = time;
			System.out.println(x+" Request processed: " + (time-My_main.time) +" by thread number "+request.threadNo+ " and the message is "+request.serverText);
			time = System.currentTimeMillis();

			request.endTime = time;

	}
}