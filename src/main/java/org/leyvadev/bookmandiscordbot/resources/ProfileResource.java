package org.leyvadev.bookmandiscordbot.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profile")
public class ProfileResource {
    @Inject
    org.leyvadev.bookmandiscordbot.services.SummonerService summonerService;

    @GET
    @Path("{summonerName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@PathParam("summonerName") String summonerName) {
        return summonerService.getSummonerImageByName(summonerName);
    }
}