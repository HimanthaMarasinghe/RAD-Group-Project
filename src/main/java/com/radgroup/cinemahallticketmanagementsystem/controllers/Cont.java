package com.radgroup.cinemahallticketmanagementsystem.controllers;

public interface Cont {
    /**
     * This method is called each time the view file is loaded.
     * It retrieves any data that needs to be displayed in the interface and assigns it to the appropriate nodes.
     * @param data Any aditioanal data that is not fetched from the Database.
     */
    void setView(Object data);
}
