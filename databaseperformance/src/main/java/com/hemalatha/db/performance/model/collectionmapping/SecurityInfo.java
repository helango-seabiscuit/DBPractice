package com.hemalatha.db.performance.model.collectionmapping;

import com.hemalatha.db.performance.converters.CaseConverter;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class SecurityInfo {

	private Boolean isSecurityCleared;

	@ElementCollection
	//@Convert(disableConversion = true)
	private List<String> roles;

	public Boolean isSecurityCleared() {
		return isSecurityCleared;
	}

	public void setSecurityCleared(Boolean securityCleared) {
		isSecurityCleared = securityCleared;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
