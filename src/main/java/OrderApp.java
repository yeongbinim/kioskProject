import java.util.List;
import java.util.Scanner;

public class OrderApp {
    private final static String menuFormat = "%-20s | W %-4s | %s";

    public static void main(String[] args) {
        // menuItem 초기화
        List<String> menuItemList = List.of(
                String.format(menuFormat, "ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                String.format(menuFormat, "SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                String.format(menuFormat, "Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                String.format(menuFormat, "Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"),
                String.format(menuFormat, "Lemonade", 2.5, "상큼한 레모네이드"),
                String.format(menuFormat, "ShackMeister Ale", 5.9, "독점 제조 맥주"),
                String.format(menuFormat, "Fifty/Fifty", 2.5, "아이스티와 레모네이드의 조합"),
                String.format(menuFormat, "Root Beer", 2.3, "전통 루트 비어"),
                String.format(menuFormat, "Shack Attack", 5.6, "초콜릿 커스터드 위에 페퍼민트, 초콜릿 칩이 토핑된 디저트"),
                String.format(menuFormat, "Cheese Fries", 3.9, "치즈와 베이컨이 토핑된 감자튀김"),
                String.format(menuFormat, "Concrete Jungle", 6.4, "바나나, 피넛 버터와 함께 블렌드된 커스터드"),
                String.format(menuFormat, "Vanilla Shake", 4.2, "고전적인 바닐라 쉐이크")
        );
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 메뉴 출력을 위한 StringBuilder
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (String menuItem : menuItemList) {
                sb.append(String.format("%2d. %s\n", i++, menuItem));
            }
            sb.append(String.format("%2d. 종료\n", 0));

            // 메뉴 출력
            System.out.println(sb);
            System.out.print("메뉴를 입력하세요: ");

            // 사용자로부터 메뉴아이템 번호 입력
            int menuNumber = scanner.nextInt();
            if (menuNumber == 0) {
                break;
            }

            // 해당 메뉴아이템 반환
            System.out.println(menuItemList.get(menuNumber - 1) + "\n");
        }
        scanner.close();
    }
}