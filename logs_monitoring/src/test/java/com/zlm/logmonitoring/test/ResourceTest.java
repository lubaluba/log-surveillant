package com.zlm.logmonitoring.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.zlm.logmonitoring.io.Resource;
import com.zlm.logmonitoring.io.impl.ClassPathResource;
import com.zlm.logmonitoring.io.impl.FileSystemResource;

public class ResourceTest {
	
	@Test
	public void testClassPathResuorce() throws IOException {
		Resource resource = new ClassPathResource("test.xml");
		
		InputStream is = resource.getInputStream();
		assertNotNull(is);
	}
	
	@Test
	public void testFileSystemResuorce() throws IOException {
		Resource resource = new FileSystemResource("src\\test\\resources\\test.xml");
		InputStream is = resource.getInputStream();
		assertNotNull(is);
	}
}
