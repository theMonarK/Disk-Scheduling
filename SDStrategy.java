/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategypatern;


/**
 *
 * @author anthony
 */
public class SDStrategy implements IStrategy {
    
    SSTFStrategy sstf = new SSTFStrategy();

    @Override
    public int getNextPos(int currentPos,int[] nextPosList,boolean isGoingUp) {
        sstf.getNextPos(currentPos, nextPosList, isGoingUp);
        if(isGoingUp){
            if(currentPos < sstf.npl[sstf.closestPosition]){
                return sstf.npl[sstf.closestPosition];
            }
            else{
                if(sstf.closestPosition+1<sstf.npl.length){
                    return sstf.npl[sstf.closestPosition+1];
                }
                else{
                    return sstf.npl[sstf.closestPosition];
                }
            }
        }
        else{
            if(currentPos > sstf.npl[sstf.closestPosition]){
                return sstf.npl[sstf.closestPosition];
            }
            else{
                if(sstf.closestPosition-1>0){
                    return sstf.npl[sstf.closestPosition-1];
                }
                else{
                    return sstf.npl[0];
                }
            }
        }               
    }  
}
