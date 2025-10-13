package demo_backend_worker.config;

import com.rabbitmq.client.ConnectionFactory;

public class ConnectionRabbitMQ {
	public void getConnection() {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");  // Host do RabbitMQ
	    factory.setUsername("guest");  // Usuário
	    factory.setPassword("guest");  // Senha
	}
}
