package org.fisco.bcos.asset.client;

import java.util.*;

import java.io.*;


public class CLI{

    private Map<String, String> map;
    private Scanner scanner;
    private boolean status;
    private String current;
    private String path;

    public CLI(){
        path = "test.txt";
        status = true;
        scanner = new Scanner(System.in);
        map = new HashMap<String, String>();
        read_file();
    }

    public boolean getStatus(){
        return this.status;
    }

    public String getCurrent(){
        return this.current;
    }

    public void setCurrentNull(){
        this.current = null;
    }

    public Map<String, String> getMap(){
        return this.map;
    }

    public void read_file(){
        try{
            FileReader fd = new FileReader(path);
            BufferedReader br = new BufferedReader(fd);
            String s1 = null;
            while((s1 = br.readLine()) != null) {
                String[] temp = s1.split("  ");
                map.put(temp[0],temp[1]);
            }
           br.close();
           fd.close();
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

	public void write_file()
	{
		try{
            File file = new File(path);
            FileWriter fw = new FileWriter(file,false);
            for (String key : map.keySet()) {
                String temp = key+"  "+map.get(key);
                fw.write(temp+"\n");
            }
            fw.flush();
            fw.close();    

        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
	}

    public boolean login()
    {
        int choice;
        String acc, pass, again;
        Console console = System.console();
        System.out.println("------Welecome to the FISCO-BCOS Project by Zq.------\n");
        System.out.println("Plz enter:\n1:LOG IN\t2:REGISTER\t0:quit()");
        if (scanner.hasNextInt()){
            choice = scanner.nextInt();
            switch(choice){
                case 0:
                    this.status = false;
                    return false;

                case 1:
                    acc = (String)scanner.nextLine();
                    System.out.print("------LOGIN------\nID: ");
                    acc = (String)scanner.nextLine();
                    System.out.print("Password:");
                    pass = new String(console.readPassword());
                    if(map.get(acc)!=null && map.get(acc).compareTo(pass) == 0) {
                        current = acc;
                        System.out.print("Log in success! Wait for key...");
                        again = (String)scanner.nextLine();
                        return true;
                    } else {
                        System.out.print("No account or wrong password! Wait for key...");
                        again = (String)scanner.nextLine();
                        return false;
                    } 

                case 2:
                    acc = (String)scanner.nextLine();
                    System.out.print("------REGISTER------\n ID: ");
                    acc = (String)scanner.nextLine();
                    System.out.print("Password:");
                    pass = new String(console.readPassword());
                    System.out.print("Reinput:");
                    again = new String(console.readPassword());
                    if(pass.compareTo(again)==0 && map.get(acc)==null){
                        map.put(acc,pass);
                        write_file();
                        read_file();
                        System.out.print("Register success! Wait for key...");
                        again = (String)scanner.nextLine();
                        return false;
                    } else {
                        System.out.print("Register failed! Wait for key...");
                        again = (String)scanner.nextLine();
                        return false;
                    }

                default:
                    System.out.print("Invalid input! Wait for key...");
                    again = (String)scanner.nextLine();
                    return false;
            }
        }
        else {
            System.out.print("Invalid input! Wait for key...");
            return false;
        }
    }

    public void clear(){
        for (int i = 0; i < 20; ++i) System.out.print("\n");
    }

    public void msg(){
        System.out.print("Dear "+current+", what do u want to do next?\n");
        System.out.println("1: 查询本人信用额度.\n2: 与其他用户进行交易.\n3: 融资/向银行贷款.\n4: 欠条拆分\n5: 转账/还贷.\n6: 查询交易.\n0: 退出登录\n\n");

    }

}