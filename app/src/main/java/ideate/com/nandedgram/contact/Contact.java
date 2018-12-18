package ideate.com.nandedgram.contact;

public class Contact {

    private String designation;

    public Contact(String name,String mobileNumber,String designation) {
        this.designation=designation;
        this.name=name;
        this.phoneNumber=mobileNumber;
    }
    public Contact() {
    }
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String name;
    private String phoneNumber;
    private String imageUrl;

}
