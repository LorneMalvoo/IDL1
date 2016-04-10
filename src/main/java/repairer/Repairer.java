package repairer;

import java.util.List;

import spoon.processing.AbstractProcessor;

public class Repairer {
	private List<AbstractProcessor<?>> operators;
	private String root;
	
	public Repairer(List<AbstractProcessor<?>> operators, String root) {
		this.operators = operators;
		this.root = root;
	}
	
	public void repair() {
		
	}
}
