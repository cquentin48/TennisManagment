import java.util.List;
import java.util.Objects;

public class TennisGame {
    private int gameOwner;
    private List<TennisSet> tennisSetList;
    private int currentSet;
    private final static int BESTOFFIVE = 5;
    private final static int BESTOFTHREE = 3;

    @Override
    public String toString() {
        return "TennisGame{" +
                "gameOwner=" + gameOwner +
                ", tennisSetList=" + tennisSetList +
                ", currentSet=" + currentSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TennisGame that = (TennisGame) o;
        return gameOwner == that.gameOwner &&
                currentSet == that.currentSet &&
                Objects.equals(tennisSetList, that.tennisSetList);
    }

    public void updateGame(int playerId){
        this.tennisSetList.get(this.currentSet).updateSet(playerId);
        if(this.tennisSetList.get(this.currentSet).isNewSet() == true){
            this.currentSet++;
            this.initGame();
        }
        if(this.countSets(playerId) <=(this.countSets((playerId+1)%2) +2)){
            this.gameOwner = playerId;
        }
    }

    public int countSets(int playerId){
        int count = 0;
        for(int i = 0;i<this.tennisSetList.size();i++){
            count = (this.tennisSetList.get(i).getOwnerOfTheSet()==playerId)?count+1:count;
        }
        return count;
    }

    public int getSetsCount(int playerId, int setId){
        return this.tennisSetList.get(setId).getPoint(playerId);
    }

    public String getPoint(int playerId){
        return this.tennisSetList.get(this.currentSet).getCurrentPoint(playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameOwner, tennisSetList, currentSet);
    }

    public TennisGame() {
        this.currentSet = 0;
        this.initGame();
    }

    public void initGame(){
        this.tennisSetList.set(this.currentSet,new TennisSet());
    }
}
