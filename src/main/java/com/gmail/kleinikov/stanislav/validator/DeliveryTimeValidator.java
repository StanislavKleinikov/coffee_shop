package com.gmail.kleinikov.stanislav.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * The {@link FacesValidator} implementation that was designed for check the
 * data introduced by a user
 * 
 * Performs whether the fields 'delivery time from' is before 'delivery time to'
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@FacesValidator(value = "deliveryTimeValidator")
public class DeliveryTimeValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput input = (UIInput) component.getAttributes().get("deliveryTimeFrom");
		Date timeFrom = (Date) input.getValue();
		Date timeTo = (Date) value;
		if (timeFrom != null && timeTo != null) {
			if (timeTo.before(timeFrom)) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid time",
						"'Delivery time to' must be after 'delivery time to'");
				throw new ValidatorException(msg);
			}
		}
	}

}
