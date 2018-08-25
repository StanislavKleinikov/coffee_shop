package com.gmail.kleinikov.stanislav.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.gmail.kleinikov.stanislav.entity.Coffee;

/**
 * EJB that is implementation of {@link CoffeeDao}
 * 
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Singleton
public class CoffeeDaoImpl implements CoffeeDao {

	@PersistenceContext(unitName = "coffeeShopPU")
	private EntityManager entityManager;

	public List<Coffee> fetchAll() {

		TypedQuery<Coffee> query = entityManager.createNamedQuery("fetchAllCoffee", Coffee.class);

		List<Coffee> list = query.getResultList();

		return list;

	}
}
