package com.krakedev.inventarios.entidades;

public class tipoDocumetos {

	private String codigoTipoDoc;
	private String descripcion;
	

	
	
	@Override
	public String toString() {
		return "tipoDocumetos [codigoTipoDoc=" + codigoTipoDoc + ", descripcion=" + descripcion + "]";
	}
	public tipoDocumetos() {

	}
	public tipoDocumetos(String codigoTipoDoc, String descripcion) {
		super();
		this.codigoTipoDoc = codigoTipoDoc;
		this.descripcion = descripcion;
	}
	public String getCodigoTipoDoc() {
		return codigoTipoDoc;
	}
	public void setCodigoTipoDoc(String codigoTipoDoc) {
		this.codigoTipoDoc = codigoTipoDoc;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
	
}
