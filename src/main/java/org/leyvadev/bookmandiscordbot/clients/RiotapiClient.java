package org.leyvadev.bookmandiscordbot.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.leyvadev.bookmandiscordbot.dto.MatchDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
@RegisterRestClient
public interface RiotapiClient {
    @GET
    @Path("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    List<String> getMatchListByPuuid(@PathParam("puuid") String puuid,
                                     @QueryParam("start") String start,
                                     @QueryParam("count") String count,
                                     @QueryParam("type") String type,
                                     @QueryParam("api_key") String apiKey);

    @GET
    @Path("/lol/match/v5/matches/{matchId}")
    MatchDto getMatchByMatchId(@PathParam("matchId") String matchId,
                               @QueryParam("api_key") String apiKey);
}
