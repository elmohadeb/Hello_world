package server;
import DAOImp.CompteDAOImp;
import DAOImp.RtraitDAOImp;
import bank.metier.Compte;
import bank.metier.Retrait;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ServerThread extends Thread {
    
        private Socket sc;
	private ObjectOutputStream write;
	private ObjectInputStream read;
        private Compte compte;
        private CompteDAOImp com=new CompteDAOImp();
        private RtraitDAOImp rt=new RtraitDAOImp();
        
        public ServerThread(Socket t) {
		// TODO Auto-generated constructor stub
		sc=t;
		try {
			write=new ObjectOutputStream(sc.getOutputStream());
                        read=new ObjectInputStream(sc.getInputStream());
                } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void run() {
        
        
           
            try {
            System.out.println("code1");

                String code =(String) read.readObject();
                               System.out.println("code2");

                Compte c=com.findByRip(code);
                if(c==null){
                     write.writeBoolean(false);
                     write.flush();
                }else{
                    write.writeBoolean(true);
                    write.flush();
                 //  write.writeObject(c);
                 //  write.flush();
                    Double montant=read.readDouble();
                    rt.create(new Retrait(c,montant));
                    c.setSolde(c.getSolde()-montant);
                    com.update(c);
                    write.writeObject("votre retrait est fait");
                    write.flush();
                   
                }
                
                
             
                
                
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
                
          
        
        
    }
        
        
        
        
        
        
}
