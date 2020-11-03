package com.todo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.dao.TodoDao;
import com.todo.dto.TodoDto;

@WebServlet("/update")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TodoDao todoDao = new TodoDao();
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String[] TYPE = {"TODO", "DOING", "DONE"};

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> updateTarget = mapper.readValue(getBody(request), new TypeReference<Map<String, Object>>() {});

		long todoId = Long.parseLong((String)updateTarget.get("todoId"));
		String todoType = (String)updateTarget.get("todoType");

		TodoDto todoDto = new TodoDto();
		todoDto.setId(todoId);
		if (TYPE[0].equals(todoType)) {
			todoDto.setType(TYPE[1]);
		} else if (TYPE[1].equals(todoType)) {
			todoDto.setType(TYPE[2]);
		}

		int updateResult = todoDao.updateTodo(todoDto);

		if (updateResult < 0) {
			PrintWriter out = response.getWriter();
			out.print("updateFail");
		}
	}

	public static String getBody(HttpServletRequest request) throws IOException {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}
}
