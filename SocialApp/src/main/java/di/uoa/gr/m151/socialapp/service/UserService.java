package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

	public void save(User appUser);

	public List<User> findAllUsers();

	boolean userExists(String username);

}
