<%@page import="model.Usuario"%>
<%
    Usuario user = new Usuario();
    
    if ( user.getLogin() == true ) { // faz o login no objeto user
        session.setAttribute("nome", user.nome);
        session.setAttribute("email", user.email);
        session.setAttribute("pkuser", user.pkuser);
    if (user.img == null) user.img = " ";
        session.setAttribute("foto", user.img);
        response.sendRedirect("index.jsp");// carrega a página de sistema
    } else {
        String sHTML = "<center>Opa! Erro de Login! " + user.statusSQL
        + "<br><a href = '../index.html'> Voltar </a></center>";
        out.println(sHTML);
    }%>


