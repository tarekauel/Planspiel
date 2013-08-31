package Client.Connection;

// Client.java
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

import Message.IMessage;

public class Client implements IClient{
	private Socket clientSocket;
	
	private final ClientUI ui;
	
	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		ui = new ClientUI(this);
		new Thread( ui ).start();		
	}	
	
	@Override
	public void connect(String ip, int port) {
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
			ui.hide();
		} catch (UnknownHostException e) {
			System.out.println("Kein Server gefunden!");

		} catch (IOException e) {
			System.out.println("Kein Server gefunden!");
		}

		this.clientSocket = socket;		
	}

	public void writeMessage(IMessage message) { // Nachricht
																// schreiben

		ObjectOutputStream object;
		try {
			object = new ObjectOutputStream(clientSocket.getOutputStream());

			object.writeObject(message);
			object.flush();
			//object.close();
		} catch (IOException e) {
			System.out.println("Server nicht gefunden!");
			e.printStackTrace();
		}

	}

	public IMessage readMessage() { // Nachricht lesen
		ObjectInputStream objectInputStream;

		IMessage message = null;
		try {
			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

			try {
				message = (IMessage) objectInputStream.readObject();
				//objectInputStream.close();
			} catch (ClassNotFoundException e) {
				System.out.println("Nachricht nicht interpretierbar!");

			}
		} catch (IOException e1) {
			System.out.println("Nachricht kann nicht gelesen werden!");
		}

		return message;
	}

	
}
