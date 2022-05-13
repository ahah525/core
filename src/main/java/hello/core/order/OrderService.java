package hello.core.order;

public interface OrderService {
    // 주문 생성 메서드
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
