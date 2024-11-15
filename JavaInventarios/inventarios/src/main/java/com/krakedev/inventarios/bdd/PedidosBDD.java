package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.EstadoPedido;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.entidades.Productos;
import com.krakedev.inventarios.entidades.Proveedores;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {

	public void insertar(Pedido pedido) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		ResultSet rsClave = null;
		int CodigoCabecera = 0;

		String sql = "insert into cabecera_pedido(id_proveedor, fecha, id_estado_pedido) values(?,?,?)";
		Date fechaActual = new Date();
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");

			ps.executeUpdate();

			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				CodigoCabecera = rsClave.getInt(1);
			}

			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();
			DetallePedido det;
			String sqlDet = "insert into detalle_pedido(id_cabecera_pedido,id_producto,cantidad_solicitada,subtotal,cantidad_recibida) values (?,?,?,?,?)";

			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);

				psDet = con.prepareStatement(sqlDet);
				psDet.setInt(1, CodigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidadSolicitada());
				psDet.setInt(5, 0);

				BigDecimal pv = det.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getCantidadSolicitada());
				BigDecimal subtotal = pv.multiply(cantidad);

				psDet.setBigDecimal(4, subtotal);

				psDet.executeUpdate();

			}

			System.out.println("Codigo Generado: " + CodigoCabecera);

		} catch (KrakeDevException e) {

			e.printStackTrace();

			throw new KrakeDevException("Error en la conexion de base de datos: " + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("Error en crear un nuevo registro: " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void Entregar(Pedido pedidoentregado) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		PreparedStatement psHS = null;
		String sql = "update cabecera_pedido set id_estado_pedido = 'R' where numero = ?";
		String sqlHistorial = "Insert into historial_stock(fecha, referencia, id_producto, cantidad) values(?,?,?,?)";
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, pedidoentregado.getCodigo());
			ps.executeUpdate();

			ArrayList<DetallePedido> detallesPedidosentregado = pedidoentregado.getDetalles();
			String sqlDetalles = "update detalle_pedido set cantidad_recibida = ?, subtotal = ?  where codigo = ?";

			DetallePedido detallesPedidosEntregados = null;

			for (int i = 0; i < detallesPedidosentregado.size(); i++) {

				detallesPedidosEntregados = detallesPedidosentregado.get(i);
				psDet = con.prepareStatement(sqlDetalles);

				psDet.setInt(1, detallesPedidosEntregados.getCantidadRecibida());

				BigDecimal pv = detallesPedidosEntregados.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(detallesPedidosEntregados.getCantidadRecibida());
				BigDecimal subtotal = pv.multiply(cantidad);

				psDet.setBigDecimal(2, subtotal);

				psDet.setInt(3, detallesPedidosEntregados.getCodigo());

				psDet.executeUpdate();

				psHS = con.prepareStatement(sqlHistorial);
				Date fechActual = new Date();
				java.sql.Date fechaSql = new java.sql.Date(fechActual.getTime());

				psHS.setDate(1, fechaSql);
				psHS.setString(2, "Pedido: " + pedidoentregado.getCodigo());
				psHS.setInt(3, detallesPedidosEntregados.getProducto().getCodigo());
				psHS.setInt(4, detallesPedidosEntregados.getCantidadRecibida());

				psHS.executeUpdate();

			}

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new KrakeDevException("Error en la conexion de base de datos: " + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("Error en registro de pedido: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Pedido> buscarPedidoPorProv(String id) throws KrakeDevException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		String sql = "select * from cabecera_pedido "
				+ "join detalle_pedido on cabecera_pedido.numero = detalle_pedido.id_cabecera_pedido "
				+ "join productos ON productos.codigo = detalle_pedido.id_producto "
				+ "where cabecera_pedido.id_proveedor = ?";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Pedido pd = new Pedido();
				Proveedores prov = new Proveedores();
				EstadoPedido ep = new EstadoPedido();
				DetallePedido detped = new DetallePedido();
				Productos prod = new Productos();

				int codigo = rs.getInt("numero");
				String idprov = rs.getString("id_proveedor");
				Date fecha = rs.getDate("fecha");
				String codEp = rs.getString("id_estado_pedido");

				int codigodetP = rs.getInt("codigo");

				Pedido cabeceraPed = new Pedido();

				int codProd = rs.getInt("id_producto");
				int cantSolicitada = rs.getInt("cantidad_solicitada");

				String subtotalString = rs.getString("subtotal");
				BigDecimal subtotal = new BigDecimal(subtotalString.replaceAll("[^0-9.]", ""));

				int cantRecibida = rs.getInt("cantidad_recibida");

				prov.setIdentificador(idprov);
				ep.setCodigo(codEp);
				cabeceraPed.setCodigo(codigo);

				detped.setCodigo(codigodetP);
				detped.setCabecera(cabeceraPed);

				prod.setCodigo(codProd);
				prod.setNombre(rs.getString("nombre"));

				detped.setProducto(prod);
				detped.setCantidadSolicitada(cantSolicitada);
				detped.setSubtotal(subtotal);
				detped.setCantidadRecibida(cantRecibida);

				ArrayList<DetallePedido> detalles = new ArrayList<DetallePedido>();
				detalles.add(detped);

				pd.setCodigo(codigo);
				pd.setProveedor(prov);
				pd.setFecha(fecha);
				pd.setEstadoPedido(ep);
				pd.setDetalles(detalles);

				pedidos.add(pd);
			}

		} catch (KrakeDevException | SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("error " + e.getMessage());

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pedidos;
	}

}
