package com.example.demo.service;

import com.example.demo.entity.ItemMania;
import com.example.demo.repository.ItemManiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemManiaServiceImpl implements ItemManiaService {

    @Autowired
    private ItemManiaRepository itemmaniaRepository;

    @Override
    public void register(ItemMania itemmania) throws Exception {
        itemmaniaRepository.create(itemmania);
    }

    @Override
    public List<ItemMania> list() throws Exception {
        return itemmaniaRepository.list();
    }

    @Override
    public ItemMania read(Integer itemmaniaNo) throws Exception {
        return null;
    }

    /*@Override
    public ItemMania read(Integer itemmaniaNo) throws Exception {
        itemmaniaRepository.read(itemmaniaNo)
    }*/

    @Override
    public void remove(Integer itemmaniaNo) throws Exception {
        itemmaniaRepository.remove(itemmaniaNo);
    }

}


