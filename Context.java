/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategypatern;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import strategypatern.graphic.GraphicFrame;

/**
 *
 * @author anthony
 */
public class Context implements Runnable {
    
    GraphicFrame mf;
    boolean start;
    int currentPos;
    int nextPos;
    int[] nextPosList;
    Map<String, IStrategy> strategies;
    IStrategy strategy;
    boolean isGoingUp;
    int maxVal;
    Thread os;
    
    Context(GraphicFrame mf,boolean start,int minVal, int maxVal, int currentPos, int[] nextPosList,Map<String, IStrategy> stratDict , boolean isGoingUp){
        this.start = start;
        this.currentPos = currentPos;
        this.nextPosList = nextPosList;
        this.strategies = stratDict;
        this.isGoingUp = isGoingUp;
        this.nextPos = currentPos;
        this.maxVal = maxVal;
        this.mf = mf;
        this.mf.setVisible(true);
        this.mf.initElement(this,minVal,maxVal);
    }
    
    @Override
    public void run(){
        while(start){
            try {
                this.updatePositions();
            } catch (InterruptedException ex) {
                Logger.getLogger(Context.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void start() throws InterruptedException{
        this.start = true;
        os = new Thread(this);
        os.start();
    }
    
    public void stop(){
        this.start = false;
    }
    
    private boolean isInList(int n){
        for(int pos=0; pos<this.nextPosList.length; pos++){
            if(this.nextPosList[pos] == n){
                return true;
            }
        }
        return false;
    }
    
    private void updatePositions() throws InterruptedException{
        this.currentPos = this.nextPos;
        this.nextPos = strategy.getNextPos(this.currentPos, this.nextPosList, this.isGoingUp);
        this.mf.updateElement(this);
        this.nextPosList = this.updateList();
        this.isGoingUp = this.currentPos < this.nextPos;
        TimeUnit.MILLISECONDS.sleep(600);        
    }
    
    public GraphicFrame getMF(){
        return this.mf;
    }
    public int getCurrentPos(){
        return this.currentPos;
    }
    public int getNextPos(){
        return this.nextPos;
    }
    public int[] getNextPosList(){
        return this.nextPosList;
    }
    
    public void setStrategy(String strat){
        this.strategy = this.strategies.get(strat);
    }
    
    public void generateList(){
        int l = this.nextPosList.length;
        this.nextPosList = new int[l];
        for(int npl=0;npl<l-1;npl++){
            this.nextPosList[l-1] = this.feedList();
        }
        this.mf.updateElement(this);
    }
    
    private int feedList(){
        Random rand = new Random();
        int r = rand.nextInt(20);
        if((!this.isInList(r)) && (r!=0)){
            return r;
        }
        else{
            return feedList();
        }
    }
    
    private int[] updateList(){
        int[] tmp = new int [this.nextPosList.length];
        int n=0;
        int pos;
        
        for(pos=0; pos<this.nextPosList.length; pos++){
            if((this.nextPosList[pos] == this.nextPos)){
                n=1;
            }
            else{
                tmp[pos-n] = this.nextPosList[pos];
            }
        }
        tmp[pos-n] = feedList();
        return tmp;
    }
}
