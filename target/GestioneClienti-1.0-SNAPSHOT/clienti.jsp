<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Clienti</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>Gestione Clienti</h1>

        <h2>Inserisci/Modifica Cliente</h2>
        <form action="clienti" method="post" class="mb-4">
            <input type="hidden" name="id" value="${cliente.id}">
            <div class="form-group">
                <label>Nome</label>
                <input type="text" name="nome" value="${cliente.nome}" class="form-control">
            </div>
            <div class="form-group">
                <label>Cognome</label>
                <input type="text" name="cognome" value="${cliente.cognome}" class="form-control">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" name="email" value="${cliente.email}" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Salva</button>
        </form>

        <h2>Lista Clienti</h2>
        <div class="row">
            <c:forEach var="cliente" items="${clientiList}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${cliente.nome} ${cliente.cognome}</h5>
                            <p class="card-text">Email: ${cliente.email}</p>
                            <a href="clienti?azione=modifica&id=${cliente.id}" class="btn btn-warning">Modifica</a>
                            <a href="clienti?azione=elimina&id=${cliente.id}" class="btn btn-danger">Elimina</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="mt-4">
            <a href="clienti?azione=esporta_csv" class="btn btn-success">Salva i Clienti in File CSV</a>
        </div>
    </div>
</body>
</html>