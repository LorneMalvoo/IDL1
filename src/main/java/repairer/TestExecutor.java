package repairer;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestExecutor {
	public List<Class<?>> classes;
	
	public TestExecutor(List<Class<?>> classes) {
		this.classes = classes;
	}
	
	public Result runTests() {
		return JUnitCore.runClasses((Class<?>[]) classes.toArray());
	}
}
