package di.uoa.gr.m151.socialapp.Helper;

import java.sql.Timestamp;

public interface ScoringStrategy {

    int calculateScore(int rating, Timestamp updateTime);

}
