package ao.gov.minfin.critografia.criptografia_cliente_servidor.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Scanner;

import ao.gov.minfin.critografia.criptografia_cliente_servidor.config.Conexao;

public class Server {
	
	Socket socketClient;
	ServerSocket serverSocket;
	
	public boolean connect() {
		
		try {
			socketClient = serverSocket.accept(); // fase de conexão
			return true;
			
		}catch(Exception e) {
			System.err.println("Não fex a conexão: "+ e.getMessage());
			return true;			
		}
	}
	
	public static void main(String[] args) throws Exception {
		Server servidor= new Server();
		servidor.rodarServidor();
		
	}
	
	public void rodarServidor() throws Exception {
		String textoRecebido ="";
		String textoEnviado  ="Olá, Cliente";
		String textoDecifrado;
		String textoCifrado;
		
		Scanner input = new Scanner(System.in);
		
		serverSocket = new ServerSocket(9600);
		System.out.println("Servidor iniciado!");
		
		while(true) {
			if(connect()) {
				//Gerar chave pública e private
				System.out.println("Gerando chave RSA...");
				
				KeyPair chaves = CriptografiaClienteServidor.gerarChavesPublicasPrivadas();
				
				//Enviar chave pública para o cliente
				System.out.println("Enviando chave pública...");
				Conexao.enviarChave(socketClient, chaves.getPublic());
				
				//Receber chave pública do cliente
				System.out.println("Recebendo chave pública do cliente...");
				PublicKey chavePublica = Conexao.receberChave(socketClient);
				
				
				textoRecebido = Conexao.receber(socketClient);
				System.out.println("\nMensagem recebida: "+ textoRecebido);
				
				// Decifrar texto recebido do cliente
				textoDecifrado = CriptografiaClienteServidor.decifrar(textoRecebido, chaves.getPrivate());
				System.out.println("Cliente enviou: "+ textoDecifrado);
				
				System.out.println("\nDigite a sua mensagem: "); //fase de dados
				textoEnviado = input.nextLine();
				
				//O texto é decifrado usando a chave públçica recebida do cliente
				textoCifrado = CriptografiaClienteServidor.cifrar(textoEnviado, chavePublica);
				System.out.println("Texto enviado: "+ textoCifrado);
				
				Conexao.enviar(socketClient, textoCifrado);
				socketClient.close();
				
				
			}
		}
	}

}


