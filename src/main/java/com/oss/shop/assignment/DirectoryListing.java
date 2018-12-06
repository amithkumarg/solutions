package com.oss.shop.assignment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class DirectoryListing {

	public static void main(String[] args) {
		String folderPath = "C:\\00docs";
		String parentDirectory = "docs";
		System.out.println(findFilePaths(folderPath, parentDirectory));
	}
	
	public static List<String> findFilePaths(String folderPath, String parentDirectory){
		List<String> paths = new ArrayList<>();
		File f = new File(folderPath);
		for (File k : FileUtils.listFiles(f, TrueFileFilter.TRUE, TrueFileFilter.TRUE)) {			
		    paths.add(k.getPath().replace(folderPath, parentDirectory));
		 }
		return paths;
	}

}
