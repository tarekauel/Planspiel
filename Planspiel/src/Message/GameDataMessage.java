package Message;

import java.io.Serializable;

public class GameDataMessage implements IMessage, Serializable {

	private int money =0;
	public GameDataMessage(int money) {
		this.money = money;
	}

	@Override
	public String getType() {
		return "GameDataMessage";
	
	}

	public int getMoney() {
		return money;
	}

	

}
