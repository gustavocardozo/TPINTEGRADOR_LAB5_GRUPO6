package presentation;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class Programa {
	public static void main(String[] args) throws SQLException, IOException{
		
		Runnable inicializador = new Runnable() {
			
			@Override
			public void run() {
				FrmMenu form = new FrmMenu();
				form.setVisible(true);
			}
		};
		
		SwingUtilities.invokeLater(inicializador);
		
	}
}