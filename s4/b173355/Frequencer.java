package s4.b173355; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
*/


public class Frequencer implements FrequencerInterface{	
	// Code to Test, *warning: This code  contains intentional problem*
	byte [] myTarget;
	byte [] mySpace;
    int [] suffixArray;
    boolean targetReady = false;
    boolean spaceReady = false;
    
    private void printSuffixArray() {
        if(spaceReady) {
            int lenMeS = mySpace.length;
            for(int i=0; i< lenMeS; i++) {
                int s = suffixArray[i];
                for(int j=s;j<lenMeS;j++) {
                    System.out.write(mySpace[j]);
                }
                System.out.write('\n');
            }
        }
    }
   //	public void setTarget(byte [] target) { myTarget = target;}
    public void setTarget(byte [] target) {
        //System.out.println(target.length);
        myTarget = target;
        if(myTarget.length > 0){ 
            targetReady = true;
        }
        
    }
    
 	//public void setSpace(byte []space) { mySpace = space; }
   	public void setSpace(byte []space) { 
        mySpace = space;
        if(mySpace.length>0) { 
            spaceReady = true;
            int lenMeS = mySpace.length;
            suffixArray = new int[lenMeS];
            for(int i = 0; i< lenMeS; i++) {
                suffixArray[i] = i;
            }
            quickSort(0, lenMeS-1);
            //printSuffixArray();
        }
    }

   private void quickSort(int left, int right) {
        if (left>=right) {
            return;
        }
            int pivot = left;
            int l = left, r = right;
            while(l<=r) {
                while(suffixCompare(l,pivot) == -1) { l++; }
                while(suffixCompare(r,pivot) == 1)  { r--; }
                if (l<=r) {
                    int tmp;
                    tmp = suffixArray[l]; 
                    suffixArray[l] = suffixArray[r];
                    suffixArray[r] = tmp;
                    if(l == pivot){
                          pivot=r;
                    }
                    if(r == pivot){
                        pivot = l;
                    }
                    l++;
                    r--;
                }
            
            }
            quickSort(left, r);  // ピボットより左側をクイックソート
            quickSort(l, right); // ピボットより右側をクイックソート
            
       }
    
    
    private int targetCompare(int i, int start, int end) {
        // if((start == end) && (i <= mySpace.length)) return 0;
        // if(mySpace[i]>myTarget[start]){
        //     return 1;
        // }
        // else if(mySpace[i]<myTarget[start]){
        //     return -1;
        // }
        // else{
        //     if(start == end)return 0;
        //     if(mySpace.length < (myTarget.length + i + 1 )) return -1;
        //     int ans = targetCompare(i+1,start+1,end);
        //     return ans;
        // }
        int stil = end - start;

        if (mySpace.length - i < stil){
           stil = mySpace.length-i;
           //return -1;
        }
        for(int j = 0;j < stil; j++){
            if(mySpace[i+j] > myTarget[start+j]){return 1;}
            if(mySpace[i+j] < myTarget[start+j]){return -1;}        
        }
        if (mySpace.length -i < end - start){return -1;}
        return 0 ;
    }
    private int suffixCompare(int i, int j) {
        int si = suffixArray[i];
        int sj = suffixArray[j];
        int s = 0;
        if(si > s) s = si;
        if(sj > s) s = sj;
        int n = mySpace.length - s;
        for(int k=0;k<n;k++) {
            if(mySpace[si+k] > mySpace[sj+k]) return 1;
            if(mySpace[si+k] < mySpace[sj+k]) return -1;
        }
        if(si < sj) return 1;
        if(si > sj) return -1;
        return 0;
    }
	
   
    private int subByteStartIndex(int start, int end){ 
    	// for(int i = 0; i < mySpace.length; i++){ 
    	//     if (targetCompare(suffixArray[i], start, end) == 0) {
    	// 	return i;
    	//     }
        // }
        int pLeft = 0;
        int pRight = suffixArray.length-1;
        
        do {
            int center = (pLeft + pRight) / 2 ;
            if (center == 0 && targetCompare(suffixArray[center],start,end)==0){
                return center;
            }
            else if (targetCompare(suffixArray[center],start,end)==0 && targetCompare(suffixArray[center-1],start,end)==-1) {
                return center;
            }
            else if (targetCompare(suffixArray[center],start,end) == -1){
                pLeft = center + 1; //真ん中の一つ右側を左端とする
            } 
            else {
                pRight = center - 1;
            }
        } while (pLeft <= pRight);
        return suffixArray.length;
    }
    private int subByteEndIndex(int start, int end){
        // for(int i=0;i<mySpace.length-1;i++){
        //     if(targetCompare(suffixArray[i],start,end)==0 && targetCompare(suffixArray[i+1],start,end) != 0)
        //         return i+1;
        // }
        int pLeft = 0;
        int pRight = suffixArray.length-1;
        do {
            int center = (pLeft + pRight) / 2;
            if(center == suffixArray.length-1 && targetCompare(suffixArray[center],start,end)==0){
                return center+1;
            }
             if (targetCompare(suffixArray[center],start,end)==0 && targetCompare(suffixArray[center+1],start,end)==1) {
                return center+1;
            }
            else if (targetCompare(suffixArray[center],start,end) == 1){
                pRight = center - 1;
            } 
            else {
                pLeft = center + 1; //真ん中の一つ右側を左端とする

            }
        } while (pLeft <= pRight);
        return suffixArray.length;
    }

    public int frequency() {
        if(targetReady == false) return -1;
        if(spaceReady == false) return 0;
       // System.out.println("きてる");
        return subByteFrequency(0, myTarget.length);
    }
    
    public int subByteFrequency(int start, int end) {
        int first = subByteStartIndex(start,end);
        int last1 = subByteEndIndex(start, end);
    //inspection code
        // for(int k=start;k<end;k++) { System.out.write(myTarget[k]); }
        //  System.out.printf(": first=%d last1=%d\n", first, last1);
         return last1 - first;
    }
    
    public static void main(String[] args) {
		Frequencer myObject;
		int freq;
		try {
			System.out.println("checking my Frequencer");
			myObject = new Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
			if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
		}
		catch(Exception e) {
	   	 System.out.println("Exception occurred: STOP");
		}
	}
}	    