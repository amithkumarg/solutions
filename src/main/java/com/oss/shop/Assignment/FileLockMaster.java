package com.oss.shop.Assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileLockMaster {
	
	static class LOGGER{
		static void error(String arg, Exception e){};
	}
	
	//easy way to use ThreadLocal, if the batch threads are child to this thread, set release in batch shutdownHook
	InheritableThreadLocal<Lock> lock = new InheritableThreadLocal<>(); 
	//or you can create a instance or static variable here to be set in child batch threads in shutdownHook,
	//depending on batch threads will have access to instance or not respectively
	
	
	public void acquireFileLock(){
		Path PATH_TO_FILE_IN_A_CONSTANT = null;
		byte[] Executing_Job = null;
		RandomAccessFile raf = null;
		FileLock fl = null;
		FileInputStream fStream = null;
		//or you can use try with resources with while() loop inside try block
		try {
			final Path lock = Files.write(PATH_TO_FILE_IN_A_CONSTANT, Executing_Job, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			raf = new RandomAccessFile(lock.toFile(), "r");
			fStream = new FileInputStream(raf.getFD()); 
			fl = fStream.getChannel().lock(0, Long.MAX_VALUE, true);
			
			/**
			 * create batch threads here or outside to complete the job
			 */
			
		} catch (final IOException e) {
			LOGGER.error("Error while trying to write file. ERROR: " + e.getMessage(), e);
			Runtime.getRuntime().exit(-1);
		} finally{
			try {
				while (true) {
					if (this.lock.get().release) {
						raf.close();
						fStream.close();
						fl.close();
						break;
					}
				}
			} catch (Exception e){
				//ignore
			}
		}
	}
}

class Lock{
	boolean release;
}