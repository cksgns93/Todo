package com.todo.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.dao.TodoDao;
import com.todo.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TodoDao todoDao = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<TodoDto> todoList = todoDao.getTodos();
			request.setAttribute("todoList", todoList);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "get list Error");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
			requestDispatcher.forward(request, response);
			return;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}
}
