class aBST {
  public Integer Tree[];

  public ABST(int depth) {
    int tree_size = 0;

    int factor = 1;
    for (int i = 0; i < depth; i++) {
      tree_size += factor;
      factor *= 2;
    }

    Tree = new Integer[tree_size];
    for (int i = 0; i < tree_size; i++)
      Tree[i] = null;
  }

  public Integer FindKeyIndex(int key) {
    return FindKeyIndex(key, 0);
  }

  Integer FindKeyIndex(int key, int currentIdx) {
    if (currentIdx > Tree.length)
      return null;

    Integer treeKey = Tree[currentIdx];

    if (treeKey == null)
      return currentIdx * (-1);

    if (treeKey == key)
      return currentIdx;

    int newIdx;

    if (key < treeKey)
      newIdx = currentIdx * 2 + 1;
    else
      newIdx = currentIdx * 2 + 2;

    return FindKeyIndex(key, newIdx);
  }

  public int AddKey(int key) {
    if (Tree[0] == null) {
      Tree[0] = key;
      return 0;
    }

    Integer keyIdx = FindKeyIndex(key);

    if (keyIdx == null)
      return -1;

    Integer absKeyIdx = Math.abs(keyIdx);

    Tree[absKeyIdx] = key;

    return absKeyIdx;
  }

}
