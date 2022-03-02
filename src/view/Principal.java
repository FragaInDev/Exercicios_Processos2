package view;

import javax.swing.JOptionPane;

import controller.killController;

public class Principal {

	public static void main(String[] args) {
		killController elMatador = new killController();
		String os = elMatador.os();
		elMatador.taskList();

		// Fazendo o painel do JOptionPane
		String[] btns = { "Matar por PID", "Matar por Nome", "Sair" }; // botões da interface
		int opt = JOptionPane.showOptionDialog(null,
				"Seu sistema operacional é o " + os + ". O que deseja fazer agora?", // Mensagem
				"Exercício de Processos 2", // Título
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, btns, btns[0]);

		// Executa a ação de acordo com o SO
		if (opt == 0) { // exec kill PID
			String pegaPid = JOptionPane.showInputDialog(null, "Digite o PID do processo que deseja finalizar:");
			int pid = Integer.parseInt(pegaPid); // transforma o PID em Int
			elMatador.killPID(pid);
			JOptionPane.showMessageDialog(null, "Processo finalizado com sucesso :)");
		} else if (opt == 1) { // exec kill nome
			String pegaNome = JOptionPane.showInputDialog(null, "Digite o Nome do processo que deseja finalizar:");
			String nome = pegaNome;
			elMatador.killNome(nome);
			JOptionPane.showMessageDialog(null, "Processo finalizado com sucesso :)");
		}

	}

}
