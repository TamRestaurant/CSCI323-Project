import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

/**
 * This class takes in a JTable or JTableModel and writes to file in CSV format
 * 
 * @author Austin Turner
 *
 */
public class WriteTableToFile extends JPanel{
	
	private static boolean isSuccessful = true;
	private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH.mm.ss");
	private static Date date; 
	private static Writer writer = null;
	private static JTable jTable;
	
	
	public static boolean tableToFile(JTable table, String fileNameDescriptor){
		jTable = table;        
		date = new Date();
		
		try {
			
			System.out.println(fileNameDescriptor + " " + dateFormat.format(date));
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Reports/" + fileNameDescriptor + " " + dateFormat.format(date) + ".csv"), "utf-8"));
		    writer.write(createText());
		    
		} catch (IOException ex) {
			isSuccessful = false;
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
		
		
		return isSuccessful;
	}// close tableToFile
	
	
	public static String createText(){
		final int ROWS  = jTable.getRowCount(), COLUMNS = jTable.getColumnCount();
		String output = "";
		
		//Gather column headings
		for (int i = 0; i < COLUMNS; i++){
			//System.out.print("COLUMN " + i);
			output += jTable.getColumnName(i);
			//See if final iteration
			if (!(i==COLUMNS-1)){
				output += ",";
				//System.out.print(",");
			}
			else{
				output +="\n";
				//System.out.println("");
			}
		}
		
		//Gather data
		for (int y = 0; y < ROWS; y++){
			
			for (int x = 0; x < COLUMNS; x++){
				output += jTable.getValueAt(y, x);
				//System.out.print(jTable.getValueAt(y, x));
				
				if (!(x==COLUMNS-1)){
					output += ",";
					//System.out.print(",");
				}
			}
			//System.out.println("");
			output += "\n"; // Create new line after column is over
			
			
		}
		
		
		return output;
		
	}

}
