package com.zlm.logmonitoring.util;

/**
 * 	@author a3325
 *	spring提供的工具类之一,用于检查一些参数
 *	比如提供非空检验	
 */
public abstract class Assert {
	public static void notNull(Object object, String message) {
		if(object == null) {
			throw new IllegalArgumentException(message);
		}
	}
}
