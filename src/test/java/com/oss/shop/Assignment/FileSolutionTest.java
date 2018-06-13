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
	 * @throws Exception
	 */
	@Test
	public void testFileNotDeletableWithMock() throws Exception {
		final File file = mock(File.class);
		file.createNewFile();
		//mock file & IO operations
		given(file.exists()).willReturn(true);
		given(file.delete()).willReturn(false);		
		given(file.getCanonicalPath()).willReturn("test.txt");
		
		exception.expect(IOException.class);
		exception.expectMessage(String.format("Cannot delete file '%s'.", file.getCanonicalPath()));
		
		new FileSolution().fileHandler(file);		
	}
	
	/**
	 * Solution 2
	 * @throws Exception
	 */
	@Test
	public void testFileNotDeletable() throws Exception {
		final File file = new File("test.txt");
		file.createNewFile();		
		exception.expect(IOException.class);
		exception.expectMessage(String.format("Cannot delete file '%s'.", file.getCanonicalPath()));
		//open file with another process for writing
		FileWriter fileWriter = new FileWriter(file);
		new FileSolution().fileHandler(file);
		fileWriter.close();
	}
}
