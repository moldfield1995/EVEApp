package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




import dataHanderler.XmlHanderler;
import itemManagement.ItemHanderler;
import itemManagement.ItemMarketData;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;

import listHanderler.ListManagment;


public class Gui {

	private JFrame mainFrame;
	private JTable ComponetTable;
	private JTable tableDefault;
	private EventHanderler events;
	private JList list;
	private JScrollPane ComponetScrollPane;
	private JScrollPane ResultsScrollPane;
	private JTable ResultsTable;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Gui() {
		events = new EventHanderler();
		initialize();
	}


	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setBounds(100, 100, 673, 414);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				events.Update(list.getSelectedIndex(), true);
				
//				ArrayList<ItemMarketData> imdArray = events.getArray();
//				DefaultTableModel dTM = (DefaultTableModel) mainTable.getModel();
//				for (int y = 1; y-1<imdArray.size(); y++){
//					if(y > mainTable.getRowCount()-1){
//					dTM.addRow(new Object[]{null, null, null, null, null});
//					mainTable.setModel(dTM);
//					}
//					mainTable.setValueAt(events.getItem(33+y).getID(), y, 0);
//					for(int x = 1; x<mainTable.getColumnCount(); x++){
//						mainTable.setValueAt(events.getItem(33+y).GetAtribute(0, x-1), y, x);
//					}
//					
//					
//				}
			}
		});
		btnNewButton.setBounds(10, 201, 89, 29);
		mainFrame.getContentPane().add(btnNewButton);
		
		JScrollPane ListScrollPane = new JScrollPane();
		ListScrollPane.setBounds(10, 26, 151, 148);
		mainFrame.getContentPane().add(ListScrollPane);
		list = new JList();
		list.setModel(events.getList());
		ListScrollPane.setViewportView(list);
		
		ComponetScrollPane = new JScrollPane();
		ComponetScrollPane.setBounds(185, 27, 403, 161);
		mainFrame.getContentPane().add(ComponetScrollPane);
		
		ComponetTable = new JTable();
		ComponetScrollPane.setViewportView(ComponetTable);
		ComponetTable.setBackground(Color.WHITE);
		ComponetTable.setToolTipText("");
		ComponetTable.setFillsViewportHeight(true);
		ComponetTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Item", "Item Price", "Amount", "Total Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		} );
		ComponetTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		ComponetTable.getColumnModel().getColumn(2).setPreferredWidth(99);
		tableDefault = ComponetTable;
		
		ResultsScrollPane = new JScrollPane();
		ResultsScrollPane.setBounds(185, 201, 403, 117);
		mainFrame.getContentPane().add(ResultsScrollPane);
		
		ResultsTable = new JTable();
		ResultsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Items", "Item Price", "Amount", "Total Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		ResultsScrollPane.setViewportView(ResultsTable);
		events.setDTM((DefaultTableModel) ComponetTable.getModel());
	}
}
