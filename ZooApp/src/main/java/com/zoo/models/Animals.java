package com.zoo.models;

import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name="Animals")
public class Animals {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="a_id")
	private int id;
	
	@Column(name="a_name", unique=true, nullable=false)
	private String name;
	
	@OneToOne
	@JoinColumn(name="t_id", referencedColumnName = "t_id")
	private HabitatType habitatType; 
	
	@Column(name="a_price", nullable=false)
	private double lifespan;
	
	@Column(name="a_price", nullable=false)
	private String diet;

	public Animals() {
		super();
	}

	public Animals(String name, HabitatType habitatType, double lifespan, String diet) {
		super();
		this.name = name;
		this.habitatType = habitatType;
		this.lifespan = lifespan;
		this.diet = diet;
	}

	public Animals(int id, String name, HabitatType habitatType, double lifespan, String diet) {
		super();
		this.id = id;
		this.name = name;
		this.habitatType = habitatType;
		this.lifespan = lifespan;
		this.diet = diet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HabitatType getHabitatType() {
		return habitatType;
	}

	public void setHabitatType(HabitatType habitatType) {
		this.habitatType = habitatType;
	}

	public double getLifespan() {
		return lifespan;
	}

	public void setLifespan(double lifespan) {
		this.lifespan = lifespan;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diet, habitatType, id, lifespan, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animals other = (Animals) obj;
		return Objects.equals(diet, other.diet) && Objects.equals(habitatType, other.habitatType) && id == other.id
				&& Double.doubleToLongBits(lifespan) == Double.doubleToLongBits(other.lifespan)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Animals [id=" + id + ", name=" + name + ", habitatType=" + habitatType + ", lifespan=" + lifespan
				+ ", diet=" + diet + "]";
	}

}
