package s4.b173355; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*s4.specificationからインポートされるもの
パッケージs4.specification;
パブリックインターフェイスInformationEstimatorInterface {
     void setTarget（byte target []）; //情報量を計算するためのデータを設定する
     void setSpace（バイトスペース[]）; //サンプル空間のデータをコンピュータ確率に設定する
     double estimation（）; //ターゲットが設定されていないか、ターゲットの長さがゼロの場合は0.0を返します。
// true値が無限大の場合、または空白が設定されていない場合は、Double.MAX_VALUEを返します。
// true値がfineteでDouble.MAX_VALUEより大きい場合の動作は未定義です。
//これは、スペースが不当に大きい場合にのみ発生することに注意してください。 とにかく他の問題に遭遇します。
//そうでなければ、情報量の推定、
}
*/

public class InformationEstimator implements InformationEstimatorInterface{
    // Code to tet, *warning: This code condtains intentional problem*
    boolean targetReady = false;
    boolean spaceReady = false;
    byte [] myTarget; // data to compute its information quantity
    byte [] mySpace;  // Sample space to compute the probability
    FrequencerInterface myFrequencer;  // Object for counting frequency
    
    /*
     
     byte [] subBytes(byte [] x, int start, int end) {
     // corresponding to substring of String for  byte[] ,
     // It is not implement in class library because internal structure of byte[] requires copy.
     byte [] result = new byte[end - start];
     for(int i = 0; i<end - start; i++) { result[i] = x[start + i]; };
     return result;
     }
     
     */
    
    // IQ: information quantity for a count,  -log2(count/sizeof(space))
    double iq(int freq) {
        return  - Math.log10((double) freq / (double) mySpace.length)/ Math.log10((double) 2.0);
    }
    
    public void setTarget(byte [] target) { 
        myTarget = target; 
        if(target.length>0) 
        targetReady = true;
    }
    public void setSpace(byte []space) {
        myFrequencer = new Frequencer();
        mySpace = space; 
        if(space.length>0){
            myFrequencer.setSpace(space);
            spaceReady = true;
        }
    }
    
    public double estimation(){
        
        if(targetReady == false) return (double) 0.0;
        if(spaceReady == false) return Double.MAX_VALUE;
        
        myFrequencer.setTarget(myTarget);
        
        double [] prefixEstimation = new double[myTarget.length+1];
        
        prefixEstimation[0] = (double) 0.0; //IE("") = 0.0;
        
        for(int n=1;n<=myTarget.length;n++) {
            // target = "abcdef..", n = 4 for example, subByte(0, 4) = "abcd",
            // IE("abcd") = min( IE("")+iq(#"abcd"),
            //                   IE("a") + iq(#"bcd"),
            //                   IE("ab")+iq(#"cd"),
            //                   IE("abc")+iq(#"d") )
            // prefixEstimation[0] = IE(""), subByte(0,4) = "abcd",
            // prefixEstimation[1] = IE("a");  subByte(1,4)= "bcd",
            // prefixEstimation[2] = IE("ab");  subByte(2,4)= "cd",
            // prefixEstimation[3] = IE("abc");  subByte(3,4)= "d",
            // prefixEstimation[4] = IE("abcd");
            //
            double value = Double.MAX_VALUE;
            for(int start=n-1;start>=0;start--) {
                int freq = myFrequencer.subByteFrequency(start, n);
                if(freq != 0) {
                    // update "value" if it is needed.
                    double value1 = prefixEstimation[start]+iq(freq);
                    if(value>value1) value = value1;
                } else {
                    // here freq ==0. This means iq(freq) is infinite.
                    // freq is monotonically descreasing in this loop.
                    // Now the current "value" is the minimum.
                    break;
                }
            }
            prefixEstimation[n]=value;
        }
        return prefixEstimation[myTarget.length];
        
 
    }
    
    public static void main(String[] args) {
        InformationEstimator myObject;
        double value;
        myObject = new InformationEstimator();
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
}
