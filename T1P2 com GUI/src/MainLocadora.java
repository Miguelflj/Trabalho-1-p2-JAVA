import java.awt.EventQueue;

public class MainLocadora {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MenuOps mn = new MenuOps();
				mn.setVisible(true);
			}
		});
	}
}
