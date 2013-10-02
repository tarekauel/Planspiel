package Server.Connection;

import java.util.ArrayList;
import java.util.Vector;

import Constant.Constant;
import Message.GameDataMessage;
import Server.Player;

/**
 * 
 * @author D059270 Der Server stellt die Kommunikation zwischen Client und
 *         Server her.
 */
public class Server {
	private static Server server;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ConnectionListener connectionListener = null;
	private int receivedGameMessages = 0;
	private int maxPlayer = 0;

	/**
	 * 
	 * started den Server
	 */
	public static void main(String[] args) {
		Server server = new Server(11111);

	}

	/**
	 * Returned den Server. Somit ist ein Sigleton sichergestellt.
	 * 
	 * @return
	 */
	public static Server getServer() {
		if (server == null) {
			server = new Server(Constant.Server.TCP_PORT);
		}

		return server;
	}

	/**
	 * 
	 * @param port
	 *            Ein ConnectionListener wird initialisiert und beginnnt am
	 *            angegebenen Port zu lauschen.
	 */
	private Server(int port) {
		ConnectionListener connectionListener = new ConnectionListener(port,
				this);
		connectionListener.start();
		this.connectionListener = connectionListener;

	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public synchronized void notifyGameData() {
		if (receivedGameMessages >= maxPlayer) {
			receivedGameMessages = 0;
			// TODO:Starte Spiel
		}
		receivedGameMessages++;
	}

	public void sendResultsToClients(
			ArrayList<GameDataMessage> messagesToClients) {
		// TODO: Send Results

	}

	public synchronized void addPlayer(Player player) {
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
