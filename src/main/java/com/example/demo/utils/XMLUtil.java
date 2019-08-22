package com.example.demo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

/**
 * ��װ��XMLת����object��objectת����XML�Ĵ���
 * 
 * @author Steven
 * 
 */
public class XMLUtil {
	/**
	 * ������ֱ��ת����String���͵� XML���
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertToXml(Object obj) {
		// ���������
		StringWriter sw = new StringWriter();
		try {
			// ����jdk���Դ���ת����ʵ��
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
			// ��ʽ��xml����ĸ�ʽ
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// ������ת�����������ʽ��xml
			marshaller.marshal(obj, sw);
		} catch (JAXBException e) {
			//e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * ���������·��ת����xml�ļ�
	 * 
	 * @param obj
	 * @param path
	 * @return
	 */
	public static void convertToXml(Object obj, String path) {
		try {
			// ����jdk���Դ���ת����ʵ��
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// ��ʽ��xml����ĸ�ʽ
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// ������ת�����������ʽ��xml
			// ���������
			FileWriter fw = null;
			try {
				fw = new FileWriter(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			marshaller.marshal(obj, fw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��String���͵�xmlת���ɶ���
	 * 
	 * @param clazz
	 * @param xmlStr
	 * @param xsdSchemaFile
	 *            ���ò���Ϊ��ʱ����У��
	 * @return
	 * @throws SAXException
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertXmlStrToObject(Class clazz, String xmlStr, File xsdSchemaFile) throws SAXException {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			// ���н�Xmlת�ɶ���ĺ��Ľӿ�
			Unmarshaller unmarshaller = context.createUnmarshaller();

			if (null != xsdSchemaFile) {
				SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
				Schema schema = schemaFactory.newSchema(xsdSchemaFile);
				unmarshaller.setSchema(schema);
			}

			StringReader sr = new StringReader(xmlStr);
			xmlObject = unmarshaller.unmarshal(sr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

	/**
	 * ��file���͵�xmlת���ɶ���
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			FileReader fr = null;
			try {
				fr = new FileReader(xmlPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			xmlObject = unmarshaller.unmarshal(fr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

}