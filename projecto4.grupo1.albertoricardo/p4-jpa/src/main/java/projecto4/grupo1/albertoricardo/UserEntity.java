package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import projecto4.grupo1.albertoricardo.roles.Role;

@Entity
@Table (name="users")
@XmlRootElement
public class UserEntity {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(unique=true,nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	@OneToMany(mappedBy="userOwner")
	private List<MusicEntity> uploadedMusics;
	@OneToMany(cascade =CascadeType.REMOVE,mappedBy="userOwner")
	private List<PlaylistEntity> userPlaylists;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="ROLES",joinColumns=@JoinColumn(name="user_id"),uniqueConstraints = @UniqueConstraint(columnNames={"roles","user_id"}))
	@Enumerated(EnumType.STRING)
	private List<Role> roles;
	public UserEntity() {
		super();
	}

	public UserEntity(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "ID: "+id+", E-mail: "+email+", password: "+password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (roles == null) {
			roles = new ArrayList<>();
			roles.add(role);
		} else roles.add(role);
	}

	//	public List<RoleEntity> getRoles() {
	//		return roles;
	//	}
	//
	//	public void setRoles(List<RoleEntity> roles) {
	//		this.roles = roles;
	//	}
	//	
	//	public void addRole(RoleEntity role) {
	//		if (this.roles == null) {
	//			this.roles = new ArrayList<RoleEntity>();
	//			roles.add(role);
	//		} else this.roles.add(role);
	//	}










}
