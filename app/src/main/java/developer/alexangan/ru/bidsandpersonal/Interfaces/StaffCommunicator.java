package developer.alexangan.ru.bidsandpersonal.Interfaces;


import android.app.Fragment;

import developer.alexangan.ru.bidsandpersonal.Models.WorkerItem;

public interface StaffCommunicator
{
    void onStaffListItemSelected(WorkerItem workerItem);

    void onLogoutCommand();

    void onClose();

    void popFragmentsBackStack();

    void openWorkersMap(Fragment fragToReplace);

    void openWorkersList(Fragment fragToReplace);
}