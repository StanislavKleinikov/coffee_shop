package com.gmail.kleinikov.stanislav.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.gmail.kleinikov.stanislav.entity.CoffeeOrder;

/**
 * EJB that is implementation of {@link OrderDao}
 * 
 * Contains the CRUD operation on a order
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Singleton
public class OrderDaoImpl implements OrderDao {

	@PersistenceContext(unitName = "coffeeShopPU")
	private EntityManager entityManager;

	@Override
	public List<CoffeeOrder> fetchAll() {
		TypedQuery<CoffeeOrder> query = entityManager.createNamedQuery("fetchAllOrder", CoffeeOrder.class);
		List<CoffeeOrder> list = query.getResultList();
		return list;
	}

	@Override
	public void saveOrUpdate(CoffeeOrder order) {
		if (order.getId() == null) {
			entityManager.persist(order);
		} else {
			entityManager.merge(order);
		}
	}

	@Override
	public void delete(Long id) {
		Query query = entityManager.createNamedQuery("deleteOrder");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gmail.kleinikov.stanislav.dao.OrderDao#fetchById(java.lang.Long)
	 */
	@Override
	public CoffeeOrder fetchById(Long id) {
		CoffeeOrder order = entityManager.find(CoffeeOrder.class, id);
		return order;
	}

}
