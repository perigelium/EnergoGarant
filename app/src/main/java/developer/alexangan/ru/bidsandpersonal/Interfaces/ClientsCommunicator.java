package developer.alexangan.ru.bidsandpersonal.Interfaces;


import android.app.Fragment;

import developer.alexangan.ru.bidsandpersonal.Models.ClientInfoItem;

public interface ClientsCommunicator
{
    void onLogoutCommand();

    void onClose();

    void onOpenLegendAndFilter(boolean gpsIsActive);

    void onMapPinSelected(ClientInfoItem clientInfoItem);

    void onOpenClientsSearch(Fragment frag);

    void onClientSearchResultsListItemSelected(ClientInfoItem clientInfoItem);

    void popFragmentsBackStack();

    void openWorkersList();
}