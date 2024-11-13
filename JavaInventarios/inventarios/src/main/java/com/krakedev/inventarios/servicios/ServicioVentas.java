package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.VentasBDD;
import com.krakedev.inventarios.entidades.Ventas;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("ventas")
public class ServicioVentas {
	@POST
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarVenta(Ventas venta) {
		VentasBDD ventasBDD = new VentasBDD();

		try {
			ventasBDD.RegistarVenta(venta);

			return Response.ok().build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("Error: " + e.getMessage());

			return Response.serverError().build();
		}

	}
}
