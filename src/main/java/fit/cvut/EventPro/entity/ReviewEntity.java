package fit.cvut.EventPro.entity;

import jakarta.persistence.*;

@Entity
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Double stars;

    @OneToOne
    @JoinColumn(name = "invitation_id")
    private InvitationEntity invitationEntity;

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InvitationEntity getInvitationEntity() {
        return invitationEntity;
    }

    public void setInvitationEntity(InvitationEntity invitationEntity) {
        this.invitationEntity = invitationEntity;
    }
}
