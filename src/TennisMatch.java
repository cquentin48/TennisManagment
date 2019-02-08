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
    private String player1Name;
    private String player2Name;


    public TennisMatch(TennisPlayer player1, TennisPlayer player2, MatchType matchType, boolean tieBreak){
        this.player1Name = player1.getName();
        this.player2Name = player2.getName();
        this.gameList = new ArrayList<>();
        this.gameList.add(new TennisGame());
        this.playerList = new ArrayList<>();
        this.playerList.add(player1);
        this.playerList.add(player2);
        this.matchType = matchType;
        this.tieBreak = tieBreak;
        this.currentGame = 0;
    }

    public TennisPlayer getPlayerObjectById(int id){
        return playerList.get(id);
    }

    public boolean isTieBreak() {
        return tieBreak;
    }

    /**
     * Get the owner of the currentPoint
     * @return owner of the currentPoint
     */
    public int getOwnerOfThePoint(){
        return gameList.get(currentGame).getOwnerOfThePoint();
    }


    /**
     * Get the owner of a chosen point
     * @return owner of a chosen point
     */
    public int getOwnerOfThePoint(int pointId){
        return gameList.get(currentGame).getOwnerOfThePoint(pointId);
    }

    public int getCurrentPoint(){
        return gameList.get(currentGame).getCurrentPoint();
    }

    public int getCurrentSet(){
        return gameList.get(currentGame).getCurrentSet();
    }

    public int getCurrentSet(int chosenGame){
        return gameList.get(chosenGame).getCurrentSet();
    }

    /**
     * Update players point
     * @param player player which has won the point
     */
    public void updateWithPointWonBy(TennisPlayer player){
        this.gameList.get(this.currentGame).updateGame(this.getPlayerId(player),tieBreak,this.matchType.getMatchType());
    }

    public boolean isGameWon(){
        return gameList.get(currentGame).isGameWon();
    }

    public boolean isGameWon(int id){
        return gameList.get(id).isGameWon();
    }

    public boolean isSetWon(int id){
        return gameList.get(id).isSetWon(id);
    }

    /**
     * Return the points of player in the current point
     * @param player chosen player
     * @return String format point
     */
    public String pointsForPlayer(TennisPlayer player){
        return this.gameList.get(this.currentGame).getPoint(this.getPlayerId(player));
    }

    /**
     * Return the number of sets
     * @return number of sets
     */
    public int currentSetNumber(){
        return this.gameList.get(this.currentGame).getCurrentSet();
    }

    /**
     * return the won games by the player
     * @param setId the id of the chosen set
     * @param player chosen player
     * @return won set games
     */
    public int gamesInCurrentSetForPlayer(int setId, TennisPlayer player){
        return this.gameList.get(this.currentGame).getPointCountInASet(this.getPlayerId(player),setId);
    }

    /**
     * Check if the match is finished or not
     * @return {True} finished match {false} unfinished match
     */
    public boolean isFinished(){
        return (this.currentGame>=this.matchType.getMatchType())?countOwnedTennisGames(this.matchType.getRequirements()):false;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * Check if the player has the requirement to win
     * @param requirements required match in order to win
     * @return {True} player has won the match {False} player has not won the match
     */
    public boolean countOwnedTennisGames(int requirements) {
        return(this.countOwnedGames(this.getPlayerId(this.playerList.get(1))) + requirements <= this.countOwnedGames(this.getPlayerId(this.playerList.get(0)))+ requirements ||
                this.countOwnedGames(this.getPlayerId(this.playerList.get(0)))+ requirements <= this.countOwnedGames(this.getPlayerId(this.playerList.get(1))))?true:false;
    }

    /**
     * Count the owned games by the player
     * @param playerId id of the player (1/2)
     * @return counter of owned games
     */
    public int countOwnedGames(int playerId){
        int count = 0;
        for(int i = 0;i<this.gameList.size();i++){
            count = (this.gameList.get(i).getGameOwner()==playerId)?count+1:count;
        }
        return count;
    }

    /**
     * Get the id of the player
     * @param player player instance
     * @return player1 => 0 | player2 => 1
     */
    public int getPlayerId(TennisPlayer player){
        return(player.getName()==player1Name)?0:(player.getName()==player2Name)?1:-1;
    }

    public void setCurrentPoints(String pointPlayer1, String pointPlayer2) {
        this.gameList.get(this.currentGame).setCurrentPoints(pointPlayer1, pointPlayer2);
    }
}
