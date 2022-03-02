package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class killController {
	private Process initProcess(String process) {
		//config process
		try {
			return Runtime.getRuntime().exec(process);
		} catch (Exception e) {
			String msgErro = e.getMessage();
			if (msgErro.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				System.err.println(msgErro);
			}
			return null;
		}
	}
	
	//Retornar o S0 que está na máquina
	public String os() {
		return System.getProperty("os.name");
	}
	
	public void taskList() { //abre a lista de processos
		String taskCmd = "";
		if(os().contains("Windows")) {
			taskCmd = "TASKLIST /FO TABLE";
		}
		else if(os().contains ("Linux")) {
			taskCmd = "ps -ef";
		}
		
		Process p = initProcess(taskCmd);
		InputStream fluxo = p.getInputStream(); //pega o fluxo de bits que vem do processo
		InputStreamReader leitor = new InputStreamReader(fluxo, StandardCharsets.UTF_8); //transformar em string
		BufferedReader buffer = new BufferedReader(leitor); //armazeno em um buffer
		
		try {
			String linha = buffer.readLine();
			
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
		}catch(IOException e) {
			System.err.println("Comando inválido");
		}
	}
	
	public void killPID(int pid) { //comando para matar o processo pelo PID
		String pidCmd = "";
		if(os().contains("Windows")) {
			pidCmd = "TASKKILL /F /PID " + pid;
		}
		else if (os().contains("Linux")) {
			pidCmd = "kill -9 " + pid;
		}
		
		initProcess (pidCmd);
	}
	
	public void killNome(String nome) { //comando para matar o processo pelo nome
		String nomeCmd = "";
		if(os().contains("Windows")) {
			nomeCmd = "TASKKILL /F /IM " + nome;
		}
		else if (os().contains("Linux")) {
			nomeCmd = "pkill -f " + nome;
		}
		
		initProcess (nomeCmd);
	}
	
}
