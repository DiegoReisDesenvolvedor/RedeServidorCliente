//DIEGO OLIVEIRA DOS REIS
package local.redes.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {
    
    private Socket conexao;
    private DataInputStream entrada;
    private DataOutputStream saida;
    
    public Cliente(String ip4, int porta){
        try {
            conexao = new Socket(ip4, porta);
            System.out.println("CLIENTE - SERVIDOR CONECTADO");
        } catch (IOException ex) {
            System.out.println("FALHA NA CONEXAO CLIENTE - SERVIDOR");
        }
    }
    
        
    public static void main(String[] args) {
        
        ValidadarEntradaCPF validadarEntradaCPF = new ValidadarEntradaCPF();
        Scanner scanner = new Scanner(System.in);
        
        
        
        try {
            Cliente novoCliente = new Cliente("127.0.0.1",50000);
            novoCliente.saida = new DataOutputStream(novoCliente.conexao.getOutputStream());
            String numeroCpf;
                        
            do {                
                System.out.println("DIGITE O NUMERO DO CPF");
                numeroCpf = scanner.nextLine();
                try {
                    validadarEntradaCPF.entradaValida(numeroCpf);
                    break;
                } catch (EntradaInvalidaException ex) {
                System.out.println("Erro: "+ex.getMessage());
                }
            }while(true);
                       
            
            novoCliente.saida.writeUTF(numeroCpf);
            
            novoCliente.entrada = new DataInputStream(novoCliente.conexao.getInputStream());
            String retornoServidor = novoCliente.entrada.readUTF();
            
            System.out.println("RESPOSTA DO SERVIDOR E: "+retornoServidor);
            novoCliente.conexao.close();
            
            
        } catch (Exception e) {
            System.out.println("FALHA NA CONEXAO CLIENTE - SERVIDOR");
        }
        
        
        
    }
    
}
