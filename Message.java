import java.io.*;
import java.util.*;

public class Message{
   File messages = new File("messages.txt");
   Scanner scan;
   int key;
   Message(int key){
      this.key = key;
   }
   public List<String> getMessages(){
      List<String> msg = new ArrayList<>();
      try{
         scan = new Scanner(messages);
         while(scan.hasNextLine()){
            if(key == Integer.parseInt(scan.next())){
               while(!scan.hasNextInt()){
                  msg.add(scan.next());
               }
            }else{
               scan.nextLine();
            }
            
         }
      }catch(Exception e){}
      if(msg.size() == 0){System.out.println("Either no messages for the user or key is invalid");}
      
      return msg;
   }
}