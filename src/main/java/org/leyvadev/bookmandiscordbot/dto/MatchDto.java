package org.leyvadev.bookmandiscordbot.dto;

public class MatchDto {
    public Metadata metadata;
    public Info info;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
