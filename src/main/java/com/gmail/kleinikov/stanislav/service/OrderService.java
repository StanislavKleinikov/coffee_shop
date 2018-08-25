package com.gmail.kleinikov.stanislav.service;

import java.util.List;

import javax.ejb.Local;

import com.gmail.kleinikov.stanislav.entity.CoffeeOrder;

/**
 * Service class for {@link com.gmail.kleinikov.stanislav.entity.CoffeeOrder}
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Local
public interface OrderService {

	void saveOrder(CoffeeOrder order);

	List<CoffeeOrder> fetchAll();

	void deleteOrder(Long id);

	CoffeeOrder fetchById(Long id);
}
