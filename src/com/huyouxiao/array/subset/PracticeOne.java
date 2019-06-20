package com.huyouxiao.array.subset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PracticeOne {

  private SubArray current;

  private List<SubArray> subArrays = new ArrayList<>();


  public static final int INPUT_SIZE = Integer.MAX_VALUE/100;

  public BigDecimal calculate(Integer target[]) {

    for(int i=0; i<target.length; i++) {
      // if current == null, new a sub array.
      if(current == null) {
        current = new SubArray();
      }
      // record start index
      if(null == current.startIndex) {
        //skip zero position.
        if(target[i] == 0) {
          continue;
        } else {
          // use no zero as first element
          current.startIndex = i;
        }

        // record negative element index.
        if(target[i]<0) {
          current.negativeIndex.add(i);
        }
      } else if(null == current.endIndex) {
        // meet zero, use previous as endIndex
        if(target[i] == 0) {
          current.endIndex = i-1;

          // add into sub arrays
          subArrays.add(this.clone(current));

          // clean current
          current = null;
        } else if(target[i]<0) { // record negative element index
          current.negativeIndex.add(i);
        }
      }
    }
    if(null != current && current.endIndex == null) {
      current.endIndex = target.length -1;
      subArrays.add(current);
    }

    BigDecimal[] results = new BigDecimal[subArrays.size()];
    for(int i=0; i<subArrays.size(); i++) {
      results[i] = subArrays.get(i).getCount(target);
      //printArray(target, subArrays.get(i).startIndex, subArrays.get(i).endIndex);
    }

    BigDecimal maxResult = BigDecimal.valueOf(0);
    int maxIndex = -1;
    for(int i=0; i<results.length; i++) {
      if(maxResult.compareTo(results[i])<0) {
        maxResult = results[i];
        maxIndex  =  i;
      }
    }

    //print max sub array.
    if(maxIndex>=0) {
      SubArray max = subArrays.get(maxIndex);
      System.out.print("find result  [");
      for(int i = max.startIndex; i<=max.endIndex; i++) {
        System.out.print(String.valueOf(target[i]));
        if(i == max.endIndex) {
          System.out.println("]");
        } else {
          System.out.print(", ");
        }
      }
    }
    return maxResult;
  }

  private SubArray clone(SubArray value) {
    SubArray subArray = new SubArray();
    subArray.startIndex = value.startIndex;
    subArray.endIndex = value.endIndex;
    subArray.negativeIndex = new ArrayList<>();
    for(Integer index : value.negativeIndex) {
      subArray.negativeIndex.add(index.intValue());
    }
    return subArray;
  }


  public static Random random = new Random(47);

  public static void main(String args[]) {
    Integer[] target = new Integer[INPUT_SIZE];
    for(int i = 0; i< INPUT_SIZE; i++) {
      if(random.nextInt(10)%4 == 0) {
        target[i]  = random.nextInt(100) * -1;
      } else {
        target[i] = random.nextInt(100);
      }

    }



    PracticeOne a = new PracticeOne();
    a.printArray(target, 0, target.length-1);
    BigDecimal result = a.calculate(target);
    System.out.println("result = "+result.toPlainString());
  }


  public void printArray(Integer[] array, int star, int end) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("print array: [");
    for(int i = star; i<= end; i++) {
      stringBuffer.append(i);
      if(i==end) {
        stringBuffer.append("]");
      } else {
        stringBuffer.append(", ");
      }
    }
    System.out.println(stringBuffer.toString());
  }
}
