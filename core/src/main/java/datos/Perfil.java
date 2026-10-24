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

	// private Perfil() {}

	
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
		try (PreparedStatement ps = BaseDatos.getConexion()
				.prepareStatement("UPDATE jugador SET nombre = ? WHERE id = 1")) {
			ps.setString(1, nombre);
			ps.executeUpdate();
		} catch (SQLException e) {
			Gdx.app.error("Perfil", "No se pudo guardar el nombre", e);
		}
	}
	
	
	
	
	 // Compra una mejora con el oro de la caja fuerte. Si la mejora ya existe, sube un nivel.
	 //Devuelve false si no alcanza el oro. El cobro y el registro van juntos en una transaccion.
	 
	public static boolean comprarMejora(String nombre, int costo) {
		Connection c = BaseDatos.getConexion();
		try {
			c.setAutoCommit(false);
			try {
				try (PreparedStatement cobro = c.prepareStatement(
						"UPDATE jugador SET oro_caja_fuerte = oro_caja_fuerte - ? "
						+ "WHERE id = 1 AND oro_caja_fuerte >= ?")) {
					cobro.setInt(1, costo);
					cobro.setInt(2, costo);
					if (cobro.executeUpdate() == 0) {
						c.rollback();
						return false;
					}
				}
				try (PreparedStatement mejora = c.prepareStatement(
						"INSERT INTO mejora (nombre, nivel) VALUES (?, 1) "
						+ "ON CONFLICT(nombre) DO UPDATE SET nivel = nivel + 1")) {
					mejora.setString(1, nombre);
					mejora.executeUpdate();
				}
				c.commit();
				return true;
			} catch (SQLException e) {
				c.rollback();
				throw e;
			} finally {
				c.setAutoCommit(true);
			}
		} catch (SQLException e) {
			Gdx.app.error("Perfil", "No se pudo comprar la mejora " + nombre, e);
			return false;
		}
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
	 
	

	public static int getEnemigosTotales() {
		return leerEntero("enemigos_totales");
	}

	public static int getNivelMaximo() {
		return leerEntero("nivel_maximo");
	}

	public static int getOroCajaFuerte() {
		return leerEntero("oro_caja_fuerte");
	}

	/**
	 * Se llama al terminar cada partida: suma los enemigos derrotados, deposita el oro ganado
	 * en la caja fuerte (enemigos * ORO_POR_ENEMIGO) y actualiza el nivel maximo si se supero.
	 */
	public static void guardarPartida(int enemigos, int nivelAlcanzado) {
		String sql = "UPDATE jugador SET "
				+ "enemigos_totales = enemigos_totales + ?, "
				+ "oro_caja_fuerte = oro_caja_fuerte + ?, "
				+ "nivel_maximo = MAX(nivel_maximo, ?) "
				+ "WHERE id = 1";
		try (PreparedStatement ps = BaseDatos.getConexion().prepareStatement(sql)) {
			ps.setInt(1, enemigos);
			ps.setInt(2, enemigos * OroPorEnemigo);
			ps.setInt(3, nivelAlcanzado);
			ps.executeUpdate();
		} catch (SQLException e) {
			Gdx.app.error("Perfil", "No se pudo guardar la partida", e);
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
