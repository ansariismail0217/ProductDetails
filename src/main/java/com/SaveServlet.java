package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


@WebServlet("/save")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("pname");
		String price = request.getParameter("pprice");
		String hdd = request.getParameter("hdd");
		String cpu = request.getParameter("cpu");
		String ram = request.getParameter("ram");
		
		ProductParts parts = new ProductParts(hdd, cpu, ram);
		
		EProduct product = new EProduct(name, new BigDecimal(price), parts);
		
		response.setContentType("text/html");
		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session= factory.openSession();
		
		Transaction t = session.beginTransaction();
		session.save(product);
		t.commit();
		response.sendRedirect("jstlSql.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
