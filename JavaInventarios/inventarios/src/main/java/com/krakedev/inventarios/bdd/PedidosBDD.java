package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.Pedido;
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

}
