/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author Raji
 */
public class MyHashTable<AnyType>{
    int full,s;
    private static final int DEFAULT_TABLE_SIZE=100;
    private HashEntry<AnyType> [ ] arr;
    
    public MyHashTable(){
	this(DEFAULT_TABLE_SIZE);
	doClear();
    }	
    public MyHashTable(int size){
	arr=new HashEntry[size];
	doClear();
    }	
    
    public boolean insert(AnyType x, boolean j, boolean p){
	int curr=findPosition(x);
	if(isActive(curr)){
            return false;
	}
	if(arr[curr]==null){
            ++full;
	}
	arr[curr]=new HashEntry<>(x,true,j,p);
	s++;
	if(full>arr.length/2){
            rehash( );
	}     
	return true;
    }
    
    public boolean remove(AnyType x){
	int currpos=findPosition(x);
	if(isActive(currpos)){
            arr[currpos].isActive = false;
            s--;
            return true;
	}else {
            return false;
        }
    }
    
    public int contains(AnyType x){
	int currpos=findPosition(x);
	if(isActive(currpos)){
            if(arr[currpos].isWord || arr[currpos].isPrefix){
                return 1;
            }else{
                return 2;
            }
	}else{
            return 0;
	}
    }
    
    private int findPosition(AnyType x){
	int currpos=myhash(x);
	while(arr[currpos]!=null && !arr[currpos].element.equals(x)){
            currpos++;
            if(currpos>=arr.length){
		currpos-=arr.length;
            }
	}		
	return currpos;
    }
    
    public void printHash(){
	for(HashEntry<AnyType> entry : arr){
            if(entry!=null){
		if(entry.isWord)
                    System.out.println(entry.element + " ->letters match");
		else if(entry.isPrefix)
                    System.out.println(entry.element + " ->prefix match");
            }			
	}
    }
       
    
    private int myhash(AnyType x){
	int val=x.hashCode();
	val%=arr.length;
	if(val<0){
            val+=arr.length;
	}
	return val;
    }
    
    
    private void rehash(){
	HashEntry<AnyType> [] oldarr=arr;
	arr=new HashEntry[2*oldarr.length];
	full=0;
        s=0;
	for(HashEntry<AnyType> entry : oldarr){
            if(entry!=null && entry.isActive){
		insert(entry.element,entry.isWord,entry.isPrefix);
            }
	}
    }
	
    private boolean isActive(int currpos){
	return arr[currpos]!=null && arr[currpos].isActive;
    }
	
    public void makeEmpty(){
	doClear();
    }
    private void doClear(){
	full=0;
	for(int i=0;i<arr.length;i++) {
            arr[i]=null;
	}
    }
    private static class HashEntry<AnyType>{
        
	boolean isActive; 
	boolean isWord; 
	boolean isPrefix;
        AnyType element;
	HashEntry(AnyType e){
            this(e,true,true,false);
	}
	public HashEntry(AnyType e,boolean i,boolean j,boolean p){
            isActive=i;
            isWord=j;
            isPrefix=p;
            element=e;
	}
    }
    
}
