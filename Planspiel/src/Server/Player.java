package Server;

import Server.Connection.ServerConnection;

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
	private ServerConnection serverConnection;
	
	public ServerConnection getServerConnection() {
		return serverConnection;
	}

	public void setServerConnection(ServerConnection serverConnection) {
		this.serverConnection = serverConnection;
	}

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
	 * @throws Exception
	 *             Location ist invalid.
	 */
	public Player(String name, String password,
			ServerConnection serverConnection, String location)
			throws Exception {

		this.name = name;
		this.password = password;
		this.serverConnection = serverConnection;
		this.ip = serverConnection.getClientSocket().getInetAddress()
				.toString();
		this.port = serverConnection.getClientSocket().getPort();

		Location loc = Location.getLocationByCountry(location);
		myCompany = new Company(loc);

	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
