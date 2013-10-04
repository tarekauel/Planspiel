package Server;

import java.net.ServerSocket;
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
	private String ip = "";
	private Socket clientSocket;
	private Company myCompany;
	
	/**
	 * 
	 * @return Das Unternehmen des Spielers
	 */
	public Company getMyCompany() {
		return myCompany;
	}
	
	
	private int port;

	/**
	 * Legt einen neuen Spieler mit den Parametern:
	 * 
	 * @param name
	 * @param password
	 * @param clientSocket
	 *            an.
	 * @throws Exception Location ist invalid.
	 */
	public Player(String name, String password, Socket clientSocket, String location) throws Exception {
		Log.newObj(new Object[] { name, password, clientSocket, ip, port });
		this.name = name;
		this.password = password;
		this.clientSocket = clientSocket;
		this.ip = clientSocket.getInetAddress().toString();
		this.port = clientSocket.getPort();
		setClientSocket(clientSocket);
		Location loc= Location.getLocationByCountry(location);
		myCompany = new Company(loc);
		
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

	public String getName() {
		Log.get(name);
		return name;
	}

	public String getIp() {
		Log.get(ip);
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		Log.get(port);
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
