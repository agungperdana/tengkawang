package com.kratonsolution.belian.tengkawang.model;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Getter
@Setter
public class MenuEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4362144267750007154L;
	
	private String menuID;
	
	private boolean delete;
	
	public MenuEvent(Object source, @NonNull String menuID, boolean delete) {
		super(source);
		setMenuID(menuID);
		setDelete(delete);
	}
}
