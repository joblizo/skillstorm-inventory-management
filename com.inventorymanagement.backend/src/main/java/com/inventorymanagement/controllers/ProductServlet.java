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
import com.inventorymanagement.daos.IProductDao;
import com.inventorymanagement.daos.ProductDao;
import com.inventorymanagement.models.Product;
import com.inventorymanagement.services.AddAccessService;
import com.inventorymanagement.services.URLParserService;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -416311452055131437L;
	IProductDao _data = new ProductDao();
	ObjectMapper _mapper = new ObjectMapper();
	URLParserService _urlParser = new URLParserService();
	AddAccessService _cors = new AddAccessService();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Controller Hit!");
		try {
			int id = _urlParser.extractIdFromUrl(req.getPathInfo());
			Product product = _data.findById(id)	;
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(product));
			resp.setStatus(200);
			
			
		}
		catch(Exception e) {
			List<Product> products = _data.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(products));
			resp.setStatus(200);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post request");
		InputStream reqBody = req.getInputStream();
		Product product = _mapper.readValue(reqBody, Product.class);
		
		_data.create(product);
		resp.setContentType("application/json");
		resp.getWriter().print(_mapper.writeValueAsString(product));
		resp.setStatus(200);
		
		System.out.println("Saved new Data");
		
//		return _data.create(employee);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post request");
		InputStream reqBody = req.getInputStream();
		Product product = _mapper.readValue(reqBody, Product.class);
		
		_data.update(product);
		resp.setContentType("application/json");
		resp.getWriter().print("Updated" + _mapper.writeValueAsString(product));
		resp.setStatus(200);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post request");
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
			Product product = _mapper.readValue(reqBody, Product.class);
			_data.delete(product);
			
			resp.setContentType("application/json");
			resp.getWriter().print(_mapper.writeValueAsString(product));
			resp.setStatus(200);
			
		}
	}
}
