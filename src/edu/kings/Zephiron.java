package edu.kings;

public class Zephiron extends NPC {
	
	private int progress;
	
	public Zephiron(String name, String description, int progress) {
		super(name, description);
		this.progress = progress;
	}
	
	@Override
	public int getProgress() {
		return progress;
	}

	@Override
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	
}
