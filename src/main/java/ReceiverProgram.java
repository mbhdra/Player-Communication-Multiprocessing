import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class ReceiverProgram {

	// connection information
	private static final int portNumber = 9999;
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
		System.out.println("Receiver program started.");

		// instantiation of necessary objects
		ReceiverPlayer receiverPlayer = new ReceiverPlayer(new ServerSocket(portNumber));
   	 	
   	 	// receiver waits message from initiator
		System.out.println("Receiver is waiting message from initiator.");
		receiverPlayer.setSocket(receiverPlayer.getServerSocket().accept());
		
		// instantiation of necessary objects
		BufferedReader reader = new BufferedReader(new InputStreamReader(receiverPlayer.getSocket().getInputStream()));
		OutputStreamWriter os = new OutputStreamWriter(receiverPlayer.getSocket().getOutputStream());
   	 	PrintWriter out = new PrintWriter(os);
		
   	 	while(true) {
   	 		
   	 		Thread.sleep(2000);
   	 		
   	 		// receive message from initiator
   	 		receiverPlayer.setMessage(reader.readLine());
   	 		
   	 		// terminate if FINISH message comes from initiator
			if(receiverPlayer.getMessage().equals("FINISH")) {
				
				System.out.println("Receiver has received \"FINISH\" message from initiator. Receiver program is terminating...");
				break;	
			}
			
			System.out.println("Receiver received message from initiator: " + receiverPlayer.getMessage());
			
			// send message to initiator
			// attach sent message count to end of the received message
			// exclude the message being sent in this cycle from the sent message count
	   	 	out.println(receiverPlayer.getMessage() + " " + String.valueOf(receiverPlayer.getSentMessageCount()));
	   	 	out.flush();
	   	 	receiverPlayer.setSentMessageCount(receiverPlayer.getSentMessageCount() + 1);
		}
		
   	 	//close sockets
		receiverPlayer.getSocket().close();
		receiverPlayer.getServerSocket().close();
		
		Thread.sleep(2000);
		System.out.println("Receiver program terminated.");
	}
}