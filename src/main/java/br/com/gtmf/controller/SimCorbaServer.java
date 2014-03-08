package br.com.gtmf.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.geotools.referencing.GeodeticCalculator;

import sim.AnonymID;
import sim.SimCorbaClient;
import sim.UserExists;
import br.com.gtmf.model.Bundle;

public class SimCorbaServer extends sim.SimCorbaServerPOA{
	
	protected Map<String, CorbaClient> clients = new HashMap<String, CorbaClient>();
	protected List<String> nicks = new Vector<String>();

	/**
	 * Recebe a notificacao de que um novo usuario entrou no bate-papo
	 * e manda a lista com todos os clientes conectados para os usuarios
	 * 
	 */
	@Override
	public String login(String nickname, SimCorbaClient client) throws UserExists {
		
		try {
			if (nicks.contains(nickname)){
				throw new sim.UserExists();
			}
			
			nicks.add(nickname);
			System.out.println("ADD: " + nickname);
			
			CorbaClient newClient = new CorbaClient(nickname, client);
			clients.put(nickname, newClient);
			
			
//			Collection<CorbaClient> tmpUsersON = clients.values();
//			
//			for (CorbaClient to : tmpUsersON) {
//				if(!to.equals(newClient)){
//					Bundle bundle = new Bundle(Bundle.LIST_USERS_ON, toString(tmpUsersON));
//					String toSend = Bundle.toJson(bundle);
////					System.out.println(toSend);// DEBUG
//					
//					to.getClient().update(toSend);
//				}
//			}
			
			return nickname;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
	
	/**
	 * Recebe a notificacao de que um usuario saiu do bate-papo
	 * e manda a lista com todos os clientes conectados para os usuarios
	 * 
	 */
	@Override
	public void logout(String id) throws AnonymID {
		
		try {
			CorbaClient client = clients.remove(id);
			
			if (client == null){
				throw new sim.AnonymID();
			}
			
			nicks.remove(client.getNickname());
			System.out.println("REMOVED: " + id);
			
//			Collection<CorbaClient> tmpUsersON = clients.values();
//			
//			for (CorbaClient to : tmpUsersON) {
//				if(!to.equals(client)){
//					Bundle bundle = new Bundle(Bundle.LIST_USERS_ON, toString(tmpUsersON));
//					String toSend = Bundle.toJson(bundle);
////					System.out.println(toSend);// DEBUG
//					
//					to.getClient().update(toSend);
//				}
//			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	@Override
	public void startRadar(String nickname, String location) {
		try {
			CorbaClient corbaClient = clients.get(nickname);
			
			if(corbaClient != null){
				corbaClient.locationToLatLng(location);				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void stopRadar(String nickname) {
		try {
			CorbaClient corbaClient = clients.get(nickname);
			
			if(corbaClient != null){
				corbaClient.setLat(0.0);
				corbaClient.setLng(0.0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void write(String from, String text) throws AnonymID {
		try {
			CorbaClient fromCorbaClient = clients.get(from);
			
			if (fromCorbaClient == null){
				throw new sim.AnonymID();
			}
					
			for (CorbaClient to : clients.values()) {
//				System.out.println(text);// DEBUG				
				to.getClient().update(text);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	@Override
	public void writeTo(String from, String to, String message) throws AnonymID {
		try {
			CorbaClient fromCorbaClient = clients.get(from);		
			CorbaClient toCorbaClient = clients.get(to);
			
			if (fromCorbaClient == null){
				throw new sim.AnonymID();		
			} if (toCorbaClient == null){
				throw new sim.AnonymID();
			}
			
//			System.out.println(message);// DEBUG
			toCorbaClient.getClient().update(message);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	@Override
	public String get_id(String nickname) {
		for (Map.Entry<String, CorbaClient> entry : clients.entrySet()) {
			String key = entry.getKey();
			CorbaClient client = entry.getValue();

			if (client.getNickname().equals(nickname)) {
				return key;
			}
		}

		return "";
	}

	/**
	 * Quando o usuario mudar sua localizacao
	 * todos os outros usuarios serao avisados
	 * e farao uma nova requisicao para ver os 
	 * clientes proximos.
	 * 
	 * Este metodo notificara a todos os usuarios
	 * que houve mudanca.
	 */
	@Override
	public String changeLocation(String nickname, String location) {
		String toReturn = "";
		
		try {
			CorbaClient corbaClient = clients.get(nickname);
			
			if(corbaClient != null){
				// atualiza a localizacao
				corbaClient.locationToLatLng(location);
				
				// Obtem os clientes mais proximos
				Collection<CorbaClient> nearly = getNearlyClients(corbaClient);
				toReturn = Bundle.toJson(new Bundle(Bundle.LIST_USERS_ON, toString(nearly)));
				
				
				Collection<CorbaClient> tmpUsersON = clients.values();
				
				for (CorbaClient to : tmpUsersON) {
					String toSend = Bundle.toJson(new Bundle(Bundle.CHANGE_USER_LOCATION, nickname));
//						System.out.println("change: " + toSend); // DEBUG
					
					if(to.getClient() != null){
						to.getClient().update(toSend);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return toReturn;
	}

	@Override
	public String listNearlyClients(String nickname) {		
		try {
			CorbaClient me = clients.get(nickname);
			
			if(me != null){				
				Collection<CorbaClient> nearly = getNearlyClients(me);
		
				String json = toString(nearly);
				Bundle bundle = new Bundle(Bundle.LIST_USERS_ON, json);
				String toSend = Bundle.toJson(bundle);
//				System.out.println("listNearlyClients: " + toSend);// TODO DEBUG
				
				return toSend;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}

	/**
	 * 
	 * @param nickname
	 * @param me
	 * @return
	 * @throws Exception
	 */
	private Collection<CorbaClient> getNearlyClients(CorbaClient me) throws Exception {
		Collection<CorbaClient> tmpUsersON = clients.values();
		
		Collection<CorbaClient> nearlyClients = new ArrayList<CorbaClient>();
					
		for (CorbaClient other : tmpUsersON) {
//			System.out.println(other.getNickname() + " " + other.getLat() + " " + other.getLng());
			
			if(!other.equals(me) && other.getLat() != 0.0 && other.getLng() != 0.0){
				
				// calcula a distancia entre os usuarios
				int totalmeters = calcDistance(
						me.getLat(), me.getLng(),
						other.getLat(), other.getLng());
				
				boolean isNearly = totalmeters <= 20000;				
//				System.out.println(isNearly + " " + totalmeters);
				
				if(isNearly){
					nearlyClients.add(other);
				}
			}
		}
		
		return nearlyClients;
	}

	/**
	 * Calcula a distancia entre duas coordenadas
	 * utilizando uma biblioteca de mapas
	 * 
	 * @param latBegin
	 * @param lngBegin
	 * @param latEnd
	 * @param lngEnd
	 * @return
	 */
	private int calcDistance(double latBegin, double lngBegin, double latEnd, double lngEnd) {
		GeodeticCalculator gc = new GeodeticCalculator(); 
		gc.setStartingGeographicPoint(lngBegin, latBegin); 
		gc.setDestinationGeographicPoint(lngEnd, latEnd);
		double distance = gc.getOrthodromicDistance(); 
		
//			GeodeticCalculator gc = new GeodeticCalculator();
//			gc.setStartingPosition( JTS.toDirectPosition( start, crs ) );
//		    gc.setDestinationPosition( JTS.toDirectPosition( end, crs ) );
//		    
//		    double distance = gc.getOrthodromicDistance();
		
		int totalmeters = (int) distance;
		int km = totalmeters / 1000;
		
		return totalmeters;
	}

	public String toString(Collection<CorbaClient> clients) {
		StringBuffer arr = new StringBuffer();

		for (Iterator iterator = clients.iterator(); iterator.hasNext();) {
			CorbaClient corbaClient = (CorbaClient) iterator.next();
			
			arr.append(corbaClient.getNickname());
			
			if(iterator.hasNext()){
				arr.append(";");
			}
		}
	    
	    return arr.toString().trim();
	}

	public String toString2(Collection<String> clients) {
		StringBuffer arr = new StringBuffer();

		for (Iterator iterator = clients.iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			
			arr.append(name);
			
			if(iterator.hasNext()){
				arr.append(";");
			}
		}
	    
	    return arr.toString().trim();
	}

	public static void main(String[] args) {
		SimCorbaServer sim = new SimCorbaServer();
		// iguatemi -> unifor
		int iguatemi2unifor = sim.calcDistance(-3.755747,-38.490859, -3.766068,-38.478348);
		
		// iguatemi -> via sul
		int iguatemi2viasul = sim.calcDistance(-3.755747,-38.490859, -3.792746,-38.479528);
		
		// iguatemi -> casa jose de alencar
		int iguatemi2josealencar = sim.calcDistance(-3.755747,-38.490859, -3.810474,-38.478842);

		// iguatemi -> aquiraz
		int iguatemi2aquiraz = sim.calcDistance(-3.755747,-38.490859, -3.924882,-38.38829);
		
		// iguatemi -> iguape
		int iguatemi2iguape = sim.calcDistance(-3.755747,-38.490859, -3.962987,-38.362284);
		
		System.out.println("iguatemi -> unifor: " + iguatemi2unifor + " metros"); // Google Maps: 1,7 km
		System.out.println("iguatemi -> via sul: " + iguatemi2viasul + " metros"); // Google Maps: 4,7 km
		System.out.println("iguatemi -> casa jose de alencar: " + iguatemi2josealencar + " metros"); // Google Maps: 6,4 km
		System.out.println("iguatemi -> aquiraz: " + iguatemi2aquiraz + " metros"); // Google Maps: 26,1 km
		System.out.println("iguatemi -> iguape: " + iguatemi2iguape + " metros"); // Google Maps: 31,9 km
	}
}
