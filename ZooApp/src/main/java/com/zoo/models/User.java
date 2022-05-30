package com.zoo.models;

import java.util.Objects;

import javax.persistence.*;


@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="u_id")
	private int userId;
	
	@Column(name="u_username", unique = true, nullable = false)
	private String username;
	
	@Column(name="u_password", nullable = false, length = 65)
	private String password;
	
	@Column(name="u_first_name", nullable = false, length = 20)
	private String firstName;
	
	@Column(name="u_last_name", nullable = false, length = 20)
	private String lastName;
	
	@Column(name="u_email", nullable = false, length = 45)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="r_id", referencedColumnName = "r_id")
	private UserRole userRole;

	public User() {
	}

	public User(String username, String password, String firstName, String lastName, String email, UserRole userRole) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public User(int userId, String username, String password, String firstName, String lastName, String email,
			UserRole userRole) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, password, userId, userRole, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& userId == other.userId && Objects.equals(userRole, other.userRole)
				&& Objects.equals(username, other.username);
	}
	
	
}
