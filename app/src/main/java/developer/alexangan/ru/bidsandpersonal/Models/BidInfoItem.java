package developer.alexangan.ru.bidsandpersonal.Models;

public class BidInfoItem
{
    int serialNumber;
    String YYMMDDDD;
    String HHMM;
    int status;

    public BidInfoItem(int serialNumber, String YYMMDDDD, String HHMM, int status)
    {
        this.serialNumber = serialNumber;
        this.YYMMDDDD = YYMMDDDD;
        this.HHMM = HHMM;
        this.status = status;
    }

    public String getYYMMDDDD()
    {
        return YYMMDDDD;
    }

    public String getHHMM()
    {
        return HHMM;
    }

    public int getStatus()
    {
        return status;
    }

    public int getSerialNumber()
    {
        return serialNumber;
    }
}
