package org.informationManager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_sort")
public class PersonSortEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "sortId")
	private int sortId;
	private String sortText;

	/**
	 * @return the sortId
	 */
	public int getSortId() {
		return sortId;
	}

	/**
	 * @param sortId
	 *            the sortId to set
	 */
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	/**
	 * @return the sortText
	 */
	public String getSortText() {
		return sortText;
	}

	/**
	 * @param sortText
	 *            the sortText to set
	 */
	public void setSortText(String sortText) {
		this.sortText = sortText;
	}

}
