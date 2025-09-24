//DIEGO OLIVEIRA DOS REIS
package local.redes.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor {
    
    private ServerSocket servidor;
    private Socket conexao;
    private DataInputStream entrada;
    private DataOutputStream saida;
    
    public Servidor(int porta){
        try {
            servidor = new ServerSocket(porta);
            System.out.println("SERVIDOR INICIADO NA PORTA "+porta);
            
        } catch (IOException ex) {
            System.out.println("ERRO AO INICIAR O SERVIDOR"+ex.getMessage());
        }
    }

    public static void main(String[] args) {
       
        Servidor novaConexao = new Servidor(50000);
        ServidorValidarCPF servidorValidarCPF = new ServidorValidarCPF();
        
        try {
            novaConexao.conexao=novaConexao.servidor.accept();
            
            novaConexao.entrada = new DataInputStream(novaConexao.conexao.getInputStream());
            String entradaCpf = novaConexao.entrada.readUTF();
            
                        
            novaConexao.saida = new DataOutputStream(novaConexao.conexao.getOutputStream());
            novaConexao.saida.writeUTF(servidorValidarCPF.validarCPF(entradaCpf));
            novaConexao.servidor.close();
            
            
        } catch (Exception e) {
            System.out.println("ERRO DE EXECUCAO DO SERVIDOR");
        }
        System.out.println("PORTA DO SERVIDOR "+novaConexao.servidor.getLocalPort()+" FINALIZADO");
    }
}
