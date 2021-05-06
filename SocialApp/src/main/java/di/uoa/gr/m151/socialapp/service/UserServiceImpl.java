package di.uoa.gr.m151.socialapp.service;


import di.uoa.gr.m151.socialapp.entity.Role;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.repository.RoleRepository;
import di.uoa.gr.m151.socialapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public boolean userExists(String username) {
		return userRepository.existsByUsername(username);
	}


	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userRepository.findByUsername(userName);
	}

	@Override
	@Transactional
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_SIMPLE")));

		 // save user in the database
		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//RegisteredUser user = userDao.findByUserName(userName);
		User user = userRepository.findByUsername(userName);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
