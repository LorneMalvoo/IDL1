package repairer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CompilerExecutor {
	private List<File> files;	
	private String targetPath;
	
	public CompilerExecutor(List<File> files, String targetPath) {
		this.files = files;
		this.targetPath = targetPath;
	}
	
	public void compile() {
		//TODO
	}
	
	public static void compile(List<File> files, String targetPath) {
		new CompilerExecutor(files,targetPath).compile();
	}
}