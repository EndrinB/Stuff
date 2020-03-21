import java.io.*;
import java.util.*;

public class MessageHandler{
   File messages = new File("messages.txt");
   private LinkedList<Integer> userKeys = new LinkedList();
  
   MessageHandler(){
   try{
    Scanner scan = new Scanner(messages);
    scan.useDelimiter("[, \n]");
    while(scan.hasNextLine()){
      userKeys.add(Integer.parseInt(scan.next()));
      scan.nextLine();
    }
    scan.close();
    
   }catch(Exception e){}
   }
   
   public boolean containsMsgKey(int k){
      return (userKeys.get(k-1) == k);
   }
}