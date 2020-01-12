package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.beans.User;
import com.dev.services.UserServices;
import com.dev.services.UserServicesImpl;

@WebServlet("/loginServ")
public class LoginServ extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("user_id");
		String password = req.getParameter("passwd");
		System.out.println(uid);
		System.out.println(password);
		UserServices services = new UserServicesImpl();
		User user = services.login(Integer.parseInt(uid), password);
		if(user != null){
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("./Home.jsp");
		}else{
			resp.sendRedirect("./Login.html");
		}
	}
}