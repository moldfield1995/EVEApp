package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;

/**
 * Main Class, sets up the GUI and the event handerler.
 * @author Matthew Oldfield
 * @version 0.5
 * 
 */
public class Gui {

	private JFrame mainFrame;
	private JTable ComponetTable;
	private JTable tableDefault;
	private EventHanderler events;
	private JList list;
	private JScrollPane ComponetScrollPane;
	private JScrollPane ResultsScrollPane;
	private JTable ResultsTable;
	private JLabel ProfitLossAmount;
	private JCheckBox buyCheckBox;
	
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
		//initialize event handerler before GUI to ensutre no nullptr's
		events = new EventHanderler();
		initialize();
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setBounds(100, 100, 673, 414);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Update");
		//Anonymous event handerler for the update button
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent arg0) {
				//Update the event handerler passing the item selected
				events.Update(list.getSelectedIndex(), buyCheckBox.isSelected());
				ProfitLossAmount.setText(events.getProfitLoss() + "");
			
			}
		});
		btnNewButton.setBounds(10, 201, 89, 29);
		mainFrame.getContentPane().add(btnNewButton);
		//The List of the ore types
		JScrollPane ListScrollPane = new JScrollPane();
		ListScrollPane.setBounds(10, 26, 151, 148);
		mainFrame.getContentPane().add(ListScrollPane);
		list = new JList();
		list.setModel(events.getList());
		ListScrollPane.setViewportView(list);
		//Top JTable, Input
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
		} );
		ComponetTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		ComponetTable.getColumnModel().getColumn(2).setPreferredWidth(99);
		tableDefault = ComponetTable;
		//Botem JTable, Output
		ResultsScrollPane = new JScrollPane();
		ResultsScrollPane.setBounds(185, 201, 403, 117);
		mainFrame.getContentPane().add(ResultsScrollPane);
		
		ResultsTable = new JTable();
		ResultsTable.setModel(new DefaultTableModel(
			new Object[][] {
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
		});
		ResultsScrollPane.setViewportView(ResultsTable);
		//Profit Loss Text
		JLabel lblProfitloss = new JLabel("Profit/Loss:");
		lblProfitloss.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfitloss.setBounds(185, 329, 75, 17);
		mainFrame.getContentPane().add(lblProfitloss);
		
		ProfitLossAmount = new JLabel("0.00");
		ProfitLossAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ProfitLossAmount.setBounds(265, 329, 151, 14);
		mainFrame.getContentPane().add(ProfitLossAmount);
		//TickBox for using buy or sell market (Best case or worse case)
		buyCheckBox = new JCheckBox("At buy price");
		buyCheckBox.setBounds(10, 251, 97, 23);
		mainFrame.getContentPane().add(buyCheckBox);
		//Set up the refences of the Tables default model
		events.setComDTM((DefaultTableModel) ComponetTable.getModel());
		events.setResDTM((DefaultTableModel) ResultsTable.getModel());
	}
}
