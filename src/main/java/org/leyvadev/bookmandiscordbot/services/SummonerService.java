package org.leyvadev.bookmandiscordbot.services;

import javax.ws.rs.core.Response;

public interface SummonerService {
    Response getSummonerImageByName(String summonerName);
}
