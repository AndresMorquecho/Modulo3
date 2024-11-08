package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedores;
import com.krakedev.inventarios.entidades.tipoDocumetos;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {

	public ArrayList<Proveedores> buscar(String subcadena) throws KrakeDevException {

		ArrayList<Proveedores> proveedores = new ArrayList<Proveedores>();
		Proveedores proveedor = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select prov.id, id_tipo_documetos, td.descripcion , prov.nombre, prov.telefono, prov.correo, prov.direccion from proveedores prov join tipo_documentos td ON td.codigo_tipo_doc = prov.id_tipo_documetos where upper(nombre) like ?";
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {

				String id = rs.getString("id");
				String codigoTipoDocumento = rs.getString("id_tipo_documetos");
				String descripcionTd = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				
				tipoDocumetos td = new tipoDocumetos(codigoTipoDocumento, descripcionTd);
				
				proveedor = new Proveedores(id, td, nombre, telefono, correo, direccion);

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

	
	
	public void CrearProveedor(Proveedores nuevoProveedor ) throws KrakeDevException {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO proveedores (id, id_tipo_documetos, nombre, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, nuevoProveedor.getIdentificador());
			ps.setString(2, nuevoProveedor.getTipoDocumento().getCodigoTipoDoc());
			ps.setString(3, nuevoProveedor.getNombre());
			ps.setString(4, nuevoProveedor.getTelefono());
			ps.setString(5, nuevoProveedor.getCorreo());
			ps.setString(6, nuevoProveedor.getDireccion());
			
			ps.execute();
			
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new KrakeDevException("Error en la conexion a la base de datos" + e.getMessage());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw  new KrakeDevException("Error en la inserccón de un nuevo proveedor, detalles: "+ e.getMessage());
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new KrakeDevException("Error en la conexion a la base de datos" + e.getMessage());
				
			}
			
		}
		
		
		
		
	}
	
	
	
}
