package com.gmail.kleinikov.stanislav.controller;

import static com.gmail.kleinikov.stanislav.util.ConstantValue.DELIVERY_FEE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import com.gmail.kleinikov.stanislav.entity.CoffeeOrder;
import com.gmail.kleinikov.stanislav.service.OrderService;

/**
 * CDI Bean that controls the operations on an {@code CoffeeOrder}
 * 
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Model
public class OrderController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private OrderService orderService;

	private Long orderId;

	private CoffeeOrder coffeeOrder;

	private List<CoffeeOrder> orders;

	@PostConstruct
	public void init() {
		orders = orderService.fetchAll();
		coffeeOrder = new CoffeeOrder();
		coffeeOrder.setDelivery(true);
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orders
	 */
	public List<CoffeeOrder> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(List<CoffeeOrder> orders) {
		this.orders = orders;
	}

	/**
	 * @return the coffeeOrder
	 */
	public CoffeeOrder getCoffeeOrder() {
		return coffeeOrder;
	}

	/**
	 * @param coffeeOrder
	 *            the coffeeOrder to set
	 */
	public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
		this.coffeeOrder = coffeeOrder;
	}

	/**
	 * Performs a method call for order validation and subsequent saving
	 *
	 * @author Stanislav Kleinikov
	 * @version 1.0
	 * @return String definition of a page
	 */
	public String saveOrder() {
		if (validateOrder()) {
			orderService.saveOrder(coffeeOrder);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"The order has been accepted for processing", coffeeOrder.toString()));
			return "index?faces-redirect=true";
		} else {
			return null;
		}

	}

	/**
	 * Counts total price of an order when its parameters (weight, price of
	 * coffee,delivery) is changed
	 *
	 * Sets new value for price of {@link CoffeeOrder}
	 *
	 * @author Stanislav Kleinikov
	 * @version 1.0
	 */
	public void priceCount() {
		if (coffeeOrder.getWeight() != null && coffeeOrder.getCoffee() != null) {
			coffeeOrder
					.setPrice(coffeeOrder.getCoffee().getPrice().multiply(BigDecimal.valueOf(coffeeOrder.getWeight()))
							.add(BigDecimal.valueOf(coffeeOrder.getDelivery() ? DELIVERY_FEE : 0))
							.setScale(2, BigDecimal.ROUND_DOWN));
		}
	}

	/**
	 * Fetches the {@link CoffeeOrder} from database using its id
	 *
	 * @author Stanislav Kleinikov
	 * @version 1.0
	 */
	public void fetchOrder() {
		if (orderId != null) {
			coffeeOrder = orderService.fetchById(orderId);
		}
	}

	/**
	 * Deletes the @{link CoffeeOrder}
	 *
	 * @author Stanislav Kleinikov
	 * @version 1.0
	 */
	public String deleteOrder() {
		FacesMessage msg;
		if (orderId != null) {
			orderService.deleteOrder(orderId);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully", "The order has been deleted");
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, msg);
			return "index?faces-redirect=true";
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The error has been occured");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return null;
	}

	/**
	 * Performs order validation
	 *
	 * @author Stanislav Kleinikov
	 * @version 1.0
	 * @return whether the order is valid
	 */
	private boolean validateOrder() {
		boolean valid = true;
		FacesContext context = FacesContext.getCurrentInstance();
		if (coffeeOrder.getCoffee() == null) {
			context.addMessage("coffee",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid: ", "The coffee is not selected."));
			valid = false;
		}
		if (coffeeOrder.getDelivery() == null) {
			context.addMessage("delivery",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid: ", "The delivery is not selected."));
			valid = false;
		}
		if (coffeeOrder.getWeight() == null) {
			context.addMessage("weight",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid: ", "The weight is not selected."));
			valid = false;
		}
		if (coffeeOrder.getDate() == null) {
			context.addMessage("date",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid: ", "The date is not selected."));
			valid = false;
		}
		if (coffeeOrder.getDeliveryTimeFrom() == null) {
			context.addMessage("deliveryTimeFrom",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid: ", "The delivery time is not selected."));
			valid = false;
		}
		if (coffeeOrder.getDeliveryTimeTo() == null) {
			context.addMessage("deliveryTimeTo",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid: ", "The delivery time is not selected."));
			valid = false;
		}
		return valid;
	}
}
