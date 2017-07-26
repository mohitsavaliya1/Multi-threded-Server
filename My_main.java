

public class My_main {
	
	public static long time;
	public static My_Server server;
	public static void main(String args[]){
		
		time = System.currentTimeMillis();
		server = new My_Server(8080,3);
		new Thread(server).start();

		try {
		    Thread.sleep(200 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		server.stop();
		try {
	    Thread.sleep(10000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Total Requests Handled :"+server.totalRequest);
		System.out.println("Requests handled by each thread :");
		for(int i=0;i<server.thread.length;i++){
			System.out.println("Thread number "+i+" has handled "+server.RequestsByThread[i]+" requests");
		}
		
	}
}
