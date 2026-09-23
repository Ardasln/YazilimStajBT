package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.TurDao;
import com.model.Tur;

@Service
public class TurServiceImpl implements TurService {

    @Autowired
    TurDao turDao;

    @Override
    public List<Tur> listTur() {
        return turDao.listTur();
    }

    @Override
    public List<Tur> listTur(String cinsid) {
        return turDao.listTur(cinsid);
    }

    @Override
    public void add(Tur tur) {
        turDao.add(tur);
    }

    @Override
    public void update(Tur tur) {
        turDao.update(tur);
    }

    @Override
    public void delete(Tur tur) {
        turDao.delete(tur);
    }

    @Override
    public Tur findTurById(String id) {
        return turDao.findTurById(id);
    }
}