/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.DriverManager; // Driver para abrir Conexão
import java.sql.ResultSet;
import java.sql.SQLException; // Tratamento de Erros SQL
import java.sql.Connection; // Armazena a Conexão Aberta
import java.sql.PreparedStatement;
public class ConectarDao {

    public Connection con = null;
    public String sql = null;
    public PreparedStatement ps = null;
    public String htmlError = null;
    public ResultSet tab = null;
    public String MeuBanco = "catalogofilms";
    public String statusSQL; /* Na variável statusSQl conterá null quando não tiver erros
Mas quando haver erros conterá a mensagem de erro capturada
Pela excessão da clausula try. */

    public ConectarDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            criarBanco();
            statusSQL = null;
        } catch (ClassNotFoundException ex) {
            htmlError = "Driver JDBC não encontrado! " + ex.getMessage();
        } catch (SQLException ex) {
            htmlError = "Servidor fora do ar ou Erro no comando SQL !" + ex.getMessage();
        }

    }

    public void criarBanco() {
        try {
            //VOU CONCERTAR ISSO DEPOIS DE VER O DIAGRAMA DO BANCO
            ps = con.prepareStatement("create database if not exists CATALOGOFILMS");
            ps.executeUpdate();
            //VOU CONCERTAR ISSO DEPOIS DE VER O DIAGRAMA DO BANCO
            ps = con.prepareStatement("use CATALOGOFILMS");
            ps.executeUpdate(); // seleciona banco
            //VOU CONCERTAR ISSO DEPOIS DE VER O DIAGRAMA DO BANCO
            sql = "CREATE TABLE IF NOT EXISTS TB_USUARIO ("
                    + "ID_USUARIO int not null AUTO_INCREMENT ,"
                    + "NM_USUARIO varchar(200) not null ,"
                    + "DS_EMAIL varchar(200) not null UNIQUE,"
                    + "DS_SENHA varchar(200) not null ,"
                    + "NM_IMAGEN varchar(20) not null ,"
                    + "primary key (ID_USUARIO))";
            
            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL

        } catch (SQLException err) {
            htmlError = err.getMessage();
        }
    }
}

