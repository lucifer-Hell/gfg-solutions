// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++){
                String[] S = br.readLine().trim().split(" ");
                for(int j = 0; j < m; j++){
                    grid[i][j] = Integer.parseInt(S[j]);
                }
            }
            Solution obj = new Solution();
            int[][] ans = obj.nearest(grid);
            for(int i = 0; i < ans.length; i++){
                for(int j = 0; j < ans[i].length; j++){
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}// } Driver Code Ends


class Solution
{
    public int solve(int[][]grid,int i,int j,int si,int sj){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length )
            return Integer.MAX_VALUE;
        if(grid[i][j]==-2)return Integer.MAX_VALUE;
        if(grid[i][j]==-1){
            // found 1
            int d=(si-i>=0)?si-i:i-si;
            d+=(sj-j>=0)?sj-j:j-sj;
            return d;
        }
        grid[i][j]=-2;
        System.out.println("curr i j "+i+" "+j);
        int ans=solve(grid,i+1,j,si,sj);
        ans=Math.min(ans,solve(grid,i-1,j,si,sj));
        ans=Math.min(ans,solve(grid,i,j+1,si,sj));
        ans=Math.min(ans,solve(grid,i,j-1,si,sj));
        grid[i][j]=0;
        return ans;
    }
    public int[][] nearest(int[][] grid)
    {
        // Code here
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    grid[i][j]=-1;
                }
            }
        }
        // for(int i=0;i<grid.length;i++){
        //     for(int j=0;j<grid[i].length;j++){
        //         if(grid[i][j]==0){
        //             grid[i][j]=solve(grid,i,j,i,j);
        //         }
        //     }
        // }
        solve(grid,3,0,3,0);
        //   for(int i=0;i<grid.length;i++){
        //     for(int j=0;j<grid[i].length;j++){
        //         if(grid[i][j]==-1){
        //             grid[i][j]=0;
        //         }
        //     }
        // }
        return grid;
    }
}