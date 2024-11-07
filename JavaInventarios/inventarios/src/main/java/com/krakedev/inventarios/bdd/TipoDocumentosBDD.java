package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.tipoDocumetos;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class TipoDocumentosBDD {

	public ArrayList<tipoDocumetos> traerTodos() throws KrakeDevException {

		ArrayList<tipoDocumetos> tipoDocumetos = new ArrayList<tipoDocumetos>();
		tipoDocumetos documento = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from tipo_documentos";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				String codigoTipoDoc = rs.getString("codigo_tipo_doc");
				String descripcion = rs.getString("descripcion");

				documento = new tipoDocumetos(codigoTipoDoc, descripcion);

				tipoDocumetos.add(documento);

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

		return tipoDocumetos;

	}

}
