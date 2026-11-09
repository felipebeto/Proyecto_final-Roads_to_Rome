package datos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.badlogic.gdx.Gdx;

public class BaseDatos {
	private static Connection conexion;

	private BaseDatos() {}

	public static void iniciar() {
		try {
			Class.forName("org.sqlite.JDBC");
			File archivo = Gdx.files.external("RoadsToRome/datos.db").file();
			archivo.getParentFile().mkdirs();
			conexion = DriverManager.getConnection("jdbc:sqlite:" + archivo.getAbsolutePath());
			crearTablas();
			//reiniciarPerfil();
		} catch (Exception e) {
			throw new RuntimeException("No se pudo iniciar la base de datos (esta sqlite-jdbc en core/build.gradle?)", e);
		}
	}

	public static Connection getConexion() {
		return conexion;
	}

	public static void cerrar() {
		try {
			if (conexion != null && !conexion.isClosed()) conexion.close();
		} catch (SQLException e) {
			Gdx.app.error("BaseDatos", "Error al cerrar la conexion", e);
		}
	}

	private static void crearTablas() throws SQLException {
		try (Statement st = conexion.createStatement()) {
			st.execute("CREATE TABLE IF NOT EXISTS jugador ("
					+ "id INTEGER PRIMARY KEY CHECK (id = 1), "
					+ "nombre VARCHAR, "
					+ "enemigos_totales INTEGER NOT NULL DEFAULT 0, "
					+ "nivel_maximo INTEGER NOT NULL DEFAULT 0, "
					+ "oro_caja_fuerte INTEGER NOT NULL DEFAULT 0 CHECK (oro_caja_fuerte >= 0))");

			st.execute("CREATE TABLE IF NOT EXISTS mejora ("
					+ "nombre VARCHAR PRIMARY KEY, "
					+ "nivel INTEGER DEFAULT 0,"
					+ "id_jugador INTEGER REFERENCES jugador(id))");
			
			st.execute("INSERT OR IGNORE INTO jugador (id) VALUES (1)");
			
		}
	}
	
	
	public static void reiniciarPerfil() {
	    try (Statement st = conexion.createStatement()) {
	        st.executeUpdate(
	            "UPDATE jugador SET nombre = NULL, " +
	            "enemigos_totales = 0, " +
	            "nivel_maximo = 0, " +
	            "oro_caja_fuerte = 0 " +
	            "WHERE id = 1"
	        );
	    } catch (SQLException e) {
	        throw new RuntimeException("No se pudo reiniciar el perfil", e);
	    }
	}
}
