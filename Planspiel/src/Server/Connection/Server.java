package Server.Connection;

import java.util.Vector;

import Server.Player;

public class Server {
	private Vector<Player> playerList = new Vector<Player>();
	private ConnectionListener connectionListener = null;

	public static void main(String[] args) {
		Server server = new Server(11111);

	}

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

	public void close() {
		connectionListener.close();
		// connectionListener.interrupt();

	}

}
