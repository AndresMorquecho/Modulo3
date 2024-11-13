package com.krakedev.inventarios.entidades;


import java.util.ArrayList;
import java.util.Date;

public class Ventas {
	private int codigo;
	private Date fecha;
	private ArrayList<DetalleVenta> detalleVentas;

	@Override
	public String toString() {
		return "Ventas [codigo=" + codigo + ", fecha=" + fecha + ", detalleVentas=" + detalleVentas + "]";
	}

	public Ventas() {
		super();
	}

	public Ventas(int codigo, Date fecha, ArrayList<DetalleVenta> detalleVentas) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.detalleVentas = detalleVentas;
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

	public ArrayList<DetalleVenta> getDetalleVentas() {
		return detalleVentas;
	}

	public void setDetalleVentas(ArrayList<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}

}
