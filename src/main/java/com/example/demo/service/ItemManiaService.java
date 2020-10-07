package com.example.demo.service;

import com.example.demo.entity.ItemMania;

import java.util.List;

public interface ItemManiaService {
    public void register(ItemMania itemMania) throws Exception;
    public List<ItemMania> list() throws Exception;
    public ItemMania read(Integer itemmaniaNo) throws Exception;
    public void remove(Integer itemmaniaNo) throws Exception;

}
