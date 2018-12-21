import java.util.ArrayList;
import java.util.List;

public class TennisMatch {
    private List<TennisPlayer> playerList;
    private List<TennisGame> gameList;
    private int currentGame;
    private MatchType matchType;
    private boolean tieBreak;
    private final static int BESTOFFIVE = 5;
    private final static int BESTOFTHREE = 3;


    public TennisMatch(TennisPlayer player1, TennisPlayer player2, MatchType matchType, boolean tieBreak){
        this.playerList = new ArrayList<>();
        this.playerList.set(0,player1);
        this.playerList.set(0,player2);
        this.matchType = matchType;
        this.tieBreak = tieBreak;
        this.currentGame = 0;
    }

    public List<TennisPlayer> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<TennisPlayer> playerList) {
        this.playerList = playerList;
    }

    public List<TennisGame> getGameList() {
        return gameList;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public boolean isTieBreak() {
        return tieBreak;
    }

    public void setTieBreak(boolean tieBreak) {
        this.tieBreak = tieBreak;
    }

    private void updateWithPointWonBy(TennisPlayer player){
        this.gameList.get(this.currentGame).updateGame(this.getPlayerId(player),tieBreak,this.matchType.getMatchType());
    }

    private String pointsForPlayer(TennisPlayer player){
        return this.gameList.get(this.currentGame).getPoint(this.getPlayerId(player));
    }

    private int currentSetNumber(){
        return this.gameList.get(this.currentGame).getCurrentSet();
    }

    private int gamesInCurrentSetForPlayer(int setId, TennisPlayer player){
        return this.gameList.get(this.currentGame).getSetsCount(this.getPlayerId(player),setId);
    }

    private boolean isFinished(){
        return (this.currentGame>=this.matchType.getMatchType())?countOwnedTennisGames(this.matchType.getRequirements()):false;
    }

    private boolean countOwnedTennisGames(int requirements) {
        return(this.countOwnedGames(this.getPlayerId(this.playerList.get(1))) + requirements <= this.countOwnedGames(this.getPlayerId(this.playerList.get(0)))+ requirements ||
                this.countOwnedGames(this.getPlayerId(this.playerList.get(0)))+ requirements <= this.countOwnedGames(this.getPlayerId(this.playerList.get(1))))?true:false;
    }

    private int countOwnedGames(int playerId){
        int count = 0;
        for(int i = 0;i<this.gameList.size();i++){
            count = (this.gameList.get(i).getGameOwner()==playerId)?count+1:count;
        }
        return count;
    }

    private int getPlayerId(TennisPlayer player){
        return(player.getName()=="Player1")?0:(player.getName()=="Player2")?1:-1;
    }
}
