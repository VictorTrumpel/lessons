package rmq;

public class CalcMinValue implements IFunctor {
  public int calculate(int[] segment) {
    if (segment.length == 1) return segment[0];

    int segmentLength = segment.length;

    int leftSegmentLength = (int) Math.floor(segmentLength / 2);
    int rightSegmentLength = segmentLength - (int) Math.floor(segmentLength / 2);

    int[] leftSegment = new int[leftSegmentLength];
    int[] rightSegment = new int[rightSegmentLength];

    for (int i = 0; i < segmentLength; i++) {
      if (i < leftSegmentLength) {
        leftSegment[i] = segment[i];
        continue;
      }

      int rightIdx = i - leftSegmentLength;

      rightSegment[rightIdx] = segment[i];
    }

    int leftRes = calculate(leftSegment);
    int rightRes = calculate(rightSegment);

    return leftRes + rightRes;
  }
}
