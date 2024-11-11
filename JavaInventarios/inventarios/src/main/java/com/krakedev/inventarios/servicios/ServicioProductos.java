package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.entidades.Productos;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("productos")
public class ServicioProductos {

	@Path("buscar/{subcadena}")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {

		ProductosBDD prodBDD = new ProductosBDD();
		ArrayList<Productos> productos = new ArrayList<Productos>();

		try {
			productos = prodBDD.buscar(subcadena);

			return Response.ok(productos).build();

		} catch (KrakeDevException e) {

			System.out.println("error: " + e.getMessage());
			return Response.serverError().entity("error al consultar : " + e.getMessage()).build();

		}

	}

	@Path("Crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Productos producto) {

		ProductosBDD prodBDD = new ProductosBDD();
		try {
			prodBDD.Crear(producto);
			return Response.ok().build();
		} catch (KrakeDevException e) {

			e.printStackTrace();

			System.out.println("Error: " + e.getLocalizedMessage());
			return Response.serverError().build();

		}

	}

}
