package Server.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ConnectionListener extends Thread {

	private int port = 0;
	private Server server = null;
	private ServerSocket serverSocket = null;
	private Vector<ServerConnection> connections = new Vector<ServerConnection>();

	ConnectionListener(int port, Server server) {
		this.server = server;
		this.port = port;
	}

	public void run() {

		try {
			serverSocket = new ServerSocket(port);

		} catch (IOException e) {
			System.out.println("Server kann nicht gestartet werden!");
			System.exit(0);

		}

		int count = 1;
		while (true) {

			ServerConnection serverConnection = new ServerConnection(
					waitForClient(serverSocket), count, server);
			serverConnection.start();
			connections.add(serverConnection);
			count++;
		}
	}

	private Socket waitForClient(ServerSocket serverSocket) { // blockiert, bis sich
															// ein Client
															// angemeldet hat
		Socket socket = null;
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			System.out
					.println("Verbindung mit Client kann nicht aufgebaut werden!");
			e.printStackTrace();
		}
		return socket;
	}

	public void close() {

		for (ServerConnection connection : connections) {
			connection.close();
			// connection.interrupt();
		}

		try {
			
			
			serverSocket.close();
			
		} catch (IOException e) {
			System.out.println("Server kann nicht beendet werden!");
		}
	}
}