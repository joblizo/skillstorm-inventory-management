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
import com.inventorymanagement.daos.IWarehouseDao;
import com.inventorymanagement.daos.WarehouseDao;
import com.inventorymanagement.models.Warehouse;
import com.inventorymanagement.services.AddAccessService;
import com.inventorymanagement.services.URLParserService;


@WebServlet(urlPatterns = "/warehouse/*")
public class WarehouseServlet extends HttpServlet{
	IWarehouseDao _data = new WarehouseDao();
	ObjectMapper _mapper = new ObjectMapper();
	URLParserService _urlParser = new URLParserService();
	AddAccessService _cors = new AddAccessService();

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514648235914190314L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Controller Hit!");
		try {
			int id = _urlParser.extractIdFromUrl(req.getPathInfo());
			Warehouse warehouse = _data.findById(id);
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(warehouse));
			resp.setStatus(200);
			
			
		}
		catch(Exception e) {
			List<Warehouse> warehouses = _data.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(warehouses));
			resp.setStatus(200);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(req);
		InputStream reqBody = req.getInputStream();
		Warehouse warehouse = _mapper.readValue(reqBody, Warehouse.class);
		
		_data.create(warehouse);
		resp.setContentType("application/json");
		resp.getWriter().print("Inserted into DB: " + _mapper.writeValueAsString(warehouse));
		resp.setStatus(200);
		
		
//		return _data.create(employee);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Warehouse warehouse = _mapper.readValue(reqBody, Warehouse.class);
		
		_data.update(warehouse);
		resp.setContentType("application/json");
		resp.getWriter().print("Updated" + _mapper.writeValueAsString(warehouse));
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
			Warehouse warehouse = _mapper.readValue(reqBody, Warehouse.class);
			_data.delete(warehouse);
			
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(warehouse));
			resp.setStatus(200);
			
		}
	}
}
