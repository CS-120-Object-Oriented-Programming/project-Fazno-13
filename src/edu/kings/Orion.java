package edu.kings;

public class Orion extends NPC {
	
	private int progress;
	
	public Orion(String name, String description, int progress) {
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
