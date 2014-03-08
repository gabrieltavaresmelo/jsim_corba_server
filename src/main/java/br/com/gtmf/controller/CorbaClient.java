package br.com.gtmf.controller;

public class CorbaClient {

	private sim.SimCorbaClient client;
	private String nickname;
	private double lat = 0.0;
	private double lng = 0.0;

	public CorbaClient(String nickname, sim.SimCorbaClient client) {
		this.nickname = nickname;
		this.client = client;

	}

	public sim.SimCorbaClient getClient() {
		return client;
	}

	public void setClient(sim.SimCorbaClient client) {
		this.client = client;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	// Modelo: -3,85678,-3898768
	public void locationToLatLng(String location){
		try {
			String [] split = location.split(",");
			
			lat = Double.parseDouble(split[0]);
			lng = Double.parseDouble(split[1]);
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			System.err.println("Erro ao converter coordenadas para double");
		}
	}

}
