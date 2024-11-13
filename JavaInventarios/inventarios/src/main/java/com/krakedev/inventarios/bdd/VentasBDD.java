package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.entidades.Ventas;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentasBDD {

	public void RegistarVenta(Ventas ventaRealizada) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;

		ResultSet rsKey = null;
		int codigoCabecera = 0;
		final BigDecimal IVA = new BigDecimal("1.12");

		PreparedStatement psDetV = null;
		PreparedStatement psHS = null;

		String sqlHistorial = "Insert into historial_stock(fecha, referencia, id_producto, cantidad) values(?,?,?,?)";
		String sqlCabeceraVentas = "insert into cabecera_ventas(fecha, total_sin_iva, iva, total) values(?,0,0,0)";
		String sqlDetallVentas = "insert into detalle_ventas(id_cabecera_ventas, id_producto, cantidad, precio_venta, sub_total, sub_total_con_iva) values (?,?,?,?,?,?)";
		String sqlUpdateCabecera = "update cabecera_ventas set total_sin_iva =?, iva = ?, total= ? where codigo = ?";

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(sqlCabeceraVentas, java.sql.Statement.RETURN_GENERATED_KEYS);

			Date fecha = new Date();
			java.sql.Date fechaActual = new java.sql.Date(fecha.getTime());

			ps.setDate(1, fechaActual);

			ps.executeUpdate();

			rsKey = ps.getGeneratedKeys();

			if (rsKey.next()) {

				codigoCabecera = rsKey.getInt(1);

			}

			ArrayList<DetalleVenta> detalledeVenta = ventaRealizada.getDetalleVentas();
			DetalleVenta detV = null;

			BigDecimal totalSinIva = BigDecimal.ZERO;
			BigDecimal iva = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;

			for (int i = 0; i < detalledeVenta.size(); i++) {
				detV = detalledeVenta.get(i);

				psDetV = con.prepareStatement(sqlDetallVentas);
				psDetV.setInt(1, codigoCabecera);
				psDetV.setInt(2, detV.getProducto().getCodigo());
				psDetV.setInt(3, detV.getCantidadVendida());

				BigDecimal pv = detV.getProducto().getPrecioVenta();
				BigDecimal cant = new BigDecimal(detV.getCantidadVendida());
				BigDecimal subtotal = pv.multiply(cant);

				psDetV.setBigDecimal(4, detV.getProducto().getPrecioVenta());
				psDetV.setBigDecimal(5, subtotal);

				totalSinIva = totalSinIva.add(subtotal);

				if (detV.getProducto().isTieneIva()) {
					BigDecimal subtotalIva = subtotal.multiply(IVA).subtract(subtotal);
					BigDecimal SubtotalMasIva = subtotal.add(subtotalIva);
					psDetV.setBigDecimal(6, SubtotalMasIva);

					iva = iva.add(subtotalIva);
					total = total.add(SubtotalMasIva);
				} else {
					psDetV.setBigDecimal(6, subtotal);
					total = total.add(subtotal);
				}

				psDetV.executeUpdate();

				psHS = con.prepareStatement(sqlHistorial);
				psHS.setDate(1, fechaActual);
				psHS.setString(2, "Venta: " + codigoCabecera);
				psHS.setInt(3, detV.getProducto().getCodigo());
				psHS.setInt(4, detV.getCantidadVendida());

				psHS.executeUpdate();

			}

			ps = con.prepareStatement(sqlUpdateCabecera);
			ps.setBigDecimal(1, totalSinIva);
			ps.setBigDecimal(2, iva);
			ps.setBigDecimal(3, total);
			ps.setInt(4, codigoCabecera);
			ps.executeUpdate();

		} catch (KrakeDevException e) {

			e.printStackTrace();
			throw new KrakeDevException("error al intententar conectarse a la base de datos " + e.getMessage());
		} catch (SQLException e) {

			e.printStackTrace();
			throw new KrakeDevException("error en la inserccion de la venta " + e.getMessage());

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
