package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.DTO.*;
import di.uoa.gr.m151.socialapp.entity.ForumThread;
import di.uoa.gr.m151.socialapp.entity.Page;
import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import di.uoa.gr.m151.socialapp.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/forum/")
public class ForumController {

    @Autowired
    ForumService forumService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/page")
    public Page addPage(@RequestBody Page page) {
        return forumService.savePage(page);
    }

    @PostMapping("/page/pageRating")
    @PreAuthorize("#pageRatingDTO.username == authentication.name")
    public Boolean addPageRating(@RequestBody PageRatingDTO pageRatingDTO) {
        return forumService.savePageRating(pageRatingDTO);
    }

    @PostMapping("/page/thread")
    public ForumThread addThread(@RequestBody ForumThreadDTO dto) {
        return forumService.saveForumThread(dto);
    }


    @PostMapping("/page/thread/threadPost")
    @PreAuthorize("#dto.creatorUsername == authentication.name")
    public ThreadPost addThreadPost(@RequestBody ThreadPostDTO dto) {
        return forumService.saveThreadPost(dto);
    }

    @PostMapping("/page/thread/threadPost/upvote")
    @PreAuthorize("#threadVoteDTO.username == authentication.name")
    public Boolean addUpVote(@RequestBody ThreadVoteDTO threadVoteDTO) {
        return forumService.saveThreadPostUpVote(threadVoteDTO);
    }

    @GetMapping(value = {"/page/{id}", "/page"})
    public Page retrievePage(@PathVariable(required = false) UUID id, @RequestParam(required = false) String title) {
        return id != null ? forumService.findPageById(id) : forumService.findPageByTitle(title);
    }


    @GetMapping(value = {"/page/thread/{id}"})
    public ForumThread retrieveThread(@PathVariable UUID id) {
        return forumService.findForumThreadById(id);
    }

    @GetMapping(value = {"/page/thread/threadPost/{id}"})
    public ThreadPost retrieveThreadPost(@PathVariable UUID id) {
        return forumService.findThreadPostById(id);
    }

    @GetMapping("/pages")
    public List<PageDTO> retrievePages(@RequestParam String currentUsername) {
        return forumService.findAllPages(currentUsername);
    }

    @GetMapping("/page/{pageId}/threads")
    public List<ForumThread> retrievePageThreads(@PathVariable UUID pageId) {

        return forumService.findAllThreadsByPage(pageId);
    }

    @GetMapping("/page/thread/{threadId}/threadposts")
    public List<ThreadPostDTO> retrieveThreadPosts(@PathVariable UUID threadId, @RequestParam String currentUsername, @
            RequestParam(required = false) Integer page) {

        if (page == null ) {
            page = 0;
        }

        return forumService.findAllThreadsPostsByThread(threadId, currentUsername, page);
    }

    @DeleteMapping("/page/thread/threadPost/upvote")
    @PreAuthorize("#username == authentication.name")
    public boolean removeUserReaction(@RequestParam UUID postId, @RequestParam String username) {
        return forumService.removeThreadPostUpVote(postId, username);
    }



}
