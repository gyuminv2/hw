import java.util.*;

class Solution {
    
    private class Word {
        String  word;
        int     step;
        
        Word(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
    
    public boolean compareWord(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        
        return diff == 1;
    }
    
    public int solution(String begin, String target, String[] words) {
        
        if (!Arrays.asList(words).contains(target)) return 0;
        
        boolean[] v = new boolean[words.length];
        Deque<Word> q = new ArrayDeque<>();
        q.offer(new Word(begin, 0));
        
        while (!q.isEmpty()) {
            Word cur = q.poll();
            String curWord = cur.word;
            int step = cur.step;
            
            if (curWord.equals(target)) {return step;}
            
            for (int i = 0; i < words.length; i++) {
                if (!v[i] && compareWord(curWord, words[i])) {
                    v[i] = true;
                    q.offer(new Word(words[i], ++step));
                }
            }
        }
        return 0;
    }
}