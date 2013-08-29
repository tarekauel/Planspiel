package Server.Connection;

// Server.java

import java.net.Socket;
import java.io.*;

import Message.*;
import Server.Player;

public class ServerConnection extends Thread {
	private Socket clientSocket;
	private int clientId = 0;
	private Server server = null;

	public void run() {
		super.run();
		while(true){
		String txt = "neuer Client connected-> Client Nr." + clientId;
		System.out.println(txt);

		IMessage message = readMessage();
		if(message== null){
			return;
		}
		switch (message.getType()) {
		case "LoginMessage":
			login((LoginMessage) message);
			break;
	
		default:
			break;
		}
	}

	}

	public ServerConnection(Socket client, int clientId, Server server) {
		this.clientSocket = client;
		this.clientId = clientId;
		this.server = server;
	}

	private void login(LoginMessage message) {
		LoginConfirmationMessage loginConfirmationMessage = new LoginConfirmationMessage(
				true, "Willkommen " + message.getName() + "!"); // Prüfe ob
																// Spielername
																// existiert!
		for (Player player : server.getPlayerList()) {
			if (player.getName().equals(message.getName())) {
				loginConfirmationMessage = new LoginConfirmationMessage(false,
						"Der Name " + message.getName() + " existiert schon!");
				//TODO: Handle relogin 
				break;
			}
		}
		if (loginConfirmationMessage.getSuccess()) {
			server.addPlayer(new Player(message.getName(), message.getPassword(), clientSocket));
		}
		writeMessage(loginConfirmationMessage);

	}

	public IMessage readMessage() { // Nachricht lesen
		ObjectInputStream objectInputStream;

		IMessage message = null;
		try {
			objectInputStream = new ObjectInputStream(
					clientSocket.getInputStream());

			try {
				message = (IMessage) objectInputStream.readObject();
				//objectInputStream.close();

			} catch (ClassNotFoundException e) {
				System.out
						.println("Nachricht kann nicht interpretiert werden!");
				e.printStackTrace();
			}
		} catch (IOException e1) {
			String txt = "Client Nr." + clientId + " disconnected!";

			System.out.println(txt);

		}

		return message;
	}

	public void writeMessage(IMessage message) { // Nachricht schreiben

		ObjectOutputStream object;
		try {
			object = new ObjectOutputStream(clientSocket.getOutputStream());
			object.writeObject(message);
			object.flush();
			//object.close();
		} catch (IOException e) {

			System.out.println("Could not send Message! ");

		}

	}

	public void close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Server kann nicht beendet werden!");
		}
	}
}
