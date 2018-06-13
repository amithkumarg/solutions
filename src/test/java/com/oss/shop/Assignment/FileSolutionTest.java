package com.oss.shop.Assignment;

import static org.mockito.BDDMockito.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileSolutionTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * Solution 1
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileNotDeletableWithMock() throws Exception {
		final File file = mock(File.class);
		file.createNewFile();
		// mock file & IO operations
		given(file.exists()).willReturn(true);
		given(file.delete()).willReturn(false);
		given(file.getCanonicalPath()).willReturn("test.txt");

		exception.expect(IOException.class);
		exception.expectMessage(String.format("Cannot delete file '%s'.", file.getCanonicalPath()));

		new FileSolution().fileHandler(file);
	}

	/**
	 * Solution 2
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileNotDeletable() throws Exception {
		File file = null;
		FileWriter fileWriter = null;
		try{
			file = new File("test.txt");
			file.createNewFile();
			file.deleteOnExit();
			exception.expect(IOException.class);
			exception.expectMessage(String.format("Cannot delete file '%s'.", file.getCanonicalPath()));
			// open file with another process for writing
			fileWriter = new FileWriter(file, true);
			new FileSolution().fileHandler(file);
		} finally{
			if(fileWriter != null){
				fileWriter.flush();
				fileWriter.close();
			}
		}
	}
}
