package br.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {
	
	private static final String SQL_BUSCA_TODOS = "SELECT * FROM AGENDA";
	private static final String SQL_INSERE = "INSERT INTO agenda(id, nome, telefone) VALUES (?, ?, ?)";
	private static final String SQL_EXCLUI = "DELETE FROM agenda WHERE id = ?";
	private static final String SQL_ATUALIZA = "UPDATE agenda SET NOME = ? WHERE id = ?";
	private Connection con = ConexaoDB.getInstance().getConnection();;
	

	public List<Contato> getTodos(){
			
			List<Contato> lista = new ArrayList<>();

			try(PreparedStatement ps = con
					.prepareStatement(SQL_BUSCA_TODOS);
					ResultSet rs = ps.executeQuery()){
				
				while(rs.next()){
					//instancia contato e adiciona na lista
					Contato ct = new Contato();
					ct.setId(rs.getInt(1));
					ct.setNome(rs.getString(2));
					ct.setTelefone(rs.getString(3));
					lista.add(ct);
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		
		return lista;
	}
	
	
	public void insere(Contato c){
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_INSERE);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getTelefone());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void atualiza(int id, Contato c){
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(SQL_ATUALIZA);
			ps.setString(1, c.getNome());
			ps.setInt(2, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void exclui(int id){
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(SQL_EXCLUI);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
