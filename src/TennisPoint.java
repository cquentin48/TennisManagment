import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TennisPoint {
    private List<String> pointList;
    private int ownerOfThePoint;
    public final static String[] POINT_LIST = {"0","15","30","40","A"};

    public TennisPoint() {
        pointList = new ArrayList<>();
        ownerOfThePoint = -1;
        initPoints();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TennisPoint that = (TennisPoint) o;
        return ownerOfThePoint == that.ownerOfThePoint &&
                Objects.equals(pointList, that.pointList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointList, ownerOfThePoint);
    }

    public List<String> getPointList() {
        return pointList;
    }

    public void setOwnerOfThePoint(int ownerOfThePoint) {
        ownerOfThePoint = ownerOfThePoint;
    }

    public int getOwnerOfThePoint() {
        return ownerOfThePoint;
    }

    @Override
    public String toString() {
        return "TennisPoint{" +
                "pointList=" + pointList +
                ", ownerOfThePoint=" + ownerOfThePoint +
                '}';
    }

    /**
     * Init the points for a new game
     */
    public void initPoints(){
        pointList.add(0,POINT_LIST[0]);
        pointList.add(1,POINT_LIST[0]);
    }

    private void updatePointList(int i, String s) {
        pointList.set(i, s);
    }

    private void initPointList(int i){
        pointList.add(1,POINT_LIST[0]);
    }

    /**
     * Update the point when a point is won by a player
     * @param playerId key of the player
     */
    public void updatePoints(int playerId){
        if(pointList.get(playerId) == "40"){
            if(pointList.get((playerId+1)%2)=="A"){
                updatePointList((playerId+1)%2, "40");
            } else if(pointList.get((playerId+1)%2) == "40"){
                updatePointList(playerId, "A");
            } else{
                updatePointList(0, "0");
                updatePointList(1, "0");
                ownerOfThePoint = playerId;
            }
        }else if(pointList.get(playerId) == "A"){
            updatePointList(0, "0");
            updatePointList(1, "0");
                ownerOfThePoint = playerId;
        }else{
            String currentPointIndex = POINT_LIST[(Arrays.asList(POINT_LIST).indexOf(pointList.get(playerId)))+1];
            pointList.set(playerId,currentPointIndex);
        }
    }

    /**
     * Update a tie-break point
     * @param playerId
     */
    public void updatePointTieBreak(int playerId){
        pointList.set(playerId, String.valueOf(Integer.parseInt(pointList.get(playerId))+1));
        checkTieBreakStatus();
    }

    /**
     * Check if the tie break is done
     * @return true tie break done | false tie break still on going
     */
    public boolean checkTieBreakStatus() {
        if (Integer.parseInt(pointList.get(0)) > Integer.parseInt(pointList.get(1)) + 2) {
            ownerOfThePoint = 0;
            return true;
        } else if (Integer.parseInt(pointList.get(1)) > Integer.parseInt(pointList.get(0)) + 2) {
            ownerOfThePoint = 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get point from the player
     * @param playerId id of the player
     * @return point for the player chosen
     */
    public int getPointFromPlayerId(int playerId){
        return Integer.parseInt(pointList.get(playerId));
    }

    /**
     * Check if this game is finished or not
     * @return {true} game finished {false} still playing this point
     */
    public boolean isNewGame(){
        return(ownerOfThePoint!=-1)?true:false;
    }

    public void setCurrentPoints(String pointPlayer1, String pointPlayer2) {
        pointList.set(0,pointPlayer1);
        pointList.set(1,pointPlayer2);
    }
}
