package presentation;

import java.io.EOFException;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import service.*;
import model.*;

public class FrmMigrarClientes extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	protected JProgressBar barra;
	protected JLabel cantidad;
	protected nCliente nC;
	public FrmMigrarClientes()
	{
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		setBounds(100,100,315,170);
		
		cantidad = new JLabel();
		cantidad.setBounds(25, 25, 315, 20);
		
		barra = new JProgressBar();
		barra.setBounds(25,50,250,70);
		panel.add(barra);
		panel.add(cantidad);
		
		nC = new nCliente();
		
		
		this.setVisible(true);
	}

	@Override
	public void run() {
		int proceso = 0;
		List<Cliente> clientes = new ArrayList<>();
		clientes = nC.ListaBase();
//		
//		clientes.add(new Cliente());
//		clientes.add(new Cliente());
//		clientes.add(new Cliente());
		
		barra.setMaximum(clientes.size());
		
		cantidad.setText("Progreso: " + proceso + "/"+clientes.size());
		
		if(clientes.size()==0)
		{
			JOptionPane.showMessageDialog(FrmMigrarClientes.this, "No hay nada para migrar");
			FrmMigrarClientes.this.dispose();
			
		}
		
		for(Cliente c : clientes)
		{
				try {
					nC.grabarCliente(c);
					System.out.println("ANDA");			
					barra.setValue(barra.getValue()+1);
					proceso++;
					cantidad.setText("Progreso: " + proceso + "/"+clientes.size());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		JOptionPane.showMessageDialog(FrmMigrarClientes.this, "Se migro correctamente");
		FrmMigrarClientes.this.dispose();
		
		
		
	}
	
	
	
}
