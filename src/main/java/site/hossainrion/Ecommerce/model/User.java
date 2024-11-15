package site.hossainrion.Ecommerce.model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import site.hossainrion.Ecommerce.util.BCrypt;

@Entity
@Table( name = "user", uniqueConstraints={@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails
{
	private static final long serialVersionUID = -5582276510868868161L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private boolean enabled = true;
	private String authority;
	
	@Override
	public List<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		List<GrantedAuthority> auth = new ArrayList<>();
	    auth.add(new SimpleGrantedAuthority(authority));
		
		return auth;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = BCrypt.hash(password);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}