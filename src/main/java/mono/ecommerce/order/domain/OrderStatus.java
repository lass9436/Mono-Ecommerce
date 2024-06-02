package mono.ecommerce.order.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    ORDERED("주문 완료"),
    SHIPPING("배송 중"),
    DELIVERED("배송 완료"),
    RETURNED("반품 완료");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
