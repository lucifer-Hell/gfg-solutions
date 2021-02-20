class Node {
    int idx,wt,d;
    boolean visited;
    boolean inPath;
    int prev=-1;
    int left,right;
    Node(int idx,int wt,int d,int left,int right){
        this.idx=idx;
        this.wt=wt;
        this.d=d;
        this.visited=false;
        this.inPath=false;
        this.left=left;
        this.right=right;
    }
}


class Solution
{
    
    public static void printPath(int curr,Node[]v){
        if(v[curr].prev!=-1)printPath(v[curr].prev,v);
        System.out.print(v[curr].wt+"->");
    }
    // update(idx-1,idx,v,q);
    public void update(int curr,int idx,Node []v,Queue<Node>q ){
        // relax if possible
        if(v[curr].inPath)return;
        // System.out.println("curr "+v[curr].wt);
        if(v[curr].d>v[idx].d+v[curr].wt){
            // update 
            if(v[curr].visited){
                // remove from priority queue
                q.remove(new Node(v[curr].idx,v[curr].wt,v[curr].d,v[curr].left,v[curr].right));
            }
            v[curr].d=v[idx].d+v[curr].wt;
            // System.out.println("updating parent of "+v[curr].idx+" to idx "+idx);
            v[curr].prev=idx;
            q.add(new Node(v[curr].idx,v[curr].wt,v[curr].d,v[curr].left,v[curr].right));
        }
        v[curr].visited=true;
    }

    public static  boolean isValid(int idx,int parent,Node []v,int n){
       
        if(idx>=0 && idx<n*n){
            Node a=v[idx];
            Node b=v[parent];
            if(a.idx==b.idx-n)return true;
            else if(a.idx==b.idx+n)return true;
            else if(a.left==b.left && a.right==b.right)return true;
            // System.out.println("returning false for "+a.idx +" of "+b.id);
            return false;

        }
        else return false;
    }
    public int minimumCostPath(int[][] grid)
    {

      // idea is to use dijkistra's algorthim
        int n=grid.length*grid.length;
        Node []v=new Node[n];
        int idx=0;
        for(int i=0;i<grid.length;i++){
          for(int j=0;j<grid[i].length;j++){
              v[idx]=new Node(idx,grid[i][j],Integer.MAX_VALUE,i-1,i+1);
              idx++;
          }
        }
        idx=0;
       
        v[idx].d=v[idx].wt;
        PriorityQueue<Node>q=new PriorityQueue<>(new Comparator<Node>(){
          @Override
          public int compare(Node a,Node b ){
           if(a.d>b.d)return 1;
           else if(a.d<b.d)return -1;
           else return 0;
          }
        });
       
        q.add(v[idx]);
        int m=grid.length;
        while(!q.isEmpty()){
            // for(Node b:q)
            // System.out.print(b.wt+" ");
            // System.out.println();
            
          Node curr=q.remove();
          // update adj nodes
          idx=curr.idx;
        //   if(idx==v.length-1)return v[idx].d;
        //   System.out.println(curr.idx);
          curr.inPath=true;
          if(isValid(idx+1,idx,v,m) && !v[idx+1].inPath)
              update(idx+1,idx,v,q);
          if(isValid(idx-1,idx,v,m) && !v[idx-1].inPath)
              update(idx-1,idx,v,q);
          if(isValid(idx-m,idx,v,m) && !v[idx-m].inPath)
              update(idx-m,idx,v,q);
          if(isValid(idx+m,idx,v,m) && !v[idx+m].inPath)
              update(idx+m,idx,v,q);
          // if(idx==v.length-1)System.out.println(v[idx].d);
          
        }
        // printPath(v.length-1,v);
        // System.out.println();
        return v[v.length-1].d;
        
    }
}