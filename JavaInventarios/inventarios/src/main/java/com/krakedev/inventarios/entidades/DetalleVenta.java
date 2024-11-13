package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetalleVenta {

	private int codigo;
	private Ventas codCabeceraVentas;
	private Productos producto;
	private int cantidadVendida;
	private BigDecimal subTotal;
	private BigDecimal subTotalConIva;

	@Override
	public String toString() {
		return "DetalleVenta [codigo=" + codigo + ", codCabeceraVentas=" + codCabeceraVentas + ", producto=" + producto
				+ ", cantidadVendida=" + cantidadVendida + ", subTotal=" + subTotal + ", subTotalConIva="
				+ subTotalConIva + "]";
	}

	public DetalleVenta() {
		super();
	}

	public DetalleVenta(int codigo, Ventas codCabeceraVentas, Productos producto, int cantidadVendida,
			BigDecimal subTotal, BigDecimal subTotalConIva) {
		super();
		this.codigo = codigo;
		this.codCabeceraVentas = codCabeceraVentas;
		this.producto = producto;
		this.cantidadVendida = cantidadVendida;
		this.subTotal = subTotal;
		this.subTotalConIva = subTotalConIva;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Ventas getCodCabeceraVentas() {
		return codCabeceraVentas;
	}

	public void setCodCabeceraVentas(Ventas codCabeceraVentas) {
		this.codCabeceraVentas = codCabeceraVentas;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getSubTotalConIva() {
		return subTotalConIva;
	}

	public void setSubTotalConIva(BigDecimal subTotalConIva) {
		this.subTotalConIva = subTotalConIva;
	}

}
