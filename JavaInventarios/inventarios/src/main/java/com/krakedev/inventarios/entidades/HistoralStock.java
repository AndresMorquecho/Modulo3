package com.krakedev.inventarios.entidades;

import java.util.Date;

public class HistoralStock {
	private int codigo;
	private Date fecha;
	private String referencia;
	private Productos idProducto;
	private int cantidad;

	@Override
	public String toString() {
		return "HistoralStock [codigo=" + codigo + ", fecha=" + fecha + ", referencia=" + referencia + ", idProducto="
				+ idProducto + ", cantidad=" + cantidad + "]";
	}

	public HistoralStock() {
		super();
	}

	public HistoralStock(int codigo, Date fecha, String referencia, Productos idProducto, int cantidad) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.referencia = referencia;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Productos getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Productos idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
