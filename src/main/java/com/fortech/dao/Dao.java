package com.fortech.dao;

import java.util.List;

public interface Dao<E, K> {
	void persist(E entity);
    void remove(E entity);
    E findById(K id);
    List<E> getRules(String query);
}
