package org.leyvadev.bookmandiscordbot.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.leyvadev.bookmandiscordbot.dto.ChampionData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@RegisterRestClient
public interface DataDragon {
    @GET
    @Path("/api/versions.json")
    List<String> getVersions();

    @GET
    @Path("/cdn/{version}/data/en_US/champion.json")
    ChampionData getChampions(@PathParam("version") String version);

    @GET
    @Path("cdn/img/champion/splash/{championName}_0.jpg")
    Response getChampionSplash(@PathParam("championName") String championName);
    @GET
    @Path("cdn/{version}/img/profileicon/{profileIconId}.png")
    Response getProfileIcon(@PathParam("version") String version, @PathParam("profileIconId") String profileIconId);
}
