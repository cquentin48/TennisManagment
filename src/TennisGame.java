import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TennisGame {
    private List<TennisPoint> pointList;
    private int currentPoint;
    private int ownerOfTheGame;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TennisGame tennisGame = (TennisGame) o;
        return currentPoint == tennisGame.currentPoint &&
                ownerOfTheGame == tennisGame.ownerOfTheGame &&
                Objects.equals(pointList, tennisGame.pointList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointList, currentPoint, ownerOfTheGame);
    }

    public TennisGame() {
        ownerOfTheGame = -1;
        currentPoint = 0;
        pointList = new ArrayList<>();
        initSet();
    }

    public int getOwnerOfTheGame() {
        return ownerOfTheGame;
    }

    /**
     * Update the current game
     * @param playerId id of the player which has won the match
     */
    public void updateGame(int playerId){
        pointList.get(currentPoint).updatePoints(playerId);
        checkStatusOfCurrentPoint(playerId);
    }

    /**
     * Get the owner of the current point
     * @return owner of the current point
     */
    public int getOwnerOfThePoint(){
        return pointList.get(currentPoint).getOwnerOfThePoint();
    }

    /**
     * Get the owner of a chosen point
     * @return owner of a chosen point
     */
    public int getOwnerOfThePoint(int pointId){
        return pointList.get(pointId).getOwnerOfThePoint();
    }

    /**
     * Check status of the current Point
     * @param playerId id of the player
     */
    private void checkStatusOfCurrentPoint(int playerId) {
        if(pointList.get(currentPoint).isNewGame() == true){
            pointList.get(currentPoint).setOwnerOfThePoint(playerId);
            currentPoint++;
            initSet();
        }
        else if(getPoint(playerId)>=7 && (getPoint((playerId+1%2))+2)<getPoint(playerId)){
            ownerOfTheGame = playerId;
        }
    }

    /**
     * Check if a game is won
     * @param isTieBreak is the match has a tie break
     * @return true game won false game still playing
     */
    public boolean isSetWon(boolean isTieBreak){
        if(isTieBreak){
            if(currentPoint>7){
                if(pointList.get(currentPoint).getPointFromPlayerId(0)>pointList.get(currentPoint).getPointFromPlayerId(1)%2){
                    return newGame(0);
                }
                else if(pointList.get(currentPoint).getPointFromPlayerId(0)>pointList.get(currentPoint).getPointFromPlayerId(1)%2){
                    return newGame(1);
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        else {
            if (currentPoint >= 6) {
                if (countOwnedPoints(0) > countOwnedPoints(1) + 2) {
                    return newGame(0);
                } else if (countOwnedPoints(0) + 2 < countOwnedPoints(1)) {
                    return newGame(1);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * New game initialization
     * @param i
     * @return true
     */
    private boolean newGame(int i) {
        ownerOfTheGame = i;
        return true;
    }

    /**
     * Count owned points
     * @param playerId id of the player of which to count
     * @return number of points owned by the player
     */
    private int countOwnedPoints(int playerId){
        int pointOwnedByPlayer = 0;
        for(int i = 0;i<pointList.size();i++){
            if(pointList.get(i).getOwnerOfThePoint() == playerId){
                pointOwnedByPlayer++;
            }
        }
        return pointOwnedByPlayer;
    }

    /**
     * Update a tie break set
     * @param playerId
     */
    public void updateTieBreakPoint(int playerId){
        if(countOwnedPoints(1) == countOwnedPoints(0) && countOwnedPoints(0) == 6){
            pointList.get(currentPoint).updatePointTieBreak(playerId);

        }else {
            pointList.get(currentPoint).updatePoints(playerId);
        }
        checkStatusOfCurrentPoint(playerId);
    }

    /**
     * Get the points from a player
     * @param playerId playerId
     * @return point count
     */
    public int getPoint(int playerId){
        int count=0;
        for(TennisPoint aPoint:pointList){
            if(aPoint.getOwnerOfThePoint()==playerId){
                count++;
            }
        }
        return count;
    }

    /**
     * Get current point
     * @param playerId id of the player
     * @return point from a player
     */
    public String getCurrentPoint(int playerId){
        return pointList.get(currentPoint).getPointList().get(playerId);
    }

    /**
     * Get current point
     * @return current played point
     */
    public int getCurrentPoint(){
        return currentPoint;
    }

    /**
     * Init a new set
     */
    private void initSet(){
        TennisPoint p = new TennisPoint();
        pointList.add(currentPoint,p);
    }

    /**
     * Check if this set has been won
     * @return {true} end of the set {false} new set
     */
    public boolean isNewSet(){
        return (ownerOfTheGame !=-1)?true:false;
    }

    /**
     * Setting current points for the players
     * @param pointPlayer1 player1 actual points
     * @param pointPlayer2 player2 actual points
     */
    public void setCurrentPoints(String pointPlayer1, String pointPlayer2) {
        pointList.get(currentPoint).setCurrentPoints(pointPlayer1,pointPlayer2);
    }
}
