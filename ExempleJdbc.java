import java.sql.DriverManager; // Charger et configurer le driver de la base de données.
import java.sql.Connection; // Réaliser la connexion et l'authentification à la base de données.
import java.sql.Statement; //  Définir et exécuter la requête SQL sur la base de données.
import java.sql.PreparedStatement; // Définir et exécuter la requête SQL paramétrée 
import java.sql.ResultSet; // Parcourir les informations retournées par la base de données 
import java.sql.SQLException; // Gestion des exceptions 

public class ExempleJdbc {	
void loadDriver() throws ClassNotFoundException {
	// Class.forName("com.mysql.jdbc.Driver");
	Class.forName("com.mysql.cj.jdbc.Driver");
	}
Connection newConnection() throws SQLException {
	final String url = "jdbc:mysql://localhost/dbessai";
	Connection conn = DriverManager.getConnection(url, "root", "");
	return conn;
	}
public void listPersons() throws SQLException {
	Connection conn = null;
	try {
		// Etape 1: Connexion à la base de données
		conn = newConnection();
		// Etape 2: Création d'une requête SQL de selction des enregistrements
		Statement st = conn.createStatement();
		String RequeteSQLSlection = "SELECT nom,prenom,age FROM personne ORDER BY age";
		// Etape 3: Exécution des requêtes Mise à jour puis selection
		int Age = 55;
		String Nom  = "Chamali";
		int NbEnregistrements = 0;
		String RequeteSQLMiseaJour = "UPDATE personne SET age = "+ Age +"  WHERE nom  = '"+ Nom +"' ";
		System.out.println( "Requête de mise à jour:  " +  RequeteSQLMiseaJour );
		NbEnregistrements = st.executeUpdate( RequeteSQLMiseaJour ); // retourne le nombre d'enregistrements mis à jour
		System.out.println( "Nombre enregistrements mis à jour:  " +  NbEnregistrements );
		String   ReqPara = "UPDATE personne SET age = ?  WHERE nom  = ? "; // Requête paramétrée
		Age = 15;  Nom  = "Chamali"; 
		PreparedStatement stmt =conn.prepareStatement( ReqPara);
		stmt.setInt(1, Age); stmt.setString(2, Nom); // Passage des paramétres à la requête 
		NbEnregistrements = stmt.executeUpdate(  ); // retourne le nombre d'enregistrements mis à jour
		System.out.println( "Nombre enregistrements mis à jour:  " +  NbEnregistrements );  
		ResultSet rs = st.executeQuery( RequeteSQLSlection ); // retourne l'ensemble des enregistrements selectionnés
		// Etape 4: Parcours et traitements des résultats de la requête
		while (rs.next()) {
			String str = "Nom: " + rs.getString(1) +  " Prénom: " + rs.getString("prenom") + " Age: " + rs.getInt(3);
			System.out.println( str );
			}
		} 
	finally { 	// Epate 5: fermeture de la connexion à la base de données
				if (conn != null) conn.close();
			}
	}
}

