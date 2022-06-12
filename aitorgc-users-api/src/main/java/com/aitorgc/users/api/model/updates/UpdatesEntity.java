package com.aitorgc.users.api.model.updates;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Aitor Gil Callejo
 */
@Entity
@Table(name = "c_updates")
public class UpdatesEntity implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@Id
	@Basic(optional = false)
	@Column(name = "organization_id", nullable = false, length = 36)
	private String organizationId;

	@Column(name = "buildings")
	@Temporal(TemporalType.TIMESTAMP)
	private Date buildings;

	@Column(name = "floors")
	@Temporal(TemporalType.TIMESTAMP)
	private Date floors;

	@Column(name = "dictionaries")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dictionaries;

	@Column(name = "subcategories")
	@Temporal(TemporalType.TIMESTAMP)
	private Date subcategories;

	@Column(name = "users")
	@Temporal(TemporalType.TIMESTAMP)
	private Date users;

	@Column(name = "panel_notifications")
	@Temporal(TemporalType.TIMESTAMP)
	private Date panelNotifications;

	@Column(name = "groups_info")
	@Temporal(TemporalType.TIMESTAMP)
	private Date groupsInfo;

	/**
	 * Constructor de la clase.
	 */
	public UpdatesEntity() {
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param organizationId El identificador de la organización a la que pertenece.
	 * @param buildings      La fecha de última actualización de los edificios.
	 * @param floors         La fecha de última actualización de los pisos y sus
	 *                       mapas.
	 * @param dictionaries   La fecha de última actualización de los diccionarios.
	 * @param subcategories  La fecha de última actualización de las subcategorias y
	 *                       carácteristicas.
	 * @param users          La fecha de última actualización de los usuarios de la
	 *                       compañía.
	 */
	public UpdatesEntity(String organizationId, Date buildings, Date floors, Date dictionaries, Date subcategories,
			Date users, Date panelNotifications, Date groupsInfo) {
		this.organizationId = organizationId;
		this.buildings = buildings;
		this.floors = floors;
		this.dictionaries = dictionaries;
		this.subcategories = subcategories;
		this.users = users;
		this.panelNotifications = panelNotifications;
		this.groupsInfo = groupsInfo;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public Date getBuildings() {
		return buildings;
	}

	public void setBuildings(Date buildings) {
		this.buildings = buildings;
	}

	public Date getFloors() {
		return floors;
	}

	public void setFloors(Date floors) {
		this.floors = floors;
	}

	public Date getDictionaries() {
		return dictionaries;
	}

	public void setDictionaries(Date dictionaries) {
		this.dictionaries = dictionaries;
	}

	public Date getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Date subcategories) {
		this.subcategories = subcategories;
	}

	public Date getUsers() {
		return users;
	}

	public void setUsers(Date users) {
		this.users = users;
	}

	public Date getPanelNotifications() {
		return panelNotifications;
	}

	public void setPanelNotifications(Date panelNotifications) {
		this.panelNotifications = panelNotifications;
	}

	public Date getGroupsInfo() {
		return groupsInfo;
	}

	public void setGroupsInfo(Date groupsInfo) {
		this.groupsInfo = groupsInfo;
	}

}
