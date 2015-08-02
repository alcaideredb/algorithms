import java.util.ArrayList;
import java.util.List;

public class PointSET {
	SET<Point2D> points;
	
	public PointSET() {
		points = new SET<>();
	}
	
	public boolean isEmpty() {
		return points.isEmpty();
	}
	
	public void insert(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		points.add(p);
	}
	
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		return points.contains(p);
	}
	
	public void draw() {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.show(0);		
		for(Point2D p : points) {
			StdDraw.point(p.x(), p.y());
		}
		StdDraw.show(0);
	}

	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new NullPointerException();
		}
		
		List <Point2D> pointsInRectangle = new ArrayList<>();
		 
		for (Point2D p : points) {	
		   if (rect.contains(p)) {
				   pointsInRectangle.add(p);
		   }
	   }
		
	   return pointsInRectangle;
	}
	
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new java.lang.NullPointerException();
		}
		Point2D minimum;
		double curMinDistance = 0.0;
		boolean flag = true;
		minimum = null;
		
		for (Point2D curPoint : points) {
			
			if (flag) {
				minimum = curPoint;
				curMinDistance = p.distanceSquaredTo(curPoint); 
				flag = false;
				continue;
			}
			
			if (p.distanceSquaredTo(curPoint) < curMinDistance) {
				minimum = curPoint;
				curMinDistance = p.distanceSquaredTo(curPoint);
			}
		}		
		
		return minimum;
	}
	   
}

