<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        input[type="date"]::-webkit-inner-spin-button,
        input[type="date"]::-webkit-calendar-picker-indicator {
            display: none;
            -webkit-appearance: none;
        }

        button[type="submit"],
        button[type="reset"]
        {
            width:20%;
        }
    </style>
    <title>Registrazione</title>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-white bg-white">
        <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/home">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/all/cars">&nbsp;Parco auto</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/all/fines">&nbsp;Multe</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/account/${username}">&nbsp;Profilo</a>
                </li>
            </ul>
        </div>
        <div class="mx-auto order-0">
            <a class="navbar-brand.abs" href="javascript:void(0);">
                <img src="${pageContext.servletContext.contextPath}/images/logo.png" width="65px" height="65px">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="jumbotron">
        <h1 style="text-align: center;">Registrazione</h1>
    </div>

    <div id="padding" class="col-md-5">
        <form action="signup" method="POST">
                <label>Nome:</label>
                <input type="text" class="form-control" name="values" placeholder="nome" style="text-transform: lowercase;" required />
                <label>Cognome:</label>
                <input type="text" class="form-control" name="values" placeholder="cognome" style="text-transform: lowercase;" required />
                <label>Data di Nascita:</label>
                <input type="date" class="form-control" name="values" placeholder="gg/mm/aaaa" />
                <label>Username:</label>
                <input type="text" class="form-control" name="values" placeholder="username" style="text-transform: lowercase;" required />
                <label>Tipologia:</label>
                <select type="text" class="form-control" name="values" placeholder="tipologia" style="text-transform: lowercase;" required >
                    <option selected disable hidden>tipologia</option>
                    <option>admin</option>
                    <option>customer</option>
                </select>
                <label>Password:</label>
                <input type="password" class="form-control" name="values" placeholder="password" required />
                <br>
                <button type="submit" class="btn btn-success">Conferma</button>
                <button type="reset" class="btn btn-danger">Annulla</button>
        </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
