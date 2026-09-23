package com.dao;

import java.util.List;
import com.model.Tur;

public interface TurDao {
    public List<Tur> listTur();
    public List<Tur> listTur(String cinsid);
    public void add(Tur tur);
    public void update(Tur tur);
    public void delete(Tur tur);
    public Tur findTurById(String id);
    public String getCinsName(String cinsid);
    
   
}