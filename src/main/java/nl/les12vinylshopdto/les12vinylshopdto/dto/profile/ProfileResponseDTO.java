package nl.les12vinylshopdto.les12vinylshopdto.dto.profile;

import java.util.List;

public class ProfileResponseDTO {
    private Long id;
    private String kcid;
    private List<Long> albums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKcid() {
        return kcid;
    }

    public void setKcid(String kcid) {
        this.kcid = kcid;
    }

    public List<Long> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Long> albums) {
        this.albums = albums;
    }
}
