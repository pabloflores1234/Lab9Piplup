<%@page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.tel131lab9.bean.Post" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lista empleados</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="newPost"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>New Post</h1>
        </div>
    </div>
    <div class="row">
        <form method="POST" action="PostServlet">
            <div class="mb-3">
                <label class="form-label" for="TituloPost">Title</label>
                <input type="text" class="form-control form-control-sm" id="TituloPost" name="TituloPost" placeholder="Ingresa el nombre del Post"
                       >
            </div>
            <div class="mb-3">
                <label class="form-label" for="Comentario">Comment</label>
                <input type="text" class="form-control form-control-sm" id="Comentario" name="Comentario" placeholder="Ingresa el contenido"
                       >
            </div>
            <a href="<%= request.getContextPath()%>/PostServlet?action=new" class="btn btn-danger">Cancelar</a>
            <input type="Submit" value="Save" class="btn btn-primary"/>
        </form>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
