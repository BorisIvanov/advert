package com.letter.service.implement;


import com.letter.dao.AdDao;
import com.letter.domain.AdItem;
import com.letter.exception.IdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("adService")
@Transactional
public class AdService implements com.letter.service.AdService {

    private final Logger log = LoggerFactory.getLogger(AdService.class);

    @Autowired
    private AdDao adDao;

    public void setSessionFactoryCode(String code) {
        adDao.setSessionFactoryCode(code);
    }

    @Override
    public void update(AdItem item) throws IdException {
        adDao.update(item);
    }

    @Override
    public void remove(long id) {
        adDao.remove(id);
    }

    @Override
    public List<AdItem> getAll() {
        return adDao.getAll();
    }

    @Override
    public AdItem get(long id) {
        return adDao.get(id);
    }

    public void truncate() {
        adDao.truncate();
    }

}
