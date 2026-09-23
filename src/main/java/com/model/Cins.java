package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cins {
    
    @Id
    private String id;
    private String ad;
    private long agacsayisi;

    public Cins() {
        super();
    }

    public Cins(String id, String ad, long agacsayisi) {
        super();
        this.id = id;
        this.ad = ad;
        this.agacsayisi = agacsayisi;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    
    public long getAgacsayisi() { return agacsayisi; }
    public void setAgacsayisi(long agacsayisi) { this.agacsayisi = agacsayisi; }
}