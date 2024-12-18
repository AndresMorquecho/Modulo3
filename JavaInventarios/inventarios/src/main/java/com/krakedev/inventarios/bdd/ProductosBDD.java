package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Productos;
import com.krakedev.inventarios.entidades.UnidadDeMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductosBDD {

	public ArrayList<Productos> buscar(String subcadena) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Productos> productos = new ArrayList<Productos>();
		ResultSet rs = null;
		String sql = "SELECT " + "    prod.codigo AS codigo_producto, " + "    prod.nombre AS nombre_producto, "
				+ "    udm.codigo_nombre AS codigo_unidad_medida, " + "    udm.descripcion AS descripcion_udm, "
				+ "    cast(prod.precio_venta as decimal(6,2)), " + "    prod.tiene_iva, "
				+ "    cast(prod.coste as decimal (6,2) ), " + "    cat.codigo_cat AS codigo_categoria, "
				+ "    cat.nombre AS nombre_categoria, " + "    prod.stock " + "FROM " + "    productos prod " + "JOIN "
				+ "    unidades_medida udm ON prod.id_unidades_medida = udm.codigo_nombre " + "JOIN "
				+ "    categorias cat ON prod.id_categorias = cat.codigo_cat " + "WHERE upper(prod.nombre) like ?";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {

				int codigoProducto = rs.getInt("codigo_producto");
				String nombreProducto = rs.getString("nombre_producto");
				String codigoUDM = rs.getString("codigo_unidad_medida");
				String descripcion_udm = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("codigo_categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				UnidadDeMedida udm = new UnidadDeMedida();

				udm.setNombre(codigoUDM);
				udm.setDescripcion(descripcion_udm);

				Categoria categoria = new Categoria();

				categoria.setCodigo(codigoCategoria);
				categoria.setNombre(nombreCategoria);

				Productos producto = new Productos();

				producto.setCodigo(codigoProducto);
				producto.setNombre(nombreProducto);
				producto.setUnidadDeMedida(udm);
				producto.setPrecioVenta(precioVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(categoria);
				producto.setStock(stock);

				productos.add(producto);

			}

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new KrakeDevException("Error al intentar conectarse a la base de datos" + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new KrakeDevException("error en la consulta sql, detalles " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new KrakeDevException("Error al intentar conectarse a la base de datos" + e.getMessage());
			}
		}

		return productos;

	}

	public void Crear(Productos producto) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Insert into productos (nombre, id_unidades_medida, precio_venta, tiene_iva, coste, id_categorias, stock) "
				+ "values(?,?,?,?,?,?,?)";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);

			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUnidadDeMedida().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.isTieneIva());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7, producto.getStock());

			ps.executeUpdate();

		} catch (KrakeDevException e) {

			e.printStackTrace();

			throw new KrakeDevException("Error al intentar coneectase a la base de datos " + e.getMessage());

		} catch (SQLException e) {

			e.printStackTrace();
			throw new KrakeDevException("Error en la consulta SQL  " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void Actualizar(Productos producto) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		String sqlupdateProd = "update productos set nombre = ?, id_unidades_medida = ?, precio_venta = ?, tiene_iva = ?, coste = ?, id_categorias = ?,stock = ? where codigo = ?  ";

		try {
			con = ConexionBDD.obtenerConexion();

			ps = con.prepareStatement(sqlupdateProd);

			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUnidadDeMedida().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());

			if (producto.isTieneIva()) {
				ps.setBoolean(4, true);
			} else {
				ps.setBoolean(4, false);
			}

			ps.setBigDecimal(5, producto.getCoste());

			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7, producto.getStock());
			ps.setInt(8, producto.getCodigo());

			ps.executeUpdate();

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			throw new KrakeDevException("Error al intentar coneectase a la base de datos " + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new KrakeDevException("Error en la consulta SQL  " + e.getMessage());
		}

	}

	public Productos buscarPorIdProd(int id) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		Productos producto = new Productos();
		ResultSet rs = null;
		String sql = "SELECT " + "    prod.codigo AS codigo_producto, " + "    prod.nombre AS nombre_producto, "
				+ "    udm.codigo_nombre AS codigo_unidad_medida, " + "    udm.descripcion AS descripcion_udm, "
				+ "    cast(prod.precio_venta as decimal(6,2)), " + "    prod.tiene_iva, "
				+ "    cast(prod.coste as decimal (6,2) ), " + "    cat.codigo_cat AS codigo_categoria, "
				+ "    cat.nombre AS nombre_categoria, " + "    prod.stock " + "FROM " + "    productos prod " + "JOIN "
				+ "    unidades_medida udm ON prod.id_unidades_medida = udm.codigo_nombre " + "JOIN "
				+ "    categorias cat ON prod.id_categorias = cat.codigo_cat " + "WHERE prod.codigo = ?";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {

				int codigoProducto = rs.getInt("codigo_producto");
				String nombreProducto = rs.getString("nombre_producto");
				String codigoUDM = rs.getString("codigo_unidad_medida");
				String descripcion_udm = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("codigo_categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				UnidadDeMedida udm = new UnidadDeMedida();

				udm.setNombre(codigoUDM);
				udm.setDescripcion(descripcion_udm);

				Categoria categoria = new Categoria();

				categoria.setCodigo(codigoCategoria);
				categoria.setNombre(nombreCategoria);

				producto.setCodigo(codigoProducto);
				producto.setNombre(nombreProducto);
				producto.setUnidadDeMedida(udm);
				producto.setPrecioVenta(precioVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(categoria);
				producto.setStock(stock);

			}

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new KrakeDevException("Error al intentar conectarse a la base de datos" + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new KrakeDevException("error en la consulta sql, detalles " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new KrakeDevException("Error al intentar conectarse a la base de datos" + e.getMessage());
			}
		}

		return producto;

	}

}
