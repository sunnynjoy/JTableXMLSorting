package com.joy.jT.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import com.joy.jT.bl.BusinessFunction;
import com.joy.jT.exception.ServiceException;
import com.joy.jT.model.Button;
import com.joy.jT.model.Car;
import com.joy.jT.model.Data;
import com.joy.jT.model.Grid;
import com.joy.jT.model.Layout;
import com.joy.jT.utils.Instance;
import com.joy.jT.utils.ParseUtil;

public class LayoutController extends JFrame {

	@Inject
	private BusinessFunction businessFunction;

	private static final long serialVersionUID = -3238101392759824997L;
	private JButton jButton;
	private JScrollPane jScrollPane;
	private JTable jTable;
	int size = 0;
	List<Car> cars = null;
	Vector<String> columns = null;
	Properties properties = new Properties();
	Button button = null;

	/**
	 * Creates JTable
	 */
	public LayoutController() {
		initComponents();
		loadProperties();
		fillDataJTable(false);
	}

	// function to display the result in JTable
	public void fillDataJTable(boolean flag) {
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(!flag ? fillColumnData() : columns);
		model = fillRowData(model, flag);
		jTable.setModel(model);
		jButton.setText(button.getValue());
	}

	private void initComponents() {
		jButton = new JButton();
		jScrollPane = new JScrollPane();
		jTable = new JTable();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jButton.setHorizontalAlignment(SwingConstants.CENTER);
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				sortRowData(evt);
			}
		});

		jTable.setModel(new DefaultTableModel(new Object[][] { { null } }, new String[] { "" }));
		jScrollPane.setViewportView(jTable);
		jTable.setAutoCreateRowSorter(true);
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(GroupLayout.Alignment.CENTER,
						layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(layout.createSequentialGroup().addComponent(jButton).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
								.addGap(25, 25, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(jButton))
				.addGap(25, 25, 25)));
		pack();
	}

	// method for sorting the row data
	private void sortRowData(ActionEvent evt) {
		cars = businessFunction.doAction(cars);
		fillDataJTable(true);
	}

	// method returns column data
	private Vector<String> fillColumnData() {
		columns = new Vector<>();
		List<Grid> grid = null;
		Layout layout = null;
		try {
			layout = ((Layout) ParseUtil.unmarshall(properties.getProperty("layoutpath"), Instance.LAYOUT));
			grid = layout.getGrid();
			button  = layout.getMenu().getButton();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		// filling the Column Data
		for (String columnIn : grid.get(0).getColumn()) {
			columns.add(columnIn);
		}
		size = columns.size();
		return columns;
	}

	// method returns row data
	private DefaultTableModel fillRowData(DefaultTableModel model, boolean flag) {
		String[] row = new String[size];
		if (!flag) {
			try {
				cars = ((Data) ParseUtil.unmarshall(properties.getProperty("dataPath"), Instance.DATA)).getCar();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}

		// filling the Row Data
		for (int i = 0; i < cars.size(); i++) {
			for (int j = 0; j < size; j++) {
				if (j == 0) {
					row[j] = cars.get(i).getMake();
				} else if (j == 1) {
					row[j] = cars.get(i).getModel();
				} else {
					row[j] = cars.get(i).getYear() + "";
				}
			}
			model.addRow(row);
		}
		return model;
	}

	private void loadProperties() {
		try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
			// load a properties file
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
