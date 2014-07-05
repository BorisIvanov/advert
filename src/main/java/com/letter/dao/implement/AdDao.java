package com.letter.dao.implement;

import com.letter.domain.AdItem;
import com.letter.exception.IdException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@SuppressWarnings("unchecked")
@Repository("adDao")
public class AdDao implements com.letter.dao.AdDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void update(AdItem item) throws IdException {
        AdItem checkItem = (AdItem)getCurrentSession()
                .createCriteria(AdItem.class)
                .add(Restrictions.eq("num", new String(item.getNum().trim())).ignoreCase())
                .uniqueResult();
        if (checkItem != null){
            if (checkItem.getId().equals(item.getId())){
                checkItem.setNum(item.getNum());
                checkItem.setValue(item.getValue());
                getCurrentSession().update(checkItem);
                return;
            } else {
                throw new IdException();
            }
        }
        getCurrentSession().save(item);
    }

    @Override
    public void remove(long id) {
        AdItem item = get(id);
        getCurrentSession().delete(item);
    }

    @Override
    public List<AdItem> getAll() {
        return (List<AdItem>)getCurrentSession().createCriteria(AdItem.class).list();
    }

    @Override
    public AdItem get(long id) {
        return (AdItem) getCurrentSession().get(AdItem.class, id);
    }

}
