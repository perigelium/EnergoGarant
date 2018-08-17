package developer.alexangan.ru.bidsandpersonal.Interfaces;

import android.app.Fragment;

import developer.alexangan.ru.bidsandpersonal.Models.BidInfoItem;


public interface BidsCommunicator
{

    void onClose();

    void popFragmentsBackStack();

    void onLogoutCommand();

    void openLeadDetails(BidInfoItem bidInfoItem, boolean editable);

    void onClientsLeadsListItemSelected(BidInfoItem bidInfoItem);

    void onOpenClientsSearch(Fragment frag);
}
