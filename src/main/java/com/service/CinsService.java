package com.service;

import java.util.List;
import com.model.Cins;

public interface CinsService {
    public List<Cins> listCins();
    public void add(Cins cins);
    public void update(Cins cins);
    public void delete(Cins cins);
    public Cins findCinsById(String id);
}