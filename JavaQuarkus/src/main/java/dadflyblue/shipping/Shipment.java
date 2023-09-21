package dadflyblue.shipping;

import dadflyblue.common.OrderInfo;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

@Entity(name = "shipments")
public class Shipment extends PanacheEntity {

  public Long shippingAddressId;

  public String shippingAddress;

  public OffsetDateTime shippingDate;

  public Long orderId;

  public static Shipment fromOrder(OrderInfo order) {
    Shipment s = new Shipment();
    {
     s.orderId = order.id;
     s.shippingDate = order.shippingDate;
     s.shippingAddressId = order.shippingAddress.id;
     s.shippingAddress = order.shippingAddress.getFormattedShippingAddress();
    }
    return s;
  }

  @Transactional
  public static Uni<Shipment> saveAsync(Shipment shipment) {
    return Uni.createFrom().item(shipment)
            .onItem().transform(s -> {
              persist(s);
              flush();
              return s;
            });
  }

  @Transactional
  public static List<Shipment> getAll() {
    return Shipment.listAll();
  }

}
