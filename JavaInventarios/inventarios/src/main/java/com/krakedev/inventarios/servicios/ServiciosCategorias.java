package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.CategoriasBDD;
import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("categorias")
public class ServiciosCategorias {

	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearCategoria(Categoria categoria) {

		CategoriasBDD Cat = new CategoriasBDD();

		try {
			Cat.crearCategoria(categoria);

			return Response.ok().build();

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("Error; " + e.getMessage());
			return Response.serverError().build();

		}

	}

	@Path("editar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Categoria categoria) {

		CategoriasBDD Cat = new CategoriasBDD();

		try {
			Cat.actualizarCategoria(categoria);

			return Response.ok().build();

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("Error; " + e.getMessage());
			return Response.serverError().build();

		}

	}

	@Path("todas")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response todos() {

		CategoriasBDD Cat = new CategoriasBDD();
		ArrayList<Categoria> cats = new ArrayList<Categoria>();
		try {
			cats = Cat.traerTodos();
			

			return Response.ok(cats).build();

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("Error; " + e.getMessage());
			return Response.serverError().build();

		}

	}

}
