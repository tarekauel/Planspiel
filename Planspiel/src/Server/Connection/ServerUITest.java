package Server.Connection;

import java.io.IOException;
import java.util.ArrayList;

import Constant.Constant;
import KIGegner.KITarek;
import Message.GameDataMessageFromClient;
import Message.GameDataMessageToClient;
import Server.GameEngine;
import Server.Location;

/**
 * 
 * @author D059270 Der Server stellt die Kommunikation zwischen Client und
 *         Server her.
 */
public class ServerUITest  {
	private static ServerUITest server;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ConnectionListener connectionListener = null;
	private int receivedGameMessages = 0;
	private int maxPlayer = Constant.Server.MAX_PLAYER;

	/**
	 * startet den Server
	 * 
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		Server.getServer();
		new KITarek(15);
	}
}
