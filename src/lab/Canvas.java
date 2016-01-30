package lab;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
public class Canvas extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private BufferedImage bi;
	private volatile boolean start = false;
	public double angle;
	public Canvas(int width, int height) {
		Dimension size = new Dimension(width, height);
		setSize(size);
		setPreferredSize(size);
	}
	@Override
	protected void paintComponent(Graphics g) {
		if (bi == null || bi.getWidth() != this.getWidth() || bi.getHeight() != this.getHeight()) {
			this.bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		}
		Graphics2D g2bi = bi.createGraphics();
		g2bi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rysujTo(g2bi);
		g2bi.dispose();
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bi, 0, 0, this);
	}//gasgaahahashsahashsdh
	public void start() {
		start = true;
		Thread thread = new Thread(this);
		thread.start();
	}//zmian zmiana zmiana
	@Override
	public void run() {
		while (start) {
			angle += .0005;
			repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void stop() {
		this.start = false;
	}
	public boolean isStarted() {
		return start;
	}
	public void rysujTo(Graphics2D g2) {
		int width = this.getWidth();
		int height = this.getHeight();
		Point2D punktSrodkowyUkladu = new Point2D.Double(width / 2.0, height / 2.0);
		//T³o
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		//S³oñce
		double srednicaSlonca = Math.min(width, height) / 10.0;
		Ellipse2D sun = new Ellipse2D.Double(punktSrodkowyUkladu.getX() - srednicaSlonca / 2.0,
				punktSrodkowyUkladu.getY() - srednicaSlonca / 2.0, srednicaSlonca, srednicaSlonca);
		g2.setColor(Color.YELLOW);
		g2.fill(sun);
		//Planety
		g2.setColor(Color.BLACK);
		List<Planeta> planety = new ArrayList<Planeta>();
		int srednica[] = {15, 20, 25, 10, 50, 40, 30, 30};
		double speed[] = {20, 24, 27, 17, 23, 25, 27, 29};
		for (int i = 0; i < 8; i++) {
			planety.add(new Planeta(angle + i * 8, srednica[i], speed[i], 
					width / 2 + i * 200, height / 2 + i * 200, punktSrodkowyUkladu));
			g2.fill(planety.get(i).getEllipse());
		}
	}
}