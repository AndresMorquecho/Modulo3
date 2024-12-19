package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.TipoDocumentosBDD;
import com.krakedev.inventarios.entidades.tipoDocumetos;
import com.krakedev.inventarios.excepciones.KrakeDevException;


@Path("tiposdocumento")
public class ServiciosTipoDocumentos {
	
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response traerTodosDocumentos() {

		TipoDocumentosBDD tipoDoc = new TipoDocumentosBDD();
		ArrayList<tipoDocumetos> documentos = new ArrayList<tipoDocumetos>();
		try {
			documentos = tipoDoc.traerTodos();
			System.out.println("todos los tipos de documentos encontrados");
			return Response.ok(documentos).build();
			
		} catch (KrakeDevException e) {
			
			e.printStackTrace();
			System.out.println("Error en la consulta " + e.getMessage() );
			return Response.serverError().build();
		}

	}
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateTipoDoc(tipoDocumetos doc) {
		
		TipoDocumentosBDD TipoD = new TipoDocumentosBDD();
		try {
			TipoD.createTipoDocumento(doc);
			System.out.println("Registro creado con exito");
			return Response.ok().build();
			
		} catch (KrakeDevException e) {
			System.out.println("Ha ocurrido un error"+ e.getMessage());
			return Response.serverError().build();
		}
		
		
	}

}
