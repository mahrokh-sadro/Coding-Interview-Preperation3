// Design a data structure that follows 
// the constraints of a Least Recently 
// Used (LRU) cache.

// Implement the LRUCache class:

// LRUCache(int capacity) Initialize the
// LRU cache with positive size capacity.
// int get(int key) Return the value of 
// the key if the key exists, otherwise return -1.
// void put(int key, int value) Update the
// value of the key if the key exists. 
// Otherwise, add the key-value pair to 
// the cache. If the number of keys exceeds
// the capacity from this operation, evict
// the least recently used key.
// The functions get and put must each run
// in O(1) average time complexity.

// 1 <= capacity <= 3000
// 0 <= key <= 104
// 0 <= value <= 105
// At most 2 * 105 calls will be made to get and put.
class LRUCache {
    
    private class Node{
        int value;
        int key;
        Node pre;
        Node next;
        public Node(){}
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    Map<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>();
        head=new Node();
        tail=new Node();
        head.next=tail;
        tail.pre=head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node=map.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
           Node node=map.get(key);
           remove(node);
           addToHead(node);
           node.value=value; 
        }
        else{
           Node newNode=new Node(key,value);
           addToHead(newNode);
           map.put(key,newNode);   
        }

        if(map.size()>capacity){
            Node lru=tail.pre;
            remove(lru);
            map.remove(lru.key);
        }
    }

    private void remove(Node node){
        node.pre.next=node.next;
        node.next.pre=node.pre;
    }

    private void addToHead(Node node){
       node.next=head.next;
       head.next.pre=node;
       node.pre=head;
       head.next=node;  
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// time:o(1)
// space:o(n)