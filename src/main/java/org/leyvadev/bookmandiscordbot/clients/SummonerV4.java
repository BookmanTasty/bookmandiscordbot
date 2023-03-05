package org.leyvadev.bookmandiscordbot.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.leyvadev.bookmandiscordbot.dto.ChampionMasteryDto;
import org.leyvadev.bookmandiscordbot.dto.LeagueEntryDTO;
import org.leyvadev.bookmandiscordbot.dto.SumonerDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;


@RegisterRestClient
public interface SummonerV4 {
    @GET
    @Path("/lol/summoner/v4/summoners/by-name/{summonerName}")
    SumonerDTO getSummonerByName(@PathParam("summonerName") String summonerName,
                                 @QueryParam("api_key") String apiKey);
    @GET
    @Path("/lol/league/v4/entries/by-summoner/{encryptedAccountId}")
    List<LeagueEntryDTO> getLeagueEntryBySummonerId(@PathParam("encryptedAccountId") String encryptedAccountId,
                                              @QueryParam("api_key") String apiKey);
    @GET
    @Path("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}/top")
    List<ChampionMasteryDto> getChampionMasteryBySummonerId(@PathParam("encryptedSummonerId") String encryptedSummonerId,
                                                           @QueryParam("count") String count,
                                                           @QueryParam("api_key") String apiKey);

}
