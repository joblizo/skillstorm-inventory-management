package com.inventorymanagement.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventorymanagement.daos.EmployeeDao;
import com.inventorymanagement.daos.IEmployeeDAO;
import com.inventorymanagement.models.Employee;
import com.inventorymanagement.services.URLParserService;

@WebServlet(urlPatterns = "/employee/*")
public class employeeServlet extends HttpServlet{
	IEmployeeDAO _data = new EmployeeDao();
	ObjectMapper _mapper = new ObjectMapper();
	URLParserService _urlParser = new URLParserService();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1058403466339293220L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Controller Hit!");
		try {
			int id = _urlParser.extractIdFromUrl(req.getPathInfo());
			Employee employee = _data.findById(id);
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(employee));
			resp.setStatus(200);
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "*");
			resp.addHeader("Access-Control-Allow-Headers", "*");
		}
		catch(Exception e) {
			List<Employee> employees = _data.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(employees));
			resp.setStatus(200);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(req);
		InputStream reqBody = req.getInputStream();
		Employee employee = _mapper.readValue(reqBody, Employee.class);
		
		_data.create(employee);
		resp.setContentType("application/json");
		resp.getWriter().print("Inserted into DB: " + _mapper.writeValueAsString(employee));
		resp.setStatus(200);
		
		
//		return _data.create(employee);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Employee employee = _mapper.readValue(reqBody, Employee.class);
		
		_data.update(employee);
		resp.setContentType("application/json");
		resp.getWriter().print("Updated" + _mapper.writeValueAsString(employee));
		resp.setStatus(200);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = _urlParser.extractIdFromUrl(req.getPathInfo());
			int result = _data.delete(id);
			if(result == 1) {
				resp.setContentType("application/json");
				resp.getWriter().print(String.format("ID: %s deleted", id));
				resp.setStatus(200);
			}
			else {
				resp.setContentType("application/json");
				resp.getWriter().print(String.format("ID: %s wasn't deleted", id));
				resp.setStatus(400);
			}
			
		}
		catch(Exception e) {
			InputStream reqBody = req.getInputStream();
			Employee employee = _mapper.readValue(reqBody, Employee.class);
			_data.delete(employee);
			
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(employee));
			resp.setStatus(200);
		}
	}

}
