package nl.les12vinylshopdto.les12vinylshopdto.dto;

public class PublisherResponseDto {

    private final Long id;
    private final String name;
    private final String address;
    private final String contactDetails;

    public PublisherResponseDto(Long id, String name, String address, String contactDetails) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactDetails = contactDetails;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContactDetails() { return contactDetails; }
}