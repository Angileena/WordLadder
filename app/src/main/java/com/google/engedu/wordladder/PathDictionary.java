/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.wordladder;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class PathDictionary {
    private static final int MAX_WORD_LENGTH = 4;
    private static HashSet<String> words = new HashSet<>();
    private static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    public PathDictionary() {
    }

    public PathDictionary(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        Log.i("Word ladder", "Loading dict");
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        Log.i("Word ladder", "Loading dict");
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() == MAX_WORD_LENGTH) {
                words.add(word);
            }

        }

        for (String word : words) {
            ArrayList<String> neigh = new ArrayList<>();
            for (int i = 0; i < word.length(); ++i)
                for (char j = 'a'; j <= 'z'; ++j) {
                    StringBuilder temp = new StringBuilder(word);

                    temp.setCharAt(i, j);
                    if (!word.equals(temp.toString()))
                        if (isWord(temp.toString()))
                            if (!neigh.contains(temp.toString()))
                                neigh.add(temp.toString());
                }
            graph.put(word, neigh);

        }
        // Log.i("neigh",graph.toString());
        // Log.i("BFS",findPath("cold","card").toString());


    }

    public boolean isWord(String word) {
        return words.contains(word.toLowerCase());
    }

    public ArrayList<String> neighbours(String word) {
        return graph.get(word);
    }

    public String[] findPath(String start, String end) {
        int i=0;
        Solution obj=new Solution();
        List<List<String>> path=obj.findLadders(start,end,words);
        Random rn = new Random();
        int n = path.size();
        int j=rn.nextInt() % n;
        if(j<0 || j>path.size()) j=i;
        String[] strarray = path.get(j).toArray(new String[0]);
        Log.i("return value",path.toString()+i+" "+j);

        return strarray;
    }
}
