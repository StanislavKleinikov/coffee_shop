package com.gmail.kleinikov.stanislav.service;

import java.util.List;

import javax.ejb.Local;

import com.gmail.kleinikov.stanislav.entity.Coffee;

/**
 * Service class for {@link com.gmail.kleinikov.stanislav.entity.Coffee}
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Local
public interface CoffeeService {

	List<Coffee> fetchAll();

}
