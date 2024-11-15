package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class CategoriasBDD {

	public void crearCategoria(Categoria categoria) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		String SqlCreate = "insert into categorias(nombre, categoria_padre) values(?,?)";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(SqlCreate);

			ps.setString(1, categoria.getNombre());
			ps.setInt(2, categoria.getCategoriaPadre().getCodigo());

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("error al insertar un dato: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void actualizarCategoria(Categoria categoria) throws KrakeDevException {
		Connection con = null;
		PreparedStatement ps = null;
		String SqlCreate = "update categorias set nombre = ?, categoria_padre = ? where codigo_cat = ?";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(SqlCreate);

			ps.setString(1, categoria.getNombre());
			ps.setInt(2, categoria.getCategoriaPadre().getCodigo());
			ps.setInt(3, categoria.getCodigo());

			ps.executeUpdate();

		} catch (SQLException | KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("error al insertar un dato: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Categoria> traerTodos() throws KrakeDevException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String SqlCreate = "select * from categorias";
	    ArrayList<Categoria> todasCat = new ArrayList<Categoria>();

	    try {
	        con = ConexionBDD.obtenerConexion();
	        ps = con.prepareStatement(SqlCreate);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            int codigo = rs.getInt("codigo_cat");
	            String nombre = rs.getString("nombre");
	            Integer padreCodigo = rs.getObject("categoria_padre", Integer.class);
	            
	            Categoria categoriaPadre = null;
	            
	            if (padreCodigo != null) {
	                categoriaPadre = new Categoria(padreCodigo, null, null);
	            }

	            Categoria cat = new Categoria(codigo, nombre, categoriaPadre);
	            todasCat.add(cat);
	        }

	    } catch (SQLException | KrakeDevException e) {
	        e.printStackTrace();
	        throw new KrakeDevException("Error al insertar un dato: " + e.getMessage());
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return todasCat;
	}


}
