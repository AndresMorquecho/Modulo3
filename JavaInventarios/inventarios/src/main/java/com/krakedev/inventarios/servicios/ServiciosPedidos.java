package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.PedidosBDD;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("pedidos")
public class ServiciosPedidos {
	@Path("registrar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Crear(Pedido pedido) {

		PedidosBDD pdd = new PedidosBDD();

		try {
			pdd.insertar(pedido);

			return Response.ok().build();

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error: " + e.getMessage());
			return Response.serverError().build();

		}

	}

	@Path("recibir")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response recibir(Pedido pedido) {

		PedidosBDD pdd = new PedidosBDD();

		try {
			pdd.Entregar(pedido);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error: " + e.getMessage());
			return Response.serverError().build();
		}

	}

	@Path("buscarPorProv/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorProv(@PathParam("id") String id) {

		PedidosBDD pdd = new PedidosBDD();
		ArrayList<Pedido> pedido = new ArrayList<Pedido>();

		try {
			pedido = pdd.buscarPedidoPorProv(id);
			return Response.ok(pedido).build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error: " + e.getMessage());
			return Response.serverError().build();
		}

	}

}
