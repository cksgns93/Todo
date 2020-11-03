package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todo.dbutil.DBUtil;
import com.todo.dto.TodoDto;

public class TodoDao {
	public int addTodo(TodoDto dto) {
		int insertCount = 0;

		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());

			insertCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertCount;
	}

	public List<TodoDto> getTodos() throws SQLException {
		List<TodoDto> todoList = new ArrayList<>();

		String sql = "SELECT id, title, name, sequence, type, date_format(regdate, '%Y-%m-%d') AS regdate FROM todo ORDER BY regdate ASC";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long id = rs.getLong("id");
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					String regDate = rs.getString("regdate");

					TodoDto todoDto = new TodoDto(id, name, regDate, sequence, title, type);
					todoList.add(todoDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return todoList;
	}

	public int updateTodo(TodoDto dto) {
		int updateCount = 0;

		String sql = "UPDATE todo SET type = ? WHERE id = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dto.getType());
			ps.setLong(2, dto.getId());

			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			updateCount = -1;
		}

		return updateCount;
	}
}
