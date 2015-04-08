package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CompanyBean;
import com.beans.CustomerBean;
import com.commands.CompanyCommand;
import com.commands.CustomerCommand;
import com.commands.parameter.CommandParameter;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}


	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requester = request.getParameter("requester");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		if("company".equalsIgnoreCase(requester)){
			CompanyCommand cmd = new CompanyCommand();
			if(!cmd.execute()){
				rd = request.getRequestDispatcher("/error.jsp");
			}else{
				CompanyBean bean = cmd.getBean();
				//request.setAttribute("result", bean);
				session.setAttribute("result", bean);
				rd = request.getRequestDispatcher(cmd.getForwardingPage());
			}
			rd.forward(request, response);
		}else if("customer".equalsIgnoreCase(requester)){
			CustomerCommand cmd = new CustomerCommand();
			if(!cmd.execute()){
				rd = request.getRequestDispatcher("/error.jsp");
			}else{
				CustomerBean bean = cmd.getBean();
				//request.setAttribute("result", bean);
				session.setAttribute("result", bean);
				rd = request.getRequestDispatcher(cmd.getForwardingPage());
			}
			rd.forward(request, response);
		}
		else if("searchCustomer".equalsIgnoreCase(requester)){
			CustomerCommand cmd = new CustomerCommand();
			//this is the new line to help query
			cmd.setParameters(getParameters(request));
			if(!cmd.execute()){
				rd = request.getRequestDispatcher("/error.jsp");
			}else{
				CustomerBean bean = cmd.getBean();
				//request.setAttribute("result", bean);
				session.setAttribute("result", bean);
				rd = request.getRequestDispatcher(cmd.getForwardingPage());
			}
			rd.forward(request, response);
		}
		else if("insertCustomer".equalsIgnoreCase(requester)){
			CustomerCommand cmd = new CustomerCommand();
			//this is the new line to help query
			cmd.setParameters(getParameters(request));
			if(!cmd.execute()){
				rd = request.getRequestDispatcher("/error.jsp");
			}else{
				CustomerBean bean = cmd.getBean();
				//request.setAttribute("result", bean);
				session.setAttribute("result", bean);
				rd = request.getRequestDispatcher(cmd.getForwardingPage());
			}
			rd.forward(request, response);
		}
	}

	private synchronized ArrayList<CommandParameter>getParameters(HttpServletRequest request){
		ArrayList<CommandParameter>paramList = new ArrayList<CommandParameter>();
		request.getParameterMap();
		//loops through all the parameters in the map and stores them in a list
		for(String key : request.getParameterMap().keySet()){
			CommandParameter cp = new CommandParameter();
			cp.setName(key);
			cp.setValue(request.getParameter(key));
			paramList.add(cp);
		}
		return paramList;

	}
}
