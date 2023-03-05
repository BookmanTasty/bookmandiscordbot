package org.leyvadev.bookmandiscordbot.dto;

import java.util.Map;

public class ChampionData {
    private String type;
    private String format;
    private String version;
    private Map<String, ChampionDetail> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, ChampionDetail> getData() {
        return data;
    }

    public void setData(Map<String, ChampionDetail> data) {
        this.data = data;
    }
}
