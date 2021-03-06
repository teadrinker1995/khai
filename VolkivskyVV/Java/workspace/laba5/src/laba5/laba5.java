package laba5;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class laba5 {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			laba5 window = new laba5();
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
		shell.setSize(252, 125);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 10, 96, 21);
		
		CLabel label = new CLabel(shell, SWT.NONE);
		label.setBounds(10, 37, 62, 21);
		label.setText("\u0420\u0443\u0437\u0443\u043B\u044C\u0442\u0430\u0442:");
		
		CLabel label_1 = new CLabel(shell, SWT.NONE);
		label_1.setBounds(78, 37, 145, 21);
		label_1.setText("");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String t = new String();
				String s = text.getText();
				StringBuffer buffer = new StringBuffer();
				for(int i = s.length()-1; i >= 0; i--)
				{
					buffer.append(s.charAt(i));
				}
				t = buffer.toString();
				label_1.setText(t);
			}
		});
		button.setBounds(10, 59, 84, 25);
		button.setText("\u041F\u0435\u0440\u0435\u0432\u0435\u0440\u043D\u0443\u0442\u0438");

	}
}
