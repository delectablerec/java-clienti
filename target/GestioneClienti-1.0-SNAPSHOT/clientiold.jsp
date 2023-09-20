<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Clienti</title>
</head>
<body>
    <h1>Gestione Clienti</h1>
    
    <h2>Inserisci/Modifica Cliente</h2>
    <form action="clienti" method="post">
        <input type="hidden" name="id" value="${cliente.id}">
        Nome: <input type="text" name="nome" value="${cliente.nome}"><br>
        Cognome: <input type="text" name="cognome" value="${cliente.cognome}"><br>
        Email: <input type="text" name="email" value="${cliente.email}"><br>
        <input type="submit" value="Salva">
    </form>
    
    <h2>Lista Clienti</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Azioni</th>
        </tr>
        <c:forEach var="cliente" items="${clientiList}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.cognome}</td>
                <td>${cliente.email}</td>
                <td>
                    <a href="clienti?azione=modifica&id=${cliente.id}">Modifica</a> | 
                    <a href="clienti?azione=elimina&id=${cliente.id}">Elimina</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="clienti?azione=esporta_csv">Salva i Clienti in File CSV</a>
</body>
</html>