package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;


@WebServlet("/gbc")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action =  request.getParameter("action");
		
		if("addList".equals(action)) {
			//데이터 가져오기
			GuestBookDao guestBookDao = new GuestBookDao();
			List<GuestBookVo> guestList = guestBookDao.guestBookList();
			System.out.println(guestList);
			
			//request에 데이터 추가
			request.setAttribute("gList", guestList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/addList.jsp");
			rd.forward(request, response);
		}else if("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookVo guestBookVo = new GuestBookVo(name, password, content);
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.guestBookInsert(guestBookVo);
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}else if("deleteForm".equals(action)) {
			System.out.println("dddddd");
			
			  int no = Integer.parseInt(request.getParameter("no"));
			  GuestBookDao guestBookDao = new GuestBookDao(); 
			  GuestBookVo guestBookVo = guestBookDao.guestBookList(no);
			  
			  request.setAttribute("gListVo", guestBookVo);
			  
			  RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			  rd.forward(request, response);
		}else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestBookDao guestBookDao = new GuestBookDao();
			GuestBookVo guestBookVo = new GuestBookVo(no, password);
			int count = guestBookDao.guestBookDelete(guestBookVo);
			System.out.println(count);
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
