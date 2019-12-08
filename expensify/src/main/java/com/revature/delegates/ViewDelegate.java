package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		System.out.println("in our view delegate; resolving uri: "+ uri);
		
		switch(uri) {
		case "/home":
			request.getRequestDispatcher("/static/Views/home.html").forward(request, response);
			break;
		case "/landing":
			request.getRequestDispatcher("/static/Views/landing.html").forward(request, response);
			break;
		case "/edit":
			request.getRequestDispatcher("/static/Views/updateemployee.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		
		}
		
	}
}