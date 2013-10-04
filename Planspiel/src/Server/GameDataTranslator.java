package Server;

import Message.GameDataMessage;
import Server.Connection.Server;

public class GameDataTranslator {

	private static GameDataTranslator gameDataTranslator = null;

	private GameDataTranslator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton Kostruktor
	 * 
	 * @return Singletonobjekt
	 */
	public static GameDataTranslator getGameDataTranslator() {
		if (gameDataTranslator == null) {
			gameDataTranslator = new GameDataTranslator();
		}
		return gameDataTranslator;
	}

	public void convertGameDataMessage2Objects(GameDataMessage gameDataMessage) {
		
	}

	public void createGameDataMessagesAndSend2Clients() {
		for (Player player : Server.getServer().getPlayerList()) {
			GameDataMessage message = new GameDataMessage(1);
			player.getMyCompany().getBankAccount().getBankBalance();
			player.getServerConnection().writeMessage(message);
		}
	}

}
