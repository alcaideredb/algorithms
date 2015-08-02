
public class KdTree {
	private Node root;
	private int size;
	
	private class Node implements Comparable<Node>{
		public Point2D point;
		public Node left;
		public Node right;
		public int level;
		
		public Node(Point2D p, int level) {
			point = new Point2D(p.x(),p.y());
			left = null;
			right = null;
			this.level = level;
		}

		@Override
		public int compareTo(Node o) {
			if (this.level % 2 == 0) {
				if(this.point.x() < o.point.x()) {
					return -1;
				}
				if (this.point.x() > o.point.x()) {
					return 1;
				}
				if(this.point.y() < o.point.y()) {
					return -1;
				}
				if(this.point.y() > o.point.y()) {
					return 1;
				}
			}
			else {
				if(this.point.y() < o.point.y()) {
					return -1;
				}
				if (this.point.y() > o.point.y()) {
					return 1;
				}
				if(this.point.x() < o.point.x()) {
					return -1;
				}
				if(this.point.x() > o.point.x()) {
					return 1;
				}
			}
			
			return 0;
		}
	}
	
	public KdTree() {
		root = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void insert (Point2D p) {
		root = insert(root,p,0);
	}
	
	private Node insert (Node node, Point2D p, int level) {
		if (node == null)
			return new Node(p,level);
		
		
		
		
		
		return node;
	}
	
}
