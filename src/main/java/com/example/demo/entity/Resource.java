@Entity
public class ResourceRequest {

    @Id @GeneratedValue
    private Long id;

    private String resourceType;

    @ManyToOne
    private User requestedBy;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String purpose;
    private String status;

    @PrePersist
    void onCreate() {
        if (status == null) status = "PENDING";
    }
}
