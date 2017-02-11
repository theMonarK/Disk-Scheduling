/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategypatern;

import static java.lang.Math.abs;
import java.util.Arrays;

/**
 *
 * @author anthony
 */
public class SSTFStrategy implements IStrategy {
    
    int closestPosition;
    int[] npl;
    
    @Override
    public int getNextPos(int currentPos,int[] nextPosList,boolean isGoingUp) {
        int prevGap=999999;
        npl =  nextPosList.clone();
        
        Arrays.sort(npl);
        for(int pos = 0; pos<npl.length; pos++){
           int gap = abs(npl[pos]-currentPos);
           if(gap < prevGap){
               prevGap = gap;
           }
           else{
               this.closestPosition = pos-1;
               return npl[pos-1];
           }
        }
        this.closestPosition = nextPosList.length-1;
        return npl[nextPosList.length-1];
    }
}
