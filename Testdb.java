
import java.sql.SQLException;

public class Testdb {
	public static void main(String[] Args) {
		ExempleJdbc test = new ExempleJdbc();
		try {
				test.loadDriver();
				test.listPersons();
			} 
		catch (ClassNotFoundException e) {
				System.out.println("Pilote JDBC introuvable!  Message erreur:  " + e.getMessage());
				} 
		catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				}
		}
	}
