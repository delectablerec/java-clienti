<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <div class="form-group">
                <label>Amministratore</label>
                <input type="checkbox" name="isAdmin" value="true" ${cliente.isAdmin ? 'checked' : ''}>
            </div>
            <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Salva</button>
        </form>

        <h2>Lista Clienti</h2>
        <div class="row">
            <c:forEach var="cliente" items="${clientiList}">
    <div class="col-md-4 mb-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${cliente.nome} ${cliente.cognome}</h5>
                <p class="card-text">Email: ${cliente.email}</p>
                <p class="card-text">Amministratore: 
                    <input type="checkbox" ${cliente.isAdmin ? 'checked' : ''} disabled>
                </p>
                <a href="clienti?azione=modifica&id=${cliente.id}" class="btn btn-warning"><i class="fas fa-edit"></i> Modifica</a>
                <a href="clienti?azione=elimina&id=${cliente.id}" class="btn btn-danger"><i class="fas fa-trash-alt"></i> Elimina</a>
            </div>
        </div>
    </div>
</c:forEach>
        </div>

        <div class="mt-4">
            <a href="clienti?azione=esporta_csv" class="btn btn-success"><i class="fas fa-file-csv"></i> Salva i Clienti in File CSV</a>
        </div>