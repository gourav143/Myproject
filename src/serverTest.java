/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaurav ambildhuke
 */
import javax.swing.JFrame;
public class serverTest {
     public static void main(String[] args){
         server s = new server();
         s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         s.startRunning();
     }
}
