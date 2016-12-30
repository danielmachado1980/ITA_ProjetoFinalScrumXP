<%-- 
    Document   : exibeTopico
    Created on : 20/09/2016, 15:26:02
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tela Exibe Tópico</title>
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
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/Topicos">Tópicos</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container">
            <!-- Forms
            ================================================== -->
            <div class="bs-docs-section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h1 id="forms">${topico.getTitulo()}</h1>
                            <h3 id="forms">${topico.getConteudo()}</h3>
                            <h6 id="forms">Por: ${topico.getUsuario().getNome()}</h6>
                        </div>
                    </div>
                </div>
                <c:forEach var="comentario" items="${topico.getComentarios()}">
                    <blockquote>
                        <p>${comentario.getComentario()}</p>
                        <small>Enviado por: <cite title="Source Title">${comentario.getUsuario().getNome()}</cite></small>
                    </blockquote>
                </c:forEach>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="well bs-component">
                            <form class="form-horizontal" method="POST" action="Comentario">
                                <fieldset>
                                    <legend></legend>
                                    <input type="hidden" name="topico" value="${topico.getCodigo()}">
                                    <div class="form-group">
                                        <label for=for="textArea" class="col-lg-2 control-label">Comentário</label>
                                        <div class="col-lg-10">
                                            <textarea class="form-control" name="conteudo" rows="6" id="textArea" required=""></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-10 col-lg-offset-2">
                                            <a href="${pageContext.request.contextPath}/Topicos" class="btn btn-default" role="button">Cancelar</a>
                                            <button type="submit" class="btn btn-primary">Inserir Novo Comentário</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery (necessario para os plugins Javascript do Bootstrap) -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </head>
</html>
