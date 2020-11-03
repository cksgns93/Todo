window.onload = function() {
    let updateButtons = document.querySelectorAll(".update_button");

    updateButtons.forEach((button) => {
        button.addEventListener("click", function(event) {
            updateTodo(event.target);
        })
    })
}

function updateTodo(targetButton) {
    let httpRequest = new XMLHttpRequest();
    let todoId = targetButton.id;
    let todoType = targetButton.closest("ul").id;
    let todoTypeUpper = todoType.toUpperCase();

    let todoData = {
        todoId: todoId,
        todoType: todoTypeUpper
    }

    httpRequest.addEventListener("load", function() {
        let updateState = httpRequest.responseText;
        if (updateState === "updateFail") {
            alert("할일 업데이트에 실패했습니다.");
            return;
        }

        moveTodo(targetButton, todoType);
        if (todoType === "doing") {
            alert("success");
        }
    })

    httpRequest.open("PUT", "./update", true);
    httpRequest.setRequestHeader('Content-type', 'application/json')
    httpRequest.send(JSON.stringify(todoData));
}

function moveTodo(targetButton, todoType) {
    let nextUl = document.getElementById(todoType).nextElementSibling;
    let targetLi = targetButton.closest("li");
    nextUl.appendChild(targetLi);

    if (todoType === "doing") {
        let targetP = targetButton.closest("p");
        targetP.removeChild(targetButton);
    }
}