package controller.controlSection.levelize;

import controller.controlSection.administrator.Administrator;
import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.GroupType;
import controller.enemy.alienGroups.RectangularGroup;
import controller.enemy.alienGroups.circularGroup.CircularGroup;
import controller.enemy.alienGroups.finalWave.SimpleFinalWave;
import controller.enemy.alienGroups.rotatingGroup.RotatingGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class LevelManager {

    Administrator admin ;

    Group currentGroup ;
    ArrayList<Group> groups ;

    int currentLevelCount = 0 ;
    int maximumLevelCount = 4 ;


    public LevelManager(Administrator admin){
        this.admin = admin ;
        groups = new ArrayList<>() ;
        initialize() ;
    }

    void initialize(){
        prepareNormalGame();
        startGame();

    }

    void prepareNormalGame(){
        addGroupsForNormalGame() ;
    }

    void prepareRandomGroupGame(){ addGroupsForRandomGame(); }

    void startGame(){
        currentGroup = groups.get(0) ;
        currentGroup.initialize();
    }


    public void nextGroup(){
        for (int i = 0;i < groups.size ();i++) {
            Group group = groups.get (i);
            if (!group.isDead ()) {
                this.currentGroup = group;
                this.currentGroup.initialize ();
                break;
            } else if (group.getType () == GroupType.FINALWAVE && group.isDead ()) {
                currentLevelCount++;
                if (currentLevelCount == maximumLevelCount) {
                    admin.finishGame ();
                    break;
                } else {
                    nextLevel ();
                    break;
                }
            }
        }
    }

    public void nextLevel(){
        addGroupsForNormalGame();
        startGame ();
    }

    void addGroupsForNormalGame(){
        groups = new ArrayList<> () ;
        groups.add (new RectangularGroup ());
        groups.add (new CircularGroup ());
        groups.add (new RotatingGroup ());
        groups.add (new SimpleFinalWave ());

    }

    void addGroupsForRandomGame()  {

        try {
            Class aClass = RectangularGroup.class;
            Constructor constructor = aClass.getConstructor ();
            Group group = (Group) constructor.newInstance ();

            System.out.println (group.getType ());
        }catch (Exception e){
            e.printStackTrace ();
        }

    }



    //getters & setters:


    public Group getCurrentGroup() {
        return currentGroup;
    }

}
