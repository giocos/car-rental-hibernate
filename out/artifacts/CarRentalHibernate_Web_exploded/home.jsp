<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <script src="https://kit.fontawesome.com/4d31790df3.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>
    <style>
        input[type="date"]::-webkit-inner-spin-button,
        input[type="date"]::-webkit-calendar-picker-indicator {
            display: none;
            -webkit-appearance: none;
        }
    </style>
    <title>Homepage</title>
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
                    <jstl:choose>
                       <jstl:when test="${role == 'admin'}">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/coupon">&nbsp;Codici sconto</a>
                        </jstl:when>
                        <jstl:when test="${role == 'customer'}">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/all/fines">&nbsp;Multe</a>
                        </jstl:when>
                    </jstl:choose>
                </li>
                <li class="nav-item active">
                    <jstl:if test="${role == 'admin'}">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/create/fine">&nbsp;Genera Multa</a>
                    </jstl:if>
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
                    <jstl:if test="${username != null}">
                        <a class="nav-link active" href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                    </jstl:if>
                </li>
            </ul>
        </div>
    </nav>
    <div class="jumbotron">
        <h1 style="text-align: center;">SI2001 Noleggio Auto</h1>
    </div>

    <jstl:if test="${role == 'admin' && !empty users}">
    <div style="margin:auto;" class="col-lg-3">
        <button type="button" class="btn btn-success btn-md" onclick="location.href='${pageContext.servletContext.contextPath}/doSignUp'">
            <i class="fas fa-user-plus"></i>&nbsp;Aggiungi utente</button>
    </div>
    <input type="text" id="inputAdmin" onkeyup="searchFilter('inputAdmin', 'tableAdmin');"  class="form-control col-lg-3"  placeholder="Cerca..." />
    <br style="line-height:2px;" />
    <table id="tableAdmin">
        <tr style="font-weight:bold;">
            <th>Id</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Data di Nascita</th>
            <th>Username</th>
            <th>Prenotazioni</th>
            <th>Elimina/Aggiungi</th>
            <jstl:forEach var="it" items="${users}">
            <tr id="user${it.getId()}">
                <td>${it.getId()}</td>
                <td>${it.getNome()}</td>
                <td>${it.getCognome()}</td>
                <td>${it.getDataDiNascita()}</td>
                <td>${it.getUsername()}</td>
                <td>${it.getPrenotazioni().size()}</td>
                <td>
                    <button type="button" class="btn btn-danger btn-md" onclick="location.href='${pageContext.servletContext.contextPath}/deleteUser?id=${it.getId()}'"><i class="fas fa-trash"></i></button>
                    <button type="button" class="btn btn-primary btn-md" onclick="openModal(${it.getId()}, 'modalUpdate2', 'idUser');"><i class="fas fa-pen"></i></button>
                </td>
            </tr>
        </jstl:forEach>
    </tr>
    </table>
    </jstl:if>

    <jstl:if test="${role == 'customer' && !empty rentals}">
    <div style="margin:auto;" class="col-lg-3">
    <button type="button" class="btn btn-success btn-md" onclick="location.href='${pageContext.servletContext.contextPath}/add/rental'">
        <i class="fas fa-plus"></i>&nbsp;Crea prenotazione</button>
    </div>
    <br/><br/>
    <input type="text" id="inputCustomer" onkeyup="searchFilter('inputCustomer', 'tableCustomer');"  class="form-control col-lg-3" placeholder="Cerca..." />
    <br style="line-height:2px;" />
    <table id="tableCustomer">
        <tr>
            <th>Id</th>
            <th>Data Prenotazione</th>
            <th>Sconto</th>
            <th>Autovettura</th>
            <th>Modello</th>
            <th>Targa</th>
            <th>Elimina/Aggiungi</th>
            <jstl:forEach var="it" items="${rentals}">
            <tr id="Rental${it.getId()}">
                <td>${it.getId()}</td>
                <td>${it.getDataPrenotazione()}</td>
                <td>${it.getBuono().getImporto()}</td>
                <td>${it.getAutovettura().getCasaCostruttrice()}</td>
                <td>${it.getAutovettura().getModello()}</td>
                <td>${it.getAutovettura().getTarga()}</td>
                <td>
                    <button type="button" class="btn btn-danger btn-md" onclick="location.href='${pageContext.servletContext.contextPath}/deleteRental?id=${it.getId()}'"><i class="fas fa-trash"></i></button>
                    <button type="button" class="btn btn-primary btn-md" onclick="openModal(${it.getId()}, 'modalUpdate1', 'idRental');"><i class="fas fa-pen"></i></button>
                </td>
            </tr>
        </jstl:forEach>
        </tr>
    </table>
    </jstl:if>

    <div class="container">
        <div id="modalUpdate1" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                    <label>Id Prenotazione:</label>
                    <input id="idRental" type="text" class="form-control" name="values" placeholder="id" style="text-transform: lowercase;" />
                    <label>Autovettura:</label>
                    <select id="carOption" type="text" class="form-control" name="values" placeholder="autovettura" style="text-transform: lowercase;" >
                        <option selected disable hidden>autovettura</option>
                        <jstl:forEach var="it" items="${cars}">
                            <option>${it}</option>
                        </jstl:forEach>
                    </select>
                    <label>Modello:</label>
                    <select id="modelOption" type="text" class="form-control" name="values" placeholder="modello" style="text-transform: lowercase;" >
                        <option selected disable hidden>modello</option>
                        <jstl:forEach var="it" items="${models}">
                            <option>${it}</option>
                        </jstl:forEach>
                    </select>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" onclick="doUpdate('modalUpdate1', 'idRental', 'update/rental')">Conferma</button>
                        <button type="reset" class="btn btn-danger" onclick="reset('modalUpdate1');">Annulla</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div id="modalUpdate2" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <label>Id Utente:</label>
                        <input id="idUser" type="text" class="form-control" placeholder="id" style="text-transform: lowercase;" readonly />
                        <label>Nome:</label>
                        <input id="name" type="text" class="form-control" placeholder="nome" style="text-transform: lowercase;" />
                        <label>Cognome:</label>
                        <input id="surname" type="text" class="form-control" placeholder="cognome" style="text-transform: lowercase;" />
                        <label>Data di Nascita:</label>
                        <input id="date" type="date" class="form-control" placeholder="gg/mm/aaaa" />
                        <label>Username:</label>
                        <input id="username" type="text" class="form-control" placeholder="username" style="text-transform: lowercase;" />
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" onclick="doUpdate('modalUpdate2', 'idUser', 'update/user')">Conferma</button>
                        <button type="reset" class="btn btn-danger" onclick="reset('modalUpdate2');">Annulla</button>
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
                        <h4 class="modal-message-succ" style="color:green; text-align:center;"></h4>
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
