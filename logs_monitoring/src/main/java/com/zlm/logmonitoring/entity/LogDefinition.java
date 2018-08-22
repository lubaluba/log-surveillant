package com.zlm.logmonitoring.entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class LogDefinition implements Serializable{	
	private static final long serialVersionUID = 1L;

	/**
	 * 日志名称
	 */
	private String logName;
	
	/**
	 * 日志所在地址
	 */
	private String logPath;
	
	/**
	 * 发送告警邮件邮箱地址
	 */
	private String sendMail;
	
	/**
	 * 发件邮箱授权码
	 */
	private String authCode;
	
	/**
	 * 接收邮件邮箱
	 */
	private String receiveMail;
	
}
