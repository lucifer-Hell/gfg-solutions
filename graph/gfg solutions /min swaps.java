class Node {
    int x,y,val;
    Node(int x,int y,int val){
        this.x=x;
        this.y=y;
        this.val=val;
    }
}


class Solution
{
    
    public boolean isValid(int i,int j,int n,int m){
        if(i>=0 && i<n && j>=0 && j<m)return true;
        else return false;
    }
    
    public boolean update(int i,int j,int[][]grid,Queue<Node>q){
        
        if(isValid(i,j,grid.length,grid[0].length) && grid[i][j]==1){
                    grid[i][j]=2;
                    q.add(new Node(i,j,2));
                    return true;
        }
        else return false;
        
    }
    
    public int orangesRotting(int[][] grid)
    {
        // idea is to use muliti source bfs
        // find all rotten tomatoes and add the them in queue
        Queue<Node> q=new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==2){
                    q.add(new Node(i,j,grid[i][j]));
                }
            }
        }
        
        int time=0;
      
        while(!q.isEmpty()){
            int size=q.size();
            boolean incTime=false;
            for(int i=0;i<size;i++){
                Node node=q.remove();
                int x=node.x;
                int y=node.y;
                // System.out.println(" grid "+x+" "+y);
              incTime=incTime |    update(x-1,y,grid,q);
              incTime=incTime |    update(x+1,y,grid,q);
              incTime=incTime |    update(x,y+1,grid,q);
              incTime=incTime |    update(x,y-1,grid,q);
            }
            if(!incTime)break;
            time++;
        }
        // check if any node is still left 
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    return -1;
                }
            }
        }
        return time;
        
        
    }
}