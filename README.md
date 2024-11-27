## Kiosk Level 5

(🛠 refactor) Kiosk는 사용자 입출력에 집중하고, 처리 로직은 MemberService에, 포매팅은 KioskFormatter로 책임 분리

[[코드 보러 가기]](./src/main/java)

```
src/
 ├── OrderApp.java
 ├── controller/
 ├    ├── KioskFormatter.java
 ├    └── Kiosk.java
 └── service/
      ├── MenuService.java
      └── domain/
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
+ start()
}

class MenuService {
- menuList: Menu[]
+ getMenus() Menu[]
+ getMenuItems() MenuItems[]
+ getMenuItem() MenuItem
}

Kiosk --> MenuService

MenuService -->"*" Menu

Menu --> "*" MenuItem

```

### 기능 화면

기능은 level4와 동일합니다.

<img width="481" alt="스크린샷 2024-11-27 오후 1 51 49" src="https://github.com/user-attachments/assets/848d63f8-0974-44a2-a8c6-7b41e8beef95">
