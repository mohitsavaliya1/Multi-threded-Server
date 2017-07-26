import java.util.ArrayList;


public class Helper implements Runnable{


	public Helper(){

	}
	public void run() {
		// TODO Auto-generated method stubn
		while(My_main.server.pending.size()>0){	
			for(int i=0;i<My_main.server.thread.length;i++){
				if(My_main.server.pending.size()==0)
					break;
				if(!My_main.server.thread[i].isAlive()){
					Request request = My_main.server.pending.remove(0);
					request.threadNo = i;
					My_main.server.RequestsByThread[i]++;
					My_main.server.thread[i]=new Thread(new Worker(request));
					My_main.server.thread[i].start();
					My_main.server.request.add(request);
				}
			}
		}
	}
}
