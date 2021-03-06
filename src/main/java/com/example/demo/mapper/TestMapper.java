package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.SqlConfig;

@Repository
@Mapper
public interface TestMapper {
	List<SqlConfig> listSqlConfig();

	public void addSqlConfig(List<SqlConfig> list);
}