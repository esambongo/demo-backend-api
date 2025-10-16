package ao.gov.minfin.critografia.criptografia_cliente_servidor.model;

import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

import ao.gov.minfin.critografia.criptografia_cliente_servidor.config.Conexao;

public class Client {
	Socket socket;
	
	public void comunicarComServidor() throws Exception {
		String textoRequisicao ="Conexao estabelecinda";
		String textoRecebido   = "";
		String textoDecifrado  ="";
		String textoCifrado    ="";
		
		socket = new Socket("localhost",9600);
		
		System.out.println("Gerando chave RSA...");
		
		KeyPair chaves = CriptografiaClienteServidor.gerarChavesPublicasPrivadas();
		
		Scanner input =new Scanner(System.in);
		System.out.println("Recebendo chave públic do servidor...");
		PublicKey chavePublic = Conexao.receberChave(socket);
		
		//enviando chave pública para o servidor
		System.out.println("Enviando chave públic...");
		
		
		Conexao.enviarChave(socket, chavePublic);
		
		
		System.out.println("\nDigite a sua mensagem: ");
		textoRequisicao=input.nextLine();
		
		textoCifrado = CriptografiaClienteServidor.cifrar(textoRequisicao, chavePublic);
		
		
		//enviar mensagem para o servidor
		 System.out.println("Mensagem cifrada: "+ textoCifrado);
		 Conexao.enviar(socket, textoCifrado);
		 
		 //Receber mensagem do servidor
		 textoRecebido = Conexao.receber(socket);
		 System.out.println("\nServidor enviou: "+ textoRecebido);
		 
		 // Decifrar texto do servidor
		 
		 
		 System.err.println("Tentando decifrar a mensagem no servidor...");
		 textoDecifrado = CriptografiaClienteServidor.decifrar(textoRecebido,chaves.getPrivate());
		 
		 
		 
		 System.out.println("Texto decifrado: "+ textoDecifrado);
		 
		 
	}
	
	public static void main(String[] args){
		
		try {
			System.out.println("COMEÇO DA EXECUÇÃO DO MAIN");
			Client cliente = new Client();
			cliente.comunicarComServidor();
			
			System.out.println("FINALIZAÇÃO DA EXECUÇÃO...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
