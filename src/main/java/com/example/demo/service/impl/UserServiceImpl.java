package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.example.demo.entity.Result;
import com.example.demo.entity.SqlConfig;
 import com.example.demo.mapper.TestMapper;
import com.example.demo.service.UserService;
 import com.example.demo.utils.XMLUtil;

/**
 * @ClassName:UserServiceImpl
 * @Description:
 */
@WebService(serviceName = "UserService", targetNamespace = "http://service.demo.example.com/", endpointInterface = "com.example.demo.service.UserService")
@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private TestMapper testMapper;
	//
	public String getUserName(String userId) {
	  List<SqlConfig> list = testMapper.listSqlConfig();
	  testMapper.addSqlConfig(list);
		 Result result = new Result();
			result.setSqlConfig(list);
			String resulXml =  XMLUtil.convertToXml(result) ;
		return resulXml;
	}

	public SqlConfig getSqlConfig(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSqlConfigB(String GDJDM) {
		// TODO Auto-generated method stub
		return null;
	}

	 

}