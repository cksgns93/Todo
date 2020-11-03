<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
<link rel="stylesheet" href="./css/newtodo.css"></link>
</head>
<body>
    <div id="container">
        <header>
            <h2>할일 등록</h2>
        </header>
        
        <section>
            <form action="./regist" method="POST">
                <label>어떤일인가요?</label>
                <input id="title" name="title" type="text" placeholder="swift 공부하기(24자까지)" maxlength="24" required>
                
                <label>누가 할일인가요?</label>
                <input id="name" name="name" type="text" placeholder="홍길동" maxlength="100" required>
                
                <label>우선순위를 선택하세요</label>
                <div class="todo_priority">
                    <input type="radio" name="sequence" value="1" required>1순위 
                    <input type="radio" name="sequence" value="2">2순위 
                    <input type="radio" name="sequence" value="3">3순위
                </div>
                
                <div class="todo_button">
                    <a href="./main">&#60;이전</a>
                    <div id="button_container">
                        <button>제출</button>
                        <button type="reset">내용지우기</button>
                    </div>
                </div>
            </form>
        </section>
    </div>
</body>
</html>