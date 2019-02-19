import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TennisPoint {
    private List<String> pointList;
    private int ownerOfThePoint;
    public final static String[] POINT_LIST = {"0","15","30","40","A"};

    public TennisPoint() {
        this.pointList = new ArrayList<>();
        this.ownerOfThePoint = -1;
        this.initPoints();
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
        this.ownerOfThePoint = ownerOfThePoint;
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
        this.pointList.add(0,POINT_LIST[0]);
        this.pointList.add(1,POINT_LIST[0]);
    }

    private void updatePointList(int i, String s) {
        pointList.set(i, s);
    }

    private void initPointList(int i){
        this.pointList.add(1,POINT_LIST[0]);
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

        if(pointList.get(0) == TennisPoint.POINT_LIST[3] || pointList.get(1) == TennisPoint.POINT_LIST[3]){
            updatePointList(playerId, Integer.toString(Integer.parseInt(this.pointList.get(playerId))));
        }else{
            updatePoints(playerId);
        }
        if(Integer.parseInt(this.pointList.get(playerId)) > Integer.parseInt(this.pointList.get((playerId+1)%2)) +2){
            this.ownerOfThePoint = playerId;
        }
    }

    public int getPointFromPlayerId(int playerId){
        return Integer.parseInt(pointList.get(playerId));
    }

    /**
     * Check if this game is finished or not
     * @return {true} game finished {false} still playing this point
     */
    public boolean isNewGame(){
        return(this.ownerOfThePoint!=-1)?true:false;
    }

    public void setCurrentPoints(String pointPlayer1, String pointPlayer2) {
        this.pointList.set(0,pointPlayer1);
        this.pointList.set(1,pointPlayer2);
    }
}
