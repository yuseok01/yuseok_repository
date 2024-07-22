package com.a508.wms.domain;

import com.a508.wms.domain.util.BaseTimeEntity;
import com.a508.wms.util.constant.ExportTypeEnum;
import com.a508.wms.util.constant.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@SQLRestriction("status_enum = 'Active'")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "floor")
public class Floor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private int floorLevel;

    @Column(nullable = false)
    private ExportTypeEnum exportTypeEnum = ExportTypeEnum.IMPORT;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum = StatusEnum.ACTIVE;

    @OneToMany(mappedBy = "floor")
    private List<ProductLocation> productLocations;

    public void updateStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    //연관관계 편의 메서드
    public void setLocation(Location location) {
        this.location = location;
        location.getFloors().add(this);
    }
}
