import java.net.*;  
import java.util.Random;
import java.io.*;  
// Mohit Savaliya 201401137 DAIICT
class My_Client{  

	public static void main(String args[])throws Exception{  
		
		try{
			
			
		Socket clientSocket = new Socket("localhost",8080); 
		
	
		Random random = new Random();
		int numberOfrequest = random.nextInt(10)+1;
		
		
		DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());   

		DataInputStream din = new DataInputStream(clientSocket.getInputStream());    
	
		
		dout.write(numberOfrequest);
		String s1 = din.readUTF();
		System.out.println(s1);
		for(int i=1;i<=numberOfrequest;i++){
			
		//	System.out.println("Sending Message "+ "Hello " + i);
			String message= "Hello " + i;
			String s = "";
			long t0 = System.currentTimeMillis();
			dout.writeUTF(message);  
			
			s = din.readUTF(); 
		//	message = br.readLine();
			System.out.println(s);
			long t1 = System.currentTimeMillis();
			System.out.println("Message " + i + " received after " + (t1-t0) + " Seconds.");
		}
		//	clientSocket.close();  
			
		System.out.println("dsfhbdkjsf");
		}
	catch (IOException e){
		e.printStackTrace();
		} 
	//	System.out.println("Mission completed");
	}
}
	
  