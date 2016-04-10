package repairer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
import org.junit.runner.Result;

public class GetClassesByFiles {
	private List<File> files;
	
	public GetClassesByFiles(List<File> files) {
		this.files = files;
	}
	
	public List<Class<?>> convert() {
		LinkedList<Class<?>> list = new LinkedList<Class<?>>();
		try {
			String path = null;
			URLClassLoader classLoader = null;
			String name = null;
			String absolutePath = null;
			String packageWithClass = null;
			String split = null;
			String root = null;
			for(File f : files) {
				name = f.getName();
				absolutePath = f.getAbsolutePath().toString().replace('\\','/');
				split = null;
				if (name.endsWith(".class") && (absolutePath.contains(Constants.TARGET_NAME_FOLDER.replace('/','\\')))) {
					// With "\\"
					if (absolutePath.contains(Constants.TARGET_CLASSES_PATH.replace('/','\\'))) {
						split = Constants.TARGET_CLASSES_PATH.replace('/','\\');;
					} else if (absolutePath.contains(Constants.TARGET_TESTCLASSES_PATH.replace('/','\\'))) {
						split = Constants.TARGET_TESTCLASSES_PATH.replace('/','\\');
					// With "/"
					} else if (absolutePath.contains(Constants.TARGET_CLASSES_PATH.replace('\\','/'))) {
						split = Constants.TARGET_CLASSES_PATH.replace('\\','/');
					} else if (absolutePath.contains(Constants.TARGET_TESTCLASSES_PATH.replace('\\','/'))) {
						split = Constants.TARGET_TESTCLASSES_PATH.replace('\\','/');
					}				
					if (split != null) {
						root = f.getAbsolutePath().toString().substring(0,absolutePath.lastIndexOf(split));
						
						packageWithClass = f.getAbsolutePath().substring(absolutePath.lastIndexOf(split)+split.length(),absolutePath.length()-6);
						packageWithClass = packageWithClass.replace('\\','.').replace('/','.');
						
						classLoader = URLClassLoader.newInstance(new URL[] { new File(root + "\\" + Constants.TARGET_CLASSES_PATH).toURI().toURL(), new File(root + "\\" + Constants.TARGET_TESTCLASSES_PATH).toURI().toURL() }, getClass().getClassLoader());

						try {
							list.add(classLoader.loadClass(packageWithClass));
						}
						catch (ClassNotFoundException cnfe) {
							System.err.println("Impossible de trouver la classe " + f.getAbsolutePath());
						}
					}
				}
			}
		} catch (MalformedURLException mue) {
			System.err.println("Erreur chargement ClassLoader");
		}
		return list;
	}
	
	public static List<Class<?>> convert(List<File> files) {
		return new GetClassesByFiles(files).convert();
	}
	public static void main(String args[]) {
		List<File> files = new FileExtractor("C:\\Users\\Étienne\\Documents\\workspace\\toto",".class").extract();
		List<Class<?>> list = new GetClassesByFiles(files).convert();
		TestExecutor test = new TestExecutor(list);
		test.runTests();
	}
}
