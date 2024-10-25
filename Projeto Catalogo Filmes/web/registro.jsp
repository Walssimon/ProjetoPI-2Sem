<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Usuario user = new Usuario(); // Instancia o objeto Usuario 

    if (!(user.statusSQL == null)) {
        out.println(user.statusSQL);
    }
    // preenche os atributos do objeto usuario
    String senhas = request.getParameter("senha");
    String rpsenhas = request.getParameter("rpsenha");
    String buttonval = request.getParameter("oper");

    if (senhas != null && rpsenhas != null && senhas.equals(rpsenhas) && "1".equals(buttonval)) {
        user.nome = request.getParameter("nome");
        user.email = request.getParameter("email");
        user.senha = senhas;
        user.incluir(); // chama o método para fazer a inclusão no banco de dados

    }
    if (!(user.statusSQL == null)) {
        out.println(user.statusSQL);
    }
    {
        if ("".equals(buttonval)) {
            String sHTML = "<center>Usuário criado com Sucesso!<br>"
                    + "<a href = '../login.html'> Voltar </a></center>";
            out.println(sHTML);
        }
    }
    
    String userLogado = String.valueOf(session.getAttribute("nome"));
    String emailLogado = String.valueOf(session.getAttribute("email"));
    String pkUserLogado = String.valueOf(session.getAttribute("pkuser"));
    String fotoCaminho = String.valueOf(session.getAttribute("img"));
    if(fotoCaminho == "" || fotoCaminho == null){
    fotoCaminho = "./imgs/Foto.jpg";}

%>
<!DOCTYPE html>
<html lan="pt-br">
    <head> <title> Cadastro de Usuários </title>
        <meta charset="utf-8">
    </head>
    <body>
        <div align = center>
            <form action="registro.jsp" name = formreg method="get" >
                <table align = center>
                    <tr><td></td><td>
                            <input type="hidden" name = oper id="idoper"> </td></tr>
                    <tr><td colspan = 2 align = center>*** Registro de Usuários *** </td></tr>
                    <tr><td align="right">Digite seu nome...:</td><td><input type="text" name = nome required> </td></tr>
                    <tr><td align="right">Digite seu email..:</td><td><input type="text" name = email required> </td></tr>
                    </td></tr>
                    <tr>
                        <td align="right">Digite sua Senha..:</td><td><input type="password" name = senha required></td>
                    </tr>
                        <tr>
                            <td align="right">Repita sua Senha..:</td><td><input type="password" name = rpsenha required></td>
                        </tr>
                        <form action="../Upload" name = formFoto method=post enctype="multipart/form-data">
                            <input type="hidden" name = pkuser value ="<%=pkUserLogado%>" >
                            <input type="hidden" name = email value ="<%=emailLogado%>" >
                        <table border="1" >
                            <tr><td align = center> ** Insira uma Foto ** </td></tr>
                            <tr><td align = center> <img src="<%=fotoCaminho%>" src="" style="height: 100px;width:100px;" alt="Foto" ></td></tr>
                            <tr><td> <input type="file" name="arquivo" id = "arq"></td></tr>
                            <tr><td align = center>
                                <input type="button" value = "Salvar" style="height: 20px; width:70px;background-color: rgba(92, 117, 116, 0.26);border-radius: 20%;"onclick=" if (formFoto.arquivo.value)submit();
                            else exit();"></td></tr>
                            </table> </form> <br>
                            <td>
                            </td>
                            <td align = center>
                        <input type="button" value="Voltar" onclick="window.location.assign('login.html');">
                        <input type="submit" value="Enviar" name="Enviar" onclick="formreg.oper.value = '1';"></td></tr> </table>
            </form> </div> </body> </html>