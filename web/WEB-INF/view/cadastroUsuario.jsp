<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : cadastroUsuario
    Created on : 20/09/2016, 15:21:54
    Author     : Daniel
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tela Cadastro</title>
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
                            <h1 id="forms">Cadastro de Usuário</h1>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="well bs-component">
                            <form class="form-horizontal" action="Cadastrar" method="POST">
                                <c:if test="${not empty erro}">
                                    <div class="alert alert-dismissible alert-danger">
                                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                                        <strong><c:out value="${erro}"/></strong>
                                    </div>
                                </c:if>
                                <fieldset>
                                    <legend></legend>
                                    <div class="form-group">
                                        <label for="inputNome" class="col-lg-2 control-label">Nome</label>
                                        <div class="col-lg-10">
                                            <input type="text" class="form-control" name="nome" placeholder="Insira o Nome" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                                        <div class="col-lg-10">
                                            <input type="email" class="form-control" name="email" placeholder="Insira o Email" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputLogin" class="col-lg-2 control-label">Login</label>
                                        <div class="col-lg-10">
                                            <input type="text" class="form-control" name="login" placeholder="Insira o Login" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword" class="col-lg-2 control-label">Senha</label>
                                        <div class="col-lg-10">
                                            <input type="password" class="form-control" name="senha" placeholder="Insira a Senha" required>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-10 col-lg-offset-2">
                                            <a href="${pageContext.request.contextPath}" class="btn btn-default" role="button">Cancelar</a>
                                            <button type="submit" class="btn btn-primary">Enviar</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
