package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.text.NumberFormatter;

import controller.Main;

public class GUIClass extends JFrame{
	
	private JLabel lblSubTotalOutput = new JLabel();
	private JLabel lblTaxOutput = new JLabel();
	private JLabel lblTotalOutput = new JLabel();
	
	public GUIClass(Main mainClass){
		this.setSize(200, 200);
		this.setVisible(true);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel pnlItem1 = new JPanel();
		JPanel pnlItem2 = new JPanel();
		JPanel pnlItem3 = new JPanel();
		JPanel pnlButtons = new JPanel();
		JPanel pnlOutputContainer = new JPanel();
		JPanel pnlOutputText = new JPanel();
		JPanel pnlOutputResults = new JPanel();
		
		JLabel lblSubTotal = new JLabel("Subtotal: ");
		JLabel lblTax = new JLabel("Tax: ");
		JLabel lblTotal = new JLabel("Total: ");
		
		pnlItem1.setLayout(new BoxLayout(pnlItem1, BoxLayout.X_AXIS));
		pnlItem2.setLayout(new BoxLayout(pnlItem2, BoxLayout.X_AXIS));
		pnlItem3.setLayout(new BoxLayout(pnlItem3, BoxLayout.X_AXIS));
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
		pnlOutputContainer.setLayout(new BoxLayout(pnlOutputContainer, BoxLayout.X_AXIS));
		pnlOutputText.setLayout(new BoxLayout(pnlOutputText, BoxLayout.Y_AXIS));
		pnlOutputResults.setLayout(new BoxLayout(pnlOutputResults, BoxLayout.Y_AXIS));
		
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat("#.##", symbol);
		NumberFormatter formatter = new NumberFormatter(format);
		
		JFormattedTextField txtItem1 = new JFormattedTextField(formatter);
		JFormattedTextField txtItem2 = new JFormattedTextField(formatter);
		JFormattedTextField txtItem3 = new JFormattedTextField(formatter);
		
		JComboBox<String> cmbItem1 = new JComboBox(mainClass.quantity);
		JComboBox<String> cmbItem2 = new JComboBox(mainClass.quantity);
		JComboBox<String> cmbItem3 = new JComboBox(mainClass.quantity);
		
		Dimension dTxt = new Dimension(100, 20);
		Dimension dCmb = new Dimension(50, 20);
		txtItem1.setMaximumSize(dTxt);
		txtItem2.setMaximumSize(dTxt);
		txtItem3.setMaximumSize(dTxt);
		cmbItem1.setMaximumSize(dCmb);
		cmbItem2.setMaximumSize(dCmb);
		cmbItem3.setMaximumSize(dCmb);
		
		JButton btnCalc = new JButton("Berechne");
		btnCalc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtItem1.getText().isEmpty() || txtItem2.getText().isEmpty() || txtItem3.getText().isEmpty()){
					JOptionPane.showMessageDialog(getContentPane(), "Alle Felder müssen ausgefüllt sein.");
					txtItem1.setText("");
					txtItem2.setText("");
					txtItem3.setText("");
				} else{
					mainClass.calcOutput(parseItemTotal(txtItem1, cmbItem1), parseItemTotal(txtItem2, cmbItem2), parseItemTotal(txtItem3, cmbItem3));
				}
			}
		});
		
		getContentPane().add(pnlItem1);
		getContentPane().add(pnlItem2);
		getContentPane().add(pnlItem3);
		getContentPane().add(pnlButtons);
		getContentPane().add(pnlOutputContainer);
		
		pnlItem1.add(txtItem1);
		pnlItem1.add(cmbItem1);
		
		pnlItem2.add(txtItem2);
		pnlItem2.add(cmbItem2);
		
		pnlItem3.add(txtItem3);
		pnlItem3.add(cmbItem3);
		
		pnlButtons.add(btnCalc);
		
		pnlOutputContainer.add(pnlOutputText);
		pnlOutputContainer.add(pnlOutputResults);
		pnlOutputText.add(lblSubTotal);
		pnlOutputText.add(lblTax);
		pnlOutputText.add(lblTotal);
		pnlOutputResults.add(lblSubTotalOutput);
		pnlOutputResults.add(lblTaxOutput);
		pnlOutputResults.add(lblTotalOutput);
		
		validate();
	}
	
	private double parseItemTotal(JFormattedTextField field, JComboBox quantity){
		return Double.parseDouble(field.getText()) * Integer.parseInt((String) quantity.getSelectedItem());
	}
	
	public void setOutputResults(double subTotal, double tax, double total){
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2);
		
		lblSubTotalOutput.setText("$"+formatter.format(subTotal));
		lblTaxOutput.setText("$"+formatter.format(tax));
		lblTotalOutput.setText("$"+formatter.format(total));
	}
}
