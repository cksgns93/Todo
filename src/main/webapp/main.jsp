<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
<link rel="stylesheet" href="./css/main.css"></link>
<script src="./js/main.js"></script>
</head>
<body>
	<header>
		<h2>나의 해야할 일들</h2>
		<a id="link" href="./regist">새로운 TODO 등록</a>
	</header>

	<section id="list_area">
		<ul id="todo">
			<li class="list_type">TODO</li>
			<c:forEach var="todo" items="${todoList}">
				<c:if test="${todo.type eq 'TODO'}">
					<li class="list_content">
						<p class="list_title">${todo.title}</p>
						<p class="list_other">
							등록날짜: ${todo.regDate}, ${todo.name}, 우선순위 ${todo.sequence}
							<button class="update_button" id="${todo.id}">→</button>
						</p>
					</li>
				</c:if>
			</c:forEach>
		</ul>

		<ul id="doing">
			<li class="list_type">DOING</li>
			<c:forEach var="todo" items="${todoList}">
				<c:if test="${todo.type eq 'DOING'}">
					<li class="list_content">
						<p class="list_title">${todo.title}</p>
						<p class="list_other">
							등록날짜: ${todo.regDate}, ${todo.name}, 우선순위 ${todo.sequence}
							<button class="update_button" id="${todo.id}">→</button>
						</p>
					</li>
				</c:if>
			</c:forEach>
		</ul>

		<ul id="done">
			<li class="list_type">DONE</li>
			<c:forEach var="todo" items="${todoList}">
				<c:if test="${todo.type eq 'DONE'}">
					<li class="list_content">
						<p class="list_title">${todo.title}</p>
						<p class="list_other">
							등록날짜: ${todo.regDate}, ${todo.name}, 우선순위 ${todo.sequence}
						</p>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</section>
</body>
</html>