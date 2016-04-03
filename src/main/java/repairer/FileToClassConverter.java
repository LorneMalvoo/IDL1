package repairer;

import java.io.File;
import java.util.List;

public class FileToClassConverter {
	private List<File> files;
	
	public FileToClassConverter(List<File> files) {
		this.files = files;
	}
	
	public List<Class<?>> convert() {
		// TODO
		return null;
	}
	
	public static List<Class<?>> convert(List<File> files) {
		return new FileToClassConverter(files).convert();
	}
}
