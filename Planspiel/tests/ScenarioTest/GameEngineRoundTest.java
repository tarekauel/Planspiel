package ScenarioTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Client.Connection.Client;
import Constant.Constant;
import Message.GameDataMessageFromClient;
import Message.GameDataMessageToClient;
import Message.IMessage;
import Message.LoginConfirmationMessage;
import Message.LoginMessage;
import Server.GameEngine;
import Server.Location;
import Server.Connection.Server;

public class GameEngineRoundTest {

	public GameEngineRoundTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
	}

	@Before
	public void initializeTests() {
	}

	@Test
	public void makeTests() {
		// Server starten
		Server s = Server.getServer();

		// Client 1 connect
		Client c1 = new Client();
		c1.connect("localhost", Constant.Server.TCP_PORT);
		c1.writeMessage(new LoginMessage("Player1", "passwort1", "Deutschland"));

		// Client 2 connect
		Client c2 = new Client();
		c2.connect("localhost", Constant.Server.TCP_PORT);
		c2.writeMessage(new LoginMessage("Player2", "passwort1", "USA"));

		// Client 3 connect
		Client c3 = new Client();
		c3.connect("localhost", Constant.Server.TCP_PORT);
		c3.writeMessage(new LoginMessage("Player3", "passwort1", "China"));

		// Read LoginConfirmationMessages
		LoginConfirmationMessage message1 = (LoginConfirmationMessage) c1
				.readMessage();
		assertEquals(true, message1.getSuccess());
		LoginConfirmationMessage message2 = (LoginConfirmationMessage) c2
				.readMessage();
		assertEquals(true, message2.getSuccess());
		LoginConfirmationMessage message3 = (LoginConfirmationMessage) c3
				.readMessage();
		assertEquals(true, message3.getSuccess());

		// Read LoginConfirmationMessages
		GameDataMessageToClient

	}

}
