package util;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.jimmyselectronics.disenchantment.TouchScreen;

public class AttendantStation {
	
	private TouchScreen touchScreen;
	private List<CustomerUI> stations;
	private List<AttendantStationListener> listeners;
	
	public AttendantStation() {
		touchScreen = new TouchScreen();
		stations = new ArrayList<CustomerUI>();
		listeners = new ArrayList<AttendantStationListener>();
	}
	
	/**
	 * Start tracking the station provided 
	 * @param station CustomerUI to start tracking
	 * @throws NullPointerException when the station provided is null
	 */
	public void registerStation(CustomerUI station) throws NullPointerException {
		if (station == null) throw new NullPointerException("CustomerUI cannot be null");
		if (stations.contains(station)) throw new IllegalArgumentException("CustomerUI is already registerd");
		stations.add(station);
		for (AttendantStationListener listener : listeners) listener.noftifyRegistered(station);
	}
	
	/**
	 * Stop tracking the station provided 
	 * @param station CustomerUI to stop tracking
	 * @throws NoSuchElementException when the station is not currently being tracked
	 * @throws NullPointerException when the station provided is null
	 */
	public void deregisterStation(CustomerUI station) throws NoSuchElementException, NullPointerException {
		if (station == null) throw new NullPointerException("CustomerUI cannot be null");
		boolean found = stations.remove(station);
		if (!found) throw new NoSuchElementException();
		for (AttendantStationListener listener : listeners) listener.noftifyDeregistered(station);
	}
	
	/**
	 * Register the provided listener
	 * @param listener AttendantStationListener to add
	 * @throws IllegalArgumentException when the listener is already registered
	 * @throws NullPointerException when the listener provided is null
	 */
	public void registerListener(AttendantStationListener listener) throws IllegalArgumentException, NullPointerException {
		if (listener == null) throw new NullPointerException("listener cannot be null");
		if (listeners.contains(listener)) throw new IllegalArgumentException("The listener provided is already registered");
		listeners.add(listener);
	}
	
	/**
	 * Deregister the provided listener
	 * @param listener AttendantStationListener to deregister
	 * @throws NoSuchElementException when the listener is not registered
	 * @throws NullPointerException when the listener provided is null
	 */
	public void deregisterListener(AttendantStationListener listener) throws NoSuchElementException, NullPointerException {
		if (listener == null) throw new NullPointerException("listener cannot be null");
		if (!listeners.remove(listener)) throw new NoSuchElementException("The listener provided is not registered");
	}
	
	public TouchScreen getTouchScreen() {
		return touchScreen;
	}
	
	/**
	 * Returns a shallow copy of the registered stations
	 * @return List<CustomerUI> list of CustomerUI's tracked by this AttendantStation
	 */
	public List<CustomerUI> getRegisteredStations() {
		return List.copyOf(stations);
	}
}
