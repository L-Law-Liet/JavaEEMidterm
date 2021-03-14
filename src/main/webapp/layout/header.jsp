<%@ page import="kz.sitedev.LibrarySystem.models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Library System</title>
</head>
<body>
    <% User user = (User) request.getSession().getAttribute("user"); %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="Hello">Home</a>
                </li>
                <% if(request.getAttribute("user") == null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="Login">Log-in</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Register">Sign-up</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="Cabinet">Cabinet</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Logout">Logout</a>
                </li>
                <% } %>
            </ul>
        </div>
    </nav>
</header>
<div class="container">
    <div class="m-5">