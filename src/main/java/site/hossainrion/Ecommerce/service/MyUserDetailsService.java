package site.hossainrion.Ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import site.hossainrion.Ecommerce.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService
{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserDetails userDetails = userRepository.findByUsername(username);
		return userDetails;
	}
	
}