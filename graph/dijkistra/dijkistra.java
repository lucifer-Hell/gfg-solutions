// leetcode problem 
// Network Delay Time
class Node {
  int v,wt;
  Node(int v,int wt){
    this.v=v;
    this.wt=wt;
  }
  
}
class Solution {


   
    int djk(ArrayList<ArrayList<Node>>g,int src,int n){
        //array implementation
        
        // shortest distance 
        int []wt=new int[n];
        int []visited=new int[n];
        Arrays.fill(wt,Integer.MAX_VALUE);
        wt[src]=0;
        int currVertex=src;
        int minWt=Integer.MAX_VALUE;
        // repeat n-1 times 
        // each time a vertex will be added to visited
        for(int i=1;i<n;i++){
            for(Node adj:g.get(currVertex)){
              if(wt[adj.v]>wt[currVertex]+adj.wt){
                wt[adj.v]=wt[currVertex]+adj.wt;
              }
            }
            visited[currVertex]=1;
            // find the next min wt vertex
            minWt=Integer.MAX_VALUE;
            for(int j=0;j<wt.length;j++){
                
              if(visited[j]==0 && wt[j]<minWt){
                currVertex=j;
                minWt=wt[j];
            }

        }
        int maxTime=0;
        for(int i:wt)maxTime=Math.max(maxTime,i);
        return (maxTime==Integer.MAX_VALUE)?-1:maxTime;
    }
    
    
    public int networkDelayTime(int[][] times, int n, int k) {
        // times 
        ArrayList<ArrayList<Node>>graph=new ArrayList<>();
        for(int i=0;i<n;i++)graph.add(new ArrayList<>());
        for(int i=0;i<times.length;i++){
            // System.out.println(i);
          int u=times[i][0];
          int v=times[i][1];
          int wt=times[i][2];
          graph.get(u-1).add(new Node(v-1,wt));  
        }
        return djk(graph,k-1,n);

    }
}