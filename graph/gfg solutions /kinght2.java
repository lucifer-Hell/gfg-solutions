class Node {
  int x,int y;
  Node (int x,int y,int d){
    this.x=x;
    this.y=y;
   
  }
}
class Solution
{
    
    public boolean isValid(int x,int y,int n,int m){
        if(x<0 || x>=n || y<0 || y>=m)return false;
        else return true;
        
    }
    public static void relax(int x,int y,int [][]board,Queue<Node>q,int d){
          // board x y data is not filled
          if(board[x][y]!=0)return;
          board[x][y]=d+1;
          q.add(new Node(x,y));
    }
    
    public int solve(int x,int y,int tx,int ty,int [][]board,int d){
        int n=board.length;
        int m=board[0].length;
        Queue<Node> q=new LinkedList<>();
        board[x][y]=0;
        q.add(new Node(x,y));
        while(!q.isEmpty()){
            Node curr=q.remove();
            if(curr.x==tx && curr.y==ty){
              return board[tx][ty];
            }
            relax(curr.x-1,curr.y+2,board,q,board[curr.x][curr.y]);
            relax(curr.x+1,curr.y+2,board,q,board[curr.x][curr.y]);
            relax(curr.x-2,curr.y-1,board,q,board[curr.x][curr.y]);
            relax(curr.x-1,curr.y-2,board,q,board[curr.x][curr.y]);
            relax(curr.x-2,curr.y+1,board,q,board[curr.x][curr.y]);
            relax(curr.x+2,curr.y+1,board,q,board[curr.x][curr.y]);
            relax(curr.x+2,curr.y-1,board,q,board[curr.x][curr.y]);
            relax(curr.x+1,curr.y-2,board,q,board[curr.x][curr.y]);
        }q,
        return Integer.MAX_VALUE;
        
    }
    /* 
    
    
        
    
    */
    
    public int minStepToReachTarget(int player[], int target[], int N)
    {
        // Code here
        int [][]board=new int[N][N];
        board[target[0]-1][target[1]-1]=Integer.MAX_VALUE;
        solve(player[0]-1,player[1]-1,target[0]-1,target[1]-1,board,0);
        return board[target[0]-1][target[1]-1];
    }
}