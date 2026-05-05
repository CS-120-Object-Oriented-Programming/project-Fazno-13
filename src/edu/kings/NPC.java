package edu.kings;

import java.util.Random;

public class NPC {

	private String name;
	private String description;
	private int question;
	private int progress;
	
	public NPC(String name, String description) {
		this.name = name;
		this.description = description;
		Random r = new Random();
		question = r.nextInt(10); // 0 to 9
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		String retVal = getName() + ", ";
		return retVal;
	}
	
	public int getQuestion() {
		return question;
	}
	
	public void setQuestion(int question) {
		this.question = question;
	}
	
	public String getPhrase() {
		//True is odd and False is even
		String retVal = "";
		switch (question) {
		case 1:
			retVal = "The name of this school is Aethereal Academy? (true/false)";
			break;
		case 2:
			retVal = "Hey, have you been inside the Mordain's Cave in the forest? (true/ false)";
			break;
		case 3:
			retVal = "Hey, were you at that party last night? (true/false)";
			break;
		case 4:
			retVal = "Orion is the Water Teacher here, right? (true/false)";
			break;
		case 5:
			retVal = "Orion is the Fire Teacher here, right? (true/false)";
			break;
		case 6:
			retVal = "Hey, your name is Ava right? (true/false)";
			break;
		case 7:
			retVal = "You are roommates with Harry, right? (true/false)";
			break;
		case 8:
			retVal = "Hey, have you tried the burritos in the Food Plaza? (true/false)";
			break;
		case 9:
			retVal = "Arawn is the founder of our Academy, right? (true/false)";
			break;
		case 0:
			retVal = "Have you obtained all 6 spells? (true/ false)";
			break;
		default:
			retVal = "...";
		}
		return retVal;
	}
	
	public int getAnswer() {
		Writer.println(getPhrase());
		if (getPhrase() != "...") {
			Command command = Reader.getCommand();
			if (!command.hasSecondWord()) {
				try {
					if (command.getCommandWord().equals(CommandEnum.TRUE) && question % 2 == 1) {
						Writer.println("Correct");
						Writer.println("Your Social Status went up");
						return 2;
					} else if (command.getCommandWord().equals(CommandEnum.FALSE) && question % 2 == 0) {
						Writer.println("Correct");
						Writer.println("Your Social Status went up");
						return 2;
					} else {
						Writer.println("Wrong");
						Writer.println("Your Social Status went down");
						return -1;
					}
				} catch (Exception e) {
					Writer.println(getName() + " walked away");
					Writer.println("Your Social Status went down");
					return -1;
				}
			} else {
				Writer.println(getName() + " walked away");
				Writer.println("Your Social Status went down");
				return -1;
			}
		} else {
			Writer.println("Your social is too low to talk to " + getName());
		}
		return 0;
	}
	
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
}
