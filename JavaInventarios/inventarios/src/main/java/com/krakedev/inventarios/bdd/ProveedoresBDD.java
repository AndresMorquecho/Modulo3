package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedores;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {

	public ArrayList<Proveedores> buscar(String subcadena) throws KrakeDevException {

		ArrayList<Proveedores> proveedores = new ArrayList<Proveedores>();
		Proveedores proveedor = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id, id_tipo_documetos, nombre, telefono, correo, direccion from proveedores where  upper(nombre) like ?";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {

				String id = rs.getString("id");
				String idTipoDocumento = rs.getString("id_tipo_documetos");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");

				proveedor = new Proveedores(id, idTipoDocumento, nombre, telefono, correo, direccion);

				proveedores.add(proveedor);

			}

		} catch (KrakeDevException e) {
			e.printStackTrace();

			throw e;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new KrakeDevException("Error en el tipado sql " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
				throw new KrakeDevException("Error al cerrar la conexion " + e.getMessage());
			}
		}

		return proveedores;

	}

}
