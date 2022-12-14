package week_06.JDBC.CRUD_01;

import myCustom.Input;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Tutorial{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("\n<< 카페 메뉴 관리 프로그램 >>");
            System.out.println("1. 신규 메뉴 등록");
            System.out.println("2. 메뉴 목록 출력");
            System.out.println("3. 메뉴 정보 삭제 (상품코드로 삭제)");
            System.out.println("4. 메뉴 정보 수정 (상품코드로 변경)");
            System.out.println("0. 프로그램 종료");
            int menu = Input._integer_(0,4);
            String dbIdPw = "kh";

            if (menu == 1) {
                Jhsql sql = new Jhsql();

                System.out.print("메뉴 이름 입력.");
                String pname = Input._string_();
                System.out.print("메뉴 가격 입력.");
                int pprice = Input._integer_();
                System.out.print("아이스 가능(Y, N) 입력.");
                String iced = Input._string_("Y","N");

                int result = sql.update(dbIdPw, dbIdPw, String.format("insert into cafe_menu values(cafe_menu_seq.nextval, '%s', '%d', '%s')", pname, pprice, iced));

                if (result > 0) {
                    System.out.println("입력 성공");
                } else {
                    System.out.println("입력 실패");
                }
            }
            else if(menu == 2){
                Jhsql sql = new Jhsql();
                ArrayList<String> result = sql.query(dbIdPw, dbIdPw, String.format("select * from cafe_menu order by 1"));
                System.out.println("pid \t: pname \t: pprice \t: iced");
                for(String str:result) {
                    System.out.println(str);
                }
            }
            else if(menu == 3){
                Jhsql sql = new Jhsql();
                // 삭제 ID 입력
                System.out.print("삭제할 메뉴 ID 입력.");
                int pid = Input._integer_();

                // sql 입력
                int result = sql.update(dbIdPw, dbIdPw, String.format("delete from cafe_menu where pid = %d", pid));

                if (result > 0) {
                    System.out.println("삭제 성공!!");
                } else {
                    System.out.println("삭제 대상이 없습니다.");
                }
            }
            else if(menu == 4){
                Jhsql sql = new Jhsql();
                // 수정 ID 입력
                System.out.print("수정할 메뉴 ID 입력.");
                int pid = Input._integer_();

                // 수정할 컬럼 내용 출력
                ArrayList<String> result_sel = sql.query(dbIdPw, dbIdPw, String.format("select * from cafe_menu where pid = %d order by 1", pid));
                System.out.println("pid\t : pname\t : pprice\t : iced");
                System.out.println(result_sel.get(0));

                // 수정 컬럼 입력
                System.out.print("수정할 컬럼 : ");
                String colName = Input._string_();

                // 수정 내용 입력
                String content = "";
                if(colName.equals("iced")){
                    System.out.print("수정 내용 입력.");
                    content = Input._string_("Y", "N");
                }else {
                    System.out.print("수정 내용 입력.");
                    content = Input._string_();
                }

                // sql 입력
                int result = sql.update(dbIdPw, dbIdPw, String.format("Update cafe_menu set %s = '%s' where pid = %d", colName, content, pid));

                if (result > 0) {
                    System.out.println("수정 성공!!");
                } else {
                    System.out.println("수정 대상이 없습니다.");
                }
            }
            else {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            }
        }
    }
}

