class Node {
  int src,dest,wt;
  Node(int src,int dest,int wt){
    this.src=src;
    this.dest=dest;
    this.wt=wt;
  }
  
}
class Solution {

    void relax(int []t,Node n){
        // if src node is infinity
        if(n.src==Integer.MAX_VALUE)return;
        if(t[n.dest]>t[n.src]+n.wt)
          t[n.dest]=t[n.src]+n.wt;
        
    }

    int minTime (ArrayList<Node> graph ,int src,int n){
        // idea is to use bellman ford to solve the problem
        int t[]=new int[n];
        Arrays.fill(t,Integer.MAX_VALUE);
        t[src]=0;
        for(int i=1;i<n;i++){
          // relax all edges v-1 times 
            for(Node node:graph){
                relax(t,node);
            }
        }
        // check if any t is infinity
        int maxTime=0;
        for(int i:t)
          if(i==Integer.MAX_VALUE)return -1;
          else maxTime=Math.max(maxTime,i);
        return maxTime;
    }
    
    
    public int networkDelayTime(int[][] times, int n, int k) {
       ArrayList<Node> graph=new ArrayList<>();
       for (int[]node :times){
          graph.add(new Node(node[0],node[1],node[2]));
       }
       return minTime(graph, k-1, n);
    }
}