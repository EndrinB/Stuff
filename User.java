import java.util.*;

public class User{
   private String name;
   private String password;
   private int key;
   Message msg ;
   private List<String> messages;
   
   User(String name,String password,int key){
      this.name = name;
      this.password = password;
      this.key = key;
      msg = new Message(key);
      messages = msg.getMessages();
      
   }
   
   public void addMessage(String message){
      messages.add(msg.addMessage(message));
   }
   
   public void removeMessage(int msgNum){
      messages.remove(msgNum);
   }
   
   public String getMessage(int msgNum){
      return messages.get(msgNum);
   }
   
   public int getMsgCount(){
      return messages.size();
   }
   public String getName(){
      return name;
   }
   
   String getPassword(){
      return password;
   }
   
   public int getUserKey(){
    return key;
   }
}
   
