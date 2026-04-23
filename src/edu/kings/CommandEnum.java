package edu.kings;

public enum CommandEnum {
	GO("go"),
	QUIT("quit"),
	HELP("help"),
	LOOK("look"),
	STATUS("status"),
	BACK("back"),
	TURNS("turns"),
	SCORE("score"),
	EXAMINE("examine"),
	TAKE("take"),
	DROP("drop"),
	INVENTORY ("inventory"),
	UNLOCK ("unlock"),
	LOCK ("lock"),
	PACK ("pack"),
	UNPACK("unpack");
	
	private final String word;
	
	private CommandEnum(String command) {
		word= command;
	}
	
	public String getCommand() {
		return word;
	}
}
