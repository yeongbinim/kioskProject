## Kiosk Level 3

[[코드 보러 가기]](./src/main/java)

```
src/
 ├── OrderApp.java
 ├── Kiosk.java
 └── domain/
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

class Kiosk {
- menuItem: MenuItem[]
+ start()
}

Kiosk --> MenuItem


```

### 기능 화면

기능은 level1, level2와 동일합니다. Kiosk 클래스가 분리되었습니다.

<img width="651" alt="스크린샷 2024-11-27 오전 10 30 02" src="https://github.com/user-attachments/assets/9b7accf3-3ddd-49e9-8700-5d30c41c4f8b" />
