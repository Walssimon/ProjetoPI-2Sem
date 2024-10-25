package model;

import Controller.ConectarDao;
import Controller.IcrudDao;
import java.sql.SQLException;

public class Usuario extends ConectarDao implements IcrudDao  {


    public String nome;
    public String email;
    public String senha;
    public int pkuser;
    public String img;
    
    public String userMaster = "admin";
    public String senhaMaster = "1234";
    public String nivelMaster = "Master";
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public void incluir(){
    try { sql = "insert into TB_USUARIO (NM_USUARIO, DS_EMAIL, DS_SENHA) "
        + "values (?,?,?) ";
    ps = con.prepareStatement(sql);
    ps.setString(1, nome); // Configura Parametros
    ps.setString(2, email); // Configura Parametros
    ps.setString(3, senha ); // Configura Parametros
    ps.executeUpdate(); // executa comando SQL
 this.statusSQL = null; // armazena null se deu tudo certo
 } catch (SQLException ex) {
 this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
 } }
    
    public boolean getLogin() {
        
        this.statusSQL = null;
        if (email.equals("admin") && senha.equals("1234")) 
        return true;
        
        try {
            
            sql = "SELECT * FROM TB_USUARIO WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?)) AND UCASE(TRIM(DS_SENHA)) = UCASE(TRIM(?))";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros
            ps.setString(2, senha); // Configura Parametros
            tab = ps.executeQuery(); // Executa comando SQL
            if (tab.next()){
                nome = tab.getString("NM_USUARIO");
                email = tab.getString("DS_EMAIL");
                pkuser = tab.getInt("ID_USUARIO");
                img = tab.getString("NM_IMAGEN");
                return true;   
            }
            if(email.equals(userMaster) && senha.equals(senhaMaster)){
                nome = "";
                email = "";
                pkuser = 0;
                img = "";
                return true;
            }
                // armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao tentar buscar Usuário! " + ex.getMessage();
        }
        return false;
    }
    
    public String retornarNomeUser(){
        
        String nomeUser = "";
        
        try{
            
            sql = "SELECT * FROM TB_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros
            ps.setString(2, senha); // Configura Parametros
            tab = ps.executeQuery(); // Executa comando SQL
            
            while(tab.next()){
                nomeUser = tab.getString(2);
            }
        
        }
        catch (SQLException ex){
            this.statusSQL = "Erro ao tentar buscar Usuário! " + ex.getMessage();
        }       
        
        return nomeUser;
    }
    //esse aqui a gente vai usar isso aqui pra outra coisa, pra substituir a imagem.
    public void alterar(){
    try { sql = "update TB_USUARIO set NM_USUARIO=?, DS_EMAIL=?"
        + " DS_SENHA=?, where DS_EMAIL=? ";
        ps = con.prepareStatement(sql); // prepara SQL
        ps.setString(1, nome); // Configura Parametros
        ps.setString(2, email); // Configura Parametros
        ps.setString(3, senha ); // Configura Parametros
        ps.setString(4, email ); // Configura Parametros
        ps.executeUpdate(); // executa comando SQL
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
    this.statusSQL = "Erro ao Alterar usuario ! <br> " +
    ex.getMessage();    
    } 
 }
    public void atualizarFoto() {
        try {
            sql = "UPDATE TB_USUARIO SET NM_FOTO = ? WHERE UCASE(TRIM(EMAIL)) = UCASE(TRIM(?)) ";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, img); // Configura Parametros
            ps.setString(2, email); // Configura Parametros
            ps.executeUpdate(); // executa comando SQL
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
         this.statusSQL = "Erro ao atualizar foto ! <br> " + sql + ex.getMessage();}}

@Override
public boolean salvar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public boolean deletar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public boolean buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public boolean buscarSQL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getPkuser() {
        return pkuser;
    }

    public void setPkuser(int pkuser) {
        this.pkuser = pkuser;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNivelMaster() {
        return nivelMaster;
    }

    public void setNivelMaster(String nivelMaster) {
        this.nivelMaster = nivelMaster;
    }

    public String getUserMaster() {
        return userMaster;
    }

    public void setUserMaster(String userMaster) {
        this.userMaster = userMaster;
    }

    public String getSenhaMaster() {
        return senhaMaster;
    }

    public void setSenhaMaster(String senhaMaster) {
        this.senhaMaster = senhaMaster;
    }


}
