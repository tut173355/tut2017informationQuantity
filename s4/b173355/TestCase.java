package s4.b173355; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
 
interface FrequencerInterface {     //このインタフェースは、周波数カウンタの設計を提供します。    
    void setTarget(byte[]  target); //検索するデータを設定します。
    void setSpace(byte[]  space);  //検索対象となるデータを設定します。
    int frequency();    // TARGETが設定されていないか、TARGETの長さがゼロの場合は-1を返します
                        //それ以外の場合は、SPACEが設定されていないか、スペースの長さがゼロの場合は0を返します。
                        //それ以外の場合は、SPACEのTAGETの頻度を取得します。
    int subByteFrequency(int start, int end);
    // target [start]、target [start + 1]、...、target [end-1]のように、targetのsubByteの頻度を取得します。
    // STARTまたはENDの値が正しくない場合、動作は未定義です。
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
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
    try {
        FrequencerInterface  myObject;
        int freq;
        int num;
        System.out.println("checking s4.b173355.Frequencer");
        myObject = new s4.b173355.Frequencer();
        myObject.setSpace("Hi Ho Hi Ho".getBytes());
        myObject.setTarget("H".getBytes());
        freq = myObject.frequency();
        /*System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
         for(int i=0; i<4 ; i++){
            for(int j=0; j<4 ; j++){
                System.out.print(i+" "+j+ ":" );
                nummyObject.subByteFrequency(i,j));
            }
         }*/
        num = myObject.subByteFrequency(0,4);
        if(-1 == num) { System.out.println("OK"); } else {System.out.println("WRONG"); }
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
    }
}
	    
