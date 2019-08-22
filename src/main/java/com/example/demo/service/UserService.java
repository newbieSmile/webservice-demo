package com.example.demo.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.example.demo.entity.SqlConfig;
 
@WebService
public interface UserService {

    @WebMethod 
    public SqlConfig getSqlConfig(@WebParam(name = "ID") String id);

    @WebMethod
    @WebResult(name="String",targetNamespace="")
    public String getUserName(@WebParam(name = "ID") String id);

}  