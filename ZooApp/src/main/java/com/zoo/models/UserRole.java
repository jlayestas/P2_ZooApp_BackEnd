package com.zoo.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="r_id")
	private int id;

	@Column(name="r_role", nullable=false)
	private String role;
	
	public UserRole() {
		super();
	}
	public UserRole(String role) {
		super();
		this.role = role;
	}
	public UserRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public int getId() {

		return id;
	}
	public void setId(int id) {

		this.id = id;
	}
	public String getRole() {

		return role;
	}
	public void setRole(String role) {

		this.role = role;
	}
	@Override
	public String toString() {

		return "UserRoles [id=" + id + ", role=" + role + "]";
	}
	@Override
	public int hashCode() {

		return Objects.hash(id, role);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return id == other.id && Objects.equals(role, other.role);
	}
}