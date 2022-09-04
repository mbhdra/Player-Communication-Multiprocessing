// class for receiver player
// holds a ServerSocket object additionally because it acts like a server in this scenario

import java.net.ServerSocket;

public class ReceiverPlayer extends Player {

	private ServerSocket serverSocket;
	
	public ReceiverPlayer(ServerSocket serverSocket) {
		super();
		this.serverSocket = serverSocket;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}	
}