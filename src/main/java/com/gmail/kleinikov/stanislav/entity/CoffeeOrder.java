package com.gmail.kleinikov.stanislav.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "fetchAllOrder", query = "from CoffeeOrder"),
		@NamedQuery(name = "deleteOrder", query = "delete from CoffeeOrder where id=:id") })

/**
 * Simple JavaBean domain object that represents a {@code CoffeeOrder}
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@Entity
@Table(name = "coffee_order")
public class CoffeeOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "coffee_order_gen", sequenceName = "coffee_order_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "coffee_order_gen")
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "coffee")
	private Coffee coffee;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "delivery_time_from")
	@Temporal(TemporalType.TIME)
	private Date deliveryTimeFrom;

	@Column(name = "delivery_time_to")
	@Temporal(TemporalType.TIME)
	private Date deliveryTimeTo;

	@Column(name = "delivery")
	private Boolean delivery;

	@Column(name = "price")
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "status")
	private Status status;

	@Column(name = "date_created", insertable = false, updatable = false, nullable = true)
	private Date dateCreated;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the coffee
	 */
	public Coffee getCoffee() {
		return coffee;
	}

	/**
	 * @param coffee
	 *            the coffee to set
	 */
	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the deliveryTimeFrom
	 */
	public Date getDeliveryTimeFrom() {
		return deliveryTimeFrom;
	}

	/**
	 * @param deliveryTimeFrom
	 *            the deliveryTimeFrom to set
	 */
	public void setDeliveryTimeFrom(Date deliveryTimeFrom) {
		this.deliveryTimeFrom = deliveryTimeFrom;
	}

	/**
	 * @return the deliveryTimeTo
	 */
	public Date getDeliveryTimeTo() {
		return deliveryTimeTo;
	}

	/**
	 * @param deliveryTimeTo
	 *            the deliveryTimeTo to set
	 */
	public void setDeliveryTimeTo(Date deliveryTimeTo) {
		this.deliveryTimeTo = deliveryTimeTo;
	}

	/**
	 * @return the delivery
	 */
	public Boolean getDelivery() {
		return delivery;
	}

	/**
	 * @param delivery
	 *            the delivery to set
	 */
	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coffee == null) ? 0 : coffee.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((deliveryTimeFrom == null) ? 0 : deliveryTimeFrom.hashCode());
		result = prime * result + ((deliveryTimeTo == null) ? 0 : deliveryTimeTo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoffeeOrder other = (CoffeeOrder) obj;
		if (coffee == null) {
			if (other.coffee != null)
				return false;
		} else if (!coffee.equals(other.coffee))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
		if (deliveryTimeFrom == null) {
			if (other.deliveryTimeFrom != null)
				return false;
		} else if (!deliveryTimeFrom.equals(other.deliveryTimeFrom))
			return false;
		if (deliveryTimeTo == null) {
			if (other.deliveryTimeTo != null)
				return false;
		} else if (!deliveryTimeTo.equals(other.deliveryTimeTo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	/*
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return weight + "kg. " + (coffee == null ? null : coffee.getName()) + " "
				+ (deliveryTimeFrom == null ? null : " from " + new SimpleDateFormat("HH:mm").format(deliveryTimeFrom))
				+ (deliveryTimeTo == null ? null : " to " + new SimpleDateFormat("HH:mm").format(deliveryTimeTo)) + " "
				+ (date == null ? null : new SimpleDateFormat("dd:MM:yyyy").format(date)) + " "
				+ (Boolean.valueOf("true").equals(delivery) ? " Delivery by courier " : " Pickup of goods ")
				+ " TotalPrice: " + price;

	}

}
