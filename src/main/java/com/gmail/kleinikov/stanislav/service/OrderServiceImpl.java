package com.gmail.kleinikov.stanislav.service;

import static com.gmail.kleinikov.stanislav.util.ConstantValue.STATUS_ACTIVE;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.gmail.kleinikov.stanislav.dao.OrderDao;
import com.gmail.kleinikov.stanislav.entity.CoffeeOrder;
import com.gmail.kleinikov.stanislav.entity.Status;

/**
 * EJB implementation of {@link OrderService} interface.
 *
 * @author Kleinikov Stanislav
 * @version 1.0
 */
@Singleton
public class OrderServiceImpl implements OrderService {

	@EJB
	private OrderDao orderDao;

	@Override
	public void saveOrder(CoffeeOrder order) {
		if (order.getStatus() == null) {
			order.setStatus(new Status(STATUS_ACTIVE));
		}
		orderDao.saveOrUpdate(order);
	}

	@Override
	public List<CoffeeOrder> fetchAll() {
		return orderDao.fetchAll().stream().filter(x -> "active".equals(x.getStatus().getName()))
				.sorted((x, y) -> x.getDateCreated().compareTo(y.getDateCreated())).collect(Collectors.toList());
	}

	@Override
	public void deleteOrder(Long id) {
		orderDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gmail.kleinikov.stanislav.service.OrderService#fetchById(java.lang.Long)
	 */
	@Override
	public CoffeeOrder fetchById(Long id) {
		return orderDao.fetchById(id);
	}

}
