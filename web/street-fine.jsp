<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <script src="https://kit.fontawesome.com/4d31790df3.js" crossorigin="anonymous"></script>
    <script src="js/fine.js"></script>
    <title>Multe</title>
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
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/coupon">&nbsp;Codici sconto</a>
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
        <h1 style="text-align: center;">Elenco Multe</h1>
    </div>

    <br>
    <jstl:if test="${role == 'admin'}">
        <button style="width:15%;" type="button" class="btn btn-danger btn-md" data-toggle="modal" data-target="#modalFine">
            <i class="fas fa-plus"></i>&nbsp;Genera multa</button>
    </jstl:if>

    <br><br>
    <jstl:if test="${!empty fines}">
    <table id="finesTable">
        <tr style="font-weight:bold;">
            <th>Id</th>
            <th>Multa</th>
            <th>Prenotazione</th>
            <th>Autovettura</th>
            <th>Modello</th>
        </tr>
        <jstl:forEach var="it" items="${fines}">
            <tr>
                <td>${it.getId()}</td>
                <td>${it.getMulta()}</td>
                <td>${it.getPrenotazione().getId()}</td>
                <td>${it.getPrenotazione().getAutovettura().getCasaCostruttrice()}</td>
                <td>${it.getPrenotazione().getAutovettura().getModello()}</td>
            </tr>
        </jstl:forEach>
    </table>
    </jstl:if>
    </div>

    <div class="container">
        <div id="modalFine" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <label>Multa:</label>
                        <input id="multa" type="text" class="form-control" name="values" placeholder="multa" style="text-transform: lowercase;" required />
                        <label>Importo:</label>
                        <input id="importo" type="number" class="form-control" name="values" placeholder="importo" required />
                        <label>Prenotazione:</label>
                        <input id="prenotazione" type="text" class="form-control" name="values" placeholder="id" style="text-transform: lowercase;" required />
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" onclick="doFine();">Conferma</button>
                        <button type="reset" class="btn btn-danger" onclick="reset();">Annulla</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div id="modalMessage" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <h4 class="modal-message-err" style="color:red; text-align:center;"></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
