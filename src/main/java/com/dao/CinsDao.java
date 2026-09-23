package com.dao;

import java.util.List;
import com.model.Cins;

public interface CinsDao {
    public List<Cins> ListCins();
    public void AddCins(Cins cins);
    public void UpdateCins(Cins cins);
    public void DeleteCins(Cins cins);
    public Cins findCinsById(String id);
    
}