package it.unical.mat.embasp.base;

/**
 * This interface is used to communicate results of solver invocation to the user
 *
 * @see Output
 */
public interface Callback {
	void callback(Output o);
}
