package recursion;

import java.util.*;

import java.io.File;

public class ReadDir {

  List<String> findFiles(String path, List<String> filesList) {
    File dir = new File(path);

    File[] filesInDir = dir.listFiles();

    for (File file : filesInDir) {
      if (file.isDirectory()) {
        findFiles(path + '/' + file.getName(), filesList);
      } else {
        filesList.add(file.getName());
      }
    }

    return filesList;
  }

  public List<String> findFiles(String path) {
    ArrayList<String> list = new ArrayList<String>();
    return findFiles(path, list);
  }

}
