package com.zlm.logmonitoring.xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zlm.logmonitoring.entity.LogDefinition;
import com.zlm.logmonitoring.factory.LogDefinitionRegisty;
import com.zlm.logmonitoring.io.Resource;

public class XmlLogDefinitionReader {
	
	private static final String PROJECTS_ELEMENT = "projects";
	private static final String LOG_NAME_ELEMENT = "log-name";
	private static final String LOG_PATH_ELEMENT = "log-path";
	private static final String ADDRESSER_MAIL_ELEMENT = "addresser-mail";
	private static final String ADDRESSEE_MAIL_ELEMENT = "addressee-mail";
	private static final String AUTHCODE_ELEMENT = "authcode";
	
	private LogDefinitionRegisty registy;
	
	public XmlLogDefinitionReader(LogDefinitionRegisty registy) {
		this.registy = registy;
	}
	
	@SuppressWarnings("unchecked")
	public void loadLogDefinition(Resource r) {
		LogDefinition logDefinition = new LogDefinition();
		try(InputStream in = r.getInputStream()){
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root  = doc.getRootElement();
			Element projects = root.element(PROJECTS_ELEMENT);
			Iterator<Element> projectsIter = projects.elementIterator();
			while(projectsIter.hasNext()) {
				//开始遍历project元素
				Element project = projectsIter.next();
				
				Element logName = project.element(LOG_NAME_ELEMENT);
				Element logPath = project.element(LOG_PATH_ELEMENT);
				if(logName != null && logPath != null) {
					logDefinition.setLogName(logName.getStringValue());
					logDefinition.setLogPath(logPath.getStringValue());
				}
				
				Element sendMail = project.element(ADDRESSER_MAIL_ELEMENT);
				if (sendMail != null) {
					logDefinition.setSendMail(sendMail.getStringValue());
				}
				
				Element authCode = project.element(AUTHCODE_ELEMENT);
				if (authCode != null) {
					logDefinition.setAuthCode(authCode.getStringValue());
				}
				
				Element receiveMail = project.element(ADDRESSEE_MAIL_ELEMENT);
				if(receiveMail != null) {
					logDefinition.setReceiveMail(receiveMail.getStringValue());
				}
				
				registy.registerLogDefinition(logDefinition);
				
			}
		}catch(IOException e){
			e.printStackTrace();
		} catch (DocumentException e) {
		
			e.printStackTrace();
		}
	}
}
