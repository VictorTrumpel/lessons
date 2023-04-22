package recursion;

import java.util.*;

public class GenerateParenthess {
  void generate(int openParent, int closeParent, String currCombo, List<String> list) {
    if (openParent == 0 && closeParent == 0) {
      list.add(currCombo);
    }

    if (openParent > 0) {
      generate(openParent - 1, closeParent + 1, currCombo + "(", list);
    }

    if (closeParent > 0) {
      generate(openParent, closeParent - 1, currCombo + ")", list);
    }
  }

  public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<String>();
    generate(n, 0, "", list);
    return list;
  }
}