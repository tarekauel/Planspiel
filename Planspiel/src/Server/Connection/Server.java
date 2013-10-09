package Server.Connection;

import java.util.ArrayList;
import java.util.Vector;

import Constant.Constant;
import Message.GameDataMessage;
import Message.GameDataMessageFromClient;
import Server.GameEngine;
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
	private int maxPlayer = Constant.Server.MAX_PLAYER;

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

	public synchronized void notifyGameData() throws Exception {
		if (receivedGameMessages >= maxPlayer) {
			//set to 0 for nextRound
			receivedGameMessages = 0;
			
			ArrayList<GameDataMessageFromClient> gameDataList = new ArrayList<GameDataMessageFromClient>();
			
			//Get GameData from all Players
			for (Player player : playerList) {
				gameDataList.add((GameDataMessageFromClient)player.getMessagesFromClient().get(player.getMessagesFromClient().size()-1));
			}
			GameEngine.getGameEngine().startNextRound(gameDataList);
			return;
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
