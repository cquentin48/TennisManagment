import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TennisSet {
    private List<TennisPoint> pointList;
    private int currentPoint;
    private int ownerOfTheSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TennisSet tennisSet = (TennisSet) o;
        return currentPoint == tennisSet.currentPoint &&
                ownerOfTheSet == tennisSet.ownerOfTheSet &&
                Objects.equals(pointList, tennisSet.pointList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointList, currentPoint, ownerOfTheSet);
    }

    public TennisSet() {
        this.ownerOfTheSet = -1;
        this.currentPoint = 0;
        this.pointList = new ArrayList<>();
        this.initSet();
    }

    public int getOwnerOfTheSet() {
        return ownerOfTheSet;
    }

    /**
     * Update the current set
     * @param playerId id of the player which has won the match
     * @param isTieBreak is this match has a tie-break
     * @param maxSets maxNumber of Sets
     */
    public void updateSet(int playerId, boolean isTieBreak, int maxSets){
        this.pointList.get(currentPoint).updatePoints(playerId);
        this.checkStatusOfCurrentPoint(playerId);
    }

    /**
     * Get the owner of the current point
     * @return owner of the currentPoint
     */
    public int getOwnerOfThePoint(){
        return pointList.get(this.currentPoint).getOwnerOfThePoint();
    }


    /**
     * Get the owner of the precedent point
     * @return owner of the precedent point
     */
    public int getOwnerOfPrecedentPoint(){
        return pointList.get(this.currentPoint-1).getOwnerOfThePoint();
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
        if(this.pointList.get(currentPoint).isNewGame() == true){
            this.pointList.get(currentPoint).setOwnerOfThePoint(playerId);
            this.currentPoint++;
            this.initSet();
        }
        else if(this.getPoint(playerId)>=7 && (this.getPoint((playerId+1%2))+2)<this.getPoint(playerId)){
            this.ownerOfTheSet = playerId;
        }
    }

    /**
     * Update a tie break set
     * @param playerId
     */
    public void updateTieBreakSet(int playerId){
        this.pointList.get(this.currentPoint).updatePointTieBreak(playerId);
        this.checkStatusOfCurrentPoint(playerId);
    }

    /**
     * Get the points from a player
     * @param playerId playerId
     * @return point count
     */
    public int getPoint(int playerId){
        int count=0;
        for(TennisPoint aPoint:this.pointList){
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
        return this.pointList.get(this.currentPoint).getPointList().get(playerId);
    }

    /**
     * Init a new set
     */
    private void initSet(){
        TennisPoint p = new TennisPoint();
        this.pointList.add(this.currentPoint,p);
    }

    /**
     * Check if this set has been won
     * @return {true} end of the set {false} new set
     */
    public boolean isNewSet(){
        return (this.ownerOfTheSet!=-1)?true:false;
    }

    public void setCurrentPoints(String pointPlayer1, String pointPlayer2) {
        this.pointList.get(this.currentPoint).setCurrentPoints(pointPlayer1,pointPlayer2);
    }
}
