package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementDelegate {
	
	private ReimbursementService rs = new ReimbursementService();

	public void getPendingReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String idStr = request.getParameter("id");
		
		if (idStr == null) {
			List<Reimbursement> reimbursements = rs.viewPendingReim();
			
			try(PrintWriter pw = response.getWriter();){
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
			}
		} else {
			if (idStr.matches("^\\d+$")) {
				List<Reimbursement> reimbursements = rs.viewPendingReimById(Integer.parseInt(idStr));
				try(PrintWriter pw = response.getWriter()){
					pw.write(new ObjectMapper().writeValueAsString(reimbursements));
				}
			} else {
				response.sendError(400, "Invalid ID param");
			}
		}

	}
	
	public void getResolvedReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String idStr = request.getParameter("id");
		
		if (idStr == null) {
			List<Reimbursement> reimbursements = rs.viewResolvedReim();
			
			try(PrintWriter pw = response.getWriter();){
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
			}
		} else {
			if (idStr.matches("^\\d+$")) {
				List<Reimbursement> reimbursements = rs.viewResolvedReimById(Integer.parseInt(idStr));
				try(PrintWriter pw = response.getWriter()){
					pw.write(new ObjectMapper().writeValueAsString(reimbursements));
				}
			} else {
				response.sendError(400, "Invalid ID param");
			}
		}
		
	}
	
	public void getReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String idStr = request.getParameter("id");
		
		if (idStr.matches("^\\d+$")) {
			Reimbursement reimbursement = rs.viewReimbursementById(Integer.parseInt(idStr));
			
			if (reimbursement == null) {
				response.sendError(404, "No reimbursement with given ID");
			} else {
				try(PrintWriter pw = response.getWriter()){
					pw.write(new ObjectMapper().writeValueAsString(reimbursement));
				}
			}
		} else {
			response.sendError(400, "Invalid ID param");
		}
	}
	
	public void submitReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String amount = request.getParameter("amount");
		String emplId = request.getParameter("emplId");
		String description = request.getParameter("description");
		
		int newReim = rs.submitReim(Double.parseDouble(amount), Integer.parseInt(emplId), description);
		if (newReim == 1) {
			response.setStatus(201);
		} else {
			response.sendError(400);
		}
	}
	
	public void resolveReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println(request.getParameter("reimId")+request.getParameter("managerId")+request.getParameter("result")+request.getParameter("reason"));
		int reimId = Integer.parseInt(request.getParameter("reimId"));
		int managerId = Integer.parseInt(request.getParameter("managerId"));
		String result = request.getParameter("result");
		String reason = request.getParameter("reason");

		int updatedReim = rs.resolveReim(reimId, managerId, result, reason);
		if (updatedReim == 1) {
			response.setStatus(204);
		} else {
			response.sendError(400);
		}
	}
}


