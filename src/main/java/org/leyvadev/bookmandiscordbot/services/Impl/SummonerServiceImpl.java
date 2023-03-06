package org.leyvadev.bookmandiscordbot.services.Impl;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.leyvadev.bookmandiscordbot.clients.DataDragon;
import org.leyvadev.bookmandiscordbot.clients.RiotapiClient;
import org.leyvadev.bookmandiscordbot.clients.SummonerV4;
import org.leyvadev.bookmandiscordbot.dto.*;
import org.leyvadev.bookmandiscordbot.services.SummonerService;
import org.leyvadev.bookmandiscordbot.utils.LolMessageService;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SummonerServiceImpl implements SummonerService {

    private static final String GIT_HUB_URL = "https://github.com/BookmanTasty/bookmandiscordbot";
    private static final String TEEMO = "Teemo";
    @Inject
    @ConfigProperty(name = "riot.api.key")
    String apiKey;
    @Inject
    @ConfigProperty(name = "match.analisys.count")
    String matchAnalisysCount;
    @RestClient
    SummonerV4 summonerV4;
    @RestClient
    RiotapiClient riotapiClient;
    @RestClient
    DataDragon dataDragon;
    @Override
    public Response getSummonerImageByName(String summonerName) {
        String version = dataDragon.getVersions().get(0);
        ChampionData champions = dataDragon.getChampions(version);
        Map<String, ChampionDetail> data = champions.getData();
        SumonerDTO sumonerDTO;
        try{
            sumonerDTO = summonerV4.getSummonerByName(summonerName, apiKey);
        }catch (Exception e){
            return Response.serverError().build();
        }
        LeagueEntryDTO perfil = summonerV4.getLeagueEntryBySummonerId(sumonerDTO.getId(), apiKey).get(0);
        ChampionMasteryDto championMasteryDto = summonerV4.getChampionMasteryBySummonerId(sumonerDTO.getId(), "1", apiKey).get(0);
        String bestChampion = TEEMO;
        for (Map.Entry<String, ChampionDetail> champion : data.entrySet()) {
            if (champion.getValue().getKey().equals(String.valueOf(championMasteryDto.getChampionId()))) {
                bestChampion = champion.getValue().getId();
            }
        }
        Response championImage;
        try {
            championImage = dataDragon.getChampionSplash(bestChampion);
        } catch (Exception e) {
            championImage = dataDragon.getChampionSplash(TEEMO);
        }
        ByteArrayInputStream championByteArray = new ByteArrayInputStream(championImage.readEntity(byte[].class));
        List<String> matchList = riotapiClient.getMatchListByPuuid(sumonerDTO.getPuuid(), "0", matchAnalisysCount, "ranked", apiKey);
        List<Participant> participantList = new ArrayList<>();
        for (String matchId : matchList) {
            MatchDto matchDto = riotapiClient.getMatchByMatchId(matchId, apiKey);
            matchDto.getInfo().getParticipants().stream().filter(participant -> participant.getPuuid().equals(sumonerDTO.getPuuid())).forEach(participantList::add);
        }
        List<BigDecimal> kdaList = new ArrayList<>();
        for (Participant participant : participantList) {
            BigDecimal kills = new BigDecimal(participant.getKills());
            BigDecimal deaths = new BigDecimal(participant.getDeaths());
            BigDecimal assists = new BigDecimal(participant.getAssists());
            BigDecimal kda = kills.add(assists).divide(deaths, 2, BigDecimal.ROUND_HALF_UP);
            kdaList.add(kda);
        }
        BigDecimal kdaSum = new BigDecimal(0);
        for (BigDecimal kda : kdaList) {
            kdaSum = kdaSum.add(kda);
        }
        kdaSum = kdaSum.divide(new BigDecimal(kdaList.size()), 2, BigDecimal.ROUND_HALF_UP);

        BufferedImage image = null;
        try {
          image  = ImageIO.read(championByteArray);
            int w = image.getWidth() / 2;
            int h = image.getHeight() / 2;
            BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(0.5, 0.5);
            AffineTransformOp scaleOp =
                    new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            image = scaleOp.filter(image, after);
        } catch (IOException e) {
            image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        }
        BufferedImage profileIcon ;
        Response profileIconResponse = dataDragon.getProfileIcon(version,sumonerDTO.getProfileIconId());
        ByteArrayInputStream profileIconByteArray = new ByteArrayInputStream(profileIconResponse.readEntity(byte[].class));
        try {
            profileIcon = ImageIO.read(profileIconByteArray);
        } catch (IOException e) {
            profileIcon = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(0, 0, 0, 0.5f));
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2d.drawImage(profileIcon, 10, 10, 128,128, null);
        g2d.setFont(new Font("Liberation", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString(sumonerDTO.getName(), 148, 74);
        g2d.setFont(new Font("Liberation", Font.BOLD, 16));
        g2d.drawString("Nivel: " + sumonerDTO.getSummonerLevel(), 148, 94);
        g2d.drawString("Rank: " + perfil.getTier() + " " + perfil.getRank()+ " "+perfil.getLeaguePoints()+"LP", 148, 114);
        g2d.drawString("KDA Factor: " + kdaSum, 148, 134);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(10, 154, 350, 30);
        g2d.setColor(Color.WHITE);
        BigDecimal barra = new BigDecimal(350);
        barra = barra.multiply(kdaSum);
        barra = barra.divide(new BigDecimal(5), 0, BigDecimal.ROUND_HALF_UP);
        g2d.fillRect(12, 156, barra.intValue(), 26);
        g2d.setFont(new Font("Liberation", Font.BOLD, 15));
        AffineTransform defaultTransform = g2d.getTransform();
        AffineTransform tiltedTransform = new AffineTransform();
        tiltedTransform.setToRotation(Math.toRadians(-15.0));
        g2d.setTransform(tiltedTransform);
        g2d.drawString(LolMessageService.getMessageForRating(kdaSum), -80, 340);
        g2d.setTransform(defaultTransform);
        g2d.setFont(new Font("Liberation", Font.PLAIN   , 12));
        g2d.drawString(GIT_HUB_URL, 10, 10);
        g2d.dispose();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageData = baos.toByteArray();
            return Response.ok(imageData).build();
        } catch (IOException e) {
            Response.serverError().build();
        }
        return Response.serverError().build();
    }
}
