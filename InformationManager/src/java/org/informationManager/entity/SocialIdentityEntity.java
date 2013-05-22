/**
 * 
 */
package org.informationManager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sony
 * 
 */
@Entity
@Table(name = "social_identity")
public class SocialIdentityEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "socialId")
	private int socialId;
	private String socialText;

	/**
	 * @return the socialId
	 */
	public int getSocialId() {
		return socialId;
	}

	/**
	 * @param socialId
	 *            the socialId to set
	 */
	public void setSocialId(int socialId) {
		this.socialId = socialId;
	}

	/**
	 * @return the socialText
	 */
	public String getSocialText() {
		return socialText;
	}

	/**
	 * @param socialText
	 *            the socialText to set
	 */
	public void setSocialText(String socialText) {
		this.socialText = socialText;
	}

}
