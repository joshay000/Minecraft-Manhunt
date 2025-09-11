package me.manhunt.gui;

public interface GuiInventoryPaginationBase {
    int getTotalPages();
    int getCurrentPage();
    void firstPage();
    void lastPage();
    void nextPage();
    void previousPage();
}
