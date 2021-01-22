package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = () -> {
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));
        
        return players;
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchFindsExistingPlayer() {
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void searchRecognisesNonexistentPlayer() {
        assertTrue(stats.search("Kariya")==null);
    }
    
    @Test
    public void teamFindsTeamMembers() {
        String result = stats.team("DET").get(0).getName();
        
        assertEquals("Yzerman", result);
    }
    
    @Test
    public void teamFindsOnlyTeamMembers() {
        int resultSize = stats.team("EDM").size();
        
        assertTrue(resultSize==3);
    }
    
    @Test
    public void topScorersFoundCorrectly() {
        String expected = "Gretzky\nLemieux\nYzerman\n";
        String result = "";
        List<Player> scorers = stats.topScorers(3);
        for (Player player : scorers) {
            result = result + player.getName() + "\n";
        }
        
        assertEquals(expected, result);
    }
    
    @Test
    public void topScorersArrangedCorrectly() {
        String expected = "Semenko";
        String result = stats.topScorers(5).get(4).getName();
        
        assertEquals(expected, result);
    }
}
