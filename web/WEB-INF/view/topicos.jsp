<%-- 
    Document   : topicos
    Created on : 20/09/2016, 15:23:13
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tela Tópicos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a href="${pageContext.request.contextPath}" class="navbar-brand">Fórum com Gamification</a>
                    <button data-target="#navbar-main" data-toggle="collapse" type="button" class="navbar-toggle">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse" id="navbar-main">
                    <ul class="nav nav-pills navbar-right">
                        <li class="active"><a href="#">${usuario.getNome()}</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/Ranking">Ranking</a></li>
                        <li><a href="${pageContext.request.contextPath}/Topicos/Inserir">Inserir Tópico</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="bs-docs-section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h1 id="forms">Tópicos</h1>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bs-component">
                <c:if test="${not empty erro}">
                    <div class="alert alert-dismissible alert-danger">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong><c:out value="${erro}"/></strong>
                    </div>
                </c:if>
                <table class="table table-striped table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Título</th>
                            <th>Usuário</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="topico" items="${topicos}">
                            <tr> 
                                <td>${topico.getCodigo()}</td>
                                <td>${topico.getTitulo()}</td>
                                <td>${topico.getUsuario().getNome()}</td>
                                <td><a href="${pageContext.request.contextPath}/Topicos/Exibir?topico=<c:out value="${topico.getCodigo()}"/>">Exibir</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
        </div>
        <!-- jQuery (necessario para os plugins Javascript do Bootstrap) -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
