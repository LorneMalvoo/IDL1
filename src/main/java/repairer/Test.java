package repairer;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.List;

public class Test {
	public static void main(String args[]) throws ClassNotFoundException {
		String s = "C:\\Users\\Étienne\\Documents\\workspace\\toto\\";
		File f = new File(s);
		List<File> list = new FileExtractor(s,".class").extract();
	}
}
