package com.radgroup.cinemahallticketmanagementsystem.controllers;

public interface Cont {
    /**
     * This method is called each time the view file is loaded.
     * It retrieves any data that needs to be displayed in the interface and assigns it to the appropriate nodes.
     * @param data Any additional data that is not fetched from the Database. null, if there are no additional data
     */
    void setView(Object data);
}
