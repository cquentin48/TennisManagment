import java.util.List;
import java.util.Objects;

public class TennisGame {
    private int gameOwner;
    private List<TennisSet> tennisSetList;
    private int currentSet;

    public int getGameOwner() {
        return gameOwner;
    }

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

    public void updateGame(int playerId, boolean isTieBreak, int maxSets){
        if(isTieBreak == true && this.getCurrentSet()==(maxSets-1)){
            updateTieBreakGame(playerId);
        }else {
            updateNormalSet(playerId, isTieBreak, maxSets);
        }
    }

    private void updateNormalSet(int playerId, boolean isTieBreak, int maxSets) {
        this.tennisSetList.get(this.currentSet).updateSet(playerId,isTieBreak,maxSets);
        this.checkStatusOfCurrentSet(playerId);
    }

    private void checkStatusOfCurrentSet(int playerId) {
        if(this.tennisSetList.get(this.currentSet).isNewSet() == true){
            this.currentSet++;
            this.initGame();
        }
        if(this.countSets(playerId) <=(this.countSets((playerId+1)%2) +2)){
            this.gameOwner = playerId;
        }
    }

    private void updateTieBreakGame(int playerId){
        this.tennisSetList.get(this.currentSet).updateTieBreakSet(playerId);
        this.checkStatusOfCurrentSet(playerId);
    }

    private int countSets(int playerId){
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

    public int getCurrentSet() {
        return currentSet;
    }

    public TennisGame() {
        this.currentSet = 0;
        this.initGame();
    }

    private void initGame(){
        this.tennisSetList.set(this.currentSet,new TennisSet());
    }
}
