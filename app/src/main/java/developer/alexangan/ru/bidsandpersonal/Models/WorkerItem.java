package developer.alexangan.ru.bidsandpersonal.Models;


import io.realm.RealmObject;


public class WorkerItem extends RealmObject
{
    private int worker_id;

    private String name;
    private String middlename;
    private String surname;
    private String position;
    private boolean subcontractor;

    private String status;
    private String company;
    private String phone;
    private String location;
    private String address;
    private String block;
    private String appartment;
    private String zipcode;

    private double latitude;
    private double longitude;

    public WorkerItem()
    {
    }

    public WorkerItem(int worker_id, String name, String middlename, String surname, String position, boolean subcontractor)
    {
        this.worker_id = worker_id;
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.position = position;
        this.subcontractor = subcontractor;
    }

    public int getWorker_id()
    {
        return worker_id;
    }

    public String getName()
    {
        return name;
    }

    public String getMiddlename()
    {
        return middlename;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getPosition()
    {
        return position;
    }

    public boolean isSubcontractor()
    {
        return subcontractor;
    }

    public String getStatus()
    {
        return status;
    }

    public String getCompany()
    {
        return company;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getLocation()
    {
        return location;
    }

    public String getAddress()
    {
        return address;
    }

    public String getBlock()
    {
        return block;
    }

    public String getAppartment()
    {
        return appartment;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }
}


