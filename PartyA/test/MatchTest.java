import com.partyA.bean.*;
import com.partyA.service.MatchService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: Nana Yin
 * Date: 12/8/19
 */
public class MatchTest {

    private Match match = new Match();
    private MatchService matchService = new MatchService();


    @Test
    public void testSave(){
        match.setWhiteID(4);
        match.setWhiteName("yinnana4");
        match.setBlackID(5);
        match.setBlackName("yinnana5");
        match.setResult("black won");
        match.setStartTime("2019-12-06 21:21:21 PM");
        match.setEndTime("2019-12-06 22:22:22 PM");

        matchService.saveMatch(match);
    }

    @Test
    public void testSearch(){
        Map<String,Object> map =  matchService.searchMatch(1,1,5);
        int total = (int) map.get("total");
        assertEquals(3,total);
    }

}