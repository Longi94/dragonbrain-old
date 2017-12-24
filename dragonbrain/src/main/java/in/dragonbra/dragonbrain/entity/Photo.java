package in.dragonbra.dragonbrain.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lngtr
 * @since 2017-12-24
 */
@Entity
@Table(name = "photo", uniqueConstraints =
@UniqueConstraint(name = "uk_photo_order", columnNames = "order_by"))
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "location")
    private String location;

    @Column(name = "device")
    private String device;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "order_by", nullable = false)
    private Integer orderBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }
}
