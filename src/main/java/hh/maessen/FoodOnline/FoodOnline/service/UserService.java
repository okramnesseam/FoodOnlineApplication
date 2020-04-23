package hh.maessen.FoodOnline.FoodOnline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.maessen.FoodOnline.FoodOnline.model.User;
import hh.maessen.FoodOnline.FoodOnline.model.UserRepository;

// This is the interface that specifies the service methods.
// This class is used by spring security to authenticate and authorize user

@Service
public class UserService implements UserDetailsService{

	private final UserRepository repository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	User curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   

} 
