
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
// class server
public class Server {
    private Socket so;
    private ServerSocket sc;
    private ServerThread st;
    
    public Server(){
        
        try {
            sc=new ServerSocket(3000);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void AcceptConnexion(){
        try {
            so=sc.accept();
            System.out.println("Client Accepter");
            st=new ServerThread(so);
            st.start();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public static void main(String[] args) {
		Server s=new Server();
		while(true){
		System.out.println("allezzzzzzzzzzzzzzzz  allezzzzzzzzzzzzzzzzz");
		s.AcceptConnexion();
               System.out.println("fin");

	}
    
}}


