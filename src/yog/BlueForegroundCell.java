package yog;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BlueForegroundCell extends DefaultTableCellRenderer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String input="";
	
	public Component getTableCellRendererComponent (JTable table,Object value,boolean isSelected,boolean hasFocus,int row, int column) 
	{
		JLabel cell= (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (value.equals(input)) 
		{
			cell.setBackground(Color.white);
			cell.setForeground(Color.blue);
		}
		else 
		{
			cell.setBackground(Color.white);
			cell.setForeground(Color.BLACK);
		}
		return cell;
	}
	
	public void setInput(String str)
	{
		this.input=str;
	}
}
