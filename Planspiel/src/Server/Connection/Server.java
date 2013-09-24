package Server.Connection;

import java.util.Vector;

import Server.Player;

/**
 * 
 * @author D059270 Der Server stellt die Kommunikation zwischen Client und
 *         Server her.
 */
public class Server {
	private Vector<Player> playerList = new Vector<Player>();
	private ConnectionListener connectionListener = null;

	/**
	 * 
	 * started den Server
	 */
	public static void main(String[] args) {
		Server server = new Server(11111);

	}

	/**
	 * 
	 * @param port
	 *            Ein ConnectionListener wird initialisiert und beginnnt am
	 *            angegebenen Port zu lauschen.
	 */
	public Server(int port) {
		ConnectionListener connectionListener = new ConnectionListener(port,
				this);
		connectionListener.start();
		this.connectionListener = connectionListener;

	}

	public Vector<Player> getPlayerList() {
		return playerList;
	}

	public void addPlayer(Player player) {
		playerList.add(player);
	}

	/**
	 * Beendet den Server und damit das ganze Spiel. Hierfür wird der
	 * ConnectionListener geschlossen, der jede einzelne Clientverbindung kappt.
	 */
	public void close() {
		connectionListener.close();
		// connectionListener.interrupt();

	}

}
