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
	
	
	public void createTipoDocumento (tipoDocumetos nuevoDocumento) throws KrakeDevException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into tipo_documentos(codigo_tipo_doc, descripcion) values(?,?)";
		try {
			con = ConexionBDD.obtenerConexion();
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, nuevoDocumento.getCodigoTipoDoc());
			ps.setString(2, nuevoDocumento.getDescripcion());
			
			ps.executeUpdate();
			
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error en la conexion a la base de datos: " + e.getLocalizedMessage());
		} catch (SQLException e) {
			throw new KrakeDevException("error al insertar el nuevo tipo de documento: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
