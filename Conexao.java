package backend;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class Conexao {

		private Conexao() {}
		
		public static Connection getConexao() {
	        try {
	            return DriverManager.getConnection("jdbc:mysql://localhost/pampabrew", "root", "");
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	}

	
	

