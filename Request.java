import java.net.Socket;

public class Request {
	protected Socket clientSocket = null;
    protected String serverText   = null;
    protected long startTime,endTime,arrivalTime;
    protected int threadNo;
    
    public Request(Socket clientSocket, String serverText, long time) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        arrivalTime = time;
    }
    
    public void setstartTime(long time){
    	this.startTime=time;
    }
    
    public void setendTime(long time){
    	this.endTime=time;
    }
    
    public void ServicedBy(int num){
    	this.threadNo=num;
    }
}
