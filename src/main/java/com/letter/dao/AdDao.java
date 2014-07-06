package com.letter.dao;

import com.letter.domain.AdItem;
import com.letter.exception.IdException;

import java.util.List;


public interface AdDao {

    public void setSessionFactoryCode(String code);

    public void update(AdItem order) throws IdException;

    public void remove(long id);

    public void truncate();

    public List<AdItem> getAll();

    public AdItem get(long id);

}
