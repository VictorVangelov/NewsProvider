package model;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.mashape.unirest.http.exceptions.UnirestException;

@Provider
public class CouldNotPostExceptionMapper implements ExceptionMapper<UnirestException> {

	@Override
	public Response toResponse(UnirestException arg0) {

		return Response
				.status(Status.BAD_REQUEST)
				.entity("Cannot establish connection with the provider! Please try again later. ")
				.entity(arg0.getMessage())
				.build();
	}

}
