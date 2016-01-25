package lab;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
public class Planeta {
	private double xPromien, yPromien;
	private Point2D punktOrbity;
	private Ellipse2D elipsa;
	public Planeta(double angle, double srednica, double speed, 
			int width, int height, Point2D punktSrodkowyUkladu) {
		this.xPromien = (width - srednica) / 5.0;
		this.yPromien = (height - srednica) / 5.0;
		this.punktOrbity = new Point2D.Double(
				punktSrodkowyUkladu.getX() + Math.cos(angle * speed) * xPromien,
				punktSrodkowyUkladu.getY() + Math.sin(angle * speed) * yPromien);
		this.elipsa = new Ellipse2D.Double(punktOrbity.getX() - srednica / 2.0,
				punktOrbity.getY() - srednica / 2.0, srednica, srednica);
	}
	public Ellipse2D getEllipse() {
		return this.elipsa;
	}
}