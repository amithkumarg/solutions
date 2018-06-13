package com.oss.shop.Assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSolution {

	public static void main(String[] args) throws IOException {
		File file = new File("c:\\Git\\Dev\\test.txt");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write("hello world 123");
		fileWriter.close();
		System.out.println(file.delete());		
	}
	
	public void fileHandler(File file) throws IOException, Exception {
		if (file.exists()) {
			LOG.warn("File {} already exists, file will be replaced.", file.getCanonicalPath());
			if (!file.delete()) {
				logAndThrow(String.format("Cannot delete file '%s'.", file.getCanonicalPath()), new IOException(String.format("Cannot delete file '%s'.", file.getCanonicalPath())));
			}
		}
	}
	
	private void logAndThrow(String str, Exception exception) throws Exception{
		LOG.warn(str);
		throw exception;
	}

}

class LOG{
	static void warn(String... strings){
		System.out.println(strings);
	}
}
