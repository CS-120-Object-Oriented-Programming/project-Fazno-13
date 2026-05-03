package edu.kings;

public enum Spell {
	FIRE("fire",false),
	WATER("water",false),
	EARTH("earth",false),
	WIND("wind",false),
	DARK("dark",false);

	private final String word;
	private boolean able;
	
	private Spell(String spell, boolean able) {
		word= spell;
		this.able = able;
	}
	
	public String getSpell() {
		return word;
	}
	
	public boolean isAble() {		
		return able;
	}
	
	public void setAble() {
		this.able = true;
	}
}
