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
	INVENTORY ("inventory"),
	UNLOCK ("unlock"),
	PLACE ("place"),
	UNPACK("unpack"),
	CAST("cast"),
	TALK("talk"),
	TRUE("true"),
	FALSE("false");
	
	private final String word;
	
	private CommandEnum(String command) {
		word= command;
	}
	
	public String getCommand() {
		return word;
	}
}
