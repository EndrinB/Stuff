import java.io.*;
import java.util.*;

public class Message{
   File messages = new File("messages.txt");
   Scanner scan;
   int key;
   FileWriter writer;
   
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
            scan.close();
            
         }
      }catch(Exception e){}
      return msg;
   }
   public String addMessage(String message){
               
      try{
         scan = new Scanner(messages);
         while(scan.hasNextLine()){
            if(key == Integer.parseInt(scan.next())){
               writer = new FileWriter(messages,true);
               writer.write(" " + message + " ");
            
               writer.flush();
               writer.close();
            }
            else{
               scan.nextLine();
            }
         }
      }catch(Exception e){}
      return message;
   }
   public String removeMessage(int num){
          String message = "";     
      try{
         scan = new Scanner(messages);
         while(scan.hasNextLine()){
            if(key == Integer.parseInt(scan.next())){
               while(num >1){
                  if(scan.hasNextInt()){System.out.println("you dont have that many messages");}
                  else{scan.next(); num--;}
               }
               message = scan.next();
            }
            else{
               scan.nextLine();
            }
         }
      }catch(Exception e){}
      return message;
   }
}
