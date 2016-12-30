<%-- 
    Document   : insereTopico
    Created on : 20/09/2016, 15:26:50
    Author     : Daniel
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tela Insere Tópico</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <!-- Forms
            ================================================== -->
            <div class="bs-docs-section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h1 id="forms">Inserir Tópico</h1>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="well bs-component">
                            <form class="form-horizontal" method="POST">
                                <c:if test="${not empty erro}">
                                    <div class="alert alert-dismissible alert-danger">
                                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                                        <strong><c:out value="${erro}"/></strong>
                                    </div>
                                </c:if>
                                <fieldset>
                                    <legend></legend>
                                    <div class="form-group">
                                        <label for="inputTitulo" class="col-lg-2 control-label">Título</label>
                                        <div class="col-lg-10">
                                            <input type="text" class="form-control" name="titulo" placeholder="Insira o título" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for=for="textArea" class="col-lg-2 control-label">Conteúdo</label>
                                        <div class="col-lg-10">
                                            <textarea class="form-control" name="conteudo" rows="3" id="textArea" required=""></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-10 col-lg-offset-2">
                                            <a href="${pageContext.request.contextPath}/Topicos" class="btn btn-default" role="button">Cancelar</a>
                                            <button type="submit" class="btn btn-primary">Enviar</button>
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
    </body>
</html>
