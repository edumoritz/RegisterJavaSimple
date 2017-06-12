package br.myhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.myhome.pojos.Contact;
import br.myhome.pojos.Product;


public class ClassDao {
	
	/*SQL Contact*/
	private static final String SQL_C_BUSCA_TODOS = "SELECT * FROM contact ORDER BY id";
	private static final String SQL_C_INSERE = "INSERT INTO contact(id, nome, telefone)VALUES(?, ?, ?)";
	private static final String SQL_C_EXCLUI ="DELETE FROM contact WHERE id = ?";
	private static final String SQL_C_ATUALIZA_NOME = "UPDATE contact SET nome = ? WHERE id = ?";
	private static final String SQL_C_ATUALIZA_TEL = "UPDATE contact SET telefone = ? WHERE id = ?";
	/*SQL Product*/
	private static final String SQL_P_BUSCA_TODOS = "SELECT * FROM Product ORDER BY id";
	private static final String SQL_P_INSERE = "INSERT INTO Product(id, nome, valor) VALUES(?, ?, ?)";
	private static final String SQL_P_EXCLUI = "DELETE FROM Product WHERE id = ?";
	private static final String SQL_P_ATUALIZA_NOME = "UPDATE Product SET nome = ? WHERE id = ?";
	private static final String SQL_P_ATUALIZA_VAL = "UPDATE Product SET valor = ? WHERE id = ?";
	
	private Connection con = ConectionBD.getInstance().getConnection();
	
	/*====================================================================================*/	
	/*As SQL Contact*/	
	public List<Contact> getTodosC(){
		List<Contact> lista = new ArrayList<>();
		
		try(PreparedStatement ps = con.prepareStatement(SQL_C_BUSCA_TODOS);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()){
				Contact ct = new Contact();
				ct.setId(rs.getInt(1));
				ct.setNome(rs.getString(2));
				ct.setTelefone(rs.getString(3));
				lista.add(ct);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return lista;
	}
	public void insereC(Contact c){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_C_INSERE);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getTelefone());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizaC(int id, Contact c){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_C_ATUALIZA_NOME);
			ps.setString(1, c.getNome());
			ps.setInt(2, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ps = con.prepareStatement(SQL_C_ATUALIZA_TEL);
			ps.setString(1, c.getTelefone());
			ps.setInt(2, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void excluiC(int id){
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(SQL_C_EXCLUI);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
/*====================================================================================*/
	/*As SQL Product*/
	public List<Product> getTodosP(){
		List<Product> lista = new ArrayList<>();
		
		//??
		try(PreparedStatement ps = con.prepareStatement(SQL_P_BUSCA_TODOS);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()){
				Product pt = new Product();
				pt.setId(rs.getInt(1));
				pt.setNome(rs.getString(2));
				pt.setValor(rs.getBigDecimal(3));
				lista.add(pt);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return lista;
	}
	public void insereP(Product p){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_P_INSERE);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNome());
			ps.setBigDecimal(3, p.getValor());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void atualizaP(int id, Product p){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_P_ATUALIZA_NOME);
			ps.setString(1, p.getNome());
			ps.setInt(2, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ps = con.prepareStatement(SQL_P_ATUALIZA_VAL);
			ps.setBigDecimal(1, p.getValor());
			ps.setInt(2, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void excluiP(int id){
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(SQL_P_EXCLUI);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
