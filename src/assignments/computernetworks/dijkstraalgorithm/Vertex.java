package assignments.computernetworks.dijkstraalgorithm;

public class Vertex {
	private final int INFINITY = 5000;
	private int distance = INFINITY;
	private String previousNode = "";
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getPreviousNode() {
		return previousNode;
	}
	public void setPreviousNode(String previousNode) {
		this.previousNode = previousNode;
	}
}
