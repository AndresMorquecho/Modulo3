package com.krakedev.inventarios.entidades;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

	private int codigo;
	private Proveedores proveedor;
	private Date fecha;
	private EstadoPedido estadoPedido;
	private ArrayList<DetallePedido> detalles;
	

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estadoPedido="
				+ estadoPedido + "]";
	}

	public Pedido() {
		super();
	}

	public Pedido(int codigo, Proveedores proveedor, Date fecha, EstadoPedido estadoPedido) {
		super();
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.estadoPedido = estadoPedido;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public ArrayList<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	
	
}
