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
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class TestCase {
    public static void main(String[] args) {
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.b173355.Frequencer");
	    myObject = new s4.b173355.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
		freq = myObject.frequency();
		System.out.println(freq);
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	 //TARGETの長さ0の確認
	try {
        FrequencerInterface  myObject1;
        int freq1;
        System.out.println("checking s4.b173355.Frequencer to return -1, when TARGET's length is zero");
        myObject1 = new s4.b173355.Frequencer(); 
        myObject1.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject1.setTarget("".getBytes());
        freq1 = myObject1.frequency();
		System.out.println(freq1);
		
        if(-1 == freq1) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
        System.out.println("Exception occurred: STOP");
	}
	 //TARGETが未設定か確認
	 try {
        FrequencerInterface  myObject2;
		int freq2;
		
        System.out.println("checking s4.b173355.Frequencer to return -1, when TARGET's not set ");
        myObject2 = new s4.b173355.Frequencer(); 
        myObject2.setSpace("Hi Ho Hi Ho".getBytes());
        freq2 = myObject2.frequency();
		System.out.println(freq2);
        if(-1 == freq2) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
        System.out.println("Exception occurred: STOP");
	}
	//SPACEの長さ0の確認
	try {
        FrequencerInterface  myObject3;
        int freq3;
        System.out.println("checking s4.b173355.Frequencer to return 0, when Space's length is zero");
        myObject3 = new s4.b173355.Frequencer(); 
        myObject3.setSpace("".getBytes());
	    myObject3.setTarget("G".getBytes());
        freq3 = myObject3.frequency();
		System.out.println(freq3);
		
        if(0 == freq3) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
        System.out.println("Exception occurred: STOP");
	}
	 //SPACEが未設定か確認
	 try {
        FrequencerInterface  myObject4;
		int freq4;
		
        System.out.println("checking s4.b173355.Frequencer to return 0, when SPACE is not set");
        myObject4 = new s4.b173355.Frequencer(); 
	    myObject4.setTarget("H".getBytes());
	    freq4 = myObject4.frequency();
		System.out.println(freq4);
        if(0 == freq4) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
        System.out.println("Exception occurred: STOP");
	}
	// is it ok?  for文内で配列の長さを超えないか？
	try {
        FrequencerInterface  myObject4;
		int freq4;
		
        System.out.println("checking the Array length of s4.b173355.Frequencer ");
        myObject4 = new s4.b173355.Frequencer(); 
		myObject4.setSpace("honma honma honma honma honm".getBytes());
		myObject4.setTarget("honma".getBytes());
	    freq4 = myObject4.frequency();
		System.out.println(freq4);
        if(4 == freq4) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
        System.out.println("Exception occurred: STOP");
	}
	
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.b173355.InformationEstimator");
	    myObject = new s4.b173355.InformationEstimator();
	    myObject.setSpace("3210321001230123".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0 "+value);
	    myObject.setTarget("01".getBytes());
	    value = myObject.estimation();
	    System.out.println(">01 "+value);
	    myObject.setTarget("0123".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0123 "+value);
	    myObject.setTarget("00".getBytes());
	    value = myObject.estimation();
	    System.out.println(">00 "+value);
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	//TARGETが未設定か確認
	try {
	    InformationEstimatorInterface myObject1;
	    double value1;
	    System.out.println("checking s4.b173355.InformationEstimator to to return -1, when TARGET's not set");
	    myObject1 = new s4.b173355.InformationEstimator();
	    myObject1.setSpace("3210321001230123".getBytes());
	    //myObject1.setTarget("0".getBytes());
		value1 = myObject1.estimation();
		System.out.println(">0 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">01 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">0123 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">00 "+value1);
		if(0.0 == value1) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
		System.out.println("Exception occurred: STOP");
	}
	//TARGETの 長さ0
	try {
	    InformationEstimatorInterface myObject1;
	    double value1;
	    System.out.println("checking s4.b173355.InformationEstimator to return -1, when TARGET's length is zero");
	    myObject1 = new s4.b173355.InformationEstimator();
	    myObject1.setSpace("3210321001230123".getBytes());
	    myObject1.setTarget("".getBytes()); //TARGETの 長さ0
		value1 = myObject1.estimation();
		System.out.println(">0 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">01 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">0123 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">00 "+value1);
		if(0.0 == value1) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
		System.out.println("Exception occurred: STOP");
	}
		//SPACEの長さ0の確認
	try{
		InformationEstimatorInterface myObject1;
		double value1;
		System.out.println("checking s4.b173355.InformationEstimator to return 0, when SPACE's length is zero");
		myObject1 = new s4.b173355.InformationEstimator();
		myObject1.setSpace("".getBytes());//SPACEの長さ0
		myObject1.setTarget("0".getBytes()); 
		value1 = myObject1.estimation();
		System.out.println(">0 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">01 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">0123 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">00 "+value1);
		if(Double.MAX_VALUE == value1) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
		System.out.println("Exception occurred: STOP");
	}
	//SPACE未定義
	try{
		InformationEstimatorInterface myObject1;
		double value1;
		System.out.println("checking s4.b173355.InformationEstimator to return 0, when SPACE is not set");
		myObject1 = new s4.b173355.InformationEstimator();
		//myObject1.setSpace("".getBytes());//SPACE未定義
		myObject1.setTarget("0".getBytes()); 
		value1 = myObject1.estimation();
		System.out.println(">0 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">01 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">0123 "+value1);
		value1 = myObject1.estimation();
		System.out.println(">00 "+value1);
		if(Double.MAX_VALUE == value1) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
		System.out.println("Exception occurred: STOP");
	}
    }
}	    
	    


	    
