package com.inventorymanagement.services;

public class URLParserService {
	public int extractIdFromUrl(String url) {
		String[] splitString = url.split("/");
		return Integer.parseInt(splitString[1]);
	}
}
