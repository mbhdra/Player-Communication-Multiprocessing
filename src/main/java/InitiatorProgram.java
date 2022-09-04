import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class InitiatorProgram {
	
	// connection information
	private static final String ipAddress = "localhost";
	private static final int portNumber = 9999;
	
	public static void main(String[] args) throws IOException, InterruptedException {
    	
		System.out.println("Initiator program started.");
		
		// instantiation of necessary objects
    	InitiatorPlayer initiatorPlayer = new InitiatorPlayer(new Socket(ipAddress, portNumber));
    	OutputStreamWriter os = new OutputStreamWriter(initiatorPlayer.getSocket().getOutputStream());
    	PrintWriter out = new PrintWriter(os);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(initiatorPlayer.getSocket().getInputStream()));
    	
    	// send first message to receiver
    	initiatorPlayer.setMessage("Hello " + String.valueOf(initiatorPlayer.getSentMessageCount()));
    	out.println(initiatorPlayer.getMessage());
    	out.flush();
    	initiatorPlayer.setSentMessageCount(initiatorPlayer.getSentMessageCount() + 1);
    	
    	while(true) {
    		
    		Thread.sleep(2000);
    		
    		// receive message from receiver
    		initiatorPlayer.setMessage(reader.readLine());
    		System.out.println("Initiator received message from receiver: " + initiatorPlayer.getMessage());
    		initiatorPlayer.setReceivedMessageCount(initiatorPlayer.getReceivedMessageCount() + 1);
    		
    		// terminate if 10 messages are sent and received
			// send FINISH message to receiver so it can also terminate
    		if(initiatorPlayer.getReceivedMessageCount() == 10) {
    			 
    			out.println("FINISH");
    			out.flush();
    			System.out.println("Initiator sent and received 10 messages. Initiator program is terminating...");
    			break;
    		}

    		// send message to receiver
    		// attach sent message count to end of the received message
    		// exclude the message being sent in this cycle from the sent message count
    		out.println(initiatorPlayer.getMessage() + " " + String.valueOf(initiatorPlayer.getSentMessageCount()));
    		out.flush();
    		initiatorPlayer.setSentMessageCount(initiatorPlayer.getSentMessageCount() + 1);
    	}
    	 
    	// close socket
    	initiatorPlayer.getSocket().close();
    	
    	Thread.sleep(2000);
    	System.out.println("Initiator program terminated.");
    }
}