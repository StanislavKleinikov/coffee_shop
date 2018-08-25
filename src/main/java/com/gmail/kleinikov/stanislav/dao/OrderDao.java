package com.gmail.kleinikov.stanislav.dao;

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
public interface OrderDao {

	List<CoffeeOrder> fetchAll();

	void saveOrUpdate(CoffeeOrder order);

	void delete(Long id);

	CoffeeOrder fetchById(Long id);
}
