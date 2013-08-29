package Message;

import java.io.Serializable;

public class LoginMessage implements IMessage, Serializable {
	private String name = "";
	private String password = "";

	public LoginMessage(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public String getType() {
		return "LoginMessage";

	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}
