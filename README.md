## Kiosk Level 4

(🎁 feature) **사용자는 메뉴아이템 선택 이전에 메뉴를 선택할 수 있다.**

[[코드 보러 가기]](./src/main/java)

```
src/
 ├── OrderApp.java
 ├── Kiosk.java
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
- menuList: Menu[]
+ start()
}

Kiosk -->"*" Menu

Menu -->"*" MenuItem

```

### 기능 화면

<img width="481" alt="스크린샷 2024-11-27 오후 1 51 49" src="https://github.com/user-attachments/assets/848d63f8-0974-44a2-a8c6-7b41e8beef95">
