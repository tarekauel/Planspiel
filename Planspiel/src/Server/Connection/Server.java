package Server.Connection;

import java.util.ArrayList;
import java.util.Vector;

import Constant.Constant;
import Message.GameDataMessage;
import Message.GameDataMessageFromClient;
import Message.GameDataMessageToClient;
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
			//All Clients are ready
			handleRound();			
			return;
		}
		receivedGameMessages++;
	}

	private void handleRound() throws Exception {
		//set to 0 for nextRound
		receivedGameMessages = 0;
		
		ArrayList<GameDataMessageFromClient> gameDataList = new ArrayList<GameDataMessageFromClient>();
		
		//Get GameData from all Players
		for (Player player : playerList) {
			gameDataList.add((GameDataMessageFromClient)player.getMessagesFromClient().get(player.getMessagesFromClient().size()-1));
		}
		
		//Play Round
		ArrayList<GameDataMessageToClient> messages= GameEngine.getGameEngine().startNextRound(gameDataList);
		
		// Send new GameData to Clients
		for (GameDataMessageToClient gameDataMessageToClient : messages) {
			//Find Player
			for (Player player : playerList) {
				if (gameDataMessageToClient.getPlayerName().equals(player.getName())) {
					//Send
					player.getServerConnection().writeMessage(gameDataMessageToClient);
				}
				
			}
		}
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
		

	}

}
