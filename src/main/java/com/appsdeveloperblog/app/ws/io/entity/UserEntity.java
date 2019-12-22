package com.appsdeveloperblog.app.ws.io.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {
 
	private static final long serialVersionUID = 5313493413859894403L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String userId;

	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus = false;
	
	@OneToMany(mappedBy="userDetails", cascade=CascadeType.ALL)
	private List<AddressEntity> addresses;
	
	@ManyToMany(cascade= { CascadeType.PERSIST }, fetch = FetchType.EAGER )
	@JoinTable(name="users_roles", 
			joinColumns=@JoinColumn(name="users_id",referencedColumnName="id"), 
			inverseJoinColumns=@JoinColumn(name="roles_id",referencedColumnName="id"))
	private Collection<RoleEntity> roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}
	
	

}
