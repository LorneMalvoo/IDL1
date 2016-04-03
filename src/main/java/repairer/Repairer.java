package repairer;

import java.util.Iterator;
import java.util.List;

public class Repairer {
	private List<String> operatorNames;
	private Iterator<String> currentOperator;
	private String path;
	
	private CompilerExecutor compiler;
	private FileExtractor extractor;
	
	private int totalStartFailures;
	
	public void repair() {
		//TODO
	}
}
