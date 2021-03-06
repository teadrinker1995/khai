package laba4lena;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class laba42lena {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			laba42lena window = new laba42lena();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		
		Label lblRes = new Label(shell, SWT.NONE);
		lblRes.setBounds(91, 37, 55, 15);
		lblRes.setText("res");
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 10, 350, 21);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String mstr = text.getText().toString();
				int count=0;
				for(int i=0;i<mstr.length();i++)
				{
					String mbuf=mstr.substring(i, i+1);
					if(mstr.substring(i, i+1).equals("a"))
					{
						count++;
					}
				}
				float lengthm=mstr.length();
				float res=(count/lengthm)*100;
				lblRes.setText(Float.toString(res));
			}
		});
		btnNewButton.setBounds(10, 34, 75, 25);
		btnNewButton.setText("New Button");
	}

}
