
public class NoBaggingRequestListener {
	
	private AttendantUI attendant;
	
	public NoBaggingRequestListener(AttendantUI attendant) {
		this.attendant = attendant;
	}

	public void notifyNoBaggingRequest(CustomerUI customer) {
		attendant.notifyNoBaggingRequest(customer);
	}
	
}
