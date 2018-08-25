package com.gmail.kleinikov.stanislav.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.gmail.kleinikov.stanislav.entity.Coffee;
import com.gmail.kleinikov.stanislav.service.CoffeeService;

/**
 * CDI Bean that controls the operations on an {@code Coffee}
 * 
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Model
public class CoffeeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private CoffeeService coffeeService;

	private List<Coffee> coffeeList;

	@PostConstruct
	public void init() {
		coffeeList = coffeeService.fetchAll();
	}

	/**
	 * @return the coffeeList
	 */
	public List<Coffee> getCoffeeList() {
		return coffeeList;
	}

	/**
	 * @param coffeeList
	 *            the coffeeList to set
	 */
	public void setCoffeeList(List<Coffee> coffeeList) {
		this.coffeeList = coffeeList;
	}

	/**
	 * Fetches the {@link Coffee} from database using its id
	 * 
	 * @author Stanislav Kleinikov
	 * @version 1.0
	 * @return the {@link Coffee} object
	 */
	public Coffee getCoffee(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Coffee coffee : coffeeList) {
			if (id.equals(coffee.getId())) {
				return coffee;
			}
		}
		return null;
	}
}
