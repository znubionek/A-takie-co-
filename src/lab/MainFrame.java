package lab;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MainFrame {
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
					window.frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Układ Słoneczny");
		frame.setBounds(100, 100, 450, 300);
		Canvas panel = new Canvas(800, 800);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(panel.isStarted()) {
					panel.stop();
					System.out.println("Program wstrzymany.");
				}
				else {
					panel.start();
					System.out.println("Program wznowiony.");
				}
			}
		});
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.start();
	}
}