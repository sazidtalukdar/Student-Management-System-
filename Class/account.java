package Class;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class account extends JFrame {
    private String user;
    private String pass;
    private String name;
    private String phone;
    private String dob;
    private String q1;
    private String q2;
    ///public String tname;
	File file,file1;
	FileWriter fwrite,write;
	
	Scanner sc,ss;
    BufferedReader reader;
    BufferedWriter writer;

    public account(String user, String pass, String name, String phone, String dob, String q1, String q2) {
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.q1 = q1;
        this.q2 = q2;
    }

    public account() {
	}


    public String getuser(){
        return user;

    }
    public String getpass(){
        return pass;

    }
    public String getname(){
        return name;

    }
    public String getphone(){
        return phone;

    }
    public String getdob(){
        return dob;

    }
    public String getq1(){
        return q1;

    }
    public String getq2(){
        return q2;

    }


    public void add(){
        try{
            boolean flag= false;
           file1 = new File("./Data/reg.txt");
           file1.createNewFile();
           ss= new Scanner(file1);
           while(ss.hasNextLine()){
            String line =ss.nextLine();
            String value[] =line.split("\t");
            if (value[0].equals(user)&&value[1].equals(pass)) {
                flag=true;
            }
           }
           ss.close();

			if (flag) {
				JOptionPane.showMessageDialog(this, "User Already exits");
			}else{
				write=new FileWriter(file1,true);
			write.write(getuser()+"\t"+getpass()+"\t"+getname()+"\t"+getphone()+"\t"+getdob()+"\t"+getq1()+"\t"+getq2()+"\n");
			
			write.flush();
			write.close();
			}

        }
        catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
    }


    public String checkAccount(String username,String userpass)
	{
		String  flag="sir nai";
        
		try
		{
			
			file= new File("./Data/reg.txt");
			sc=new Scanner(file);
			
			while(sc.hasNextLine())
			{
				String line=sc.nextLine();
				String[] value=line.split("\t");
				if(value[0].equals(username)&&value[1].equals(userpass))
				{
					flag="sir ace";
                   // tname=value[2];
                    break;
				}
			}
			
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return flag;
}
    public void updatepass(String user, String pass,String q1, String q2) {
        File file = new File("./Data/reg.txt");
        File tempFile = new File("./Data/temp.txt");

        try {
            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean userFound = false;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\t");

                if (values.length >= 6 && values[0].equals(user) && values[5].equals(q1)&&values[6].equals(q2)) {
                    userFound = true;
                    values[1] = pass;
                    line = String.join("\t", values);

                    writer.write(line);

                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (userFound) {
                if (!file.delete()) {
                    JOptionPane.showMessageDialog(null, "Unable to delete the original file");
                    return;
                }

                if (!tempFile.renameTo(file)) {
                    JOptionPane.showMessageDialog(null, "Unable to rename the temporary file");
                    return;
                }

                JOptionPane.showMessageDialog(null, "User password updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "User not found or security questions incorrect.");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
