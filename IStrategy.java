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
public interface IStrategy {
    public int getNextPos(int currentPos,int[] nextPosList,boolean isGoingUp);
}
