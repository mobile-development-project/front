package be.svv.repository;

import be.svv.model.request.ModelRequest;
import be.svv.viewmodel.ViewModelCallback;

public interface RepositoryInterface
{

    /**
     * Returns all elements
     *
     * @param callback
     */
    void getAll (final ViewModelCallback callback);

    /**
     * Add a new element
     *
     * @param request
     * @param callback
     * @param <T>      needs to herit from <b>ModelRequest</b>
     */
    <T extends ModelRequest> void add (T request, final ViewModelCallback callback);

    /**
     * Updates an element
     *
     * @param request
     * @param id
     * @param callback
     * @param <T>
     */
    <T extends ModelRequest> void update (T request, int id, final ViewModelCallback callback);


    /**
     * Delete an element
     *
     * @param callback
     */
    void delete (int id, final ViewModelCallback callback);

}
