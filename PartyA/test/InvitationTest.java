import com.partyA.bean.GameBoard;
import com.partyA.bean.Invitation;
import com.partyA.service.InvitationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User: Nana Yin
 * Date: 10/11/19
 */

public class InvitationTest {
    private GameBoard board;
    @BeforeEach
    public void setUp(){
    }

    @Test
    public void test(){
        Invitation inv = new Invitation("123","234","123456","1","2");
        InvitationService i = new InvitationService();
        i.addInvitation(inv);
        assertEquals("{\"total\":1,\"page\":1,\"rows\":[]}", i.searchInvitation(1,1,"123"));
    }



}
