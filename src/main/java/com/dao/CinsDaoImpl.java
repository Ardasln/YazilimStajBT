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
public class CinsDaoImpl implements CinsDao {

    private static final String COLLECTION_NAME = "cins";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Cins> ListCins() {
        List<Cins> list = mongoTemplate.findAll(Cins.class, COLLECTION_NAME);
        return list;
    }

    @Override
    public void AddCins(Cins cins) {
        cins.setId(UUID.randomUUID().toString());
        if (!mongoTemplate.collectionExists(Cins.class)) {
            mongoTemplate.createCollection(Cins.class);
        }
        mongoTemplate.insert(cins, COLLECTION_NAME);
    }

    @Override
    public void UpdateCins(Cins cins) {
        mongoTemplate.save(cins, COLLECTION_NAME);
    }

    @Override
    public void DeleteCins(Cins cins) {
        // Cins silindiğinde o cinse ait türlerin de silinmesini sağlayan döngü
        List<Tur> list = mongoTemplate.find(new Query(Criteria.where("cinsid").is(cins.getId())), Tur.class, "tur");
        for (int i = 0; i < list.size(); ++i) {
            mongoTemplate.remove(list.get(i), "tur");
        }
        mongoTemplate.remove(cins, COLLECTION_NAME);
    }

    @Override
    public Cins findCinsById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.find(query, Cins.class, COLLECTION_NAME).get(0);
    }
}