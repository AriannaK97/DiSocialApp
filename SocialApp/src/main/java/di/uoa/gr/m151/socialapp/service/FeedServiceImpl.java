package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.Helper.FeedPostDTOComparator;
import di.uoa.gr.m151.socialapp.Helper.InitialDummyScoringStrategy;
import di.uoa.gr.m151.socialapp.Helper.ScoringStrategy;
import di.uoa.gr.m151.socialapp.entity.*;
import di.uoa.gr.m151.socialapp.repository.FeedPostRepository;
import di.uoa.gr.m151.socialapp.repository.FeedReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class FeedServiceImpl implements FeedService{

    static int defaultFeedPostScore = 4;

    @Autowired
    FeedPostRepository feedPostRepository;

    @Autowired
    FeedReactionRepository feedReactionRepository;

    @Autowired
    UserService userService;

    @Override
    public FeedPost findById(UUID id) {
        return feedPostRepository.findById(id).orElse(null);
    }

    @Override
    public FeedPost saveFeedPost(FeedPostDTO feedPostDTO) {

        User postUser = userService.findByUserName(feedPostDTO.getUsername());

        if (postUser == null) {
            return null;
        }

        FeedPost feedPost = new FeedPost();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        feedPost.setPostTime(timestamp);
        feedPost.setContent(feedPostDTO.getContent());
        feedPost.setUser(postUser);

        return feedPostRepository.save(feedPost);
    }

    @Override
    public List<FeedPost> findAllPosts() {
        return feedPostRepository.findAll();
    }

    @Override
    public Collection<FeedPostDTO> retrieveFeed(String username) {
        List<FeedPost> feedPostList = feedPostRepository.findAll();
        List<FeedPostDTO> feedList = new ArrayList<FeedPostDTO>();

/*        PriorityQueue<FeedPostDTO> feedQueue = new PriorityQueue<FeedPostDTO>
                (50, new FeedPostDTOComparator());*/

        //feedPostList.remove(0);
        ScoringStrategy scoringStrategy = new InitialDummyScoringStrategy();

        for (FeedPost feedPost : feedPostList) {
            FeedPostDTO dto = new FeedPostDTO();
            dto.setPostTime(dto.getDateFormat().format(feedPost.getPostTime()));
            dto.setContent(feedPost.getContent());
            dto.setPostId(feedPost.getId());
            dto.setUsername(feedPost.getUser().getUsername());
            dto.setUserColor(feedPost.getUser().getColor());
            dto.setScore(scoringStrategy.calculateScore(defaultFeedPostScore, feedPost.getPostTime()));
            List<FeedReactionDTO> reactionList = new ArrayList<FeedReactionDTO>();
            for (FeedReaction feedReaction : feedPost.getUserReactions()) {
                FeedReactionDTO feedReactionDTO = new FeedReactionDTO();
                feedReactionDTO.setPostId(feedReaction.getFeedPost().getId());
                feedReactionDTO.setUsername(feedReaction.getUser().getUsername());
                if (feedReaction.getUser().getUsername().equals(username)) {
                    dto.setCurrentUserReaction(feedReaction.getReactionType());
                }
                feedReactionDTO.setReactionType(feedReaction.getReactionType());
                reactionList.add(feedReactionDTO);
            }
            dto.setUserReactions(reactionList);
            //feedQueue.add(dto);
            feedList.add(dto);
        }

        User user = userService.findByUserName(username);

        if (user == null) {
            return null;
        }

        for (UserPageRating pageRating : user.getPageRatings()) {

            int complexScore = scoringStrategy.calculateScore(
                    pageRating.getRating(), pageRating.getPage().getLastUpdated());
            if (complexScore > 0) {
                FeedPostDTO dto = new FeedPostDTO();
                dto.setScore(complexScore);
                dto.setPostTime(dto.getDateFormat().format(pageRating.getPage().getLastUpdated()));
                dto.setPostId(pageRating.getPage().getId());
                dto.setPageTitle(pageRating.getPage().getTitle());
                //feedQueue.add(dto);
                feedList.add(dto);
            }

        }
        feedList.sort(new FeedPostDTOComparator());
        //System.out.println(feedQueue);
        return feedList;
    }



    @Override
    public boolean saveFeedPostReaction(FeedReactionDTO feedReactionDTO){
        FeedPost feedPost = findById(feedReactionDTO.getPostId());
        User reactingUser = userService.findByUserName(feedReactionDTO.getUsername());

        if (feedPost == null || reactingUser == null) {
            return false;
        }
        /**
         * reaction '1' is the default reaction value for an existing reaction in the post
         * */
/*        if(feedReactionDTO.getReactionType() == 1)
            feedPost.addUserReaction(reactingUser, feedReactionDTO.getReactionType());
        else
            feedPost.removeUserReaction(reactingUser);*/
        feedPost.addUserReaction(reactingUser, feedReactionDTO.getReactionType());
        return  feedPostRepository.save(feedPost) != null;



        //return feedPost.getUserReactions();
    }


    public boolean removePostReaction(UUID feedPostId, String username ) {

        FeedPost feedPost = feedPostRepository.findById(feedPostId).orElse(null);
        User user = userService.findByUserName(username);

        if (feedPost == null || user == null) {
            return false;
        }

        if (feedPost.removeUserReaction(user)) {
            feedPostRepository.save(feedPost);
            return true;
        }
        return false;

    }

}
