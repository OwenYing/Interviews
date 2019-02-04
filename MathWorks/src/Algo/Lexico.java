package Algo;

import java.util.*;

public class Lexico {
    public List<String> solution(String input) {
        // Sort O(nlogn)
        if(input == null || input.length() == 0) return new ArrayList<>();

        //1. For smallest, ascending, pull the first (aeiou) to the start, pull the last fuyin to the end
        //2. For biggest, descending, pull the last (aeiou) to the first, pull the last
        char[] chs = input.toCharArray();
        Arrays.sort(chs);
        int start = 0, end = chs.length - 1;
        for(start = 0; start < chs.length; start ++)
            if(isAEIOU(chs[start]))
                break;
        for(end = chs.length - 1; end >= 0; end --)
            if(isFuyin(chs[end]))
                break;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < chs.length; i++)
            if(i != start && i != end)
                sb.append(chs[i]);

        sb.insert(0, chs[start]);
        sb.append(chs[end]);

        return null;
    }

}
