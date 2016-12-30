<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html >
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tela Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
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
                        <li><a href="${pageContext.request.contextPath}/Cadastrar">Cadastrar Usuário</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container">
            <form class="form-signin" method="POST">
                <c:if test="${not empty erro}">
                    <div class="alert alert-dismissible alert-danger">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong><c:out value="${erro}"/></strong>
                    </div>
                </c:if>
                <h2 class="form-signin-heading">Autenticação</h2>
                <label for="inputLogin" class="sr-only">Login</label>
                <input type="text" id="inputLogin" name="login" class="form-control" placeholder="Entre com o login" required autofocus>
                <label for="inputPassword" class="sr-only">Senha</label>
                <input type="password" id="inputPassword" name="senha" class="form-control" placeholder="Entre com a senha" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Enviar</button>
            </form>

        </div> <!-- /container -->
        <!-- jQuery (necessario para os plugins Javascript do Bootstrap) -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
