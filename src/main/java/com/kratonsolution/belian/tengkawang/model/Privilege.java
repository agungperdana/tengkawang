package com.kratonsolution.belian.tengkawang.model;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public enum Privilege {

	User, Enroll, Administrator, Super_Administrator;

	public String toString() {

		switch (this) {
			case Administrator:
				return "Administrator";
			case Enroll:
				return "Enroll";
			case Super_Administrator:
				return "Super Administrator";
			default:
				return "User";
		}
	}
}
