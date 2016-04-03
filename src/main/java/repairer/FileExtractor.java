package repairer;

import java.io.File;
import java.util.List;

public class FileExtractor {
	
	private File path;
	private String extension;
	
	public FileExtractor(File path, String extension) {
		this.path = path;
		this.extension = extension;
	}
	public FileExtractor(String path, String extension) {
		this(new File(path),extension);
	}

	public List<File> extract() {
		//TODO
		return null;
	}
	
	public static List<File> extract(File path, String extension) {
		return new FileExtractor(path,extension).extract();
	}
	public static List<File> extract(String path, String extension) {
		return new FileExtractor(path,extension).extract();
	}
}
