package com.service;

import java.util.List;
import com.model.Tur;

public interface TurService {
    public List<Tur> listTur();
    public List<Tur> listTur(String cinsid);
    public void add(Tur tur);
    public void update(Tur tur);
    public void delete(Tur tur);
    public Tur findTurById(String id);
}