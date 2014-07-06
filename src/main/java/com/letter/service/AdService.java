package com.letter.service;

import com.letter.domain.AdItem;
import com.letter.exception.IdException;

import java.util.List;



public interface AdService {

    public void setSessionFactoryCode(String code);

    public void update(AdItem id) throws IdException;

    public void remove(long id);

    public List<AdItem> getAll();

    public AdItem get(long id);

    public void truncate();

}
