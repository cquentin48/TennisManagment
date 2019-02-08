import org.junit.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class TennisMatchTest {
    private ArrayList<TennisMatch> matchList;

    public TennisMatchTest() {
        this.matchList = new ArrayList<>();
        this.matchList.add(new TennisMatch(new TennisPlayer("Player1"), new TennisPlayer("Player2"),MatchType.BEST_OF_THREE,false));
        this.matchList.add(new TennisMatch(new TennisPlayer("Player1"), new TennisPlayer("Player2"),MatchType.BEST_OF_THREE,true));
        this.matchList.add(new TennisMatch(new TennisPlayer("Player1"), new TennisPlayer("Player2"),MatchType.BEST_OF_FIVE,false));
        this.matchList.add(new TennisMatch(new TennisPlayer("Player1"), new TennisPlayer("Player2"),MatchType.BEST_OF_FIVE,true));
    }

    /**
     * Check an end of set with tie break enabled
     */
    @Test
    public void newGameWithTieBreak(){

    }

    /**
     * Check an end of set with tie break enabled
     */
    @Test
    public void newGameWithoutTieBreak(){

    }

    /**
     * Check for a point if it is correctly set
     */
    @Test
    public void normalPointUpdated(){
        String point = TennisPoint.POINT_LIST[0];
        assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(0)), point);
        boolean pointOver = false;
        for(int i = 0;i<TennisPoint.POINT_LIST.length && pointOver == false;i++){

            matchList.get(0).updateWithPointWonBy(matchList.get(0).getPlayerObjectById(0));
            if(i<=2){
                point = TennisPoint.POINT_LIST[i+1];
            }else{
                pointOver = true;
                point = TennisPoint.POINT_LIST[0];
            }
            assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(0)), point);
        }
    }

    /**
     * Check an advantage situation in a tennis match
     */
    @Test
    public void advantage(){
        setCurrentPoint(TennisPoint.POINT_LIST[3],TennisPoint.POINT_LIST[3],0);
        assertTrue(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(0))=="A");
        assertTrue(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(1))=="40");
    }

    @Test
    public void advantageLost(){
        setCurrentPoint(TennisPoint.POINT_LIST[4],TennisPoint.POINT_LIST[3],0);
        assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(0)), "A");
        assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(1)), "40");
        matchList.get(0).updateWithPointWonBy(matchList.get(0).getPlayerObjectById(1));
        assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(0)), "40");
        assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(1)), "40");
    }

    @Test
    public void winPointWithAdvantage(){
        setCurrentPoint(TennisPoint.POINT_LIST[4],TennisPoint.POINT_LIST[3],0);
        assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(0)), "0");
        assertSame(matchList.get(0).pointsForPlayer(matchList.get(0).getPlayerObjectById(1)), "0");
        assertSame(0, matchList.get(0).getOwnerOfThePoint(0));
    }

    private void setCurrentPoint(String currentPointPlayer1, String currentPointPlayer2, int winnerPlayer) {
        matchList.get(0).setCurrentPoints(currentPointPlayer1, currentPointPlayer2);
        matchList.get(0).updateWithPointWonBy(matchList.get(0).getPlayerObjectById(winnerPlayer));
    }

    @Test
    public void updateSetWithoutTieBreak(){
        for(int i = 0; i<6; i++){
            setCurrentPoint(TennisPoint.POINT_LIST[4],TennisPoint.POINT_LIST[3],0);
            assertSame(i+1,this.matchList.get(0).getCurrentPoint());
            assertFalse(this.matchList.get(0).isGameWon());
        }
        setCurrentPoint(TennisPoint.POINT_LIST[4],TennisPoint.POINT_LIST[3],0);
        assertSame(1,this.matchList.get(0).getCurrentSet());
        assertTrue(this.matchList.get(0).isSetWon(0));
    }

    public void tearDown(){

    }
}