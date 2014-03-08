package br.com.gtmf.window;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import br.com.gtmf.controller.ServerCreator;
import br.com.gtmf.utils.FXOptionPane;
import br.com.gtmf.utils.FXOptionPane.Response;
import br.com.gtmf.utils.NetworkUtils;

/**
 * 
 * Classe que contem os eventos da Tela Principal
 * 
 * 
 * @author Gabriel Tavares
 *
 */
public class MainLayoutWindow {

	// Widgets
    @FXML private Label lbPorta;
    @FXML private Label lbInterfaces;
    @FXML private Label lbStatus;
    @FXML private ToggleButton tbStatus;

	// Referencia a Stage
	private Stage stage;

	private ServerCreator servidorCreator;
	private String[] args;
	
	
	public void setParams(Stage stage) {
		this.stage = stage;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	/**
	 * Inicializa a classe controller. 
	 * Eh chamado automaticamente depois do fxml ter sido carregado 
	 */
	@FXML
	private void initialize() {
		String body = "";
		Map<String, String> ifaces = NetworkUtils.getInterfaces();
		
		Set<String> chaves = ifaces.keySet();
		for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			String value = ifaces.get(key);
			
			body += key;
			body += ": ";
			body += value;
			body += "\n";
		}

		body += "\n";
		
		lbInterfaces.setText(body);
	}

	
	// ---------------------------------------------------
	// -- 
	// -- 	Metodos dos Eventos dos componentes da tela
	// --
	// ---------------------------------------------------

	
	@FXML
	private void handleExit() {
		finish();
	}
	
	@FXML
	private void handleAbout() {
		String body = "";

		body += "Curso:";
		body += "\n";
		body += "Engenharia de Computação";
		body += "\n";
		body += "\n";
		
		body += "Disciplina:";
		body += "\n";
		body += "Programação Paralela e Distribuída";
		body += "\n";
		body += "18/11/2013";
		body += "\n";
		body += "\n";

		body += "Trabalho:";
		body += "\n";
		body += "Sistema de Interação Móvel (SIM)";
		body += "\n";
		body += "\n";
		
		body += "Aluno:";
		body += "\n";
		body += "Gabriel Tavares";
		body += "\n";
		body += "gabrieltavaresmelo@gmail.com";
		body += "\n";
		body += "https://github.com/gabrieltavaresmelo";
		body += "\n";
		body += "\n";

		FXOptionPane.showMessageDialog(stage, body, "Sobre", "OK");
	}
	
	@FXML
	public void tbStatusItemChange() {
		if(tbStatus.isSelected()){
			turnOn();
		} else{			
			turnOff();			
		}
	}

	private void turnOn(){
		tbStatus.setText("Desconectar");
		lbStatus.setText("Conectado");

		String result = "";
		
		try{
//			for (int i = 0; i < args.length; i++) {
//				String item = args[i];
//				result += item + " ";
//			}
			
			String item = args[1];
			String [] split = item.split("::");
			
			result = split[1];
			
			lbPorta.setText(result.trim());
			
		} catch(Exception e){
			System.err.println(e.getMessage());
		}		
		
		servidorCreator = new ServerCreator(args);
		
		if(!servidorCreator.isConnected()){
			servidorCreator = null;
			turnOff();
			
			if(tbStatus.isSelected()){
				tbStatus.setSelected(false);
			}
			
			FXOptionPane.showMessageDialog(stage,
					"Não foi possível estabelecer a conexão!", "Erro", "OK");
			return;
		}
	}
	
	private void turnOff(){
		tbStatus.setText("Conectar");
		lbStatus.setText("Desconectado");		

		if(servidorCreator != null){
			servidorCreator.close();
			servidorCreator = null;
		}
	}


	/**
	 * Encerra a aplicacao
	 */
	public void finish() {		
		Response resp = FXOptionPane.showConfirmDialog(stage,
				"Deseja encerrar a Aplicação?", "Sair", "Sim", "Não");
		
		if(resp == Response.YES){
			turnOff();
			stage.close();
	//		Platform.exit();
			System.exit(0); // FIXME Procurar um metodo menos "agressivel"
		}
	}
	
}
