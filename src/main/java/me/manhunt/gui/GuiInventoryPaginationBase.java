package me.manhunt.gui;

public interface GuiInventoryPaginationBase {
    int getTotalPages();
    int getCurrentPage();
    void firstPage(boolean hasPermission);
    void lastPage(boolean hasPermission);
    void nextPage(boolean hasPermission);
    void previousPage(boolean hasPermission);
}
