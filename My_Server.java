import java.net.*;  

import java.io.*; 
import java.util.*;
import java.io.DataInputStream;

public class My_Server implements Runnable{  
	protected Socket cs=null;
	protected static ArrayList<Request> request = new ArrayList<Request>();
	protected static ArrayList<Request> pending = new ArrayList<Request>();
	protected int totalRequest = 0;
	protected Helper hel = new Helper();
	protected Thread helper = new Thread();
	protected Thread[] thread;
	protected ServerSocket ss;
	protected int RequestsByThread[];
	protected int port   = 8080;
	protected boolean isStopped = false;
	long time =  0;
	DataInputStream din;
	DataOutputStream dout;
	
	public My_Server(int port,int len){
		this.port = port;
		thread = new Thread[len];
		RequestsByThread = new int[len];
	}
	public void run(){
		
		for(int i=0;i<thread.length;i++) thread[i]=new Thread();

		openServerSocket();
		
		System.out.println("My server is created on port =	"+port );
		
		while(! isStopped() ){
			
			Socket cs = null;
		
				System.out.println("Waiting for incoming requests" );
				try {
					if(! isStopped())
					cs = ss.accept();
				//	else cs.close();
					
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					//e3.printStackTrace();
				}
				time=System.currentTimeMillis();
			int n = 0;
			try {	if(! isStopped())
					din	= new DataInputStream(cs.getInputStream());
			
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				if(! isStopped())
				 dout =new DataOutputStream(cs.getOutputStream());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
			try {
				if(! isStopped())
				n = din.read();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			totalRequest += n;
		//	System.out.println("Total Requested receved is " + totalRequest);
			try {
					if(n>0)
					dout.writeUTF(n+" requests received.");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
 
			for(int j=1;j<=n;j++){
				try {
					String msg = din.readUTF();
				//	System.out.println("Message recieved from client "+msg);
					time=System.currentTimeMillis();
					Request req = new Request(cs, msg,time);
					pending.add(req);
					Thread.sleep(1000);
				//	System.out.println("Message send to client "+msg);	
					dout.writeUTF(msg);
					dout.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(!helper.isAlive()){
				helper=new Thread(hel);
				helper.start();
			}	
	
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		}
		
	}
	
	public synchronized void stop(){
			isStopped = true;
			System.out.println("Server Stopped.") ;
			try {
			ss.close();
		//	cs.close();
		//	return;
			} catch (IOException e) {
				throw new RuntimeException("Error closing server", e);
			}
		}

	private void openServerSocket() {
		try {
			this.ss = new ServerSocket(this.port);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port 8080", e);
		}
	}
	private synchronized boolean isStopped() {
		return this.isStopped;
	}

}