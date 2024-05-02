<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-expand-lg bg-body-tertiary mb-4">
    <div class="container">
        <a class="navbar-brand" href="/">PROJECT</a>
        <%-- PROJECT 부분이 아이콘이 들어가는 위치, href는 아이콘을 눌렀을 때 가지는 경로--%>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="/" class="nav-link">
                        목록
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/add" class="nav-link">
                        글쓰기
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/member/list" class="nav-link">
                        회원목록
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/member/signup" class="nav-link">
                        회원가입
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>