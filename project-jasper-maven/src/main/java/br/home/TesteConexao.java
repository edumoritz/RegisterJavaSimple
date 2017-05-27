package br.home;

public class TesteConexao {
	
	public static void main(String[] args) {
		//sem o new, 3 variaveis que referencia 1 objeto só
		ConexaoDB condb = ConexaoDB.getInstance();
		ConexaoDB condb1 = ConexaoDB.getInstance();
		ConexaoDB condb2 = ConexaoDB.getInstance();
	}

}
