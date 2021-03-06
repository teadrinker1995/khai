package laba32lena;

import java.util.Random;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class laba32lena {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			laba32lena window = new laba32lena();
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
		shell.setSize(201, 300);
		shell.setText("SWT Application");
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 59, 164, 141);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(49, 10, 76, 21);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Size:");
		label.setBounds(10, 16, 33, 15);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.removeAll();
				list.add(makeSomething(Integer.parseInt(text.getText())));
			}
			public String makeSomething(int r)
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
				int kp=0,kn=0;
				String myRes="����";
				for(int i=0; i<r;i++)
				{
					for(int j=0;j<r;j++)
					{
						if(matrix[i][j]%2==0)
						{
							kp++;
						}
						else kn++;
						if(kp==kn)
						{
							myRes="����";
						}
					}
				}
				return myRes;
			}
		});
		button.setText("New Button");
		button.setBounds(10, 204, 75, 25);

	}

}
