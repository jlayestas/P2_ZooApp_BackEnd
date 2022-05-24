package com.zoo.models;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="habitat_type")
public class HabitatType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="t_id")
	public int id;
	
	@Column(name="t_name", nullable=false)
	public String name;

	public String getName() {
		return name;
	}

	public HabitatType() {
		super();
	}

	public int getId() {
		return id;
	}

	public HabitatType(String name) {
		super();
		this.name = name;
	}
	

	public HabitatType(int id) {
		super();
		this.id = id;
	}

	public HabitatType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "HabitatType [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HabitatType other = (HabitatType) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

}
