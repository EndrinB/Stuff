import java.io.*;
import java.util.*;

public class Message{
   File messages = new File("messages.txt");
   MessageHandler msg;
   Scanner scan;
   int key;
   FileWriter writer;
   
   Message(int key,boolean created){
      this.key = key;
      
      
      if(created){}
      else{
         try{
            writer = new FileWriter(messages,true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(key + ",\n");
            
            pw.flush();
            pw.close();         
         }
         catch(Exception e){}
      }
   }
   public List<String> getMessages(){
      List<String> msg = new ArrayList<>();
      try{
         scan = new Scanner(messages);
         while(scan.hasNextLine()){
            String s = scan.nextLine()+"";
            String k = s.charAt(0)+"";
            if(key == Integer.parseInt(k)){
               s = s.substring(2);
               String[] msgs = s.split(",");
               int count = 0;
               while(count < msgs.length){
                  msg.add(msgs[count]);
                  count++;
               }            
            }
         
         }
         
      }catch(Exception e){}
      scan.close();
      return msg;
   }
   public String addMessage(String message){               
      List<String> lines = new ArrayList<>();
      try{ 
         scan = new Scanner(messages);
         while(scan.hasNextLine()){
            String line = scan.nextLine();
            String k = line.charAt(0)+"";
            if(Integer.parseInt(k) == key){
               lines.add(line + message + ",");
            }else{
               lines.add(line);
            }
         }
         writer = new FileWriter(messages,false);
         BufferedWriter bw = new BufferedWriter(writer);
         PrintWriter pw = new PrintWriter(bw);
         int count = 0;
         while(count < lines.size()){
            pw.println(lines.get(count));
            count++;
         }
         pw.flush();
         pw.close();
         scan.close();
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
