package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tur {
    
    @Id
    private String id;
    private String cinsid;
    private String cinsadi;
    private String ad;
    private boolean aktifveyadegil;
    private String aciklama;

    public Tur() {
        super();
    }

    public Tur(String cinsid) {
        super();
        this.cinsid = cinsid;
    }

    public Tur(String id, String cinsid, String cinsadi, String ad, boolean aktifveyadegil, String aciklama) {
        super();
        this.id = id;
        this.cinsid = cinsid;
        this.cinsadi = cinsadi;
        this.ad = ad;
        this.aktifveyadegil = aktifveyadegil;
        this.aciklama = aciklama;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getCinsid() { return cinsid; }
    public void setCinsid(String cinsid) { this.cinsid = cinsid; }
    
    public String getCinsadi() { return cinsadi; }
    public void setCinsadi(String cinsadi) { this.cinsadi = cinsadi; }
    
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    
    public boolean isAktifveyadegil() { return aktifveyadegil; }
    public void setAktifveyadegil(boolean aktifveyadegil) { this.aktifveyadegil = aktifveyadegil; }
    
    public String getAciklama() { return aciklama; }
    public void setAciklama(String aciklama) { this.aciklama = aciklama; }
}