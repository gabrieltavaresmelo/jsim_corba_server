package br.com.gtmf.controller;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManager;

public class ServerCreator implements Runnable {

	private boolean isConnected = false;
	
	private ORB orb;
	
	Thread thread = new Thread(this);
	
	public ServerCreator(String [] args) {
		try {
			// Inicializando o ORB
			orb = ORB.init(args, null);

			// Obtendo uma referencia ao POA
			org.omg.CORBA.Object obj = orb
					.resolve_initial_references("RootPOA");
			POA rootpoa = POAHelper.narrow(obj);
			
			// Obtendo uma referencia ao POA manager
			POAManager manager = rootpoa.the_POAManager();
			
			// Ativando o manager
			manager.activate();

			// Obtendo o NameService
			obj = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = org.omg.CosNaming.NamingContextExtHelper
					.narrow(obj);

			// Instanciando o servidor
			SimCorbaServer cs = new SimCorbaServer();
			
			// Conectando com o servidor com o ORB
			sim.SimCorbaServer chatserver = cs._this(orb);
			
			// Criando uma referencia ao NameService
			ncRef.rebind(ncRef.to_name("sim_corba"), chatserver);

			System.out.println("Servidor Criado");
			isConnected = true;
			
			
			thread.start();	

		} catch (Exception e) {
			e.printStackTrace(System.out);
//			System.err.println(e.getMessage());
			isConnected = false;
		}
	}
	
	public void run() {
		try {
			// iniciando o orb
			orb.run();
			
		} catch (Exception e) {
		}
	}
	
	public boolean isConnected() {
		return isConnected;
	}

	public void close() {
		orb.destroy();
		System.out.println("Servidor Destruido");
	}
}