// 不改变task顺序
public static String taskSchedule(char[] tasks, int cd) {
    StringBuilder sb = new StringBuilder();
    int cp = cd; int i = 0;
    int time = 0;
    Map<Character, Integer> map = new HashMap<>();
    Queue<Character> queue = new LinkedList<>();
    for(;i < tasks.length;i++) {
        if (!map.containsKey(tasks[i])) {
            sb.append(tasks[i]);
            map.put(tasks[i], sb.length()-1);
            time ++;
        }
        else {
            if(time-map.get(tasks[i]) <= cd) {
                sb.append('x');
                i--;
                time ++;
            }
            else {
                sb.append(tasks[i]);
                time ++;
                map.put(tasks[i], sb.length()-1);
            }
        }
    }
    return sb.toString();
}

//改变task顺序
public static String taskSchedule(char[] tasks, int cd) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
        }
        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>( //frequency sort
                (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

        q.addAll(map.entrySet());

        int count = 0;
        while (!q.isEmpty()) {
            int k = cd + 1;
            List<Map.Entry> tempList = new ArrayList<>();
            while (k > 0 && !q.isEmpty()) {
                Map.Entry<Character, Integer> top = q.poll(); // most frequency task
                top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
                tempList.add(top); // collect task to add back to queue
                k--;
                count++; //successfully executed task
                sb.append(top.getKey());
            }

            for (Map.Entry<Character, Integer> e : tempList) {
                if (e.getValue() > 0) q.add(e); // add valid tasks
            }

            if (q.isEmpty()) break;
            count = count + k; // if k > 0, then it means we need to be idle
            while(k-- > 0) sb.append('x');

        }
        return sb.toString();
}
