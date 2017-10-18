package app.feed.com.ui.common;

/**
 * Created by debu on 15/10/17.
 */

public interface MVPview {
    /**
     * Hide a loading view.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);
}
