package org.leyvadev.bookmandiscordbot.dto;

import java.util.List;

public class Perks {
    public StatPerks statPerks;
    public List<Style> styles;

    public StatPerks getStatPerks() {
        return statPerks;
    }

    public void setStatPerks(StatPerks statPerks) {
        this.statPerks = statPerks;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }
}
