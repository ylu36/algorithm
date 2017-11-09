public static void removeInvalidParentheses1(String s) {
        int[] index = new int[s.length()];
        Stack<Integer> stack = new Stack<>();
        List<Character> res = new ArrayList<>();
        for(int i = 0; i < s.length(); i ++) {
            if(s.charAt(i) == '(')
                stack.push(i);
            else if(s.charAt(i) == ')')
                if(!stack.isEmpty()) {
                    index[stack.pop()] = 1;
                    index[i] = 1;
                }
            else
                index[i] = 1;
        }
        for(int i = 0; i < s.length(); i ++) {
            if(index[i] == 1)
                res.add(s.charAt(i));
        }
        System.out.println(res);
    }

    public static String removeInvalidParentheses(String s) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()) {
            if(c == '(') i ++;
            else if(c == ')') i --;
            if(i >= 0) sb.append(c);
            else i = 0;
        }
        s = sb.reverse().toString();
        sb = new StringBuilder();
        i = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') i --;
            else if(c == ')') i ++;
            if(i >= 0) sb.append(c);
            else i = 0;
        }
        return sb.reverse().toString();
    }
