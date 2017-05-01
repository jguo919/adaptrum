import java.util.*;
import java.io.*;

/**
 * Assume: input is a rectangular image, with same number of elements in each column and each row.
 */
public class asciiArt {

  private class linkedNode {
    char value;
    linkedNode next;

    private linkedNode(char value) {
      this.value = value;
      this.next = null;
    }
  }

  private class linkedList {
    linkedNode head;
    linkedNode last;

    private linkedList(linkedNode n) {
      this.head = n;
      this.last = head;
    }

    private void append(linkedNode n) {
      this.last.next = n;
      this.last = n;
    }
  }

  public int colNum;
  public int rowNum;
  public ArrayList<linkedList> array;

  public asciiArt(String f) {
    array = new ArrayList<>();
    File file = new File(f);
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      String text;
      while ((text = reader.readLine()) != null) {
        linkedNode head = new linkedNode(text.charAt(0));
        linkedList oneRow = new linkedList(head);
        for (int i = 1; i < text.length(); i++) {
          linkedNode node = new linkedNode(text.charAt(i));
          oneRow.append(node);
        }
        this.colNum = text.length();
        array.add(oneRow);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      }
    }

    this.rowNum = array.size();
  }

  public void colInsert(char c, int index) {
    for (int i = 0; i < array.size(); i++) {
      linkedList oneRow = array.get(i);
      linkedNode node = oneRow.head;
      int counter = 0;
      while (node.next != null) {
        if (counter == index - 1) {
          linkedNode newNode = new linkedNode(c);
          newNode.next = node.next;
          node.next = newNode;
          break;
        } else {
          counter++;
          node = node.next;
        }
      }
    }
  }

  public void rowInsert(char c, int index) {
    linkedNode head = new linkedNode(c);
    linkedList newRow = new linkedList(head);
    for (int i = 1; i < this.colNum; i++) {
      linkedNode n = new linkedNode(c);
      newRow.append(n);
    }
    array.add(index, newRow);
  }

  public String toString() {
    String s = "";
    for (int i = 0; i < array.size(); i++) {
      String sub = "";
      linkedList oneRow = array.get(i);
      linkedNode node = oneRow.head;
      while (node != null) {
        s += node.value;
        sub += node.value;
        node = node.next;
      }
      System.out.println(sub);
    }
    return s;
  }

  public static void main(String[] args) {
    asciiArt trail = new asciiArt("input.txt");
    trail.colInsert('|', 5);
    trail.toString();

  }
}