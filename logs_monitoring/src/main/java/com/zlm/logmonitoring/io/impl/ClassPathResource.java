package com.zlm.logmonitoring.io.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.zlm.logmonitoring.io.Resource;
import com.zlm.logmonitoring.util.ClassUtils;

public class ClassPathResource implements Resource {

	private final String path;
	private final ClassLoader classLoader;
	
	public ClassPathResource(String path) {
		this(path,(ClassLoader)null);
	}
	public ClassPathResource(String path, ClassLoader classLoader) {
		this.path = path;
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = this.classLoader.getResourceAsStream(this.path);
		if(in == null) {
			throw new FileNotFoundException(path + "can not be opened");
		}
		return in;
	}

	public String getDescription() {
		return this.path;
	}

}
