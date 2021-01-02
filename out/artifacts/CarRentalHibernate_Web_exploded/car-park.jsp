<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <title>Parco Auto</title>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-white bg-white">
        <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/home">Home</a>
                </li>
                <li class="nav-item active">
                    <jstl:choose>
                        <jstl:when test="${role == 'admin'}">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/coupon">&nbsp;Codici sconto</a>
                        </jstl:when>
                        <jstl:otherwise>
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/all/fines">&nbsp;Multe</a>
                        </jstl:otherwise>
                    </jstl:choose>
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
        <h1 style="text-align: center;">Parco Autovetture</h1>
    </div>

    <table style="margin:auto;">
        <tr style="font-weight:bold;">
            <th>Targa</th>
            <th>Modello</th>
            <th>Casa Costruttrice</th>
            <th>Anno Immatricolazione</th>
            <th>Categoria</th>
            <jstl:forEach var="it" items="${cars}">
                <tr>
                    <td>${it.getTarga()}</td>
                    <td>${it.getModello()}</td>
                    <td>${it.getCasaCostruttrice()}</td>
                    <td>${it.getAnnoImmatricolazione()}</td>
                    <td>${it.getCategoria().getCategoria()}</td>
                </tr>
            </jstl:forEach>
    </tr>
    </table>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
