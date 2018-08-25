package com.gmail.kleinikov.stanislav.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.gmail.kleinikov.stanislav.dao.CoffeeDao;
import com.gmail.kleinikov.stanislav.entity.Coffee;

/**
 * EJB implementation of {@link CoffeeService} interface.
 *
 * @author Kleinikov Stanislav
 * @version 1.0
 */
@Singleton
public class CoffeeServiceImpl implements CoffeeService {

	@EJB
	private CoffeeDao coffeeDao;

	public List<Coffee> fetchAll() {
		return coffeeDao.fetchAll();
	}

}
