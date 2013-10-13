package Server.Connection;

import java.io.IOException;
import java.util.ArrayList;

import Constant.Constant;
import Message.GameDataMessageFromClient;
import Message.GameDataMessageToClient;
import Server.GameEngine;
import Server.Location;

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
	 * startet den Server
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			UDPServer udpServer = new UDPServer(Constant.Server.TCP_PORT,
					Constant.Server.UDP_PORT);
			udpServer.start();
			getServer();
			int round = 50;
			for (int i = 0; i < Constant.Server.PLAYER_KI_TAREK; i++) {
				new KIGegner.KITarek(round);
			}
			for (int i = 0; i < Constant.Server.PLAYER_KI_LARS; i++) {
				new KIGegner.KI(round);
			}

		} catch (Exception e) {
			System.out
					.println("Server musste aufgrund eines Fehlers beendet werdenbeendet werden!");
			e.printStackTrace();
		}
	}

	/**
	 * Returned den Server. Somit ist ein Sigleton sichergestellt.
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Server getServer() throws IOException {
		if (server == null) {
			Location.initLocations();
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
		// Das hier muss vorher sein, damit nicht 4 Nachrichten empfangen werden
		// müssen, um mit 3 Leuten zu spielen!!
		receivedGameMessages++;
		//KIs werden mit berücksichtigt
		if (receivedGameMessages >= (maxPlayer + Constant.Server.PLAYER_KI_LARS + Constant.Server.PLAYER_KI_TAREK)) {
			// All Clients are ready
			handleRound();
			return;
		}

	}

	private void handleRound() throws Exception {
		// set to 0 for nextRound
		receivedGameMessages = 0;

		ArrayList<GameDataMessageFromClient> gameDataList = new ArrayList<GameDataMessageFromClient>();

		// Get GameData from all Players
		for (Player player : playerList) {
			gameDataList.add((GameDataMessageFromClient) player
					.getMessagesFromClient().get(
							player.getMessagesFromClient().size() - 1));
		}

		// Play Round
		ArrayList<GameDataMessageToClient> messages = GameEngine
				.getGameEngine().startNextRound(gameDataList);

		// Send new GameData to Clients
		for (GameDataMessageToClient gameDataMessageToClient : messages) {
			// Find Player
			for (Player player : playerList) {
				if (gameDataMessageToClient.getPlayerName().equals(
						player.getName())) {
					// Send
					player.getServerConnection().writeMessage(
							gameDataMessageToClient);
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
