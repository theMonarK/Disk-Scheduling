/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategypatern;

import java.util.HashMap;
import java.util.Map;
import strategypatern.graphic.GraphicFrame;

/**
 *
 * @author anthony
 */
public class OS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        int[] ints = {16,10,12,3,14,6,15};
        GraphicFrame mf = new GraphicFrame();
        Map<String, IStrategy> stratDict = new HashMap<String, IStrategy>();
        stratDict.put("fcfs",new FCFSStrategy());
        stratDict.put("sstf",new SSTFStrategy());
        stratDict.put("sd",new SDStrategy());
        Context context = new Context(mf,false,1,20,5,ints,stratDict,false);
        
    }
    
}
