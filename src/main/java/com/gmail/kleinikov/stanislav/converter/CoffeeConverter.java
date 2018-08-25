package com.gmail.kleinikov.stanislav.converter;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.gmail.kleinikov.stanislav.controller.CoffeeController;
import com.gmail.kleinikov.stanislav.entity.Coffee;

/**
 * The {@link FacesConverter} implementation that was designed for converting a
 * {@link Coffee} object to {@link String} and on the contrary
 * 
 *
 * @author Stanislav Kleinikov
 * @version 1.0
 */
@FacesConverter(value = "coffeeConverter")
public class CoffeeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String coffeeId) {
		Application application = context.getApplication();
		CoffeeController coffeeController = application.evaluateExpressionGet(context, "#{coffeeController}",
				CoffeeController.class);
		Coffee coffee = coffeeController.getCoffee(Long.valueOf(coffeeId));
		return coffee;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}

		if (!(value instanceof Coffee)) {
			throw new ConverterException("Value is not a valid instance of SomeEntity.");
		}
		return ((Coffee) value).getId().toString();
	}

}
