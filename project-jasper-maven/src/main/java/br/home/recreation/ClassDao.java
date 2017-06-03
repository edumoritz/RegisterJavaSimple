package br.home.recreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.home.Contato;

public class ClassDao {
	/*SQL CONTATO*/
	private static final String SQL_C_BUSCA_TODOS = "SELECT * FROM agenda ORDER BY id";
	private static final String SQL_C_INSERE = "INSERT INTO agenda(id, nome, telefone)VALUES(?, ?, ?)";
	private static final String SQL_C_EXCLUI ="DELETE FROM agenda WHERE id = ?";
	private static final String SQL_C_ATUALIZA_NOME = "UPDATE agenda SET nome = ? WHERE id = ?";
	private static final String SQL_C_ATUALIZA_TEL = "UPDATE agenda SET telefone = ? WHERE id = ?";
	/*SQL PRODUTO*/
	private static final String SQL_P_BUSCA_TODOS = "SELECT * FROM produto ORDER BY id";
	private static final String SQL_P_INSERE = "INSERT INTO produto(id, nome, valor) VALUES(?, ?, ?)";
	private static final String SQL_P_EXCLUI = "DELETE FROM produto WHERE id = ?";
	private static final String SQL_P_ATUALIZA_NOME = "UPDATE produto SET nome = ? WHERE id = ?";
	private static final String SQL_P_ATUALIZA_VAL = "UPDATE produto SET valor = ? WHERE id = ?";
	
	private Connection con = ConectionBD.getInstance().getConnection();

/*====================================================================================*/	
	/*AÇÃO SQL CONTATO*/	
	public List<Contato> getTodosC(){
		List<Contato> lista = new ArrayList<>();
		
		//??
		try(PreparedStatement ps = con.prepareStatement(SQL_C_BUSCA_TODOS);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()){
				Contato ct = new Contato();
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
	public void insereC(Contato c){
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
	
	public void atualizaC(int id, Contato c){
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
	/*AÇÃO SQL PRODUTO*/
	public List<Produto> getTodosP(){
		List<Produto> lista = new ArrayList<>();
		
		//??
		try(PreparedStatement ps = con.prepareStatement(SQL_P_BUSCA_TODOS);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()){
				Produto pt = new Produto();
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
	public void insereP(Produto p){
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
	public void atualizaP(int id, Produto p){
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
