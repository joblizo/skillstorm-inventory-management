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
import com.inventorymanagement.daos.IInventoryDao;
import com.inventorymanagement.daos.InventoryDao;
import com.inventorymanagement.models.Inventory;
import com.inventorymanagement.services.AddAccessService;
import com.inventorymanagement.services.URLParserService;

@WebServlet(urlPatterns = "/inventory/*")
public class InventoryServlet extends HttpServlet {
	IInventoryDao _data = new InventoryDao();
	ObjectMapper _mapper = new ObjectMapper();
	URLParserService _urlParser = new URLParserService();
	AddAccessService _addAccess = new AddAccessService(); //alernative to CORS filter

	/**
	 * 
	 */
	private static final long serialVersionUID = -3147730135087791919L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Inventory Servlet");
		try {
			int id = _urlParser.extractIdFromUrl(req.getPathInfo());
			Inventory inventory = _data.findById(id);
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(inventory));
			resp.setStatus(200);
			
			
		}
		catch(Exception e) {
			List<Inventory> inventorys = _data.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(inventorys));
			resp.setStatus(200);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post for Inventory");
		InputStream reqBody = req.getInputStream();
		Inventory inventory = _mapper.readValue(reqBody, Inventory.class);
		
		_data.create(inventory);
		resp.setContentType("application/json");
		resp.getWriter().print("Inserted into DB: " + _mapper.writeValueAsString(inventory));
		resp.setStatus(200);
		
		
//		return _data.create(inventory);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("PUT REQUEST!");
			InputStream reqBody = req.getInputStream();
			Inventory inventory = _mapper.readValue(reqBody, Inventory.class);
			
			_data.update(inventory);
			System.out.println(_mapper.writeValueAsString(inventory));
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(inventory));
			resp.setStatus(200);
		}
		catch(Exception e) {
		
		}
		
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
			Inventory inventory = _mapper.readValue(reqBody, Inventory.class);
			_data.delete(inventory);
			
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(inventory));
			resp.setStatus(200);
			
		}
	}

}
