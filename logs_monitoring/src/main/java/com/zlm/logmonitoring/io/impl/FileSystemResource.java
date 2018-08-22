package com.zlm.logmonitoring.io.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.zlm.logmonitoring.io.Resource;
public class FileSystemResource implements Resource {
	
	private File file;
	
	public FileSystemResource(String path){
		file = new File(path);
	}
	
	@Override
	public InputStream getInputStream() throws FileNotFoundException{
		
		return new FileInputStream(file);
	}
}
