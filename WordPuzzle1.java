/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Raji
 */
public class WordPuzzle1 {
    int row,col;
    String [][] puzzle;
    int count;
	
    public void findWord(MyHashTable<String> t, int type) {
	count=0;
	for(int i=0;i<row;i++) {
            int x=0;
            StringBuilder wb;
            
            while(x<col) {
		wb=new StringBuilder();
		for(int j=x;j<col;j++) {
                    wb.append(puzzle[i][j]);
                    int temp=t.contains(wb.toString());
                    if(temp==0 && type==1) {
			wb.setLength(0);
			break;
                    }else if(temp==1) {
			count++;
			System.out.println(wb);
                    }else {}
		}
		x++;
            }	
            StringBuilder wbr;
            int kr=col - 1;
            while(kr>0) {
		wbr = new StringBuilder();
		wbr.append(puzzle[i][kr]);
		for(int j=kr-1;j>=0;j--) {
                    wbr.append(puzzle[i][j]);
                    int temp=t.contains(wbr.toString());
                    if(temp==0 && type==1) {
			wbr.setLength(0);
			break;
                    }else if(temp==1) {
                        count++;
			System.out.println(wbr);
                    }else {}
		}
		kr--;
            }		
	}
        for(int j=0;j<col;j++) {
            StringBuilder wb;
            int k=0;
            while(k<row-1) {
                wb = new StringBuilder();
		wb.append(puzzle[k][j]);
		for(int i= k+1;i<row;i++) {
                    wb.append(puzzle[i][j]);
                    int temp=t.contains(wb.toString());
                    if(temp==0 && type==1) {
			wb.setLength(0);
			break;
                    }else if(temp==1) {
			count++;
			System.out.println(wb); 
                    }else {}
		}
		k++;
            }
            StringBuilder wbr;
            int kr=row - 1;
            while(kr>0) {
		wbr= new StringBuilder();
		wbr.append(puzzle[kr][j]);
                for(int i=kr-1;i>=0;i-- ) {
                    wbr.append(puzzle[i][j]);
                    int temp=t.contains(wbr.toString());
                    if(temp==0 && type==1) {
			wbr.setLength(0);
			break;
                    }else if(temp==1) {
			count++;
			System.out.println(wbr);
                    }else {}
                }
		kr--;
            }		
	}
	StringBuilder sb1=new StringBuilder();
        for(int i=0;i<puzzle.length;i++){
            for(int j=0;j<puzzle[0].length;j++){
		int x=i;
		sb1.setLength(0);
		for(int k=j;k>=0;k--){
                    sb1.append(puzzle[x][k]);
                    if(sb1.length()<2){
		        x--;
			if(x<0){
                            break;
                        }
                        continue;
                    }
                    int temp=t.contains(sb1.toString());
                    if (temp==0 && type==1){
			sb1.setLength(0);
			break;
                    }
                    else if(temp==1){
			count++;
			System.out.println(sb1);
                    }
                    else{ }
                    x--;
                    if(x<0){
			break;
                    }
                }
            }
        }
	for(int i=1;i<row;i++) {
            StringBuilder wb;
            int k=col-1;
            while(k>0) {
		wb=new StringBuilder();
		wb.append(puzzle[i-1][k-1]);
		int ir=i;
		for(int j=k;j<col;j++) {
                    wb.append(puzzle[ir][j]);
                    int temp=t.contains(wb.toString());
                    if(temp==0 && type==1) {
			wb.setLength(0);
			break;
                    }else if(temp==1) {
			count++;
			System.out.println(wb); 
                    }else {}
                    ir++;
                    if(ir>row -1) {
			break;
                    }
		}
		k--;
            }	
            StringBuilder wbr;
            int kr=col-1;
            while(kr>0) {
		wbr = new StringBuilder();
		wbr.append(puzzle[i][kr]);
		int irr=i-1;
		for(int j=kr-1;j>=0;j--) {
                    wbr.append(puzzle[irr][j]);
                    int temp=t.contains(wbr.toString());
                    if(temp==0 && type==1) {
			wbr.setLength(0);
			break;
                    }else if(temp==1) {
			count++;
			System.out.println(wbr); 
                    }else {}
                    irr--;
                    if(irr<0) {
			break;
                    }					
		}
		kr--;
            }
	}
    }
	
    public void createRandomPuzzle(int ro, int c) {
        Random r=new Random();
        row=ro;
	col=c;		
	puzzle=new String[row][col];
	for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
		puzzle[i][j]=Character.toString((char)(97 + r.nextInt(26)));
            }
	}	
    }

    public void printRandomPuzzle() {
        System.out.println("randomly generated puzzle:");
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
		System.out.print(puzzle[i][j]+"  ");
            }
            System.out.println("  ");
	}
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows and column: ");
	int row = sc.nextInt();
	int column = sc.nextInt();
	WordPuzzle1 p = new WordPuzzle1();
	MyHashTable<String> t = new MyHashTable<>();
	if(row<1||column<1){
            System.out.println("Value should be grater than 0");
	}else{
            System.out.println("choose the type of hashing.press 0 -> Simple and 1 -> advanced:");
            int en=sc.nextInt();
            p.createRandomPuzzle(row,column);
            Scanner s=null;
            try {
		s=new Scanner(new File("C:\\Users\\Raji\\Documents\\NetBeansProjects\\Sample\\src\\sample\\Dictionary.txt"));
            } catch(FileNotFoundException f){
		f.printStackTrace();
            }
            int count=0,pcount=0;
            long hstart=System.nanoTime();
            while(s.hasNext()){
                String w=s.nextLine();
		if(en==0){
		    StringBuilder sb=new StringBuilder();
                    for(int i=0;i<w.length();i++){
			sb.append(w.charAt(i));
			if(i==w.length()-1){
                            t.insert(sb.toString(),true,false);
                            count++;
			}
			else{								
                            t.insert(sb.toString(),false,true);
                            pcount++;
			}							
                    }
		} 
                else{
                    t.insert(w,true,false);
                    count++;
		} 	 
            }     
            s.close();
            long hstop=System.nanoTime();
            long totalhtime=hstop-hstart;
            System.out.println( "time taken(in ms) to HASH words: " + totalhtime/1000000);
            //t.printHash();
            long starttime=System.nanoTime();
            p.printRandomPuzzle();
            p.findWord(t, en);
            long endtime=System.nanoTime();
            long totaltime=endtime-starttime;
            System.out.println("total number of words found : " +p.count);
            System.out.println( "time taken(in ms) for finding words: " +totaltime/1000000);
	}
	sc.close();		
    }
}
