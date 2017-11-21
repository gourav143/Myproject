
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaurav ambildhuke
 */
public class server extends JFrame {
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;
    private ServerSocket server;
    
    public server(){
        super("gaurav's Instant Messenger");
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
            sendMessage(event.getActionCommand());
            userText.setText("");
        }
    });
    add(userText,BorderLayout.NORTH);
    chatWindow = new JTextArea();
    add(new JScrollPane(chatWindow));
    setSize(500,400);
    setVisible(true);
}
    //set up and run server
    public void startRunning(){
        try{
            server = new ServerSocket(3306,100);
            while(true){
                try{
                    waitForConnection();
                    setupStream();
                    whileChatting();
                }catch(EOFException e){
                    showMessage("\n server ended connection");
                    
                }
                finally{
                    closeCrap();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void waitForConnection() throws IOException{
        showMessage("waiting for someone to connect...\n");
        connection = server.accept();
        showMessage("now connected to"+connection.getInetAddress().getHostName());//returns the ip address in the form of string
        
    }
    public void setupStream() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input =new ObjectInputStream(connection.getInputStream());
        showMessage("\nStreams are setup!");
    }
    public void whileChatting() throws IOException{
        String message="you are online";
        sendMessage(message);
        ableToType(true);
        do{
            try{
                message  = (String)input.readObject();
                showMessage("\n"+message);
                
            }catch(ClassNotFoundException e){
                showMessage("\n message not found");
            }
        }while(!message.equals("CLIENT - END"));
    } 
    //close streams and socket after you done chatting
    public void closeCrap() {
        showMessage("\n Closing connections");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //send a message to client
    private void sendMessage(String message){
        try{
            output.writeObject("SERVER - "+message);
            output.flush();
            showMessage("\nSERVER - "+message);
        }
            catch(IOException e){
                    chatWindow.append("\nCANT SEND MESSAGE !!!");
                    
            }
        }
    //update the chat window
    private void showMessage(final String text){
        swingUtilities.invokeLater(  //update the part ofyour screen
        new Runnable(){
            public void run(){
                chatWindow.append(text);
            }
        }
        );
     }
    private void ableToType(final boolean set){
        swingUtilities.invokeLater( 
        new Runnable(){
            public void run(){
                userText.setEditable(true);
            }
        }
        );
    }
 
}