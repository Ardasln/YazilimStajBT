package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.CinsDao;
import com.model.Cins;

@Service
public class CinsServiceImpl implements CinsService {
    
    @Autowired
    CinsDao cinsDao;

    @Override
    public List<Cins> listCins() {
        return cinsDao.ListCins();
    }

    @Override
    public void add(Cins cins) {
        cinsDao.AddCins(cins);
    }

    @Override
    public void update(Cins cins) {
        cinsDao.UpdateCins(cins);
    }

    @Override
    public void delete(Cins cins) {
        cinsDao.DeleteCins(cins);
    }

    @Override
    public Cins findCinsById(String id) {
        return cinsDao.findCinsById(id);
    }
}