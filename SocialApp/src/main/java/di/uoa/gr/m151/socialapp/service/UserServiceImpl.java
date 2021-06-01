package di.uoa.gr.m151.socialapp.service;


import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.DTO.PageRatingDTO;
import di.uoa.gr.m151.socialapp.DTO.UserDTO;
import di.uoa.gr.m151.socialapp.entity.*;
import di.uoa.gr.m151.socialapp.repository.RoleRepository;
import di.uoa.gr.m151.socialapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
	public List<User> findAllUsers(Integer page) {
		Pageable pageable = PageRequest.of(page,20);
		return userRepository.findAll(pageable).toList();
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
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_SIMPLE")));

		if (user.getColor() == null || user.getColor().isEmpty()) {
			Random rand = new Random();
			int randomColor = rand.nextInt(5);
			switch (randomColor) {
				case 0:
					user.setColor("cornflowerblue");
					break;
				case 1:
					user.setColor("black");
				case 2:
					user.setColor("darkgreen");
				case 3:
					user.setColor("rebeccapurple");
				case 4:
					user.setColor("yellow");
			}
		}

		 // save user in the database
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public UserDTO fillUserDTO(User user) {

		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setPhone(user.getPhone());
		userDTO.setColor(user.getColor());

		return userDTO;

	}

	public UserDTO fillEnhancedUserDTO(User user, Boolean includeRatings, Boolean includeFeedPosts) {
		UserDTO userDTO = fillUserDTO(user);

		if (includeRatings) {
			List<PageRatingDTO> ratingsList = new ArrayList<PageRatingDTO>();
			for (UserPageRating rating : user.getPageRatings()) {
				PageRatingDTO dto = new PageRatingDTO();
				dto.setUsername(user.getUsername());
				dto.setPageId(rating.getPage().getId());
				dto.setRating(rating.getRating());
				dto.setPageTitle(rating.getPage().getTitle());
				ratingsList.add(dto);
			}
			userDTO.setPageRatings(ratingsList);
		}

		if (includeFeedPosts) {

			List<FeedPostDTO> feedPostList = new ArrayList<>();

			for (FeedPost feedPost : user.getFeedPosts()) {
				FeedPostDTO dto = new FeedPostDTO();
				dto.setPostTime(dto.getDateFormat().format(feedPost.getPostTime()));
				dto.setContent(feedPost.getContent());
				dto.setPostId(feedPost.getId());
				dto.setUsername(feedPost.getUser().getUsername());
				List<FeedReactionDTO> reactionList = new ArrayList<FeedReactionDTO>();
				for (FeedReaction feedReaction : feedPost.getUserReactions()) {
					FeedReactionDTO feedReactionDTO = new FeedReactionDTO();
					feedReactionDTO.setPostId(feedReaction.getFeedPost().getId());
					feedReactionDTO.setUsername(feedReaction.getUser().getUsername());
					if (feedReaction.getUser().getUsername().equals(user.getUsername())) {
						dto.setCurrentUserReaction(feedReaction.getReactionType());
					}
					feedReactionDTO.setReactionType(feedReaction.getReactionType());
					reactionList.add(feedReactionDTO);
				}
				dto.setUserReactions(reactionList);
				feedPostList.add(dto);
			}
			userDTO.setUserFeedPosts(feedPostList);
		}

		return userDTO;
	}

	@Override
	public List<UserDTO> searchUsers(String usernamePart) {

		List<User> userList = userRepository.findByUsernameContaining(usernamePart);
		List<UserDTO> dtoList = new ArrayList<UserDTO>();

		for (User user : userList) {
			UserDTO dto = fillUserDTO(user);
			dtoList.add(dto);
		}

		return dtoList;
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
