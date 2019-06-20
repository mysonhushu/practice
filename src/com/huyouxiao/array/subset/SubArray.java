package com.huyouxiao.array.subset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SubArray {
  public Integer startIndex;
  public Integer endIndex;
  public List<Integer> negativeIndex = new ArrayList<>();

  public BigDecimal getCount(Integer[] array) {
    int size = negativeIndex.size();
    if(size % 2 == 0) {
      return this.getMultiplyResult(array, startIndex, endIndex);
    } else if(size > 1) {
      // remove last negative
      BigDecimal first =  this.getMultiplyResult(array, startIndex, negativeIndex.get(0));
      BigDecimal last  =  this.getMultiplyResult(array, negativeIndex.get(negativeIndex.size()-1), endIndex);

      if(first.compareTo(last)>0)  {
        endIndex = negativeIndex.get(negativeIndex.size()-1)-1;
        return this.getMultiplyResult(array, startIndex, endIndex);
      } else { // remove first negative
        startIndex = negativeIndex.get(0)+1;
        return this.getMultiplyResult(array, startIndex, endIndex);
      }
    } else {
      BigDecimal previous = this.getMultiplyResult(array, startIndex, negativeIndex.get(0)).abs();
      BigDecimal  last = this.getMultiplyResult(array, negativeIndex.get(0), endIndex).abs();
      // return previous > last ? previous : last;
      if(previous.compareTo(last)>0) {
        endIndex = negativeIndex.get(0)-1;
      } else {
        startIndex = negativeIndex.get(0)+1;
      }
      return this.getMultiplyResult(array, startIndex, endIndex);
    }
  }


  private BigDecimal getMultiplyResult(Integer[] array, int star, int end) {
    BigDecimal result = BigDecimal.valueOf(1);
    for(int i = star; i<= end; i++) {
      result = result.multiply(BigDecimal.valueOf(array[i]));
    }
    return result;
  }
}
