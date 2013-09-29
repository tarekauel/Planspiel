package Server;

import java.net.Socket;

import Logger.Log;

/**
 * 
 * @author D059270 Speichert entsprechende Daten des Spielers. Über getter und
 *         setter kann auf die Attribute zugegriffen werden.
 * 
 */

public class Player {

	private String name = "";
	private String password = "";
	private Socket clientSocket;

	/**
	 * Legt einen neuen Spieler mit den Parametern:
	 * 
	 * @param name
	 * @param password
	 * @param clientSocket
	 *            an.
	 */
	public Player(String name, String password, Socket clientSocket) {
		Log.newObj(new Object[]{name,password,clientSocket});
		setName(name);
		setPassword(password);
		setClientSocket(clientSocket);
		Log.methodExit();
	}

	public Socket getClientSocket() {
		Log.get(clientSocket);
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		Log.method(clientSocket);
		this.clientSocket = clientSocket;
	}

	public String getPassword() {
		Log.get(password);
		return password;
	}

	public void setPassword(String password) {
		//TODO: SINN?
		this.password = password;
	}

	public String getName() {
		Log.get(name);
		return name;
	}

	public void setName(String name) {
		//TODO: SINN?
		this.name = name;
	}

}
