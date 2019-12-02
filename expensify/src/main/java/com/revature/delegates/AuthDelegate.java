package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.impl.EmployeeDaoImpl;
import com.revauture.model.Employee;

public class AuthDelegate {
	
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		// check to see if there is an auth header
		if(authToken!=null) {
			String[] tokenArr = authToken.split(":");
			// if the token is formatted the way we expect, we can take the id from it and query for that user
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				if(idStr.matches("^\\d+$")) {
					// check to see if there is a valid user and if that user is the appropriate role in their token
					Employee employee = employeeDao.getEmployeeById(Integer.parseInt(idStr));
					if(employee!=null && employee.isManager()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Employee employee = employeeDao.getUserByUsernameAndPassword(username, password);

		if(employee!=null) {
			String token = employee.getUserId()+":"+employee.isManager();
			System.out.println(token);
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
		
	}
}
