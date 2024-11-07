package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Proveedores;
import com.krakedev.inventarios.excepciones.KrakeDevException;


@Path("proveedores")
public class ServiciosProveedores {
	
	@Path("buscar/{subcadena}")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {

		ProveedoresBDD prov = new ProveedoresBDD();
		ArrayList<Proveedores> proveedores = new ArrayList<Proveedores>();

		try {
			proveedores = prov.buscar(subcadena);
			return Response.ok(proveedores).build();

		} catch (KrakeDevException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error en la busqueda" + e.getMessage()).build();
		}

	}

}
