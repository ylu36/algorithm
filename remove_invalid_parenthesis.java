public static void removeInvalidParentheses1(String s) {
        Stack<Integer> left, right;
        left = new Stack<>();
        right = new Stack<>();
        for(int i = 0; i < s.length(); i ++) {
            if(s.charAt(i) == '(')
                left.push(i);
            else {
                if(left.isEmpty())
                    right.push(i);
                else
                    left.pop();
            }
        }
        while(!left.isEmpty() || !right.isEmpty()) {
            if(!left.isEmpty()) System.out.println(left.pop());
            if(!right.isEmpty()) System.out.println(right.pop());
        }
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
