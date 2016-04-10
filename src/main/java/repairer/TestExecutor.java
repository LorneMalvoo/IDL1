package repairer;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestExecutor {
	private List<Class<?>> classes;
	private final JUnitCore jUnitCore;
	
	private int nbTestsOK;
	private int nbTestsKO;
	
	public TestExecutor(List<Class<?>> classes) {
		this.classes = classes;
		this.jUnitCore = new JUnitCore();
	}
	
	
	public int getNbTestsOK() {
		return nbTestsOK;
	}


	public void setNbTestsOK(int nbTestsOK) {
		this.nbTestsOK = nbTestsOK;
	}


	public int getNbTestsKO() {
		return nbTestsKO;
	}


	public void setNbTestsKO(int nbTestsKO) {
		this.nbTestsKO = nbTestsKO;
	}
	
	public void setClasses(List<Class<?>> classes) {
		this.classes = classes;
	}


	public void runTests() {
		this.nbTestsKO = 0;
		this.nbTestsOK = 0;
		int total = 0;
		Result result = null;
		if (classes != null) {
			for (Class<?> c : classes) {
				result = jUnitCore.run(c);
				nbTestsKO = nbTestsKO + result.getFailureCount();
				total = total + result.getRunCount();
			}
		}
		nbTestsOK = total - nbTestsKO;
	}
}
