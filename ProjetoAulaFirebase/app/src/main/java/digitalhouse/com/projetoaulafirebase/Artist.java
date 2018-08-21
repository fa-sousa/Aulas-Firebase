package digitalhouse.com.projetoaulafirebase;

import com.google.firebase.database.PropertyName;

public class Artist {
    private String artistId;
    private String name;
    private String nationality;

    @PropertyName("Influenced_by")
    public String influencedBy;

    public Artist() {
    }

    public Artist(String artistId, String name, String nationality, String influencedBy) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.influencedBy = influencedBy;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getInfluencedBy() {
        return influencedBy;
    }

    public void setInfluencedBy(String influencedBy) {
        this.influencedBy = influencedBy;
    }
}
