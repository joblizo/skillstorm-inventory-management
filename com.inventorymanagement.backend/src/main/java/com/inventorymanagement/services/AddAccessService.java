package com.inventorymanagement.services;

import javax.servlet.http.HttpServletResponse;

	public class AddAccessService {
		public HttpServletResponse addAccess(HttpServletResponse resp) {
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE,PATCH");
			resp.addHeader("Access-Control-Allow-Headers", "*");
			return resp;
		}
}
