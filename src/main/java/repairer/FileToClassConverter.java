package repairer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

public class FileToClassConverter {
	private List<File> files;
	
	public FileToClassConverter(List<File> files) {
		this.files = files;
	}
	
	public List<Class<?>> convert() {
		LinkedList<Class<?>> list = new LinkedList<Class<?>>();
		String name = null;
		String absolutePath = null;
		String res = null;
		String split = null;
		for(File f : files) {
			name = f.getName();
			absolutePath = f.getAbsolutePath().toString();
			res = null;
			split = null;
			if (name.endsWith(".class")) {
				if (absolutePath.contains(Constants.TARGET_CLASSES_PATH)) {
					split = Constants.TARGET_CLASSES_PATH;
				} else if (absolutePath.contains(Constants.TARGET_TESTCLASSES_PATH)) {
					split = Constants.TARGET_TESTCLASSES_PATH;
				} else if (absolutePath.contains(Constants.SRC_CLASSES_PATH)) {
					split = Constants.SRC_CLASSES_PATH;
				} else if (absolutePath.contains(Constants.SRC_TESTCLASSES_PATH)) {
					split = Constants.SRC_TESTCLASSES_PATH;
				}
				if (split != null) {
					res = f.getAbsolutePath().substring(absolutePath.lastIndexOf(split)+split.length());
					res = res.replace('\\','.').replace('/','.');
					try {
						list.add(Class.forName(res));
					}
					catch (ClassNotFoundException cnfe) {
						System.err.println("Impossible de trouver la classe " + f.getAbsolutePath());
					}
				}
			}
		}
		return list;
	}
	
	public static List<Class<?>> convert(List<File> files) {
		return new FileToClassConverter(files).convert();
	}
}
