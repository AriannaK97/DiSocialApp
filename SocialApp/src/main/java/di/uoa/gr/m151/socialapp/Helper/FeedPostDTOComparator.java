package di.uoa.gr.m151.socialapp.Helper;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;

import java.util.Comparator;

public  class FeedPostDTOComparator implements Comparator<FeedPostDTO> {
    public int compare(FeedPostDTO f1, FeedPostDTO f2) {
        if (f1.getScore() < f2.getScore()) {
            return 1;
        }
        else if(f1.getScore() > f2.getScore()) {
            return -1;
        }
        return 0;
    }
}
