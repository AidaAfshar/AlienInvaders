package model.dataManagement;

import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.GroupType;

public class GameState {

    String playerName ;
    GroupType groupType;
    int deadsCount ;

    public GameState(String playerName , GroupType groupType, int deadsCount){
        this.groupType = groupType ;
        this.deadsCount = deadsCount ;
        this.playerName = playerName ;
    }


    public static Group getGroupState(GroupType groupType, int deadsCount){
        Group group = Group.diagnoseType(groupType) ;
        for(int i=group.getAliens().size()-1 ; i>group.getAliens().size()-1-deadsCount ; i--){
            group.getAliens().get(i).setAlive(false);
        }

        return group ;
    }

    public static void saveGroupState(Group group){

    }

    @Override
    public String toString() {
            return "{" +
                    "name=" + playerName +
                    ", groupType=" + groupType  +
                    ", deadsCount =" + deadsCount +
                    '}';

    }

    //getters


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName=playerName;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType=groupType;
    }

    public int getDeadsCount() {
        return deadsCount;
    }

    public void setDeadsCount(int deadsCount) {
        this.deadsCount=deadsCount;
    }
}
