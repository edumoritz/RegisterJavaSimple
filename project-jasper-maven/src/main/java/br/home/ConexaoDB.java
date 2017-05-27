package br.home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Singleton
public final class ConexaoDB {// final não da pra estender

	// static permiter ser unica como o metodo main
	private static ConexaoDB self;

	private Connection con;

	// construtor private
	private ConexaoDB() {
		try {
			this.con = DriverManager
					.getConnection(
							"jdbc:postgresql://localhost:5432/agenda", 
							"postgres", "banco");
			
			
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						//Referencia a classe principal ConexaoDB
						ConexaoDB.this.con.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public final static synchronized ConexaoDB getInstance() {
		if (self == null) {
			self = new ConexaoDB();
		}
		return self;
	}

	// metodo publico para as classes de fora conectar
	public Connection getConnection() {
		return this.con;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Só pode haver um!");
	}

}
