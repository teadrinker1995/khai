package laba3;

import java.util.Random;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class laba3z2 {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			laba3z2 window = new laba3z2();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(375, 272);
		shell.setText("SWT Application");
		
		Label lblSize = new Label(shell, SWT.NONE);
		lblSize.setText("Size:");
		lblSize.setBounds(10, 16, 33, 15);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(49, 10, 76, 21);
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 59, 164, 141);
		
		List list_1 = new List(shell, SWT.BORDER);
		list_1.setBounds(185, 59, 164, 141);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.removeAll();
				findMax(Integer.parseInt(text.getText()));
			}
			
			public void findMax(int r)
			{
				Random rand = new Random();
				int[][] matrix = new int[r][r];
				for (int i = 0; i < matrix.length; i++) {
				  matrix[i] = new int[r];
				  for (int j = 0; j < matrix.length; j++) {
				    matrix[i][j] = -i;
				  }
				}
				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < matrix.length; j++) {
						matrix[i][j]=rand.nextInt(10);
					}
				}
				String mst="";
				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < matrix.length; j++) {
						mst+=Integer.toString(matrix[i][j])+" ";
					}
					list.add(mst);
					mst="";
				}
				int max=matrix[r/2][r/2];
				int counter=0;
				String scnd="";
				list_1.removeAll();
				for (int i = r/2; i < r; i++)
				{
					for (int j = 0; j < r; j++)
					{
						if(j>=(r/2)-counter&&j<=(r/2)+counter)
						{
							scnd+=Integer.toString(matrix[i][j])+" ";
							if(matrix[i][j]>max)
							{
								max=matrix[i][j];
							}
						}
					}
					counter++;
					list_1.add(scnd);
					scnd="";
					
				}
				list_1.add("Max element:" + max);
			}
		});
		button.setText("New Button");
		button.setBounds(10, 204, 75, 25);
	}

}
