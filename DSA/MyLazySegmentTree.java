import java.util.*;
import java.lang.*;
import java.io.*;

class SegmentTree{
    int[] seg;
    int [] lazy;
    
    public SegmentTree(int n){
        seg = new int[n*4];
        lazy = new int[n*4];
    }
    
    public void build(int idx, int l, int r, int[]arr){
        if(l==r){
            seg[idx]= arr[l];
            return;
        }
        int mid = l+(r-l)/2;
        build(idx*2+1,l,mid,arr);
        build(idx*2+2,mid+1,r,arr);
        
        seg[idx] = seg[idx*2+1]+seg[idx*2+2]; 
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
        
        seg[idx] = seg[idx*2+1]+ seg[idx*2+2]; 
        
    }
    
    public void push(int idx, int l, int r){
        int left = 2*idx +1;
        int right = 2*idx+2;
        seg[idx]+= (lazy[idx]*(r-l+1));
        if(left<lazy.length)lazy[left] += lazy[idx];
        if(right<lazy.length)lazy[right] += lazy[idx];
        lazy[idx] = 0;
    }
    
    public void rangeUpdate(int idx, int l, int r, int x, int y, int val){
        if(lazy[idx]!=0){
            push(idx,l,r);
        }
        if(r<x || y<l)return;
        
        if(x<=l && y>=r){
            lazy[idx]+=val;
            push(idx,l,r);
        }
        else{
            int mid = l+(r-l)/2;
            rangeUpdate(idx*2+1,l,mid,x,y,val);
            rangeUpdate(idx*2+2,mid+1,r,x,y,val);
            seg[idx] = seg[idx*2+1]+ seg[idx*2+2];
        }
        
    }
    
    public int query(int idx, int l, int r, int x, int y){
        if(lazy[idx]!=0){
             push(idx,l,r);
        }
        if(r<x || y<l)return 0;
        if(x<=l && y>=r){
            return seg[idx];
        }
        else{
            int mid = l+(r-l)/2;
             return query(idx*2+1,l,mid,x,y)+ query(idx*2+2,mid+1,r,x,y);
        }
    }
}
public class MyLazySegmentTree
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int []arr = {1,1,1,1,4,2};
		int n = arr.length;
		SegmentTree st = new SegmentTree(n);
		st.build(0,0,n-1,arr);
		System.out.println(st.query(0,0,n-1,0,3));
		st.rangeUpdate(0,0, n-1, 0, 3,-1);
		System.out.println(st.query(0,0,n-1,0,3));

	}
}
