package di.uoa.gr.m151.socialapp.Helper;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class InitialDummyScoringStrategy implements ScoringStrategy{

    static final TimeUnit evaluationTimeUnit = TimeUnit.HOURS;

    @Override
    public int calculateScore(int rating, Timestamp updateTime) {
        if (rating == 0) {
            return rating;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long diffInMS = timestamp.getTime() - updateTime.getTime();
        long diffInDays = evaluationTimeUnit.convert(diffInMS, TimeUnit.MILLISECONDS);

        int complexScore = rating - (int) diffInDays;

        if (complexScore <= 0) {
            complexScore = 1;
        }

        return complexScore;

    }
}
