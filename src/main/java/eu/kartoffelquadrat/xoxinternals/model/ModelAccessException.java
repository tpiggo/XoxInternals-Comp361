package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Custom Exception that is fired whenever model modifications are requested that would lead to an inconsistent
 * state.
 *
 * @author Maximilian Schiedermeier
 */
public class ModelAccessException extends Exception {
    public ModelAccessException(String cause) {
        super(cause);
    }
}
