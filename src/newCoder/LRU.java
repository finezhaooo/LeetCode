package newCoder;


import java.util.*;

/**
 * @ClassName : LRU
 * @Description:
 * @Author zhaooo
 * @Date 2021/4/9/22:10
 * <p>
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * [要求]
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 * 示例1
 * 输入
 * 复制
 * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
 * 返回值
 * 复制
 * [1,-1]
 * 说明
 * 第一次操作后：最常使用的记录为("1", 1)
 * 第二次操作后：最常使用的记录为("2", 2)，("1", 1)变为最不常用的
 * 第三次操作后：最常使用的记录为("3", 2)，("1", 1)还是最不常用的
 * 第四次操作后：最常用的记录为("1", 1)，("2", 2)变为最不常用的
 * 第五次操作后：大小超过了3，所以移除此时最不常使用的记录("2", 2)，加入记录("4", 4)，并且为最常使用的记录，然后("3", 2)变为最不常使用的记录
 * 备注:
 * 1 \leq K \leq N \leq 10^51≤K≤N≤10
 * 5
 * <p>
 * -2 \times 10^9 \leq x,y \leq 2 \times 10^9−2×10
 * 9
 * ≤x,y≤2×10
 * 9
 */
public class LRU {

    public class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int CACHE_SIZE;

        public LRUCache(int cache_size) {
            super((int) (Math.ceil(cache_size / 0.75) + 1), 0.75f, true);
            CACHE_SIZE = cache_size;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > CACHE_SIZE;
        }
    }

    public class LruNode {
        public int val;
        public int key;
        public LruNode next;
        public LruNode pre;

        public LruNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public void addFirst(LruNode head, LruNode node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    public void removeLast(LruNode tail) {
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
    }

    public void remove(LruNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        // write code here
        int[] result = new int[operators.length];
        int index = 0;
        HashMap<Integer, LruNode> map = new HashMap<>();
        LruNode head = new LruNode(0, 0);
        LruNode tail = new LruNode(0, 0);
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;
        // 若opt=1，接下来两个整数x, y，表示set(x, y)
        // 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                LruNode node = new LruNode(operator[1], operator[2]);
                if (map.size() == k) {
                    map.remove(tail.pre.key);
                    removeLast(tail);
                }
                addFirst(head, node);
                map.put(operator[1], node);
            } else {
                LruNode node = map.getOrDefault(operator[1], new LruNode(Integer.MAX_VALUE, -1));
                result[index++] = node.val;
                if (node.key != Integer.MAX_VALUE) {
                    remove(node);
                    addFirst(head, node);
                }
            }
        }
        return Arrays.copyOf(result, index);
    }
}

