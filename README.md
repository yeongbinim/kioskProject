## Kiosk Challenge 1

(🎁 feature) 사용자는 선택한 메뉴를 장바구니에 추가하고, 주문할 수 있다.

[[코드 보러 가기]](./src/main/java)
```
src/
├── OrderApp.java
├── controller/
├    ├── KioskFormatter.java
├    └── Kiosk.java
└── service/
     ├── MenuService.java
     ├── OrderService.java
     └── domain/
          ├── Cart.java
          ├── Order.java
          ├── Menu.java
          └── MenuItem.java
```

### 클래스 다이어그램

```mermaid
classDiagram

class MenuItem {
- name
- price
- description
}

class Menu {
- name
- menuItemList: MenuItem[]
+ getMenuItems() MenuItem[]
}

class Kiosk {
- menuService: MenuService
- orderService: OrderService
+ start()
}

class MenuService {
- menuList: Menu[]
+ getMenus() Menu[]
+ getMenuItems() MenuItems[]
+ getMenuItem() MenuItem
}

class OrderService {
- cart: Cart
+ createOrder()
+ clearCart()
+ addItemToCart()
+ calculateTotal()
}

class Cart {
- cartItems: CartItem[]
+ createOrder() Order
}

class CartItem {
- menuItem: MenuItem
- quantity
}

class Order {
- totalPrice
}

Kiosk --> MenuService
Kiosk --> OrderService

MenuService -->"*" Menu
Menu --> "*" MenuItem
OrderService --> Cart
Cart -->"*" CartItem
Cart ..> Order: create

```


### 기능 화면

<div align="center">
<img width="550" alt="스크린샷 2024-11-27 오후 1 51 49" src="https://github.com/user-attachments/assets/e494928d-7e52-465b-b029-14a39b5663d0" />
</div>

