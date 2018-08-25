package com.gmail.kleinikov.stanislav.dao;

import java.util.List;

import javax.ejb.Local;

import com.gmail.kleinikov.stanislav.entity.Coffee;

/**
 * DAO class for {@link com.gmail.kleinikov.stanislav.entity.Coffee}
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Local
public interface CoffeeDao {
	List<Coffee> fetchAll();
}
