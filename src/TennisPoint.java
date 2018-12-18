import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TennisPoint {
    private List<String> pointList;
    private int ownerOfThePoint;
    private final static String[] POINT_LIST = {"0","15","30","40","A"};

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

    public void setPointList(List<String> pointList) {
        this.pointList = pointList;
    }

    public int getOwnerOfThePoint() {
        return ownerOfThePoint;
    }

    public void setOwnerOfThePoint(int ownerOfThePoint) {
        this.ownerOfThePoint = ownerOfThePoint;
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
        pointList.set(0,"0");
        pointList.set(1,"0");
    }

    /**
     * Update the point when a point is won by a player
     * @param playerId key of the player
     */
    public void updatePoints(int playerId){
        if(pointList.get(playerId) == "40"){
            if(pointList.get((playerId+1)%2)=="A"){
                pointList.set(1,"40");
            } else if(pointList.get((playerId+1)%2) == "40"){
                pointList.set(0,"A");
            } else{
                ownerOfThePoint = playerId;
            }
        }else if(pointList.get(playerId) == "A"){
                ownerOfThePoint = playerId;
        }else{
            pointList.set(playerId,POINT_LIST[(Arrays.asList(POINT_LIST).indexOf(pointList.get(playerId)))+1]);
        }
    }

    /**
     * Check if this game is finished or not
     * @return {true} game finished {false} still playing this point
     */
    public boolean isNewGame(){
        return(this.ownerOfThePoint!=-1)?true:false;
    }
}
