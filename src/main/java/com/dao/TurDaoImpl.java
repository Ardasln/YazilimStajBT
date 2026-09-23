package com.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.model.Cins;
import com.model.Tur;

@Repository
public class TurDaoImpl implements TurDao {

    public static final String COLLECTION_NAME = "tur";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Tur> listTur() {
        List<Tur> list = mongoTemplate.findAll(Tur.class, COLLECTION_NAME);
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).setCinsadi(getCinsName(list.get(i).getCinsid()));
        }
        return list;
    }

    @Override
    public List<Tur> listTur(String cinsid) {
        Query query = new Query(Criteria.where("cinsid").is(cinsid));
        List<Tur> list = mongoTemplate.find(query, Tur.class, COLLECTION_NAME);
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).setCinsadi(getCinsName(list.get(i).getCinsid()));
        }
        return list;
    }

    @Override
    public void add(Tur tur) {
        if (!mongoTemplate.collectionExists(Tur.class)) {
            mongoTemplate.createCollection(Tur.class);
        }
        tur.setId(UUID.randomUUID().toString());
        tur.setCinsadi(getCinsName(tur.getCinsid()));
        mongoTemplate.insert(tur, COLLECTION_NAME);
    }

    @Override
    public void update(Tur tur) {
        mongoTemplate.save(tur, COLLECTION_NAME);
    }

    @Override
    public void delete(Tur tur) {
        mongoTemplate.remove(tur, COLLECTION_NAME);
    }

    @Override
    public Tur findTurById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        List<Tur> list = mongoTemplate.find(query, Tur.class, COLLECTION_NAME);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String getCinsName(String cinsid) {
        if (cinsid == null || cinsid.isEmpty()) {
            return "";
        }
        Query query = new Query(Criteria.where("id").is(cinsid));
        List<Cins> list = mongoTemplate.find(query, Cins.class, "cins");
        if (list != null && !list.isEmpty()) {
            return list.get(0).getAd();
        }
        return "";
    }
}