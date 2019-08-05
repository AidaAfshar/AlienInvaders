package controller.controlSection.levelize;

import controller.controlSection.administrator.Administrator;
import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.GroupType;
import controller.enemy.alienGroups.RectangularGroup;
import controller.enemy.alienGroups.TrainGroup;
import controller.enemy.alienGroups.circularGroup.CircularGroup;
import controller.enemy.alienGroups.finalWave.FinalWave;
import controller.enemy.alienGroups.finalWave.SimpleFinalWave;
import controller.enemy.alienGroups.rotatingGroup.RotatingGroup;
import controller.enemy.aliens.Gravitus;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;


public class LevelManager {

    Administrator admin ;

    Group currentGroup ;
    ArrayList<Group> groups ;

    int currentLevelCount = 0 ;
    int maximumLevelCount = 4 ;

    ArrayList<Class> groupsClasses = new ArrayList<>() ;
    ArrayList<Class> finalWavesClasses = new ArrayList<>() ;


    public LevelManager(Administrator admin){
        this.admin = admin ;
        groups = new ArrayList<>() ;
        initialize() ;
    }

    void initialize() {
        addClasses();
        addGroupsForRandomGame();
        startGame();
    }

    void addClasses(){
        groupsClasses.add(RectangularGroup.class) ;
        groupsClasses.add(CircularGroup.class) ;
        groupsClasses.add(RotatingGroup.class) ;
        groupsClasses.add(TrainGroup.class) ;
        finalWavesClasses.add(SimpleFinalWave.class) ;
    }

    void addGroupsForRandomGame() {
        int [] randomNumbers = produce3RandomNumbers() ;
        Class[] randomClasses = get3RandomClasses(randomNumbers) ;

        groups = new ArrayList<>() ;
        for(int i=0 ; i<3 ; i++){
            groups.add(loadGroup(randomClasses[i])) ;
        }
        groups.add(loadGroup(getRandomFinalWave())) ;
    }


    void startGame(){
        currentGroup = groups.get(0) ;
        currentGroup.initialize();
    }

    public void AddGroupClass(Class aClass){
        Class superClass = aClass.getSuperclass() ;

        if(superClass.equals(Group.class)){
            groupsClasses.add(aClass) ;
        }
        if(superClass.equals(FinalWave.class)){
            finalWavesClasses.add(aClass) ;
        }
    }

    public static Group loadGroup(Class aClass){

        try {
            Constructor constructor = aClass.getConstructor ();
            Group group = (Group) constructor.newInstance ();

            return group ;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null ;
    }


    public void nextGroup() {
        for(int i=0;i<groups.size();i++){
            Group group=groups.get(i);
            System.out.println(group.getType());
            if(!group.isDead()){
                this.currentGroup = group;
                this.currentGroup.initialize();
                break;
            }else if(group.getType()==GroupType.FINALWAVE && group.isDead()){
                currentLevelCount++;
                Gravitus.setResistance(100*currentLevelCount);
            }
            if(currentLevelCount==maximumLevelCount){
                admin.finishGame();
                break;
            }else{
                nextLevel();
                break;
            }
        }
    }


    public void nextLevel() {
        admin.upgradeScore() ;
        addGroupsForRandomGame();
        startGame ();
    }

//    void addGroupsForNormalGame(){
//        groups = new ArrayList<> () ;
//        groups.add (new RectangularGroup ());
//        groups.add (new CircularGroup ());
//        groups.add (new RotatingGroup ());
//        groups.add (new SimpleFinalWave ());
//    }


    Class[] get3RandomClasses(int[] randomNumbers){
        Class[] randomClasses = new Class[3] ;

        for(int i=0 ; i<3 ; i++){
            randomClasses[i] = groupsClasses.get(randomNumbers[i]) ;
         }

        return randomClasses ;
    }

    Class getRandomFinalWave(){
        Random random = new Random();
        int n = random.nextInt(finalWavesClasses.size()) ;
        Class c =finalWavesClasses.get(n);
        return c ;
    }

    int[] produce3RandomNumbers(){
        int[] numbers = new int[3] ;
        Random random = new Random() ;
        numbers[0] = random.nextInt(groupsClasses.size()) ;

        int i=0 ;

        while (true){
        if(i==0){
            numbers[0] = random.nextInt(groupsClasses.size()) ;
            i=1 ;
        }
        if(i==1){
            int num = random.nextInt(groupsClasses.size()) ;
            while (true){
                if(num == numbers[0]){
                    num = random.nextInt(groupsClasses.size()) ;
                }else{
                    numbers[1]=num ;
                    i=2 ;
                    break;
                }
            }
        }
            if(i==2){
                int num=random.nextInt(groupsClasses.size());
                while(true){
                    if(num==numbers[0] || num==numbers[1]){
                        num=random.nextInt(groupsClasses.size());
                    }else{
                        numbers[2] = num ;
                        i=3 ;
                        break;
                    }
                }
            }

        if(i==3) return numbers;

        }
    }


    //getters & setters:


    public Group getCurrentGroup() {
        return currentGroup;
    }

}
