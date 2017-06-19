package com.google.engedu.wordladder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ajacob2609 on 6/19/2017.
 */

public class Solution {
    public List<List<String>> findLadders(String start, String end, HashSet<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        PathDictionary obj=new PathDictionary();
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(start, 1, null));

        dict.add(end);

        int minStep = 0;

        HashSet<String> visited = new HashSet<String>();
        HashSet<String> unvisited = new HashSet<String>();
        unvisited.addAll(dict);

        int preNumSteps = 0;

        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
            int currNumSteps = top.numSteps;

            if(word.equals(end)){
                if(minStep == 0){
                    minStep = top.numSteps;
                }

                if(top.numSteps == minStep && minStep !=0){
                    //nothing
                    ArrayList<String> t = new ArrayList<String>();
                    t.add(top.word);
                    while(top.pre !=null){
                        t.add(0, top.pre.word);
                        top = top.pre;
                    }
                    result.add(t);
                    continue;
                }

            }

            if(preNumSteps < currNumSteps){
                unvisited.removeAll(visited);
            }

            preNumSteps = currNumSteps;

            for(String newWord:obj.neighbours(word)){
                if(unvisited.contains(newWord)){
                    queue.add(new WordNode(newWord, top.numSteps+1, top));
                    visited.add(newWord);
                }


            }



        }
        // Log.i("result",result.get(0).toString());

        return result;

    }
}
