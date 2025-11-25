/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Usuario;

/**
 *
 * @author gregory
 */
public class UsuarioDAO {
    public void create(Usuario usu) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "INSERT INTO usuarios(nomeUsu, emailUsu, senhaUsu, tipoUsu) VALUES(?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, usu.getNomeUsu());
            stmt.setString(2, usu.getEmailUsu());
            stmt.setString(3, usu.getSenhaUsu());
            stmt.setString(4, usu.getTipoUsu());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Uso cadastrado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Usuario. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Usuario> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList();
        
        try {
            String query = "SELECT * FROM usuarios";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("idUsu"));
                usu.setNomeUsu(rs.getString("nomeUsu"));
                usu.setEmailUsu(rs.getString("emailUsu"));
                usu.setTipoUsu(rs.getString("tipoUsu"));
                
                listaUsuarios.add(usu);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar usuarios. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return listaUsuarios;
    }
    
    // Traz um objeto específico a partir do id
    public Usuario read(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM usuarios WHERE idUsu = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("idUsu"));
                usu.setNomeUsu(rs.getString("nomeUsu"));
                usu.setEmailUsu(rs.getString("emailUsu"));
                usu.setTipoUsu(rs.getString("tipoUsu"));
                return usu;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Usuario. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    
    public void update(Usuario usu) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE usuarios SET nomeUsu = ?, emailUsu = ?, senhaUsu = ?, tipoUsu = ? WHERE idUsu = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, usu.getNomeUsu());
            stmt.setString(2,usu.getEmailUsu());
            stmt.setString(3,usu.getSenhaUsu());
            stmt.setString(4, usu.getTipoUsu());
            stmt.setInt(5, usu.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar Usuario. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy(Usuario usu) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM usuarios WHERE idUsu = ?";
            stmt = con.prepareStatement(query);            
            stmt.setInt(1, usu.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario excluído com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Usuario. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
