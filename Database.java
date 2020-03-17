import java.util.*;
import java.io.*;

public class Database{
   HashMap<Integer,User> database = new HashMap<Integer,User>();
   static File server = new File("database.txt"); 
   FileWriter writer;
   int key;
   Database()
   {
      key = 1;
      try{
         Scanner scan = new Scanner(server);
         while(scan.hasNextLine()){
            database.put(key,new User(scan.next(),scan.next(),key));
            key++;
         }
      }catch(Exception e){}  
   }
   public User getUser(int key){
      User user = null;
      if(database.containsKey(key)){ 
         user = database.get(key);
      }else{
         System.out.println("No user identified with the current key");
      }
      return user;
   }
   public void addUser(String name,String password){
    
      try{
         writer = new FileWriter(server,true);
         writer.write(name + " " + password +"\n");
         
         writer.flush();
         writer.close();
         
         writer = new FileWriter("messages.txt",true);
         writer.write(key + " ");
      }catch(Exception e){}
   }
}

