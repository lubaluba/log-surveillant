package com.zlm.logmonitoring.quartz;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zlm.logmonitoring.entity.LogDefinition;
import com.zlm.logmonitoring.util.JavaMailUtils;

public class LogJob implements Job {

	//任务是否正在执行标记
	private static boolean isRun = false;
	
	//指针,记录每次读取到日志文件最后位置
	private static long seed = 0;
	
	//日志详情信息
	private LogDefinition logDefinition;
	
	private String logName;
	
	public LogJob(String logName, LogDefinition logDefinition) {
		this.logDefinition = logDefinition;
		this.logName = logName;
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//具体的业务逻辑
		//打印当前时间xxxx-xx-xx xx:xx:x
		Date date =new Date();
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(! isRun) {
			isRun = true;
			System.out.println("["+sf.format(date)+"]: 开始扫描日志" + logName);
			try {
				task();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				isRun = false;
			}
		} else {
			System.out.println("上一次任务未执行完,跳过本次执行");	
		}	
		
	}
	
	/**
	 * 读取日志的任务
	 */
	public void task() throws IOException{
		File file = new File(logDefinition.getLogPath());
		if(!file.exists()){
			throw new IllegalStateException("file can not be null");		
		}
		
		//随机读取
		StringBuilder error= new StringBuilder();	
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		long len = raf.length();
		
		if(len<seed) {
			seed = 0;
		}
			
		raf.seek(seed);
		String line;
		
		//匹配日志中异常信息,此处需要改进,最好使用正则表达式
		while((line=raf.readLine())!=null) {
			if(line.contains("ERROR")) {
				error.append("<font color='red'>"+line+"</font>"+"<br/>");
				while((line=raf.readLine()).contains("Exception")) {
					error.append(line+"<br/>");
				}
				while((line=raf.readLine()).contains("at")) {
					error.append("&nbsp;&nbsp;&nbsp;&nbsp;"+line+"<br/>");	
				}
			}	
		}
		
		seed=raf.length();
		raf.close();
		if(error.length()!=0){
			System.out.println("发现异常,邮件发送中...");
			sendEmail(error.toString(),new Date()+" "+file.getName()+"  error");
		}else {
			System.out.println("未发现异常,等待下次扫描");
		}
	}

	private  void sendEmail(String message,String name) {
		JavaMailUtils.sendEmail("smtp.qq.com", logDefinition.getSendMail(), logDefinition.getAuthCode(), logDefinition.getSendMail(),
				logDefinition.getReceiveMail().split(","),
    	name,message,"text/html;charset=utf-8");
	}

	public String getLogName() {
		return logName;
	}
	
}
