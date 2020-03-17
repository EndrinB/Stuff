import java.util.*;

public class User{
   private String name;
   private String password;
   private int key;
   private List<String> messages = new ArrayList<>();
   
   User(String name,String password,int key){
      this.name = name;
      this.password = password;
      this.key = key;
   }
   
   public void addMessage(String message){
      messages.add(message);
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
   
