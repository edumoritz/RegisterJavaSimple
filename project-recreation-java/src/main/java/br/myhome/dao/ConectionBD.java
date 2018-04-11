package br.myhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class ConectionBD {
	
	private static ConectionBD self;

	private Connection con;

	private ConectionBD() {
		try {
			this.con = DriverManager
					.getConnection(
							"jdbc:postgresql://localhost:5432/cadastros", 
							"postgres", "postgres");
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				
				public void run() {
					try {
						ConectionBD.this.con.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public final static synchronized ConectionBD getInstance() {
		if (self == null) {
			self = new ConectionBD();
		}
		return self;
	}

	public Connection getConnection() {
		return this.con;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Só pode haver um!");
	}

}
