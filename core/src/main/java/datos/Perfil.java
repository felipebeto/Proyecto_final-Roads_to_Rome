package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import com.badlogic.gdx.Gdx;

/**
 * Datos del jugador que se conservan entre partidas (base de datos SQLite):
 * nombre, enemigos totales, nivel maximo, oro en la caja fuerte y mejoras del personaje.
 */
public class Perfil {
	public static final int OroPorEnemigo = 10;

	public static String getNombre() {
		try (Statement st = BaseDatos.getConexion().createStatement();
				ResultSet rs = st.executeQuery("SELECT nombre FROM jugador WHERE id = 1")) {
			return rs.next() ? rs.getString("nombre") : null;
		} catch (SQLException e) {
			Gdx.app.error("Perfil", "No se pudo leer el nombre", e);
			return null;
		}
	}

	public static void setNombre(String nombre) {
		ejecutar("UPDATE jugador SET nombre = ? WHERE id = 1", nombre);
	}
	
	public static int getEnemigosTotales() {
		return leerEntero("enemigos_totales");
	}

	public static int getNivelMaximo() {
		return leerEntero("nivel_maximo");
	}

	public static int getOroCajaFuerte() {
		return leerEntero("oro_caja_fuerte");
	}

	
	
	public static boolean comprarMejora(String nombre, int costo) {
		if (getOroCajaFuerte() < costo) return false;
		// Primero se guarda la mejora y despues se cobra: si algo falla, no se pierde oro
		return ejecutar("INSERT INTO mejora (nombre, nivel, id_jugador) VALUES (?, 1, 1) "
				+ "ON CONFLICT(nombre) DO UPDATE SET nivel = nivel + 1", nombre)
				&& ejecutar("UPDATE jugador SET oro_caja_fuerte = oro_caja_fuerte - ? WHERE id = 1", costo);
	}
	
	
	//hay q ver bien esto
	// Mejoras realizadas al personaje: nombre -> nivel. 
	public static LinkedHashMap<String, Integer> getMejoras() {
		LinkedHashMap<String, Integer> mejoras = new LinkedHashMap<>();
		try (Statement st = BaseDatos.getConexion().createStatement();
				ResultSet rs = st.executeQuery("SELECT nombre, nivel FROM mejora ORDER BY nombre")) {
			while (rs.next()) mejoras.put(rs.getString("nombre"), rs.getInt("nivel"));
		} catch (SQLException e) {
			Gdx.app.error("Perfil", "No se pudieron leer las mejoras", e);
		}
		return mejoras;
	}

	/**
	 * Se llama al terminar cada partida: suma los enemigos derrotados, deposita el oro ganado
	 * en la caja fuerte (enemigos * ORO_POR_ENEMIGO) y actualiza el nivel maximo si se supero.
	 */
	public static void guardarPartida(int enemigos, int nivelAlcanzado) {
		ejecutar("UPDATE jugador SET enemigos_totales = enemigos_totales + ?, "
				+ "oro_caja_fuerte = oro_caja_fuerte + ?, nivel_maximo = MAX(nivel_maximo, ?) WHERE id = 1",
				enemigos, enemigos * OroPorEnemigo, nivelAlcanzado);
	}
	
	
	private static boolean ejecutar(String sql, Object... parametros) {
		try (PreparedStatement ps = BaseDatos.getConexion().prepareStatement(sql)) {
			for (int i = 0; i < parametros.length; i++) ps.setObject(i + 1, parametros[i]);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			Gdx.app.error("Perfil", "Error al ejecutar: " + sql, e);
			return false;
		}
	}

	private static int leerEntero(String columna) {
		try (Statement st = BaseDatos.getConexion().createStatement();
				ResultSet rs = st.executeQuery("SELECT " + columna + " FROM jugador WHERE id = 1")) {
			return rs.next() ? rs.getInt(columna) : 0;
		} catch (SQLException e) {
			Gdx.app.error("Perfil", "No se pudo leer " + columna, e);
			return 0;
		}
	}
}
