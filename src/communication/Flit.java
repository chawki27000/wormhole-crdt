package communication;

public class Flit {

	public enum FlitType {
		HEAD, TAIL, DATA
	}

	// attribute
	private FlitType type;

	public Flit(FlitType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "" + type;
	}

	public FlitType getType() {
		return type;
	}
}
