package com.joy.jT.launcher;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import com.joy.jT.client.LayoutController;
import com.joy.jT.config.DependencyInjection;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ApplicationLauncher {
	
	public static void main(String[] args) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(LayoutController.class.getName()).log(Level.ALL, null, ex);
		}

		/* Using Juice to initialize the dependencies and invoke the layout Controller */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LayoutController layout = new LayoutController();
				Module module = new DependencyInjection();
				Injector injector = Guice.createInjector(module);
				injector.injectMembers(layout);
				layout.setVisible(true);
			}
		});
	}
}
