import java.util.List;
import java.util.Map;
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
        this.initSet();
    }

    public List<TennisPoint> getPointList() {
        return pointList;
    }

    public void setPointList(List<TennisPoint> pointList) {
        this.pointList = pointList;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public int getOwnerOfTheSet() {
        return ownerOfTheSet;
    }

    public void setOwnerOfTheSet(int ownerOfTheSet) {
        this.ownerOfTheSet = ownerOfTheSet;
    }

    public void updateSet(int playerId){
        this.pointList.get(currentPoint).updatePoints(playerId);
        if(this.pointList.get(currentPoint).isNewGame() == true){
            this.currentPoint++;
            this.initSet();
        }
        if(this.getPoint(playerId)>=7 && (this.getPoint((playerId+1%2))+2)<this.getPoint(playerId)){
            this.ownerOfTheSet = playerId;
        }
    }

    public int getPoint(int playerId){
        int count=0;
        for(TennisPoint aPoint:this.pointList){
            if(aPoint.getOwnerOfThePoint()==playerId){
                count++;
            }
        }
        return count;
    }

    public String getCurrentPoint (int playerId){
        return this.pointList.get(this.currentPoint).getPointList().get(playerId);
    }

    private void initSet(){
        this.pointList.set(this.currentPoint,new TennisPoint());
    }

    public boolean isNewSet(){
        return (this.ownerOfTheSet!=-1)?true:false;
    }
}
