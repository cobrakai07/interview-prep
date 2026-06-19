import java.util.*;
import java.lang.*;
import java.io.*;

class SegmentTree{
    int[] seg;
 
    public SegmentTree(int n){
        seg = new int[n*4];
    }
    
    public void build(int idx, int l, int r, int[]arr){
        if(l==r){
            seg[idx]= arr[l];
            return;
        }
        int mid = l+(r-l)/2;
        build(idx*2+1,l,mid,arr);
        build(idx*2+2,mid+1,r,arr);
        
        seg[idx] = Math.max(seg[idx*2+1],seg[idx*2+2]); 
    }
    
   
    public void update(int idx, int l, int r, int i, int val){
        if(l==r){
            seg[idx] = val;
            return;
        }
        int mid = l+(r-l)/2;
        if(mid>=i)
            update(idx*2+1,l,mid,i,val);
        else 
            update(idx*2+2,mid+1,r,i,val);
        
        seg[idx] = Math.max(seg[idx*2+1],seg[idx*2+2]); 
        
    }
    
    public int query(int idx, int l, int r, int x, int y){
        if(r<x || y<l)return Integer.MIN_VALUE;
        if(x<=l && y>=r){
            return seg[idx];
        }
        else{
            int mid = l+(r-l)/2;
             return Math.max(query(idx*2+1,l,mid,x,y), query(idx*2+2,mid+1,r,x,y));
        }
    }
}
public class MySegmentTree
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int []arr = {1,3,2,6,4,2};
		int n = arr.length;
		SegmentTree st = new SegmentTree(n);
		st.build(0,0,n-1,arr);
		System.out.println(st.query(0,0,n-1,1,4));
		st.update(0, 0, n-1, 3, 45);
		System.out.println(st.query(0,0,n-1,1,4));

	}
}
