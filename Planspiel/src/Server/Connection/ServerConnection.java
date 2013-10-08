package Server.Connection;

// Server.java

import java.net.Socket;
import java.io.*;

import Message.*;
import Server.GameEngine;
import Server.Player;

/**
 * 
 * @author D059270
 * 
 *         Die ServerConnection stellt eine einzelne Verbindung mit einem Client
 *         da und sollte in einem neuen Thread gestartet werden.
 * 
 */

public class ServerConnection extends Thread {
	private Socket clientSocket;
	private int clientId = 0;
	private Server server = null;

	/**
	 * Wird von der Thread.start()-Methode aufgerufen. Es wird auf ankommende
	 * Nachrichten des Clients gewartet und über die Console dokumentiert.
	 * Nachrichten vom Server an den Client und umgekehrt implementieren alle
	 * das Interface IMessage, wodurch eine stadardtisierte Schnittstelle
	 * existiert. Je nach Messagetyp wird Entsprechendes unternommen.
	 */
	public void run() {
		super.run();
		while (true) {
			String txt = "neuer Client connected-> Client Nr." + clientId;
			System.out.println(txt);

			IMessage message = readMessage();
			if (message == null) {
				return;
			}
			switch (message.getType()) {
			case "LoginMessage": // Es handelt sich um eine Loginanfrage des
									// Clients
				login((LoginMessage) message);
				break;

			default:
				break;
			}
		}

	}

	/**
	 * 
	 * @param client
	 * @param clientId
	 * @param server
	 *            Es wird eine ClientSocket übergeben, worüber mit dem Client
	 *            kommuniziert werden kann. Dem Client ist hier eine eindeutige
	 *            ID zuzuordnen. Außerdem ist hier der Server anzugeben, der die
	 *            Verbindung angenommen hat.
	 */
	public ServerConnection(Socket client, int clientId, Server server) {
		this.clientSocket = client;
		this.clientId = clientId;
		this.server = server;
	}

	/**
	 * 
	 * @param message
	 *            : Die LoginMessage des Clients Diese Methode wird bei eine
	 *            Loginanfrage eines Clients aufgerufen. Es wird geprüft, ob der
	 *            Spieler schon existiert. Existiert er noch nicht, wird eine
	 *            LoginConfirmationMessage generiert, die den Spieler
	 *            willokommen heißt. Außerdem wird er in die Liste der Spieler
	 *            aufgenommen. Existiert der Spieler schon, bekommt er eine
	 *            Nachricht, dass er schon existiert. Dies ist auch eine
	 *            LoginConfirmationMessage*
	 */
	private void login(LoginMessage message) {
		boolean isRelogin = false;
		IMessage messageBack = null;
		LoginConfirmationMessage loginConfirmationMessage = new LoginConfirmationMessage(
				true, "Willkommen " + message.getName() + "!");
		// Prüfe ob Spielername existiert.
		for (Player player : server.getPlayerList()) {
			if (player.getName().equals(message.getName())) {
				if (GameEngine.getGameEngine().getRound() == 1) { // 2 Spieler
																	// haben den
																	// gleichen
																	// Namen
																	// ausgewaehlt.
					loginConfirmationMessage = new LoginConfirmationMessage(
							false, "Der Name " + message.getName()
									+ " existiert schon!");
				} else { // Spieler loggt sich wieder ein.
					if (player.getPassword().equals(message.getPassword())) { // Passwort
																				// stimmt
						player.setIp(clientSocket.getInetAddress().toString());
						player.setPort(clientSocket.getPort());
						player.setServerConnection(this);
						isRelogin = true;
					}
				}

				break;
			}
		}
		messageBack = loginConfirmationMessage;
		if (loginConfirmationMessage.getSuccess()) { // Login erfolgreich
			try {
				if (isRelogin) {
					// messageBack = new GameDataMessage(0);
					// TODO: setGameData

				} else {
					server.addPlayer(new Player(message.getName(), message
							.getPassword(), this, message
							.getChosenLocation()));

				}

			} catch (Exception e) {
				// TODO: Falsche Location
				e.printStackTrace();
			}
		}

		writeMessage(messageBack);
	}

	/**
	 * 
	 * @return Liefert die empfangene Nachricht des Clients zurück. Diese muss
	 *         danach in den entsprechenden Typ gecastet werden. Sollte die
	 *         Nachricht nicht gelesen werden können, wird dies auf der Console
	 *         dokumentiert.
	 */
	public IMessage readMessage() { // Nachricht lesen
		ObjectInputStream objectInputStream;

		IMessage message = null;
		try {
			objectInputStream = new ObjectInputStream(
					clientSocket.getInputStream());

			try {
				message = (IMessage) objectInputStream.readObject();
				// objectInputStream.close();

			} catch (ClassNotFoundException e) {
				System.out
						.println("Nachricht kann nicht interpretiert werden!");
				e.printStackTrace();
			}
		} catch (IOException e1) {
			String txt = "Client Nr." + clientId + " disconnected!";

			System.out.println(txt);

		}

		return message;
	}

	/**
	 * 
	 * @param message
	 *            Schreibt eine Nachricht an den Client. die Nachricht muss
	 *            dabei das Interface IMessage implementiert haben. Sollte diese
	 *            nicht gesendet werden können, wird dies auf der Console
	 *            dokumentiert.
	 */
	public void writeMessage(IMessage message) { // Nachricht schreiben

		ObjectOutputStream object;
		try {
			object = new ObjectOutputStream(clientSocket.getOutputStream());
			object.writeObject(message);
			object.flush();
			// object.close();
		} catch (IOException e) {

			System.out.println("Could not send Message! ");

		}

	}

	public Socket getClientSocket(){
		return clientSocket;
	}
	/**
	 * Schließt die Verbindung mit dem Client
	 */
	public void close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Server kann nicht beendet werden!");
		}
	}
}
