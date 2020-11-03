package com.todo.newtodo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.dao.TodoDao;
import com.todo.dto.TodoDto;

@WebServlet("/regist")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TodoDao todoDao = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/newtodo.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));

		TodoDto todoDto = new TodoDto();
		todoDto.setName(name);
		todoDto.setTitle(title);
		todoDto.setSequence(sequence);

		int registResult = todoDao.addTodo(todoDto);

		if (registResult > 0) {
			response.sendRedirect("./main");
		} else {
			request.setAttribute("error", "regist Error");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
