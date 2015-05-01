
import java.text.NumberFormat;
import java.util.Random;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;




public class form {
	
	private static Text text;
	private static Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static long factor(int p) {
	    return (p == 1 ? 1 : p * factor(p - 1));
	}
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 382);
		shell.setText("SWT Application");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u0427\u0430\u0441\u0442\u0438\u043D\u0430 1");
		group.setBounds(10, 10, 127, 91);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBounds(29, 21, 41, 15);
		label_1.setText("\u0412\u0456\u0434 1 \u0434\u043E");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(71, 18, 26, 21);
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(18, 42, 90, 25);
		btnNewButton.setText("\u0412\u0438\u043A\u043E\u043D\u0430\u0442\u0438");
		
		final Label label = new Label(group, SWT.NONE);
		label.setBounds(4, 73, 119, 15);
		
		Group group_1 = new Group(shell, SWT.NONE);
		group_1.setText("\u0427\u0430\u0441\u0442\u0438\u043D\u0430 2");
		group_1.setBounds(10, 107, 251, 170);
		
		text_1 = new Text(group_1, SWT.BORDER);
		text_1.setBounds(107, 18, 26, 21);
		
		final List list_1 = new List(group_1, SWT.BORDER);
		list_1.setEnabled(false);
		list_1.setBounds(135, 78, 106, 79);
		
		final List list = new List(group_1, SWT.BORDER);
		list.setEnabled(false);
		list.setBounds(17, 78, 106, 79);
		
		Button button = new Button(group_1, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int n = Integer.parseInt(text_1.getText());				
				int mas[][] = new int [n][n], i, j;
				String str = "";
				list.removeAll();
				list_1.removeAll();
				for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++) {
						Random rand = new Random();
						mas[i][j] = rand.nextInt(100);	
						str += String.valueOf(mas[i][j] + " ");
					}
					list.add(str);
					str = "";
				}
				int max = 0;
				for (i = n - 1; i >= 0; i--) {
					for (j = 0; j < n; j++) {
						if (mas[i][j] > max) {
							max = mas[i][j];
						}					
						str += String.valueOf(mas[i][j] + " ");
					}
					n -= 1;
					list_1.add(str);
					str = "";					
				}
				str = "" + max;
				list_1.add(str);
			}
		});
		button.setBounds(107, 47, 90, 25);
		button.setText("\u0412\u0438\u043A\u043E\u043D\u0430\u0442\u0438");
		
		
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setBounds(17, 21, 75, 51);
		label_2.setText("\u0420\u043E\u0437\u043C\u0456\u0440\u043D\u0456\u0441\u0442\u044C\r\n\u043A\u0432\u0430\u0434\u0440\u0430\u0442\u043D\u043E\u0433\u043E\r\n\u043C\u0430\u0441\u0441\u0438\u0432\u0443");
		
		Group group_2 = new Group(shell, SWT.NONE);
		group_2.setText("\u0427\u0430\u0441\u0442\u0438\u043D\u0430 3");
		group_2.setBounds(267, 10, 157, 267);
		
		final List list_2 = new List(group_2, SWT.BORDER);
		list_2.setEnabled(false);
		list_2.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		list_2.setBounds(10, 48, 135, 100);
		
		final List list_3 = new List(group_2, SWT.BORDER);
		list_3.setEnabled(false);
		list_3.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		list_3.setBounds(10, 154, 135, 100);
		
		Button button_1 = new Button(group_2, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				list_2.removeAll();
				list_3.removeAll();
				int n = 8;
				int mas[][] = new int [n][n], i, j, b1[][] = new int [3][n], b2[][] = new int [3][n];
				String str = "";
				for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++) {
						Random rand = new Random();
						mas[i][j] = rand.nextInt(100);
						str += String.valueOf(mas[i][j] + " ");
					}
					list_2.add(str);
					str = "";
				}
				for (i = 0; i < 3; i++) {
					for (j = 0; j < n; j++) {
						b1[i][j] = mas[i][j];						
					}					
				}
				int h;
				for (i = n-3, h = 0; i < n && h <= 3; i++, h++) {					
					for (j = 0; j < n; j++) {
						b2[h][j] = mas[i][j];												
					}					
				}
				for (i = 0; i < 3; i++) {
					for (j = 0; j < n; j++){
						mas[i][j] = b2[i][j];
					}
				}
				for (i = n-3, h = 0; i < n && h <= 3; i++, h++) {
					for (j = 0; j < n; j++) {
						mas[i][j] = b1[h][j];						
					}
				}
				for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++) {						
						str += String.valueOf(mas[i][j] + " ");
					}
					list_3.add(str);
					str = "";
				}
			}
		});
		button_1.setBounds(33, 17, 90, 25);
		button_1.setText("\u0412\u0438\u043A\u043E\u043D\u0430\u0442\u0438");
		
		
		
		
		
		
		
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				for (int i = 1; i <= Integer.parseInt(text.getText()); i++){
					double result = 0;
					double f = factor(i);
					double add = 1 / f; 
					result += add;					
					label.setText(String.valueOf(result));
				}
				
			}
		});
		
		

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
