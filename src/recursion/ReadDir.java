package recursion;

import java.util.*;

import java.io.File;

public class ReadDir {

  public List<String> findFiles(String path) {
    File dir = new File(path);

    File[] filesInDir = dir.listFiles();

    List<String> filesList = new ArrayList<String>();

    for (File file : filesInDir) {
      if (file.isDirectory()) {
        List<String> subFiles = findFiles(path + '/' + file.getName());
        filesList.addAll(subFiles);
      } else {
        filesList.add(file.getName());
      }
    }

    return filesList;
  }
}
