<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

<%--
    <div class="row justify-content-center" style="background-color: grey; height: 100px;">
    <div class="col-6" style="background-color: red"></div>
    <div class="col-4" style="background-color: blue"></div>
    <div class="col-4" style="background-color: green"></div>
    </div>
--%> <%--col이라는 클래스는 row클래스에 감싸져있다, 최대 길이는 12--%>

<c:import url="/WEB-INF/fragment/navbar.jsp"></c:import>

<div class="container"> <%--row를 감싸준다--%>

    <div class="row justify-content-center"> <%-- 행에 justify-content-center를 준다--%>
        <div class="col-6"> <%-- col은 적절한 너비를 가지게 함, row 안에 작성 --%>

            <h3 class="mb-4">${board.id} 번 게시물</h3>

            <%-- 1rem = 16px --%>
            <div class="mb-3"> <%--margin-bottom 3--%>
                <label for="inputTitle" class="form-label">
                    <%--form-label은 label을 눌렀을 때, for에 id 값을 주면 input태그의 id로 이동시켜준다--%>
                    제목
                </label>
                <input id="inputTitle" class="form-control" type="text" value="${board.title}" readonly>
                <%--form-control은 텍스트 양식을 바꿔준다, type에 텍스트의 형식을 지정해준다--%>
            </div>
            <div class="mb-3">
                <label for="textareaContent" class="form-label">
                    본문
                </label>
                <textarea id="textareaContent" class="form-control" cols="30" rows="10"
                          readonly>${board.content}</textarea>
            </div>
            <div class="mb-3">
                <label for="inputWriter" class="form-label">
                    작성자
                </label>
                <input id="inputWriter" class="form-control" type="text" value="${board.writer}" readonly>
            </div>
            <div class="mb-3">
                <label for="inputInserted" class="form-label">
                    작성일시
                </label>
                <input id="inputInserted" class="form-control" type="datetime-local" value="${board.inserted}" readonly>
            </div>

            <div class="mb-3">
                <%-- button이 외부로 빠졌을때 form의 id를 form을 통해주면 작동한다 --%>
                <button form="formDelete" class="btn btn-danger">삭제</button>
                <a href="/modify?id=${board.id}" class="btn btn-secondary">수정</a>
                <%--btn btn-danger와 btn btn-secondary 클래스는 버튼의 디자인을 바꿔준다--%>
            </div>

        </div>
    </div>
</div>

<div style="display: none"> <%--form의 형식 button이 외부로 빠졌지만 id를 통해 작동한다--%>
    <form id="formDelete" action="/delete" method="post" onsubmit="return confirm('삭제 하시겠습니까?')">
        <input type="hidden" name="id" value="${board.id}">
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
