package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.UserDTO;
import di.uoa.gr.m151.socialapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

	User save(User appUser);

	User update(User user);

	List<User> findAllUsers(Integer page);

	boolean userExists(String username);

	UserDTO fillUserDTO(User user);

	public UserDTO fillEnhancedUserDTO(User user, Boolean includeRatings, Boolean includeFeedPosts);

	List<UserDTO> searchUsers(String usernamePart);

}
