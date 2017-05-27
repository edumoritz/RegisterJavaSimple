package br.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {
	
	private static final String SQL_BUSCA_TODOS = "SELECT * FROM AGENDA";
	

	public List<Contato> getTodos(){
			Connection con = ConexaoDB.getInstance().getConnection();
			
			List<Contato> lista = new ArrayList<>();

			try(PreparedStatement ps = con
					.prepareStatement(SQL_BUSCA_TODOS);
					ResultSet rs = ps.executeQuery()){
				
				while(rs.next()){
					//instancia contato e adiciona na lista
					
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		
		
		
		return null;
	}
	
	
	public void insere(Contato c){
		
	}
	
	public void atualiza(int id, Contato c){
		
	}
	
	public void exclui(int id){
		
	}

}
