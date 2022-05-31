package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;
import com.javaex.webutil.WebUtil;


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
			
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/addList.jsp");
			rd.forward(request, response);
			*/
		}else if("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookVo guestBookVo = new GuestBookVo(name, password, content);
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.guestBookInsert(guestBookVo);
			
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=addList");
			//response.sendRedirect("/guestbook2/gbc?action=addList");
		}else if("deleteForm".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			rd.forward(request, response);
			*/
		}else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestBookDao guestBookDao = new GuestBookDao(); 
			GuestBookVo guestBookVo = guestBookDao.guestBookList(no);
			
			if(guestBookVo.getPassword().equals(password)) {
				
				int count = guestBookDao.guestBookDelete(guestBookVo);
				
				WebUtil.redirect(request, response, "/guestbook2/gbc?action=addList");
				//response.sendRedirect("/guestbook2/gbc?action=addList");
				System.out.println(count);
			}else {
				WebUtil.redirect(request, response, "/guestbook2/gbc?action=addList");
				//response.sendRedirect("/guestbook2/gbc?action=addList");
				System.out.println("패스워드를 잘못입력하셨습니다.");
			}
		}
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
